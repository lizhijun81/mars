package spring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TestAOP {

    public static void main(String[] args) throws Exception {
        ProxyModeFactory proxyModeFactory = new ProxyModeFactory();

        List<Invocation> invocations = new ArrayList<>();// 增强
        invocations.add(new LogInvocation());
        invocations.add(new TimeInvocation());

        Chain chain = new Chain(invocations);// 增强的责任链

        ProxyMode proxyMode = proxyModeFactory.createProxyMode(new Person(), chain);

//        Object proxy = proxyMode.getProxy();
//        Man person = (Man) proxy;
//        person.speak();

        Object proxy = proxyMode.getProxy();
        Person person = (Person) proxy;
        person.speak();
    }

}

class ProxyModeFactory {
    /**
     * 判断 obj 是不是实现了 接口
     * @param target 目标对象
     * @param chain 增强的责任链
     * @return 代理模型
     */
    public ProxyMode createProxyMode(Object target, Chain chain) {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            return new CGLIBProxyMode(target, chain);
        }
        return new JdkProxyMode(target, chain);
    }
}

interface ProxyMode {
    Object getProxy();
}

/**
 * jdk 的方式 动态代理
 */
class JdkProxyMode implements ProxyMode, InvocationHandler {

    private Object target;

    private Chain chain;

    public JdkProxyMode(Object target, Chain chain) {
        this.target = target;
        this.chain = chain;
    }

    @Override
    public Object getProxy() {
        Class<?>[] interfaces = this.target.getClass().getInterfaces();
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.chain.setInvocationContext(new InvocationContext(this.target, method, args));
        this.chain.process();
        return null;// 这个地方需要放到 责任链中进行调用
    }
}

/**
 * cglib 的方式 实现动态代理
 */
class CGLIBProxyMode implements ProxyMode, MethodInterceptor {
    private Object target;

    private Chain chain;

    public CGLIBProxyMode(Object target, Chain chain) {
        this.target = target;
        this.chain = chain;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        this.chain.setInvocationContext(new InvocationContext(this.target, method, args));
        this.chain.process();// 调用
        return null;
    }
}

class InvocationContext {
    private Object target;

    private Method method;

    private Object[] args;

    public InvocationContext(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}

class Chain {
    private List<Invocation> invocations;

    private InvocationContext invocationContext;

    private int index = -1;

    public Chain(List<Invocation> invocations) {
        this.invocations = invocations;
    }

    // 遍历 所有的 Invocation
    public void process() {
        index++;
        if (index < invocations.size()) {
            invocations.get(index).doWork(this);
        } else {
            try {
                Object[] args = this.invocationContext.getArgs();
                Method method = this.invocationContext.getMethod();
                Object target = this.invocationContext.getTarget();

                method.invoke(target, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void setInvocationContext(InvocationContext invocationContext) {
        this.invocationContext = invocationContext;
    }
}

interface Invocation {
    void doWork(Chain chain);
}

class LogInvocation implements Invocation {
    @Override
    public void doWork(Chain chain) {
        System.out.println("LogInvocation doWork ...");
        chain.process();
    }
}

class TimeInvocation implements Invocation {
    @Override
    public void doWork(Chain chain) {
        System.out.println("TimeInvocation doWork start ...");
        long startTime = System.currentTimeMillis();
        chain.process();
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("TimeInvocation doWork end, time: " + endTime + " ..." );
    }
}