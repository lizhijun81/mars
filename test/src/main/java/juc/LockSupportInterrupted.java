package juc;

import java.util.concurrent.locks.LockSupport;

/**
 * 测试 LockSupport.park() 是否响应中断
 *   LockSupport.park() 不响应中断，但只要线程的中断状态为true，线程将会立即执行；
 *      1.线程是同步中则立即执行
 *      2.如果线程已经为中断状态，则执行LockSupport.park()时，不会进入同步状态，而是立即执行
 */
public class LockSupportInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int count = 100000;
                while(--count > 0) {

                }
                System.out.println("LockSupport.park() 前 ...");
                LockSupport.park();
                System.out.println("LockSupport.park() 后 ...");
            }
        };

        t1.start();

        System.out.println("t1线程的中断前状态" + t1.isInterrupted());
        t1.interrupt();// 中断
        System.out.println("t1线程的中断后状态" + t1.isInterrupted());

        Thread.sleep(3000L);
//        System.out.println("LockSupport.unpark()前...");
//        // LockSupport.unpark(t1); 唤醒指定的线程
//        System.out.println("LockSupport.unpark()后...");

//        System.out.println("t1线程的中断前状态" + t1.isInterrupted());
//        t1.interrupt();// 中断
//        System.out.println("t1线程的中断后状态" + t1.isInterrupted());

    }

}
