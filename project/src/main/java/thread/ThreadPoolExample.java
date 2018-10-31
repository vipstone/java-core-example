package thread;

import com.sun.jmx.snmp.tasks.ThreadService;

import javax.security.auth.callback.Callback;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池示例类
 */
public class ThreadPoolExample {

    public static void main(String[] args) {

        // 使用Executors方式创建
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        // 原始创建方式
        ThreadPoolExecutor tp = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//        tp.allowCoreThreadTimeOut(true); // 运行关闭核心线程池

        // 基础使用
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + new Date().getTime());
            }
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + new Date().getTime());
            }
        });

        // 带回调的线程池
        Future<Long> result = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return new Date().getTime();
            }
        });
        try {
            System.out.println("运行结果：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 延迟线程池
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("time:" + new Date().getTime());
            }
        }, 10, TimeUnit.SECONDS);


    }

}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return sum;
    }
}
