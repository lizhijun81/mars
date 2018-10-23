package ThreadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Objects;
import java.util.concurrent.*;

public class TestSchedulerThreadPool {

    public static void main(String[] args) throws InterruptedException {
        TestSchedulerThreadPool pool = new TestSchedulerThreadPool();

//        pool.init();
//        Thread.sleep(Integer.MAX_VALUE);

        pool.test();

    }

    public void test() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 2, 0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactoryBuilder()
                        .setNameFormat("DiscoveryClient-HeartbeatExecutor-%d")
                        .setDaemon(true)
                        .build()
        );

        for (int j = 0;  j < 3; j++) {
            HeartbeatTask task = new HeartbeatTask(String.valueOf(j));
            threadPoolExecutor.submit(task);
        }

        Thread.sleep(Integer.MAX_VALUE);

    }

    ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(3,
            new ThreadFactoryBuilder()
                    .setNameFormat("DiscoveryClient-%d")
                    .setDaemon(true)
                    .build());

    public void init(){

        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactoryBuilder()
                .setNameFormat("heartbeat-%d")
                .setDaemon(true)
                .build());


        HeartbeatTask heatbeatTask = new HeartbeatTask("1");

        TestTask testTask = new TestTask(executorService, heatbeatTask);
        scheduled.schedule(testTask, 2, TimeUnit.SECONDS);
    }

    int time = 10;

    private class TestTask implements Runnable {

        private ExecutorService executorService;

        private HeartbeatTask task;

        public TestTask(ExecutorService executorService, HeartbeatTask task) {
            this.executorService = executorService;
            this.task = task;
        }

        @Override
        public void run() {
            task = new HeartbeatTask(String.valueOf(time));

            Future future = executorService.submit(task);
            try {
                future.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                time+=10;
                System.out.println(e.getMessage());
            }

            System.out.println(future);

            if (Objects.nonNull(future)) {
                future.cancel(true);
            }

            if (!scheduled.isShutdown()) {
                System.out.println(Thread.currentThread().getName() + "======" + time);
                scheduled.schedule(this, time, TimeUnit.SECONDS);
                int i =0;

            }
        }
    }

    class HeartbeatTask implements Runnable {

        private String taskName;

        public HeartbeatTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            this.heatbeat();
        }

        private void heatbeat() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "======" + taskName);
            }
        }
    }
}
