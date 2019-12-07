package jvm.bootstrap.box;

public class BoxTest {
    public static void main(String[] args) {

        Integer a = 0;
        Integer b = 1;
        Integer c = 2;
        Integer d = 3;

        Long e = 3L;
        Integer f = 3;

        Integer x = 300;
        Integer y = 300;

        // ==  在 算术运算 中会自动拆箱
        System.out.println(d == 3); // 自动拆箱
        System.out.println(d == (b + c)); //
        System.out.println(d.equals(b + c)); // b + c 后 自动装箱后，进行 equals
        System.out.println(e == (b + c));// 把 Long 和 Integer 的 包装类型 都 自动拆箱 后，进行比较


        // equals 判断 对象类型不同，导致 值相等，类型不相等使得 表达式为false
        System.out.println(e.equals(b + c));
//        System.out.println(d == e);  //== 不会转变类型

        // d、f 通过自动装箱 valueOf 后，变为 Integer，但是在Integer中存在缓存了，-127到127的Integer，
        // 故：-127到127的Integer 的对象引用相同，故 == 比较 时，相同
        System.out.println(d == f);

        // 300 不存在 Integer 的 缓存中，引用不同，== 比较时，不相同
        System.out.println(x == y);
    }
}
