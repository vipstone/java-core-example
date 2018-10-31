package thread;

/**
 * 死锁实现
 */
public class DeadLockDemo {
    static Object object1 = new Object();
    static Object object2 = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (object1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object2) {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (object2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object1) {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        };
        thread.start();
        thread2.start();
    }
}
