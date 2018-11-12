package example;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class SocketExample {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        int port = 4343; //端口号
//        // Socket 服务器端（简单的发送信息）
//        Thread sThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ServerSocket serverSocket = new ServerSocket(port);
//                    while (true) {
//                        // 等待连接
//                        Socket socket = serverSocket.accept();
//                        Thread sHandlerThread = new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
//                                    printWriter.println("hello world！");
//                                    printWriter.flush();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                        sHandlerThread.start();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        sThread.start();
//
//        // Socket 客户端（接收信息并打印）
//        try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
//            bufferedReader.lines().forEach(s -> System.out.println("客户端：" + s));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        // NIO 多路复用
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4,
//                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try (Selector selector = Selector.open();
//                     ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
//                    serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
//                    serverSocketChannel.configureBlocking(false);
//                    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//                    while (true) {
//                        selector.select(); // 阻塞等待就绪的Channel
//                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
//                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
//                        while (iterator.hasNext()) {
//                            SelectionKey key = iterator.next();
//                            try (SocketChannel channel = ((ServerSocketChannel) key.channel()).accept()) {
//                                channel.write(Charset.defaultCharset().encode("你好，世界"));
//                            }
//                            iterator.remove();
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        // Socket 客户端（接收信息并打印）
//        try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
//            bufferedReader.lines().forEach(s -> System.out.println("NIO 客户端：" + s));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // AIO线程复用版
        Thread sThread = new Thread(new Runnable() {
            @Override
            public void run() {
                AsynchronousChannelGroup group = null;
                try {
                    group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
                    AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
                    server.accept(null, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                        @Override
                        public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                            server.accept(null, this); // 接收下一个请求
                            try {
                                Future<Integer> f = result.write(Charset.defaultCharset().encode("你好，世界"));
                                f.get();
                                System.out.println("服务端发送时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                result.close();
                            } catch (InterruptedException | ExecutionException | IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                        }
                    });
                    group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sThread.start();

        // Socket 客户端
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        Future<Void> future = client.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));
        future.get();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        client.read(buffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                System.out.println("客户端打印：" + new String(buffer.array()));
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(10 * 1000);
    }
}



