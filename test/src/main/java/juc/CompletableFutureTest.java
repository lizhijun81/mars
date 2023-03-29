package juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CompletableFutureTest {

    public static void main(String[] args) {

        testThenApplyMixedHandle();
    }

    private static void testThenApplyMixedHandle() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("异步处理任务: supplyAsync1 get 111 ");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, executorService).thenApply((integer) -> {
            System.out.println("串行得到异步任务结果后，接着进行处理：thenApply2 apply 222 ");
            // 模拟产生 异常
            int i = 1 / 0;
            return integer + 1;
        }).thenApply((integer) -> {
            System.out.println("串行得到异步任务结果后，接着进行处理：thenApply3 apply 333 ");
            return integer + 1;
        }).handle((integer, throwable) -> {// thenApply中发生异常时，直接跳转到handle处理，handle之后的thenApply也不会处理； 但是入参 integer为null无法使用；throwable不为null；所以handle中可以依据throwable进行处理
            System.out.println("串行得到异步任务结果后，接着进行处理2：handle4 apply 444 ");
            return integer + 1;
        }).thenApply((integer) -> {
            System.out.println("串行得到异步任务结果后，接着进行处理1：thenApply5 apply 555 ");
            return integer + 1;
        }).whenComplete((integer, e) -> {
            if (e == null) {
                System.out.println("处理计算完成的结果: whenComplete accept result = " + integer);
            }
        }).exceptionally(e -> {
            System.out.println("处理计算过程中发生的异常，无法处理上游计算完成的结果；exceptionally apply exception");
            e.printStackTrace();
            return null;
        });
        executorService.shutdown();
    }

    private static void testThenApply() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("异步处理任务： CompletableFuture get 111 ");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, executorService).thenApply((integer) -> {
            System.out.println("串行得到异步任务结果后，接着进行处理1：handle apply 222 ");
            // 模拟产生 异常
            int i = 1 / 0;
            return integer + 1;
        }).thenApply((integer) -> {// 发生异常时，终止thenApply的继续执行，直接执行whenComplete
            System.out.println("串行得到异步任务结果后，接着进行处理2：handle apply 333 ");
            return integer + 1;
        }).whenComplete((integer, e) -> {
            if (e == null) {
                System.out.println("处理计算完成的结果 whenComplete accept result = " + integer);
            }
        }).exceptionally(e -> {
            System.out.println("处理计算过程中发生的异常，无法处理上游计算完成的结果；exceptionally apply exception");
            e.printStackTrace();
            return null;
        });
        executorService.shutdown();
    }

    private static void testHandle() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("异步处理任务： CompletableFuture get 111 ");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, executorService).handle((integer, throwable) -> {
            System.out.println("串行得到异步任务结果后，接着进行处理1：handle apply 222 ");
            // 模拟产生 异常
            // int i = 1 / 0;
            return integer + 1;
        }).handle((integer, throwable) -> {// 发生异常时，仍然接着进行依次的handle处理；但是入参 integer为null无法使用；throwable不为null；所以handle中可以依据throwable进行处理
            System.out.println("串行得到异步任务结果后，接着进行处理2：handle apply 333 ");
            return integer + 1;
        }).whenComplete((integer, e) -> {
            if (e == null) {
                System.out.println("处理计算完成的结果 whenComplete accept result = " + integer);
            }
        }).exceptionally(e -> {
            System.out.println("处理计算过程中发生的异常，无法处理上游计算完成的结果；exceptionally apply exception");
            e.printStackTrace();
            return null;
        });

        executorService.shutdown();
    }

}
