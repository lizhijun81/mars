package inner;

public class TestInnerClass {

    public void useInnerClass(final int num) {
        final DataBean bean = new DataBean("sss");

        InnerClass innerClass = new InnerClass() {
            @Override
            void doSomething() {
                System.out.println(num);
                System.out.println(bean.name);
            }
        };

        innerClass.doSomething();
    }

    public static void main(String[] args) {
        TestInnerClass testInnerClass = new TestInnerClass();
        testInnerClass.useInnerClass(5);
    }
}

class InnerClass {
    void doSomething() {

    }
}

class DataBean {
    public String name;

    public DataBean(String name) {
        this.name = name;
    }
}