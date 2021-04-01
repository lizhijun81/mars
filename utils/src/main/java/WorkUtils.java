import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkUtils {

    private static final AtomicInteger SEQUENCE = new AtomicInteger(1);

    private static final String PREFIX = "guazi-rentcar-clue-thread-";

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10, r -> {
        Thread thread = new Thread(r);

        thread.setName(PREFIX + SEQUENCE.get());
        SEQUENCE.incrementAndGet();
        return thread;
    });

    private WorkUtils() { }

    @SuppressWarnings("unused")
    public static <T> Future<T> submit(Callable<T> task) {
        return executorService.submit(task);
    }

    public static void execute(Runnable task) {
        executorService.execute(task);
    }
}
