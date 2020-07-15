package jvm.bootstrap.load;

public class Test {
    /**
     * 1. 只有定义静态变量的类才会被初始化
     * 2. 子类的初始化前首先初始化父类
     */
    public static void main(String[] args) {
        System.out.println(TChild.p_str);// 通过子类引用父类的静态变量，只会对父类进行初始化
//        System.out.println(TChild.c_str);// 访问子类的静态变量，父类会先被初始化，然后对子类进行初始化
    }
}

class TParent {
    public static String p_str = "parent hello word";
    
    static {// 静态块是在类被加载时执行
        System.out.println("parent static black run ...");
    }
}

class TChild extends TParent {
    public static String c_str = "child hello word";
    
    static {
        System.out.println("child static black run ...");
    }
}