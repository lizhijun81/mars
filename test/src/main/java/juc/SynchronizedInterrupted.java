package juc;

/**
 * Synchronized 不会相应中断
 */
public class SynchronizedInterrupted {

    private Object obj1 = new Object();

    private Object obj2 = new Object();

    public void test1() {
        synchronized (obj1) {
            int i = 1000000;
            while(i-- > 0) {

            }
            System.out.println("juc.SynchronizedInterrupted.test1 add lock obj1");
            synchronized (obj2) {
                System.out.println("juc.SynchronizedInterrupted.test1 add lock obj2");
            }
        }
    }

    public void test2() {
        synchronized (obj2) {
            int i = 100000;
            while(i-- > 0) {

            }
            System.out.println("juc.SynchronizedInterrupted.test2 add lock obj2");
            synchronized (obj1) {
                System.out.println("juc.SynchronizedInterrupted.test2 add lock obj1");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedInterrupted synchronizedInterrupted = new SynchronizedInterrupted();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronizedInterrupted.test1();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronizedInterrupted.test2();
            }
        };

        t1.start();
        t2.start();

        Thread.sleep(3000L);

        t1.interrupt();
        t2.interrupt();
        System.out.println("t1 interrupt " + t1.isInterrupted());
        System.out.println("t2 interrupt " + t2.isInterrupted());

        System.out.println("main interrupted " + Thread.interrupted());
        System.out.println("main interrupted " + Thread.interrupted());
        System.out.println("main interrupted " + Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("main interrupted " + Thread.interrupted());
        System.out.println("main interrupted " + Thread.interrupted());
        System.out.println("main interrupted " + Thread.interrupted());
    }

}
