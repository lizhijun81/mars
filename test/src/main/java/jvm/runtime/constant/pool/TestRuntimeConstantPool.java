package jvm.runtime.constant.pool;

public class TestRuntimeConstantPool {

    public static void main(String[] args) {
        System.out.println(T1.t1 == T2.t1);
    }

}

class T1 {
    public static String t1 = "11";
}
class T2 {
    public static String t1 = "11";
}
