package jvm.bootstrap.load;

import java.io.*;

/**
 * 自定义类加载器
 */
public class Test7 extends ClassLoader {

    private String classLoaderName;

    private String path;

    public Test7(ClassLoader parent, String classLoaderName, String path) {
        super(parent);
        this.classLoaderName = classLoaderName;
        this.path = path;
    }

    public Test7(String classLoaderName, String path) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println(classLoaderName + " invoke find class name :" + name);
        FileInputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            File file = new File(path + name.replaceAll("\\.", "/") + ".class");
            in = new FileInputStream(file);
            int read = in.read();
            while (read != -1) {
                out.write(read);
                read = in.read();
            }

            bytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String path = "/Users/lizhijun/Documents/GitHub/mars/test/doc/classloader/";
        String className = "jvm.bootstrap.load.Parent7_1";
        Test7 test7 = new Test7("myClassLoader", path);

        System.out.println(test7.getClass().getClassLoader());

        Class<?> clazz = test7.loadClass(className);

        Object o = clazz.newInstance();
    }
}

/*
1. Parent7_1 、 Parent7_2 都不在 class path 中
myClassLoader invoke find class name :jvm.bootstrap.load.Parent7_1
Parent7_1 static block ...
parent7_1 init
parent7_1 class loader jvm.bootstrap.load.Test7@2f0e140b
parent7_1 currentThread class loader sun.misc.Launcher$AppClassLoader@18b4aac2
myClassLoader invoke find class name :jvm.bootstrap.load.Parent7_2
Parent7_2 static block ...
parent7_2 init
parent7_2 class loader jvm.bootstrap.load.Test7@2f0e140b
parent7_2 currentThread class loader sun.misc.Launcher$AppClassLoader@18b4aac2


2. Parent7_1 、 Parent7_2 都在 class path 中
Parent7_1 static block ...
parent7_1 init
parent7_1 class loader sun.misc.Launcher$AppClassLoader@18b4aac2
parent7_1 currentThread name main
parent7_1 currentThread class loader sun.misc.Launcher$AppClassLoader@18b4aac2
Parent7_2 static block ...
parent7_2 init
parent7_2 class loader sun.misc.Launcher$AppClassLoader@18b4aac2
parent7_2 currentThread name main
parent7_2 currentThread class loader sun.misc.Launcher$AppClassLoader@18b4aac2


3. Parent7_1 不在、 Parent7_2 在 class path 中
myClassLoader invoke find class name :jvm.bootstrap.load.Parent7_1
Parent7_1 static block ...
parent7_1 init
parent7_1 class loader jvm.bootstrap.load.Test7@2f0e140b
parent7_1 currentThread class loader sun.misc.Launcher$AppClassLoader@18b4aac2
Parent7_2 static block ...
parent7_2 init
parent7_2 class loader sun.misc.Launcher$AppClassLoader@18b4aac2
parent7_2 currentThread name main
parent7_2 currentThread class loader sun.misc.Launcher$AppClassLoader@18b4aac2


4. Parent7_1 在、 Parent7_2 不在 class path 中
// 类中引用的类是由 加载 该类 的类加载器进行加载的
// 所以Parent7_1在由AppClassLoader 经历委托加载成功后；AppClassLoader 再次经历委托的方式 Parent7_2，
// 发现AppClassLoader的两个父加载器都不能加载类，AppClassLoader加载Parent7_2发现认证不能进行加载

Parent7_1 static block ...
parent7_1 init
parent7_1 class loader sun.misc.Launcher$AppClassLoader@18b4aac2
parent7_1 currentThread name main
parent7_1 currentThread class loader sun.misc.Launcher$AppClassLoader@18b4aac2
Exception in thread "main" java.lang.NoClassDefFoundError: jvm/bootstrap/load/Parent7_2
	at jvm.bootstrap.load.Parent7_1.<init>(Parent7_1.java:13)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at java.lang.Class.newInstance(Class.java:442)
	at jvm.bootstrap.load.Test7.main(Test7.java:69)
Caused by: java.lang.ClassNotFoundException: jvm.bootstrap.load.Parent7_2
	at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	... 7 more

 */