package interview;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerII {
    Random random = new Random();

    private static final LinkedList<Integer> queue = new LinkedList<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    Runnable producer = () -> {
        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                lock.lock();

                while (queue.size() > 10) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "生产者睡眠");
                        notFull.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }

                int n = random.nextInt();
                System.out.println(Thread.currentThread().getName() + "生产  " + n);

                queue.add(n);
                notEmpty.signalAll();
            } catch (Exception e) {
                e.getStackTrace();
            } finally {
                lock.unlock();
            }
        }
    };


    Runnable consumer = () -> {
        while (true) {
            try {
                lock.lock();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (queue.size() == 0) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "消费者睡眠");
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }

                Integer n = queue.removeFirst();
                System.out.println(Thread.currentThread().getName() + "消费  " + n);
                notFull.signalAll();
            } catch (Exception e) {
                e.getStackTrace();
            } finally {
                lock.unlock();
            }

        }
    };

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerII producerConsumerII = new ProducerConsumerII();
        Thread p1 = new Thread(producerConsumerII.producer);

        Thread p2 = new Thread(producerConsumerII.producer);

        Thread c1 = new Thread(producerConsumerII.consumer);

        Thread c2 = new Thread(producerConsumerII.consumer);

        c1.start();
        Thread.sleep(10000);

        p1.start();
        p2.start();

        Thread.sleep(Integer.MAX_VALUE);
    }

}
