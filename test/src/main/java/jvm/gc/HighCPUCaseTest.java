package jvm.gc;

/**
 * CPU 飙高 问题查询
 */
public class HighCPUCaseTest {

    public void test() {
        int count = 0;
        while(true) {
            count++;
            if (count % 10000 == 0) {
                System.out.println(count);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HighCPUCaseTest highCPUCaseTest = new HighCPUCaseTest();

        new Thread("HighCPUCaseTest thread") {
            @Override
            public void run() {
                highCPUCaseTest.test();
            }
        }.start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}
