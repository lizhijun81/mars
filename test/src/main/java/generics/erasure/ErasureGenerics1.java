package generics.erasure;

public class ErasureGenerics1<T extends Number> {
    T object;

    public ErasureGenerics1(T object) {
        this.object = object;
    }

    public void add (T object){

    }
}