package jvm.reference;

//import sun.misc.Cleaner;

/**
 * Java提供对虚引用的封装Cleaner；当虚引用被GC时，ReferenceHandler线程回调Cleaner.clean()，从而执行封装到Cleaner中的Runnable()做一些资源回收；
 * 如Java直接内存的回收，就是依赖Cleaner
 */
public class CleanerTest {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static class CleanerRunnable implements Runnable {

        private String name;

        @Override
        public void run() {
            System.out.println("CleanerRunnable ...." + name);
        }

        public CleanerRunnable(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CleanerTest cleanerTest = new CleanerTest();
        cleanerTest.setName("xiaoming");

        CleanerRunnable cleanerRunnable = new CleanerRunnable(cleanerTest.getName());

//        Cleaner cleaner = Cleaner.create(cleanerTest, cleanerRunnable);

        cleanerTest = null;
        System.gc();

        Thread.sleep(Integer.MAX_VALUE);
    }

}
