package jvm.bootstrap.load;

public class 初始化阶段的死锁问题 extends Thread{
    private char flag;

    public 初始化阶段的死锁问题(char flag) {
        this.flag = flag;
        this.setName("Thread" + flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("jvm.bootstrap.load.Static" + flag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " over");
    }

    public static void main(String[] args) {
        初始化阶段的死锁问题 t1 = new 初始化阶段的死锁问题('A');
        t1.start();

        初始化阶段的死锁问题 t2 = new 初始化阶段的死锁问题('B');
        t2.start();
    }
}

class StaticA {
    static {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        try {
            Class.forName("jvm.bootstrap.load.StaticB");
        } catch (ClassNotFoundException e) {
        }
        System.out.println("staticA init Ok");
    }
}
class StaticB {
    static {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        try {
            Class.forName("jvm.bootstrap.load.StaticA");
        } catch (ClassNotFoundException e) {
        }
        System.out.println("staticB init Ok");
    }
}