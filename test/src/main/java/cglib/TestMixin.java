package cglib;

import net.sf.cglib.proxy.Mixin;


/**
 * 这是一种将多个接口混合在一起的方式, 实现了多个接口
 * 这种方式是一种多继承的替代方案, 很大程度上解决了多继承的很多问题, 实现和理解起来都比较容易
 */
public class TestMixin {
    public static void main(String[] args) {
        Class<?>[] interfaces = new Class[] {MyInterfaceA.class, MyInterfaceB.class};
        Object[] delegates = new Object[] {new MyInterfaceAImpl(), new MyInterfaceBImpl()};

        Mixin mixin = Mixin.create(interfaces, delegates);

        MyInterfaceA myInterfaceA = (MyInterfaceA) mixin;
        myInterfaceA.methodA();

        MyInterfaceB myInterfaceB = (MyInterfaceB) mixin;
        myInterfaceB.methodB();
    }
}

interface MyInterfaceA {
    void methodA();
}

interface  MyInterfaceB {
    void methodB();
}

class MyInterfaceAImpl implements MyInterfaceA {
    public void methodA() {
        System.out.println("MyInterfaceAImpl.methodA()");
    }
}

class MyInterfaceBImpl implements MyInterfaceB {
    public void methodB() {
        System.out.println("MyInterfaceBImpl.methodB()");
    }
}