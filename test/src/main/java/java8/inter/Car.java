package java8.inter;

import java.util.function.Consumer;

public class Car {

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public Car() {
        System.out.println("init....");
    }

    public Car(int i) {
        System.out.println("init param....");
    }
}