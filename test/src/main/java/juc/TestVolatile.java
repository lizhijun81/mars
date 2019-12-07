package juc;



/**
 * volatile 变量只能保证可见性，不能保证原子性
 * 首先获取volatile变量的值
 * 将该变量的值加1
 * 将该volatile变量的值写会到对应的主存地址
 */
public class TestVolatile {

    private volatile int value = 1;

    public static void main(String[] args) throws InterruptedException {
        TestVolatile testVolatile = new TestVolatile();

        testVolatile.test();

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
}
