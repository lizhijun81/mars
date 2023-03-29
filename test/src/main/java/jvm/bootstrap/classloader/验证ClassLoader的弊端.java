package jvm.bootstrap.classloader;


/**
 * 验证ClassLoader的弊端：上层的ClassLoader无法访问下层的ClassLoader中已经加载的class；
 */
public class 验证ClassLoader的弊端 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 使用 appClassLoader 将 jvm.bootstrap.load.类初始化顺序 类加载
        Class<?> aClass = Class.forName("jvm.bootstrap.load.类初始化顺序");
        System.out.println(aClass);

        ClassLoader classLoader = null;
        ClassLoader extClassLoader = Thread.currentThread().getContextClassLoader().getParent();
        ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();

        classLoader = extClassLoader;

        // appClassLoader 已经加载了 "jvm.bootstrap.load.类初始化顺序"， extClassLoader 因ClassNotFoundException，无法完成加载
        aClass = Class.forName("jvm.bootstrap.load.类初始化顺序", false, classLoader);
        System.out.println(aClass);
    }
}
