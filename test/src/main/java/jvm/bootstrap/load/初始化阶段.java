package jvm.bootstrap.load;

/**
 * 初始化阶段执行的动作（JVM自动生成类初始化方法 <clinit>）
 * 静态变量的赋值 和 静态代码块的执行；在 class 中定义的 顺序 决定了 在 初始化方法中执行的顺序
 *
 */
public class 初始化阶段 {

    // 需要初始化阶段的类初始化方法中进行赋值
    // 自动添加 包装方法 然后进行赋值操作 需要类初始化方法中进行赋值
    /*
         5 bipush 19
         7 invokestatic #3 <java/lang/Integer.valueOf>
        10 putstatic #4 <jvm/bootstrap/load/初始化阶段.aInteger>
     */
    private static final Integer aInteger = 19;

    // 需要初始化阶段的类初始化方法中进行赋值
    // 给基础类型手动通过封装方法赋值时；需要通过类初始化方式中进行赋值
    /*
        13 bipush 19
        15 invokestatic #3 <java/lang/Integer.valueOf>
        18 invokevirtual #5 <java/lang/Integer.intValue>
        21 putstatic #6 <jvm/bootstrap/load/初始化阶段.ai>
     */
    private static final int ai = Integer.valueOf(19);

    // 需要初始化阶段的类初始化方法中进行赋值
    /*
        28 ldc #8 <1233>
        30 invokespecial #9 <java/lang/String.<init>>
        33 putstatic #10 <jvm/bootstrap/load/初始化阶段.SFN>
     */
    private static final String SFN = new String("1233");

    // 需要初始化阶段的类初始化方法中进行赋值
    private static final String SF = "1233";

    // 需要初始化阶段的类初始化方法中进行赋值
    private static int a = 19;

    static {
        System.out.println("......");
    }

    // 需要初始化阶段的类初始化方法中进行赋值
    private static int b = 10;

}
