package jvm.bootstrap.load;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试数据所使用的类加载器
 */
public class 数组的类加载器 {
    public static void main(String[] args) {
        String[] arr = new String[10];// JVM 创建内部的名称为[Ljava.lang.String的类；使用的类加载器和数组元素的类加载器相同
        System.out.println(arr.getClass());
        System.out.println(arr.getClass().getClassLoader());// null => BootStrap ClassLoader; String 通过 BootStrap ClassLoader
        System.out.println("=======");

        数组的类加载器[] arrs = new 数组的类加载器[10];
        System.out.println(arrs.getClass());
        System.out.println(arrs.getClass().getClassLoader());// AppClassLoader 数组的类加载器 是通过 App ClassLoader

        int[] arrss = new int[10];
        System.out.println(arrss.getClass());
        System.out.println(arrss.getClass().getClassLoader());// null; 是指的没有通过ClassLoader进行加载； 因为int 等基本数据类型不需要通过ClassLoader进行加载；他们是JVM内部的符号

        // JVM 内部创建的数组类型 没有任何的属性和方法
        Field[] declaredFields = arr.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        Method[] declaredMethods = arr.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
    }
}
