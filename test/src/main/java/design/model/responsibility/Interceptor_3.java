package design.model.responsibility;

public class Interceptor_3 implements Interceptor {
    @Override
    public void interceptor(Chain chain) {
        System.out.println("interceptor.....3");

//        chain.process();
    }
}
