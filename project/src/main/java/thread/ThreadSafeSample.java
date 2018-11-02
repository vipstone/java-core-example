package thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 非安全线程模拟
public class ThreadSafeSample {
    public int _number;
    public void add() {
        for (int i = 0; i < 100000; i++) {
            int former = _number++;
            int latter = _number;
            if (former != latter-1){
                System.out.printf("数据不相等 => former=" +  former + " latter=" + latter);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample lockExample = new ThreadSafeSample();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lockExample.add();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lockExample.add();
            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
