package thread;

//public class MyThread implements Runnable {
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName());
//    }
//}


public class MyThread extends Thread{
    @Override
    public void run() {
        synchronized (MyThread.class){
            try {
                MyThread.class.wait(1000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//import java.util.concurrent.Callable;
//
//public class MyThread implements Callable<String> {
//
//    @Override
//    public String call() throws Exception {
//        System.out.println(Thread.currentThread().getName());
//        return Thread.currentThread().getName();
//    }
//}