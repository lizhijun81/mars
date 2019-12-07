package juc;


import java.util.concurrent.CopyOnWriteArrayList;

public class TestCopyOnWriteArrayList {


    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        Thread t1 = new Thread(new T1(copyOnWriteArrayList));
        Thread t2 = new Thread(new T2(copyOnWriteArrayList));

        t1.start();
        t2.start();

        Thread.sleep(10000);
    }
}

class T1 implements Runnable {

    private CopyOnWriteArrayList<String> copyOnWriteArrayList;

    T1(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        this.copyOnWriteArrayList = copyOnWriteArrayList;
    }

    @Override
    public void run() {
        System.out.println("add....");
        copyOnWriteArrayList.add("a");
        System.out.println(".....");
    }
}

class T2 implements Runnable {

    private CopyOnWriteArrayList<String> copyOnWriteArrayList;

    T2(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        this.copyOnWriteArrayList = copyOnWriteArrayList;
    }

    @Override
    public void run() {
        System.out.println("get....");
        String s = copyOnWriteArrayList.get(0);
        System.out.println("result...." + s);
    }
}