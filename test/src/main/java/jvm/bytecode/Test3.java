package jvm.bytecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.ServerSocket;

/*
 * 异常表
 */

//Parent try init ...
//finally ...
// finally 是在 return 之前执行的，在执行finally之前先执行了new Parent，然后执行了 finally，之后执行 return

//IOException ...
//finally ...
//Parent return init ...

// finally 是在 return 之前执行的

public class Test3 {

    public Parent3 test() {
        try {
            FileInputStream file = new FileInputStream("rtyu");
            ServerSocket serverSocket = new ServerSocket(9222);
            return new Parent3("try");
        } catch (IOException ex) {
            System.out.println("IOException ...");
        } catch (Exception ex) {
            System.out.println("Exception ...");
        } finally {
            System.out.println("finally ...");
        }

        return new Parent3("return");
    }

    public String str() {
        System.out.println("test str ....");
        return "str";
    }

    public Test3() {
        System.out.println("test3 init ...");
    }

    public static void main(String[] args) throws Throwable {
        Test3 test3 = new Test3();
        test3.test();

        MethodType methodType = MethodType.methodType(void.class);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle constructor = lookup.findConstructor(Test3.class, methodType);

        Object invoke = constructor.invoke();
    }
}

class Parent3{
    public Parent3(String str) {
        System.out.println("Parent " + str + " init ...");
    }
}