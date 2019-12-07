package proxy;

import java.lang.reflect.Method;

public class ReflectTest {

    public void test() {
        System.out.println("......");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        ReflectTest reflectTest = new ReflectTest();
        Method test = reflectTest.getClass().getDeclaredMethod("test");
        Class<?> declaringClass = test.getDeclaringClass();
        System.out.println(declaringClass);

        if (Object.class.equals(declaringClass)) {
            System.out.println("method is object");
        }
    }


}
