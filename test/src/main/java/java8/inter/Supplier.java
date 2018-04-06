package java8.inter;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}