package proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface ProxyInterface {
    void sayHi();
}

class ProxyInvocationHandler implements InvocationHandler {
    private Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println(proxy);
        System.out.println("sayHi");
        method.invoke(target, args);
        return null;
    }
}

class ProxyInterfaceImpl implements ProxyInterface {
    @Override
    public void sayHi() {
        System.out.println("say hello");
    }
}

public class ProxyTest {
    public static void main(String[] args) {

        ProxyInterface proxyInterface = new ProxyInterfaceImpl();

        ProxyInterface proxyInterface_1 = (ProxyInterface) Proxy.newProxyInstance(
                proxyInterface.getClass().getClassLoader(),
                proxyInterface.getClass().getInterfaces(),
//                new Class[]{ProxyInterface.class},
                new ProxyInvocationHandler(new ProxyInterfaceImpl()));

        proxyInterface_1.sayHi();
    }
}


