package thread;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池示例类
 */
public class ThreadPoolExample {

    public static void main(String[] args) {


//        ExecutorService es = Executors.newSingleThreadExecutor();
//        ExecutorService es = Executors.newCachedThreadPool();
//        ExecutorService es = Executors.newFixedThreadPool(2);
//        ExecutorService es = Executors.newScheduledThreadPool(2);
        ExecutorService es = Executors.newWorkStealingPool();


        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + new Date().getTime());
            }
        });
        es.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + new Date().getTime());
            }
        });


        System.out.println(Thread.currentThread().getName() + ":" + new Date().getTime());

//        es.shutdown();

    }

}
