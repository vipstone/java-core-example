package thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

// 线程同步与锁示例
public class LockExample {
    public int number;
    public void add() {
        for (int i = 0; i < 100000; i++) {
            int former = number++;
            int latter = number;
            if (former != latter - 1) {
                System.out.printf("数据不相等 => former=" + former + " latter=" + latter);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        LockExample lockExample = new LockExample();
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
