package design.model.responsibility;

import java.util.List;

public class ChainImpl implements Interceptor.Chain {
    public List<Interceptor> interceptors;

    public int index;

    public ChainImpl(List<Interceptor> interceptors, int index) {
        this.interceptors = interceptors;
        this.index = index;
    }

    @Override
    public void process(List<Interceptor> interceptors, int index) {
        Interceptor interceptor = interceptors.get(index);

        interceptor.interceptor(this);
    }
}
