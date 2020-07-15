package jvm.bytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import static java.lang.invoke.MethodHandles.*;
import static java.lang.invoke.MethodType.*;

public class Listie extends ArrayList {
    public String toString() {
        return "[wee Listie]";
    }
    static Lookup lookup() {
        return MethodHandles.lookup();
    }

    public static void main(String[] args) throws Throwable {
        // no access to constructor via invokeSpecial:
        MethodHandle MH_newListie = Listie.lookup().findConstructor(Listie.class, methodType(void.class));
        Listie l = (Listie) MH_newListie.invokeExact();

//        Listie.lookup().findSpecial(Listie.class, "<init>", methodType(void.class), Listie.class); //"impossible"

        // access to super and self methods via invokeSpecial:
        MethodHandle MH_super = Listie.lookup().findSpecial(ArrayList.class, "toString" , methodType(String.class), Listie.class);
        System.out.println((String) MH_super.invokeExact(l));

        MethodHandle MH_this = Listie.lookup().findSpecial(Listie.class, "toString" , methodType(String.class), Listie.class);
        System.out.println((String) MH_this.invokeExact(l));

        // ArrayList method toString()
        /*
            MethodHandle用于模拟invokespecial时，必须遵守跟Java字节码里的invokespecial指令相同的限制:
            它只能调用到传给findSpecial()方法的最后一个参数（“specialCaller”）的直接父类的版本。
        */
        MethodHandle MH_duper = Listie.lookup().findSpecial(Object.class, "toString" , methodType(String.class), Listie.class);
        System.out.println((String) MH_duper.invokeExact(l));

        MethodHandle MH_Virtual = Listie.lookup().findVirtual(Object.class, "toString" , methodType(String.class));
        System.out.println((String) MH_Virtual.invoke(l));


//        Listie.lookup().findSpecial(String.class, "toString", methodType(String.class), Listie.class);
//
//        Listie subl = new Listie() {public String toString() { return "[subclass]"; }};

//        assertEquals(""+l, (String) MH_this.invokeExact(subl)); // Listie method
    }
}


