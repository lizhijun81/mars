package jvm.object;

import org.openjdk.jol.info.ClassLayout;

/**
 * 分析Synchronized轻量级锁的对象头MarkWorld；
 *  2.使用偏向锁后，MarkWorld的hashcode存放在哪？？使用偏向锁中使用同步对象的hashcode()后，偏向锁会升级为重量级锁
 *
 * -XX:-UseBiasedLocking 关闭偏向锁
 *           锁类型标志位          是否偏向锁
 * 加锁前        01                   0
 * 加锁中        00                   0
 * 释放锁        01                   0
 *
 *
 */
@SuppressWarnings("all")
public class JOLObject轻量级锁 {

    public static void main(String[] args) {
        Object obj = new Object();

        System.out.println("加锁前。。。");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            System.out.println("加锁后。。。");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());

//            // 偏向锁种使用hashcode
//            System.out.println(Integer.toHexString(obj.hashCode()));
//            System.out.println(Integer.toBinaryString(obj.hashCode()));
//            System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        }
        System.out.println("释放锁后。。。");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
