package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
//            @Override
//            protected String initialValue() {
//                return "thread-" + counter.getAndIncrement();
//            }
        };


        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set(Thread.currentThread().getName());

                    //fghjkl

                    threadLocal.get();
                }
            });
            thread.setName("thread-" + counter.getAndIncrement());
            thread.start();
            threads[i] = thread;
        }


        Thread.sleep(Integer.MAX_VALUE);
    }
}
