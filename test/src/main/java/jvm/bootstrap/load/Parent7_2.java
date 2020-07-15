package jvm.bootstrap.load;

public class Parent7_2 {
    static {
        System.out.println("Parent7_2 static block ...");
    }

    public Parent7_2() {
        System.out.println("parent7_2 init ");
        System.out.println("parent7_2 class loader " + this.getClass().getClassLoader());
        System.out.println("parent7_2 currentThread name " + Thread.currentThread().getName());
        System.out.println("parent7_2 currentThread class loader " + Thread.currentThread().getContextClassLoader());

        // 当 Parent7_1 由自定义加载器加载、Parent7_2由 AppClassLoad 加载，父加载器加载的类不能引用子加载器加载的类
        // 子加载器可以使用父加载器的命名空间，但是父加载器不能使用子加载器的命名空间
//        System.out.println(Parent7_1.class);
    }
}