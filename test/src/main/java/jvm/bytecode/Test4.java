package jvm.bytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Date;

public class Test4 {

    public static void main(String[] args) throws Throwable {



        MethodType methodType = MethodType.methodType(void.class, String.class);

        MethodHandles.Lookup lookup = Animal.lookup();

//        MethodHandle p_test = lookup.findSpecial(Animal.class, "test", methodType, Animal.class);
//        p_test.invoke(animal, "invoke");
//        p_test.invokeExact(animal, "invokeExact");
//        p_test.invoke(dog, "invoke");
//        p_test.invokeExact(dog, "invokeExact");// 必须精确匹配

        {
            Animal animal = new Animal();
            Dog dog = new Dog();
//            class jvm.bytecode.Animal
//            animal str invoke
//            class jvm.bytecode.Animal
//            animal str invokeExact
//            dog str invoke
//            expected (Animal,String)void but found (Dog,String)void
//            MethodHandle v_c_test = lookup.findVirtual(Animal.class, "test", methodType);
//            v_c_test.invoke(animal, "invoke");
//            v_c_test.invokeExact(animal, "invokeExact");
//            v_c_test.invoke(dog, "invoke");// 当前对象的的类中 查找方法描述符，执行了当前对象的方法
//            v_c_test.invokeExact(dog, "invokeExact");
        }


        {
            {
//            class jvm.bytecode.Animal
//            animal str invoke
//            class jvm.bytecode.Animal
//            animal str invokeExact
//            class jvm.bytecode.Dog
//            animal str invoke
//            expected (Animal,String)void but found (Dog,String)void

            /*
                    第一个参数： refc           从指定的refc类中访问，访问 方法描述符指定的方法
                    第二个参数： name           方法的名字
                    第三个参数： MethodType     方法的描述符
                    第四个参数： specialCaller  从指定的specialCaller的类 按给定的描述符查找
             */

                Animal animal = new Animal();
                Dog dog = new Dog();
//            MethodHandle s_c_test = lookup.findSpecial(Animal.class, "test", methodType, Animal.class);
//            s_c_test.invoke(animal, "invoke");
//            s_c_test.invokeExact(animal, "invokeExact");
//            s_c_test.invoke(dog, "invoke");// dog 子类调用父类的 test 方法； 子类型向上转型为父类型
//            s_c_test.invokeExact(dog, "invokeExact");
            }


            {
                Animal animal = new Animal();
                Dog dog = new Dog();
                MethodHandle s_p_test = Dog.lookup().findSpecial(Dog.class, "test", methodType, Dog.class);
//                s_p_test.invoke(animal, "invoke");// Cannot cast jvm.bytecode.Animal to jvm.bytecode.Dog 父类型不能转换成子类型； invoke 方法会做类型转换
//                s_p_test.invokeExact(animal, "invokeExact");// expected (Dog,String)void but found (Animal,String)void ； invokeExact 方法按指定的
                s_p_test.invoke(dog, "invoke");// dog 子类调用父类的 test 方法； 子类型向上转型为父类型
                s_p_test.invokeExact(dog, "invokeExact");
            }

        }


        {

        }

//        {// 从子类引用父类
//            MethodHandle s_c_test = lookup.findSpecial(Animal.class, "test", methodType, Dog.class);
////        class jvm.bytecode.Animal
////        animal str invoke
////        class jvm.bytecode.Animal
////        animal str invokeExact
////        dog str invoke
////            s_c_test.invoke(animal, "invoke");
////            s_c_test.invokeExact(animal, "invokeExact");
////            s_c_test.invoke(dog, "invoke");// 子类调用子的方法
////            s_c_test.invokeExact(dog, "invokeExact");
//        }
    }

}

class Animal {
    public void test(String str) {
        System.out.println(this.getClass());
        System.out.println("animal str " + str);
    }

    public void test(Date date) {
        System.out.println("animal date");
    }

    static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

}

class Dog extends Animal {
    @Override
    public void test(String str) {
        System.out.println("dog str " + str);
    }

    @Override
    public void test(Date date) {
        System.out.println("dog date");
    }

    static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }
}
