package ThreadPool;


public class TestInterruptedException {

    /**
     * Interrupted 是线程用来 优雅结束线程的方式；相比于 stop 这种直接暴力结束线程的方式更
     *
     * 1、当 线程的 Interrupted 标志位为 true 时：线程不能进入 sleep 等阻塞方法；
     * 2、当 线程已经进入到 sleep 等 阻塞方法时，线程的 Interrupted 标志变为 true 时，会 跑出 InterruptedException；
     *      1、sleep、JUC lock、object.await、Thread.join 这些 都是可中断的方法
     *      2、synchronized 不能被中断
     *
     * 有时候阻塞的方法抛出InterruptedException异常并不合适，例如在Runnable中调用了可中断的方法，
     * 因为你的程序是实现了Runnable接口，然后在重写Runnable接口的run方法的时候，那么子类抛出的异常要小于等于父类的异常。
     * 而在Runnable中run方法是没有抛异常的。所以此时是不能抛出InterruptedException异常。
     * 如果此时你只是记录日志的话，那么就是一个不负责任的做法，因为在捕获InterruptedException异常的时候自动的将是否请求中断标志置为了false。
     * 至少在捕获了InterruptedException异常之后，如果你什么也不想做，那么就将标志重新置为true，以便栈中更高层的代码能知道中断，并且对中断作出响应。
     */
    public static void main(String[] args) throws InterruptedException {

        int index = 0;
        if (!true & ++index > 0) {

        }
        System.out.println(index);

//        Thread t = new Thread(() -> {
//            int i = 0;
//            long time = System.currentTimeMillis();
//            try {
//                while (true) {
//                    Thread.currentThread().interrupt();
//                    System.out.println(i++);
//                    System.out.println("=======");
//                    Thread.sleep(5000);
//                    System.out.println("*******");
//                    System.out.println("time" + (System.currentTimeMillis() - time));
//                }
//            } catch (InterruptedException e) {
//                System.out.println("InterruptedException time: " + (System.currentTimeMillis() - time));
//                System.out.println("InterruptedException: " + i);
//                System.out.println("InterruptedException status: " + Thread.currentThread().isInterrupted());
//
//                Thread.currentThread().interrupt();
//            }
//        });
//
//        t.start();
//        Thread.sleep(1000);
//        t.interrupt();
//        Thread.sleep(Integer.MAX_VALUE);

//        Object monitor = new Object();
//
//        Thread t1 = new Thread(() -> {
//            synchronized (monitor) {
//                while (true) {
//                    System.out.println("doing......" + Thread.currentThread().isInterrupted());
//                }
//            }
//        });
//
//        t1.start();
//        Thread.sleep(1000);
//        t1.interrupt();
//        System.out.println("t1 status " + t1.isInterrupted());
//        Thread.sleep(3000);
    }




}
