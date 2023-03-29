package jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 对 Reference 中的 ReferenceHandler 线程进行测试
 *   Reference静态全局变量：Reference.pending ；pending为链表的头结点，由JVM内部维护第一个头结点值，只有由ReferenceHandler在出列表时，进行维护；
 *   Reference对象成员变量：Reference.discovered； 是一次GC时，可能需要回收的SoftReference、WeakReference、虚引用对象 列表；
 *      指向始终指向链表的第二个节点，由JVM内部进行维护；
 *
 *   ReferenceHandler线程从 Reference.pending、 Reference.discovered 链表出 头结点
 *      Reference r = reference.pending;// 头结点赋值给临时变量
 *      reference.pending = reference.discovered; //reference.discovered=next节点，赋值给头结点；从链表中取得头结点
 *      reference.discovered = null;    // 将该reference对象的discovered置为空，不再持有pending链表
 */
public class ReferenceHandlerTest {

    private static class WReference1 extends WeakReference<Object> {
        public WReference1(Object referent, ReferenceQueue<? super Object> q) {
            super(referent, q);
        }
    }

    private static class WReference2 extends WeakReference<Object> {
        public WReference2(Object referent, ReferenceQueue<? super Object> q) {
            super(referent, q);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();

        WReference1 wObj1 = new WReference1(new Object(), queue);

        WReference2 wObj2 = new WReference2(new Object(), queue);

        System.gc();
        Thread.sleep(Integer.MAX_VALUE);
    }



}
