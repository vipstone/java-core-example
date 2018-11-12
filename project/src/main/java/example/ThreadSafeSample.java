package example;

// 非安全线程模拟
public class ThreadSafeSample {
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
        ThreadSafeSample threadSafeSample = new ThreadSafeSample();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                threadSafeSample.add();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                threadSafeSample.add();
            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
