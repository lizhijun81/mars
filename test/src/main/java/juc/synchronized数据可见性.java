package juc;

import java.util.concurrent.CountDownLatch;

public class synchronized数据可见性 {

    public int count = 0;

    public synchronized void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {

        int countThreadNum = 100;
        CountDownLatch downLatch = new CountDownLatch(countThreadNum);

        synchronized数据可见性 test = new synchronized数据可见性();
        for (int i = 0; i < countThreadNum; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        test.add();
                    }
                    downLatch.countDown();
                }
            }.start();
        }

        downLatch.await();

        System.out.println(test.count);
    }

}
