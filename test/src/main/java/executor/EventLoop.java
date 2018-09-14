package executor;

import java.util.concurrent.Executor;

public class EventLoop {

    private Thread thread;

    private Executor executor;

    public EventLoop(Executor executor) {
        this.executor = executor;
    }

    public void doStartThread(){
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                thread = Thread.currentThread();
                System.out.println(thread.getId());
            }
        });
    }

    public Thread getThread() {
        return thread;
    }

    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop(new MyExecutor());
        eventLoop.doStartThread();
        System.out.println(eventLoop.getThread().getId());
    }
}
