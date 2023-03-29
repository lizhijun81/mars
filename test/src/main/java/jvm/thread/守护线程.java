package jvm.thread;

/**
 * 主线程执行结束后，守护线程立即结束；不管守护线程是否执行结束;
 *      用户线程在主线程执行结束后，依然还在运行；thread.setDaemon(false);
 *
 *
 */
public class 守护线程 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("I am thread is Daemon = " + Thread.currentThread().isDaemon());
                }
            }
        });

//        thread.setDaemon(true);
        thread.start();

        Thread.sleep(5000L);
        System.out.println("main thread finish");
    }

}
