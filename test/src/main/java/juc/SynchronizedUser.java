package juc;

public class SynchronizedUser {

    private int id;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }

    /**
     * 对class对象进行加锁
     */
    public synchronized static void testSyncStatic1() throws InterruptedException {
        System.out.println("testSyncStatic1 before ... " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        System.out.println("testSyncStatic1 after ...");
    }

    /**
     * 对class对象进行加锁
     */
    public synchronized static void testSyncStatic2() throws InterruptedException {
        System.out.println("testSyncStatic2 execute ... " + Thread.currentThread().getName());
    }
}
