package jvm.bootstrap.load;

public class Parent7_1 {
    static {
        System.out.println("Parent7_1 static block ...");
    }

    public Parent7_1() {
        System.out.println("parent7_1 init ");
        System.out.println("parent7_1 class loader " + this.getClass().getClassLoader());
        System.out.println("parent7_1 currentThread name " + Thread.currentThread().getName());
        System.out.println("parent7_1 currentThread class loader " + Thread.currentThread().getContextClassLoader());
        new Parent7_2();
    }
}