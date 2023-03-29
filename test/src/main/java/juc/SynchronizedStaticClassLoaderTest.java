package juc;

import jvm.bootstrap.classloader.UserDefineClassLoader;

import java.lang.reflect.InvocationTargetException;

/**
 * Synchronized static 监视器对象为Class对象；不同ClassLoader加载相同的class；创建不同的Class对象，同时进行加锁
 */
public class SynchronizedStaticClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {

        UserDefineClassLoader loader1 = new UserDefineClassLoader("/Users/lizhijun/Documents/GitHub/mars/test/doc");
        Class userClass1 = loader1.findClass("juc.SynchronizedUser");

        UserDefineClassLoader loader2 = new UserDefineClassLoader("/Users/lizhijun/Documents/GitHub/mars/test/doc");
        Class userClass2 = loader2.findClass("juc.SynchronizedUser");

        Object obj1 = userClass1.newInstance();

        Object obj2 = userClass2.newInstance();

        new Thread("a") {
            @Override
            public void run() {
                try {
                    userClass1.getDeclaredMethod("testSyncStatic1").invoke(obj1);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("b") {
            @Override
            public void run() {
                try {
                    userClass2.getDeclaredMethod("testSyncStatic2").invoke(obj2);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
