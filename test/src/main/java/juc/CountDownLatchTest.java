package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch 的内部实现为 AQS，在
 */
public class CountDownLatchTest {
    private static final int THREAD_NUM = 20;// 线程数量

    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) throws InterruptedException {
        new CountDownLatchTest().start();
    }

    public void start() {

        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new CountDownThread(), "CountDownThread：" + i).start();
            countDownLatch.countDown();
        }
    }

    class CountDownThread implements Runnable {
        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
