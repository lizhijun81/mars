package jvm.bootstrap.load;

public class Parent7_3 {
    static {
        System.out.println("Parent7_2 static block ...");
    }

    public Parent7_3() {
        System.out.println("Parent7_3 init ");
        System.out.println("Parent7_3 class loader " + this.getClass().getClassLoader());
        System.out.println("Parent7_3 currentThread name " + Thread.currentThread().getName());
        System.out.println("Parent7_3 currentThread class loader " + Thread.currentThread().getContextClassLoader());
    }
}
