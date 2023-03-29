package juc;

/**
 * 测试Volatile变量可见性问题
 */
public class Volatile变量可见性 {

    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {

            private Object obj = new Object();

            @Override
            public void run() {
                while (flag) {
                    synchronized (obj) {// 加锁；底下 不允许更新到和 加锁进行从排序； 类似Volatile读语义；读取从主内存读取最新的数据
                        System.out.println("子线程执行synchronized begin..." + flag);
                    }// 释放锁  类似Volatile写语义；将线程本地内存中的数据作废
                    System.out.println("子线程执行synchronized after..." + flag);
                }
                System.out.println("子线程执行结束...");
            }
        }.start();

        Thread.sleep(3000L);
        flag = false;
        System.out.println("主线程执行结束...flag " + flag);
    }

}
