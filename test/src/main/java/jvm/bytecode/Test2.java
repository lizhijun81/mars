package jvm.bytecode;

public class Test2 {

    Parent parent = new Parent();

    String str = "welcome";

    private int x = 5;

    public static Integer in = 10;

    // Parent init ...
    // Test2 init ...
    // 实例的属性在编译之后是由 构造器按照属性在java文件中的顺序依次进行赋值；
    // 与类变量在 类的构造器<clinit> 进行赋值相同
    public Test2() {
        System.out.println("Test2 init ...");
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();

        test2.setX(8);

        in = 20;
    }

    public void setX(int x) {
        this.x = x;
    }
}

class Parent{
    public Parent() {
        System.out.println("Parent init ...");
    }
}
