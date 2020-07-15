package jvm.bootstrap.load;

public class SubClass extends Parent {

    /* 静态变量 */
    public static String s_StaticField = "子类--静态变量";

    /* 变量 */
    public String s_Field = "子类--变量";

    /* 静态初始化块 */
    static {
        System.out.println(s_StaticField);
        System.out.println("子类--静态初始化块");
    }

    /* 初始化块 */ {
        System.out.println(s_Field);
        System.out.println("子类--初始化块");
    }

    /* 构造器 */
    public SubClass() {
        System.out.println("子类--构造器");
        System.out.println("i=" + i + ",j=" + j);
    }

    /**
     * 1、执行 main 方法是，会初始化 main 方法所在的类。故 Parent 和 SubClass 的类的 static 块优先于 main 方法的执行;
     * 2、当 main 方法不在 SubClass 中时，先执行 main 方法，当执行 new SubClass() 时，会  Parent、SubClass 类会初始化
     */
    public static void main(String[] args) {
        System.out.println("子类main方法");
        new SubClass();
        new SubClass();
    }
}