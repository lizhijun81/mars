package jvm.thread;

import java.util.concurrent.*;

public class ThreadLocalTest {

//    private static ThreadLocal<ThreadLocalContent> THREAD_LOCAL = new ThreadLocal<>();

    private ThreadLocal<ThreadLocalContent> THREAD_LOCAL = new ThreadLocal<>();

    public void set(ThreadLocalContent content) {
        THREAD_LOCAL.set(content);
    }

    public ThreadLocalContent get() {
        //static int a = 1;
        return THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<ThreadLocalContent> THREAD_LOCAL = new ThreadLocal<>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, Integer.MAX_VALUE, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocalTest.set(new ThreadLocalContent(new byte[10 * 1024 * 1024]));
//                System.out.println("do.....");
//                System.gc();// 回收 ThreadLocal
//                System.out.println(threadLocalTest.get());;
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                    System.out.println("end...");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();

        pool.execute(new Runnable() {
            @Override
            public void run() {
//                ThreadLocal<ThreadLocalContent> THREAD_LOCAL = new ThreadLocal<>();
                THREAD_LOCAL.set(new ThreadLocalContent(new byte[10 * 1024 * 1024]));
                System.out.println("do.....");
//                System.gc();// 回收 ThreadLocal
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        TimeUnit.SECONDS.sleep(1);// 先执行thread

        System.gc();// 回收 ThreadLocal

        // 等待执行gc
        TimeUnit.SECONDS.sleep(60);

        System.out.println(pool);
    }

}
