package jvm.bytecode;

import java.util.concurrent.TimeUnit;

/*

死锁的线程的分析

Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x00007fdadbaeb558 (object 0x00000007958dc0a8, a java.lang.Class),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x00007fdadbaea848 (object 0x0000000795a0fe50, a java.lang.Class),
  which is held by "Thread-1"

Java stack information for the threads listed above:
Thread-1
    等待锁：<0x00000007958dc0a8> (a java.lang.Class for jvm.bytecode.A);
    已锁定：<0x0000000795a0fe50> (a java.lang.Class for jvm.bytecode.B)
Thread-0
    等待锁：<0x0000000795a0fe50> (a java.lang.Class for jvm.bytecode.B)
    已锁定：<0x00000007958dc0a8> (a java.lang.Class for jvm.bytecode.A)

两个线程分别阻塞等待jvm.bytecode.B 的类对象和 jvm.bytecode.A 的类对象；两个线程相互等待
===================================================
"Thread-1":
        at jvm.bytecode.A.method(Test5.java:22)
        - waiting to lock <0x00000007958dc0a8> (a java.lang.Class for jvm.bytecode.A)
        at jvm.bytecode.B.method(Test5.java:39)
        - locked <0x0000000795a0fe50> (a java.lang.Class for jvm.bytecode.B)
        at jvm.bytecode.Test5$$Lambda$2/824909230.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:748)
"Thread-0":
        at jvm.bytecode.B.method(Test5.java:35)
        - waiting to lock <0x0000000795a0fe50> (a java.lang.Class for jvm.bytecode.B)
        at jvm.bytecode.A.method(Test5.java:26)
        - locked <0x00000007958dc0a8> (a java.lang.Class for jvm.bytecode.A)
        at jvm.bytecode.Test5$$Lambda$1/885284298.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.

 */
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(A::method).start();
        new Thread(B::method).start();

        TimeUnit.SECONDS.sleep(1000);
    }
}

class A {
    public static synchronized void method() {
        System.out.println("method from A");

        try {
            Thread.sleep(5000);
            B.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B {
    public static synchronized void method() {
        System.out.println("method form B");

        try {
            Thread.sleep(5000);
            A.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}