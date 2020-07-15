package jvm.bytecode;

public class Test1 {
    private int a = 1;

    {
        System.out.println("object black: " + a);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
    }
}
