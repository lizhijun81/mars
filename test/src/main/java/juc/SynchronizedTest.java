package juc;

/**
 * synchronized static  对class对象进行加锁
 * synchronized init 对实例对象进行加锁
 * 这两个对不同对象进行加锁；二者之间不会互斥；所以不会相互阻塞；能够并发执行
 */
public class SynchronizedTest {

    /**
     * 对class对象进行加锁
     */
    public synchronized static void testSyncStatic() throws InterruptedException {
        System.out.println("testSyncStatic before ... " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        System.out.println("testSyncStatic after ...");
    }

    /**
     * 对实例对象进行加锁；
     */
    public synchronized void testSyncInit() {
        System.out.println("testSyncInit ..." + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread("a") {
            @Override
            public void run() {
                try {
                    synchronizedTest.testSyncStatic();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("b") {
            @Override
            public void run() {
                synchronizedTest.testSyncInit();
            }
        }.start();
    }
}
