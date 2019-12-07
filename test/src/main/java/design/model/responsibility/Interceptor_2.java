package design.model.responsibility;

public class Interceptor_2 implements Interceptor {
    @Override
    public void interceptor(Chain chain) {
        System.out.println("interceptor.....2");
//        chain.process();
    }
}
