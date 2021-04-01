package interview;

public class TestInteger {
    public static void main(String[] args) {
        Integer a = 1;
        Integer y = new Integer(1);
        System.out.println(a == y);


        Integer b = 2;
        Integer c = 3;

        System.out.println(a == 1); // a 拆箱后进行 ==

        System.out.println(c == (a + b));// a + b 先拆箱后计算， 接着c也拆箱后进行比较

        System.out.println(c.equals(a + b)); // a + b 先拆箱为 int，后通过Integer.valueOf 进行装箱；通过equals判断后，所以为true

        Long g = 3L;
        System.out.println(g == (a + b)); // a + b 拆箱， g 拆箱， 都等于3； 所以 相等
        System.out.println(g.equals(a + b));// a + b 先拆箱 后装箱为Integer； 所以equals不相等


        Long h = 2L;
        System.out.println(g.equals(a + h));// a + h 先拆箱 后装箱为 Long
    }
}
