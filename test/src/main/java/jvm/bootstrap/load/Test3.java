package jvm.bootstrap.load;

import java.util.Random;
import java.util.UUID;

/**
 * 1.接口中的变量都是静态常量，编译阶段通过常量传播优化后，静态常量被放到调用静态常量的类的常量池中；所以不会对 父接口和子接口进行加载和初始化。
 * 2.子接口初始化时不要求父接口也初始化，只是加载了父接口但是没有初始化
 * 3.只有使用到接口中的在编译期不能确定值的静态常量时才会对接口进行初始化
 */
public class Test3 {
    public static void main(String[] args) {
        // 这两种情况和类的情况相同
        // 接口使用 编译期确定的常量 和 运行期确定的常量 的情况
//        System.out.println(Parent_3_1.str);// 只使用接口中编译期确定的常量也不会对接口进行加载和初始化
//        System.out.println(Parent_3_1.num);// 使用接口在运行期确定的常量时，会对接口进行加载和初始化

        // 子接口使用父接口 编译期确定的常量 和 运行期确定的常量 的情况
//        System.out.println(Child_3_2.str);// 通过子接口使用父接口中的编译期确定的常量子接口和父接口都不会加载和初始化
//        System.out.println(Child_3_2.num);// 通过子接口使用父接口中的运行期确定的常量，父接口会加载和初始化，子接口只会加载

        // 子接口使用自己的 编译期确定的常量 和 运行期确定的常量 的情况
//        System.out.println(Child_3_2.num_1);// 编译期确定的常量:子接口和父接口都不会加载和初始化
//        System.out.println(Child_3_2.str_1);// 运行期确定的常量:子接口的初始化不会导致父接口进行初始化，但是导致父接口会加载

        // 实现类和接口
//        System.out.println(Child_3_4.num_3);// 当实现类初始化时不会初始化类的接口
    }
}

interface Parent_3_1 {
    public static final String str = "parent hello world";

    public static final int num = new Random().nextInt(2);

    public static final Child_3_3 CHILD_3_3_1 = new Child_3_3("Parent_3_1");
}

interface Child_3_2 extends Parent_3_1 {

    public static final String str_1 = UUID.randomUUID().toString();

    public static final int num_1 = 1;

    public static final Child_3_3 CHILD_3_3_2 = new Child_3_3("Parent_3_1");
}

class Child_3_3 {
    public Child_3_3(String className) {
        System.out.println(className + " Child_3_3 init ...");
    }
}

class Child_3_4 implements Child_3_2 {
    public static int num_3 = 0;
}