package juc;

/**
 * 线程中断测试
 *  1.Object.wait()、Thread.sleep()都会相应中断，抛出InterruptedException中断异常
 *  2.Synchronized 不会相应中断
 *  3.LockSupport.park() 不响应中断，但只要线程的中断状态为true，线程将会立即执行；
 *      i.线程是同步中则立即执行
 *      ii.如果线程已经为中断状态，则执行LockSupport.park()时，不会进入同步状态，而是立即执行
 *  4.没有启动的线程和已经执行结束的线程的 isInterrupted()都是false
 */
public class ThreadInterruptedTest {

    private Object monitorObj = new Object();

    public synchronized void test() throws InterruptedException {
        synchronized (monitorObj) {
            System.out.println("juc.ThreadInterruptedTest.test ...");
            // monitorObj.wait();
            long currentTimeMillis = System.currentTimeMillis();
            Thread.sleep(5000);
            System.out.println(System.currentTimeMillis() - currentTimeMillis);
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ThreadInterruptedTest threadInterruptedTest = new ThreadInterruptedTest();
//
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    threadInterruptedTest.test();// thread 加锁 后 monitorObj.wait()
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        thread.start();
//
//        Thread.sleep(3000L);
//
//        thread.interrupt();// 中断 thread


        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 300; i++) {
                    System.out.println(i);
                }
                System.out.println("当前线程的中断状态" + Thread.currentThread().isInterrupted());
            }
        };
//
//        thread.start();
        System.out.println("thread线程的中断状态1" + thread.isInterrupted());
////        Thread.sleep(3L);
//        thread.interrupt();
//        System.out.println("thread线程的中断状态2" + thread.isInterrupted());
//        Thread.sleep(3000L);
//        System.out.println("thread线程的中断状态3" + thread.isInterrupted());

    }

}
