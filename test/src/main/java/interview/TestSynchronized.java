package interview;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class TestSynchronized {
    private final Object monitor = new Object();

    private final Object monitor_1 = new Object();

    public void test() throws InterruptedException {
        synchronized (monitor) {
            synchronized (monitor) {
                System.out.println(Thread.currentThread().getName() + "获取到锁。。。");
                monitor.wait();
            }
        }
    }

    public void test_1() throws InterruptedException {
        synchronized (monitor) {
            synchronized (monitor_1) {
                System.out.println(Thread.currentThread().getName() + "获取到锁。。。");
                monitor_1.wait();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DemoTest demoTest = new DemoTest();

        TestSynchronized testSynchronized = new TestSynchronized();
//
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    testSynchronized.test();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        t.start();

        /*
            Thread-0获取到锁。。。
            main获取到锁。。。
            说明: monitor.wait();将monitor上加了两次的锁都给释放
        */
//        Thread.sleep(10000);
//        testSynchronized.test();

        Thread t_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testSynchronized.test_1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /*
            Thread-0获取到锁。。。
            main线程被阻塞
            说明：monitor_1.wait(); 只是释放monitor_1对象上的锁，没有释放monitor的锁；
         */
        t_1.start();
        Thread.sleep(10000);
        testSynchronized.test_1();


//        System.out.println("=================");
//        System.out.println(VM.current().details());
//        System.out.println("=================");
//        System.out.println("before");
//        System.out.println(ClassLayout.parseInstance(demoTest).toPrintable());
//        System.out.println("after");
//        demoTest.hashCode();
//        System.out.println(ClassLayout.parseInstance(demoTest).toPrintable());
    }
}

class DemoTest {
}