package example;

import java.util.Date;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws Exception {

//        // ----------- BLOCKED 阻塞状态 --------------
//        MyCounter myCounter = new MyCounter();
//        // 线程1调用同步线程，模拟阻塞
//        new Thread(() -> myCounter.increase()).start();
//        // 线程2继续调用同步阻塞方法
//        Thread example = new Thread(() -> myCounter.increase());
//        example.start();
//        // 让主线程等10毫秒
//        Thread.currentThread().sleep(10);
//        // 打印线程2，为阻塞状态：BLOCKED
//        System.out.println(example.getState());


//        // ----------- TERMINATED 状态 --------------
//        Thread example = new Thread() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        };
//        example.start();
//        // 主线程挂起200毫秒，等thread执行完成
//        Thread.sleep(200);
//        // TERMINATED
//        System.out.println(example.getState());

//        // ----------- WAITING 或 TIMED_WAITING 状态 --------------
//        Thread example = new Thread(new MyThread());
//        example.start();
//        // 主线程挂起200毫秒，等thread执行完成
//        Thread.sleep(200);
//        // 输出WAITING，线程thread一直处于被挂起状态
//        System.out.println(example.getState());
//        synchronized (MyThread.class) {
//            MyThread.class.notify();
//        }


//// ----------- TERMINATED 状态 --------------
//        Thread example = new Thread(() -> System.out.println(Thread.currentThread().getName()));
//        example.start();
//// 让主线程等10毫秒
//        Thread.currentThread().sleep(10);
//        System.out.println(example.getState());

//        Thread example = new Thread(() -> System.out.println(Thread.currentThread().getName()));
//        example.start();

//        //----------- Callable Future 创建线程 --------------
//        Callable<String> callable = new MyThread();
//        FutureTask<String> ft = new FutureTask<>(callable);
//        new Thread(ft, "threadName").start();
//        System.out.println(ft.get());

//        // ----------- sleep、wait持锁测试 --------------
//        SynchronizedTest synchronizedTest = new SynchronizedTest();
//        synchronizedTest.start();
//        synchronizedTest.secord();
//        //主线程稍等10毫秒
//        Thread.sleep(10);
//        System.out.println(synchronizedTest.number);
//
//        System.out.println(Runtime.getRuntime().availableProcessors());

//        ExecutorService es = Executors.newSingleThreadExecutor();
//        ExecutorService es = Executors.newCachedThreadPool();
//        ExecutorService es = Executors.newFixedThreadPool(6);
//        ExecutorService es = Executors.newWorkStealingPool();
        ExecutorService es = Executors.newScheduledThreadPool(5);
        es.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(Thread.currentThread().getName()+ " | 时间：" + new Date().getTime());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//        es.shutdown();

   


    }

}
