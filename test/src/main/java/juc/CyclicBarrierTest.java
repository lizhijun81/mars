package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 */
public class CyclicBarrierTest {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        cyclicBarrier.await();
    }
}
