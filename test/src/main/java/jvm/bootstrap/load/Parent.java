package jvm.bootstrap.load;

class Parent {
    public static final String P_STATIC_FINAL_FIELD = "常量";

        /* 静态变量 */
    public static String p_StaticField = "父类--静态变量";

    /**
     * 静态变量在 准备 阶段初始化为 零值，在 类初始 阶段执行时，将 为 静态变量 进行赋值
     */
    public String    p_Field = "父类--变量";
    protected int    i    = 9;
    protected int    j    = 0;
        /* 静态初始化块 */
    static {
        System.out.println( p_StaticField );
        System.out.println( "父类--静态初始化块" );
    }
        /* 初始化块 */
    {
        System.out.println( p_Field );
        System.out.println( "父类--初始化块" );
    }
        /* 构造器 */
    public Parent()
    {
        System.out.println( "父类--构造器" );
        System.out.println( "i=" + i + ", j=" + j );
        j = 20;
    }
}

