package interview;

public class TestInteger {
    public static void main(String[] args) {
        Integer a = 1;
        Integer y = new Integer(1);
        System.out.println(a == y);


        Integer b = 2;
        Integer c = 3;
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));

        Long g = 3L;
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));

        Integer d = 3;

        Integer x = a + b;






    }
}
