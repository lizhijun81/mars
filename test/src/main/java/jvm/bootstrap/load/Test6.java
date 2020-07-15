package jvm.bootstrap.load;

/**
 * HotSpot 虚拟机的BootstrapClassLoader类加载器用 null 来表示
 */
public class Test6 {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());// String 的类在 rt.jar 包中，由Bootstrap Class Loader 加载的
        System.out.println(int.class.getClassLoader());
        System.out.println(Parent6_1.class.getClassLoader());// Parent6_1 类在 工程应用中，由 App Class Loader 加载的; 只是加载过程，并没有对类进行初始化
        System.out.println(Thread.currentThread().getContextClassLoader());// 当前线程的 Class Loader 时候其父类的加载器; 而其父类的加载器通常是 App Class Loader

        System.out.println("=====================");

        // 类加载器的层级关系
        // AppClassLoader -> ExtClassLoader -> null[BootstrapClassLoader]
        ClassLoader classLoader = Parent6_1.class.getClassLoader();
        System.out.println(classLoader);
        while (classLoader != null) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }

        System.out.println("=====================");

        // 数组的ClassLoader

        // 基本数据类型的数组的类的类加载器是 null，没有类加载器
        int[] is = new int[2];
        System.out.println(is.getClass().getClassLoader());// null，没有类加载器

        // 数组类型的类加载器和元素的类加载器相同；但是数组类型的类是由JVM加载创建的
        String[] strs = new String[2];
        System.out.println(strs.getClass().getClassLoader());// null，Bootstrap的类加载器

        Parent6_1[] parent6_1s = new Parent6_1[2];
        System.out.println(parent6_1s.getClass().getClassLoader());// AppClassLoader

    }
}

class Parent6_1 {
    static {
        System.out.println("Parent6_1 ...");
    }
}