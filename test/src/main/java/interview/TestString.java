package interview;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestString {
    public static void main(String[] args) throws Exception {

        Random random = new Random();

        Callable<Integer> c = () -> {
            Thread.sleep(20000);
            return random.nextInt();
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(c);

        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());

        Thread.sleep(Integer.MAX_VALUE);
    }
}
