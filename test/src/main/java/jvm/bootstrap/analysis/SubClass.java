package jvm.bootstrap.analysis;


public class SubClass extends ParentClass implements Interface1 {
    public static int A = 3;

    public static void main(String[] args) {
        int a = SubClass.A;
    }
}
