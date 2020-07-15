package jvm.concurrent;

public class TestSynchronized {
    private Object object;

public void test1() {
    synchronized (this) {
        System.out.println(this);
    }
}

public synchronized void test2() {
    System.out.println(this);
}

public static synchronized void test3() {
    System.out.println(TestSynchronized.class);
}

public static void main(String[] args) {
    System.out.println("");
}
}
