package generics;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;

public class MyGenerics {

    /**
     * 定义泛型方法
     */
    public <K, V> Map<K, V> newMap(K k, V v) {
        System.out.println(k.toString() + "===" + v.toString());
        Map<K, V> map = new HashMap<K, V>();
        map.put(k, v);
        return map;
    }

    public <T> T cast(Class<T> clzz, Object object) throws IllegalAccessException, InstantiationException {

        Class aClass = clzz.getClass().newInstance();
        return null;
    }


    public void iter1(List<?> list) {
        list.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println("Consumer1" + "====" + o.toString());
            }
        }.andThen(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println("Consumer2"+ "====" + o.toString());
            }
        }));
    }

    public <T> void iter(List<T> list) {
        list.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println("Consumer1" + "====" + o.toString());
            }
        }.andThen(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println("Consumer2"+ "====" + o.toString());
            }
        }));
    }


    public void f1() {

    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MyGenerics generics = new MyGenerics();

//        List<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
//        generics.iter(integers);
//
//        List<?> lists = new ArrayList<String>();
//        lists.add(null);
//
//        Object o = lists.get(1);

        List<String> listString = new ArrayList<>();
        listString.add("hjkl");

        Method add = listString.getClass().getDeclaredMethod("add", Object.class);
        add.invoke(listString, new Date());

        for (int i = 0; i < listString.size(); i++) {
            System.out.println(listString.get(i));
        }

        List<Integer> listInteger = Lists.newArrayList(1, 2);

        Method add1 = listInteger.getClass().getDeclaredMethod("add", Object.class);
        add1.invoke(listInteger, "hello");

        for (int i = 0; i < listInteger.size(); i++) {
            System.out.println(listInteger.get(i));
        }


//        generics.test();
//        generics.getMap(generics.newMap("k", 2)); //

    }

    public void test() {
//        InnerClass<Integer> innerClass = new InnerClass<>(2);
//        Integer integer = innerClass.get();
//        System.out.println(integer);
//
//        InnerClass<Long> innerClass1 = new InnerClass<>(2L);

        InnerClass<? extends Fruit> appleInnerClass = new InnerClass<>(new Apple());

        InnerSubClass<Integer> integerInnerSubClass = new InnerSubClass<>(1);

        InnerSubClass1 innerSubClass1 = new InnerSubClass1("111");

    }

    private class InnerClass<T>{
        private T t;

        public InnerClass(T t) {
            System.out.println();
            System.out.println(t.toString());
            Type[] genericInterfaces = t.getClass().getGenericInterfaces();
            System.out.println();
            this.t = t;
        }

        public T get() {
            return this.t;
        }
    }

    private class InnerSubClass<T> extends InnerClass<T> {
        public InnerSubClass(T t) {
            super(t);
        }

        @Override
        public T get() {
            return super.get();
        }
    }

    private class InnerSubClass1 extends InnerClass<String> {
        public InnerSubClass1(String s) {
            super(s);
        }

        @Override
        public String get() {
            return super.get();
        }
    }

    private class InnerSubClass2 extends InnerClass{

        public InnerSubClass2(Object o) {
            super(o);
        }

        @Override
        public Object get() {
            return super.get();
        }
    }

    class Fruit {}
    class Apple extends Fruit {}
    class RedApple extends Apple {}
    class Orange extends Fruit {}

}
