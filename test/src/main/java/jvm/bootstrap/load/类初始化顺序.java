package jvm.bootstrap.load;

/**
 * main 方法所在类被初始化后，其他的类在main方法执行的过程中，不断进行初始化
 * -XX:+TraceClassLoading
 *
 * [Loaded jvm.bootstrap.load.类初始化顺序 from file:/Users/lizhijun/Documents/GitHub/mars/test/target/classes/]
 * 类初始化顺序 init....
 * [Loaded jvm.bootstrap.load.类初始化顺序1 from file:/Users/lizhijun/Documents/GitHub/mars/test/target/classes/]
 * 类初始化顺序1 init....
 * [Loaded jvm.bootstrap.load.类初始化顺序2 from file:/Users/lizhijun/Documents/GitHub/mars/test/target/classes/]
 * 类初始化顺序2 init....
 * [Loaded jvm.bootstrap.load.类初始化顺序3 from file:/Users/lizhijun/Documents/GitHub/mars/test/target/classes/]
 * 类初始化顺序3 init....
 */
public class 类初始化顺序 {
    static {
        System.out.println("类初始化顺序 init....");
    }
    public static int a = 1;

    public static void main(String[] args) {
        int a1 = 类初始化顺序1.a;
        int a2 = 类初始化顺序2.a;
        int a3 = 类初始化顺序3.a;
    }
}
class 类初始化顺序1 {
    static {
        System.out.println("类初始化顺序1 init....");
    }
    public static int a = 1;
}
class 类初始化顺序2 {
    static {
        System.out.println("类初始化顺序2 init....");
    }
    public static int a = 1;
}
class 类初始化顺序3 {
    static {
        System.out.println("类初始化顺序3 init....");
    }
    public static int a = 1;
}
