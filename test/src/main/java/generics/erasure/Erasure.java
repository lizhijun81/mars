package generics.erasure;

import java.lang.reflect.Method;

/**
 * 泛型擦除
 */
public class Erasure {


    public static void main(String[] args) throws NoSuchMethodException {
        Erasure erasure = new Erasure();
        erasure.test();
    }

    public void test() throws NoSuchMethodException {
        ErasureGenerics<String> erasureGenerics = new ErasureGenerics<>("hello");
        Class<? extends ErasureGenerics> erasureGenericsClass = erasureGenerics.getClass();
        System.out.println("erasureGenerics class is:" + erasureGenericsClass);
        Method add = erasureGenericsClass.getDeclaredMethod("add", Object.class);
        System.out.println("method is :" + add.toString());
        System.out.println("=========================================");

        ErasureGenerics1<Integer> erasureGenerics1 = new ErasureGenerics1<>(100);
        System.out.println("erasureGenerics1 class is:" + erasureGenerics1.getClass());
        Method add1 = erasureGenerics1.getClass().getDeclaredMethod("add", Number.class);
        System.out.println("method is :" + add1.toString());
    }






}
