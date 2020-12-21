package spring.mybatis;


import spring.Man;
import spring.Person;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestMybatisPlugin {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        LoggerPlugin loggerPlugin = new LoggerPlugin(person);
        Man loggerProxy = (Man) Proxy.newProxyInstance(TestMybatisPlugin.class.getClassLoader(), Person.class.getInterfaces(), loggerPlugin);

        TimePlugin timePlugin = new TimePlugin(loggerProxy);
        Man timeProxy = (Man) Proxy.newProxyInstance(TestMybatisPlugin.class.getClassLoader(), Person.class.getInterfaces(), timePlugin);

        timeProxy.speak();
    }
}

class ChainPlugin {
    public Object proxy() {
        return null;
    }
}

interface Plugin {

}

class LoggerPlugin implements Plugin, InvocationHandler {

    private Object target;

    public LoggerPlugin(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("LoggerPlugin plugin .....");
        return method.invoke(target, args);
    }
}

class TimePlugin implements Plugin, InvocationHandler {

    private Object target;

    public TimePlugin(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("TimePlugin plugin .... " + (endTime - startTime));
        return result;
    }

}
