package jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 *
 * ReferenceQueue
 * 使用场景：
 *      ReferenceQueue 的存在就是将对象的可达性产生变化后(其实就是对象即将变为不可达时)，可以通知到用户线程。
 * 实现原理：
 *      1. Reference 中维护的 等待队列(pending)，当 垃圾回收器 触发对应 引用的回收条件时，加入到该pending队列。[由JDK底层进行维护]
 *      2. Reference 中维护着一个高优先级的线程，将pending队列中的 Reference的引用添加到 ReferenceQueue 中。
 *
 * SoftReference 软引用
 * 回收条件：当内存不足时，GC时进行回收；
 * 使用场景：缓存；Guava 中内存级别的缓存使用 SoftReference进行实现；
 *
 * WeakReference 弱引用
 * 回收条件：当没有强引用对对象进行引用时，每次进行GC时进行回收；
 * 使用场景：结合ReferenceQueue追踪对象是否已经被回收；Netty有针对资源泄露的检测即使用WeakReference进行实现；
 *
 * PhantomReference 虚幻引用PhantomReference
 * 回收条件：当对象只有虚引用指向它时（即不影响对象生命周期）
 * 使用场景：结合ReferenceQueue追踪对象是否已经被回收；Java 直接内存中，用于管理JVM外的内存区域
 *
 * WeakReference 和 PhantomReference 的区别：
 *      WeakReference 可以通过获取到原对象的引用，但是PhantomReference获取不到，其内部直接重写get方法返回null；
 */
public class ReferenceTest {
    public static void main(String[] args) {

        System.out.println(Integer.numberOfLeadingZeros(8192));

        /*
           当原对象不再被强引用引用时，在下次进行GC时，弱引用指向的原对象将被回收。
            1. 原对象不再被强引用引用时；
            2. 在进行下次GC；
         */
        UserInfo userInfo = new UserInfo("xiaoming");
        UserInfo anotherUser = userInfo;

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<UserInfo> weakUser = new WeakReference<>(userInfo, referenceQueue);

        System.out.println("userInfo 初始数据");
        System.out.println("strong ref:" + anotherUser);
        System.out.println("weak ref:" + weakUser.get());
        System.out.println("referenceQueue info:" + referenceQueue.poll());

        userInfo = null;
        System.gc();

        /*
            userInfo 重新指向 null，但是 anotherUser 仍然存在强引用，所以当GC产生时，WeakReference引用指向的原对象没有被回收
         */
        System.out.println("userInfo 重新指向 null");
        System.out.println("strong ref:" + anotherUser);
        System.out.println("weak ref:" + weakUser.get());
        System.out.println("referenceQueue info:" + referenceQueue.poll());

        /*
            userInfo 和 anotherUser 都指向null后，没有 强引用指向 原对象，所以在GC发生时，弱引用指向的原对象被回收;
            并且将weakReference对象的引用加到 ReferenceQueue的 中。
         */
        anotherUser = null;
        System.gc();
        System.out.println("userInfo, anotherUser 都指向 null");
        System.out.println("strong ref:" + anotherUser);
        System.out.println("weak ref:" + weakUser.get());
        System.out.println("referenceQueue info:" + referenceQueue.poll());
    }
}

class UserInfo {
    private String name;

    public UserInfo(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name is " + name;
    }
}

class WeakReferenceObject<T> extends WeakReference<T> {

    public WeakReferenceObject(T referent) {
        super(referent);
    }

    public WeakReferenceObject(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    @Override
    public String toString() {
        return "this is WeakReferenceObject...";
    }
}