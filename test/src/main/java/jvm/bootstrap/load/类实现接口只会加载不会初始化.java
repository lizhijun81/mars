package jvm.bootstrap.load;

import java.util.Random;

/**
 * 类实现接口; 接口只会加载不会初始化
 */
public class 类实现接口只会加载不会初始化 implements inter1 {

    public static void main(String[] args) {
        // 类实现接口; 接口只会加载不会初始化
        // 类实现接口只会加载不会初始化 t1 = new 类实现接口只会加载不会初始化();

        // 接口进行类初始化
        // int a = inter1.a;

        // 接口没有类初始化
        int b = inter1.b;
    }

}
interface inter1 {
    public static final Thread t = new Thread() {
        {
            System.out.println("inter1.t init");
        }
    };

    public static final int a = new Random().nextInt(10);

    public static final int b = 1;
}