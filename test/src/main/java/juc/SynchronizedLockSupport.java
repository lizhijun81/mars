package juc;

import java.util.concurrent.locks.LockSupport;

/**
 * 验证 LockSupport.park() 不会释放 Synchronized锁
 */
public class SynchronizedLockSupport {

    private Object obj1 = new Object();

    private Object obj2 = new Object();

    public void test1() {
        synchronized (obj1) {
            int i = 1000000;
            while(i-- > 0) {

            }
            LockSupport.park();
            System.out.println("juc.SynchronizedLockSupport.test1 add lock obj1");
            synchronized (obj2) {
                System.out.println("juc.SynchronizedLockSupport.test1 add lock obj2");
            }
        }
    }

    public void test2() {
        synchronized (obj2) {
            int i = 100000;
            while(i-- > 0) {

            }
            System.out.println("juc.SynchronizedLockSupport.test2 add lock obj2");
            synchronized (obj1) {
                System.out.println("juc.SynchronizedLockSupport.test2 add lock obj1");
            }
        }
    }


    public static void main(String[] args) {
        SynchronizedLockSupport synchronizedLockSupport = new SynchronizedLockSupport();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronizedLockSupport.test1();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronizedLockSupport.test2();
            }
        };

        t1.start();
        t2.start();

//        LockSupport.unpark(t1);
    }

}
