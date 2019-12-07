package jvm.bootstrap.load;

public class Main {
    /**
     * 加载  验证  准备  解析  初始化  使用  卸载
     */
    public static void main(String[] args) {
//        System.out.println("main方法");
//        new SubClass();

        // 1、通过子类引用父类的静态变量，子类不会被初始化；但是 在hotspot中，子类SubClass会被加载
//        String p_staticField = SubClass.p_StaticField;

        // 2、通过创建数组子类时，子类不会被初始化，但是会被加载
//        SubClass[] subClasses = new SubClass[10];

        // 3、引用静态常量，类不会进行类加载和初始化；静态常量是在编译期间将存入到常量池中，在方法区的 运行时常量池中？
        //  说是在 JVM书中说是在 准备 阶段 为 常量值 进行赋值，但是 从 类的加载log中看，没有进行 该类的加载，怎么为啥直接到了准备阶段
        String pStaticFinalField = Parent.P_STATIC_FINAL_FIELD;
        System.out.println(pStaticFinalField);
        TestFinalField.test();
    }
}
