package spring;

import java.util.concurrent.TimeUnit;

public class Person {

    public void speak() throws Exception {
        System.out.println("I am person ...");
        TimeUnit.MILLISECONDS.sleep(1000);
    }

}
