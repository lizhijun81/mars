//package spring.cglib;
//
//import com.google.common.collect.Lists;
//import net.sf.cglib.proxy.*;
//import java.lang.reflect.Method;
//import java.util.List;
//
//public class CglibTest {
//    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//
//        enhancer.setSuperclass(Student.class);
//
//        // Callbacks 设置增强器
////        enhancer.setCallbacks(new Callback[]{new MyMethodInterceptor_Common(), new MyMethodInterceptor_Father()});
//
//        NodeInterceptor_1 nodeInterceptor_1 = new NodeInterceptor_1();
//        NodeInterceptor_2 nodeInterceptor_2 = new NodeInterceptor_2();
//
//        Chain chain = new Chain();
//        chain.add(nodeInterceptor_1);
//        chain.add(nodeInterceptor_2);
//
//        enhancer.setCallbacks(new Callback[]{chain});
//
//        // CallbackFilter可以实现不同的方法使用不同的回调方法
//        enhancer.setCallbackFilter(new MyCallbackFilter());
//
//        Student stu = (Student) enhancer.create();
//
////        stu.speak();    // 自己的方法被代理
////        stu.walk();     // 自己的方法被代理
////        stu.study();    // 自己的方法被代理
//        stu.sayHello(); // 父类 Father 的方法 也被代理
////        stu.toString(); // 父类 Object 的方法也被代理
//
//    }
//}
//
//class MyCallbackFilter implements CallbackFilter {
//    @Override
//    public int accept(Method method) {
//        if (method.getDeclaringClass().equals(Father.class)) {
//            return 1;// 当 调用方法为 Father 类时，使用 MyMethodInterceptor_Father 增强
//        }
//
//        return 0;// 其他的使用 MyMethodInterceptor_Common 增强
//    }
//}
//
//class MyMethodInterceptor_Common implements MethodInterceptor {
//    /**
//     * @param obj  Object：由CGLib动态生成的代理类实例，
//     * @param method Method：上文中实体类所调用的被代理的方法引用，
//     * @param arg Object[]：参数值列表，
//     * @param proxy MethodProxy：生成的代理类对方法的代理引用。
//     */
//    @Override
//    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
//        System.out.println("Before: " + method);
//        // 调用代理类实例上的proxy方法的父类方法，即真实对象中的方法
//        Object object = proxy.invokeSuper(obj, arg);// 调用代理类的方法的super方法
//        System.out.println("After: " + method);
//        return object;
//    }
//}
//
//class MyMethodInterceptor_Father implements MethodInterceptor {
//    @Override
//    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        System.out.println("Father before: " + method);
//        Object object = proxy.invokeSuper(obj, args);
//        System.out.println("Father after: " + method);
//        return object;
//    }
//}
//
//class Father{
//    public void sayHello() {
//        System.out.println("this is father");
//    }
//}
//
//interface Person{
//    void speak();
//    void walk();
//}
//
//class Student extends Father implements Person {
//    public void study(){
//        System.out.println("i am student.");
//    }
//
//    @Override
//    public void speak() {
//        System.out.println("i am student ,i can speak");
//    }
//
//    @Override
//    public void walk() {
//        System.out.println("i am student ,i can walk");
//    }
//}
//
//
//class ChainContext implements MethodInterceptor {
//
//    private Chain chain;
//
//    public ChainContext(Chain chain) {
//        this.chain = chain;
//    }
//
//    @Override
//    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        return chain.process();
//    }
//}
//
//class Chain {
//    private List<Chain.NodeInterceptor> chains = Lists.newArrayList();
//
//    private Object target;
//
//    private int index = -1;
//
//    public Chain(Object target) {
//        this.target = target;
//    }
//
//    public Object process() {
//        index++;
//        Object result;
//        if (index == chains.size()) {
//            result = target;
//        } else {
//            result = chains.get(index).process(this);
//        }
//
//        return result;
//    }
//
//    public void add(NodeInterceptor nodeInterceptor) {
//        chains.add(nodeInterceptor);
//    }
//
//    interface NodeInterceptor {
//        Object process(Chain chain);
//    }
//}
//
//class NodeInterceptor_1 implements Chain.NodeInterceptor {
//    @Override
//    public Object process(Chain chain) {
//        System.out.println("start NodeInterceptor_1....");
//        Object result = chain.process();
//        System.out.println("end NodeInterceptor_1....");
//        return result;
//    }
//}
//
//class NodeInterceptor_2 implements Chain.NodeInterceptor {
//
//    @Override
//    public Object process(Chain chain) {
//        System.out.println("start NodeInterceptor_2....");
//        Object result = chain.process();
//        System.out.println("end NodeInterceptor_2....");
//        return result;
//    }
//}