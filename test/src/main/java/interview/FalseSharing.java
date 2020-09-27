package interview;

import sun.misc.Contended;

public final class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 5L * 10000L * 10000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {

        runTest();
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        final long start = System.currentTimeMillis();
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
        System.out.println(Thread.currentThread().getName() + ":duration = " + (System.currentTimeMillis() - start));

    }

    @Contended static final class VolatileLong { //extends AbstractPaddingObject
        public volatile long value = 0L;
//        public long p1, p2, p3, p4, p5, p6; // 填充，可以注释后对比测试
    }

    abstract static class AbstractPaddingObject {
        protected long p1, p2, p3, p4, p5, p6;// 填充
    }

}
