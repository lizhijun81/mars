package juc;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 变量只能保证可见性，只能保证读写原子性；i++ 复合操作不能保证原子性
 *    首先获取volatile变量的值
 *    将该变量的值加1
 *    将该volatile变量的值写会到对应的主存地址
 */
public class TestVolatile {

    private volatile int value = 1;

    public static void main(String[] args) throws InterruptedException {
        TestVolatile testVolatile = new TestVolatile();

//        testVolatile.test();

        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            TestVolatile t_1 = new TestVolatile();
            new Thread(() -> t_1.write()).start();

            new Thread(() -> {
                boolean read = t_1.read();
                if (read) {
                    integer.incrementAndGet();
                }
            }).start();
        }

        System.out.println(integer.get());

        Thread.sleep(Integer.MAX_VALUE);
    }

    private void test() {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                int k = 10000;
                while (k > 0) {
                    value = value + 1;
                    k--;
                }
            });
            t.start();
        }
        System.out.println(value);
    }

    private int a = 0;
    private volatile boolean isTrue = false;

    public void write() {
        a = 1;
        isTrue = true;
    }

    public boolean read() {
        if (isTrue) {
            a++;
        } else {
            return true;
        }
        return false;
    }
}
