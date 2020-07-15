package jvm.gc;

/*
 *此代码演示了两点:
 *   1.对象可以在被GC时自我拯救。
 *   2.这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK=null;

    public void isAlive(){
        System.out.println("yes,i am still alive:)");
    }

    /**
     * 1. finalize 方法只会执行一次
     * 2. 被放到 F-Queue 的队列中由 JVM 的 Finalizer 的线程执行
     * 3. finalize 方法在GC是进行：
     *      即 对象不可达之后进行垃圾回收；但是在finalize方法中，可以将当前对象变成可达对象后，该对象将不能被回收
     * 4. finalize 方法中出现阻塞或者长时间的运行将会导致JVM的Finalizer线程阻塞，所以JVM只保证finalize方法被执行，但是不能保证被执行结束
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(Thread.currentThread().getName());
        System.out.println("finalize mehtod executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[]args)throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();
        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no,i am dead:(");
        }
        // 下面这段代码与上面的完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();

        // 因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_HOOK!=null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no,i am dead:(");
        }
    }
}