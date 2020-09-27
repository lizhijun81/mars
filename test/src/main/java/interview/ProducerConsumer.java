package interview;


import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumer {

    Random random = new Random();

    private static final LinkedList<Integer> queue = new LinkedList<>();

    private static final Object monitor = new Object();

    Runnable producer = () -> {
        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (monitor) {
                while (queue.size() > 10) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "生产者睡眠");
                        monitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }

                int n = random.nextInt();
                System.out.println(Thread.currentThread().getName() + "生产  " + n);

                queue.add(n);
                monitor.notifyAll();
            }
        }
    };

    Runnable consumer = () -> {
        while (true) {
            synchronized (monitor) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (queue.size() == 0) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "消费者睡眠");
                        monitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }

                Integer n = queue.removeFirst();
                System.out.println(Thread.currentThread().getName() + "消费  " + n);
                monitor.notifyAll();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread p1 = new Thread(producerConsumer.producer);

        Thread p2 = new Thread(producerConsumer.producer);

        Thread c1 = new Thread(producerConsumer.consumer);

        Thread c2 = new Thread(producerConsumer.consumer);

        c1.start();
        Thread.sleep(10000);

        p1.start();
        p2.start();



        Thread.sleep(Integer.MAX_VALUE);
    }

}
