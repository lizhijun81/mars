package jvm.bootstrap.load;

/**
 * 数组元素类型的加载和初始化时机
 *  1.-XX:+TraceClassLoading 跟踪class加载日志
 *  2.声明引用类型数组时，会对数组元素类型进行加载，但是不会进行初始化
 */
public class LoadArrayTest {
    static {
        System.out.println("LoadArrayTest 类初始化....");
    }

    public static void main(String[] args) {
        ArrayElement[] elements = new ArrayElement[10];
    }
}
class ArrayElement {
    static {
        System.out.println("ArrayElement 类初始化....");
    }
}