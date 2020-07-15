package jvm.bootstrap.load;

public class Test5 {
    public static void main(String[] args) {
        System.out.println(new Parent5_1());
    }
}

class Parent5_1 {

    static {
        System.out.println("Parent5_1 static black 1 ...");
    }

    public static Parent5_2 parent5_2 = new Parent5_2(1);

    static {
        System.out.println("Parent5_1 static black 2 ...");

        // parent5_2_1 = new Parent5_2();
        // parent5_2_1.getClass();
    }

    public static Parent5_2 parent5_2_1 = new Parent5_2(2);

    public Parent5_1() {
        System.out.println("Parent5_1 init ...");
    }
}

class Parent5_2 {
    public Parent5_2(int i) {
        System.out.println("Parent5_2 " + i + " init ...");
    }
}