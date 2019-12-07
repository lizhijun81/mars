package design.model.responsibility;

import java.util.List;

public interface Interceptor {

    void interceptor(Chain chain);

    interface Chain {
        void process(List<Interceptor> interceptors, int index);


    }
}
