package jvm.thread;

public class ThreadLocalContent {

    private byte[] value;

    public ThreadLocalContent(byte[] value) {
        this.value = value;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("ThreadLocalContent finalize ...");
    }
}
