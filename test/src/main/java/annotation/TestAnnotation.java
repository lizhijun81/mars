package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@MyAnnotation(name = "class")
public class TestAnnotation {

    @MyAnnotation(name = "field", value = 1)
    private int field;


    @MyAnnotation(name = "getField", value = 2)
    public String getField(@MyAnnotation(name = "input") String input, @MyAnnotation(name = "input1") String input1){
        System.out.println(input);
        return input;
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {

        Class<TestAnnotation> clzzz = TestAnnotation.class;
        MyAnnotation annotation = clzzz.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.name() +"=="+annotation.value());

        MyAnnotation annotation1 = clzzz.getDeclaredField("field").getAnnotation(MyAnnotation.class);
        System.out.println(annotation1.name() +"=="+annotation1.value());

        Method method = clzzz.getDeclaredMethod("getField", String.class, String.class);
        MyAnnotation methodAnnotation = method.getAnnotation(MyAnnotation.class);
        System.out.println(methodAnnotation.name() +"=="+methodAnnotation.value());

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                if(parameterAnnotations[i][j].annotationType().equals(MyAnnotation.class)) {
                    MyAnnotation myAnnotations = (MyAnnotation) parameterAnnotations[i][j];
                    System.out.println(myAnnotations.name() +"=="+myAnnotations.value());
                }
            }
        }
    }
}
