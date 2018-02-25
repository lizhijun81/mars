package annotation;


import java.lang.annotation.*;


/**
 * 自定义annotation
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.TYPE_PARAMETER, ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface MyAnnotation {

    String name();

    int value() default 0;

}
