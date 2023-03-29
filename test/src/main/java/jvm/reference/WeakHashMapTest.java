package jvm.reference;

import java.util.WeakHashMap;

/**
 * 测试 WeakHashMap
 *  且测试当 WeakReferenceQueue 中，被回调时是否还能取到WeakReference
 */
public class WeakHashMapTest {

    public WeakHashMap<Integer, String> build() {
        WeakHashMap<Integer, String> wkMap = new WeakHashMap<>();
        wkMap.put(1000, "1");
        wkMap.put(2000, "2");
        wkMap.put(3000, "3");
        return wkMap;
    }

    public static void main(String[] args) throws InterruptedException {
        WeakHashMapTest weakHashMapTest = new WeakHashMapTest();
        WeakHashMap<Integer, String> weakHashMap = weakHashMapTest.build();

        System.gc();// 强制GC，将没有强引用的弱引用GC

        Thread.sleep(5000);

        String s = weakHashMap.get(1);// WeakHashMap 会处理已经GC了的WeakReference; WeakReferenceQueue.poll()
        System.out.println(s);

    }
}
