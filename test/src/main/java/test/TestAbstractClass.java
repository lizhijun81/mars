package test;

public class TestAbstractClass {
    public static void main(String[] args) {
        TestAbstract_2 testAbstract_2 = new TestAbstract_2();
        testAbstract_2.test("");
    }
}

abstract class TestAbstract {
    abstract void test(String str);

    public void say(String str) {

    }
}

class TestAbstract_1 extends TestAbstract {

    @Override
    void test(String str) {
        System.out.println("TestAbstract_1 test ...");
        say("");
    }

    @Override
    public void say(String str) {
        System.out.println("TestAbstract_1 say ...");
    }
}

class TestAbstract_2 extends TestAbstract {

    private TestAbstract_1 testAbstract_1 = new TestAbstract_1();

    @Override
    void test(String str) {
        testAbstract_1.test("");
    }

    @Override
    public void say(String str) {
        System.out.println("TestAbstract_2 say ...");
    }


}