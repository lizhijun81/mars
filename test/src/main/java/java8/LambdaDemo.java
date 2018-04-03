package java8;

import java8.inter.Car;
import java8.inter.GreetingService;
import java8.inter.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LambdaDemo {
    public static void main(String[] args) {

        //================================= lambda ===============================================
        GreetingService greetingService = (message) -> {
            System.out.println("start...");
            System.out.println("hello, " + message);
            System.out.println("end...");
        };

        greetingService.sayMessage("World");

        //========================= 方法引用：方法引用是lambda的一种简写方式 =========================
        List<Car> carList = new ArrayList<>();
        // 引用构造器
        Car car = Car.create(Car::new);// 该种方式只能引用默认的构造构造函数()-> new Car()
        System.out.println(car.toString());
        carList.add(car);

        car = Car.create( () -> new Car(1) );
        System.out.println(car.toString());
        carList.add(car);

        // 静态方法引用
        carList.forEach(Car::collide);// 等价于 (car1) -> Car.collide(car1)

        // 特定类的任意对象的方法引用
        carList.forEach((Car::repair));// 等价于 (car1 -> car1.repair())

        // 特定对象的方法引用
        final Car finalCar = car;
        carList.forEach(finalCar::follow);// 等价于 (car1 -> finalCar.follow(car1))


    }
}

