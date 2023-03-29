package jvm.object;

import com.sun.org.apache.bcel.internal.generic.ObjectType;
import org.openjdk.jol.info.ClassLayout;

/**
 * 分析Synchronized偏向锁的对象头MarkWorld；
 *  1.退出Synchronized块后，对象头中的MarkWorld的线程Id不会被清除
 *  2.使用偏向锁后，MarkWorld的hashcode存放在哪？？使用偏向锁中使用同步对象的hashcode()后，偏向锁会升级为重量级锁
 *  3.开启偏向锁后，初始对象的MarkWorld中，直接即为偏向锁；101，但是不指向任何的线程
 *
 * -XX:+UseBiasedLocking 开启偏向锁
 * -XX:BiasedLockingStartupDelay=0 偏向锁启用时间
 *
 *           锁类型标志位          是否偏向锁
 * 加锁前        01                   1
 * 加锁中        01                   1
 * 释放锁        01                   1
 *
 * 00000101
 * 10010000
 */
public class JOLObject偏向锁 {

    public static void main(String[] args) {
        Object obj = new Object();

        System.out.println("加锁前hashCode前。。。");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        // 加锁前：使用hashCode后，将使用轻量级锁进行加锁
//        obj.hashCode();
//        System.out.println("加锁前hashCode后。。。");
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            System.out.println("加锁后。。。");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());

//            // 偏向锁中使用hashcode，膨胀为重量级锁
//            System.out.println(Integer.toHexString(obj.hashCode()));
//            System.out.println(Integer.toBinaryString(obj.hashCode()));
//            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        System.out.println("释放锁后hashCode前。。。");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());


        //  偏向锁后：调用同步对象的hashCode后，偏向锁变为无锁
//        obj.hashCode();
//
//        System.out.println("释放锁后hashCode后。。。");
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

//       //   偏向锁后再次加锁：将使用轻量级锁进行加锁
//        synchronized (obj) {
//            System.out.println("再次加锁后。。。");
//            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
//
////            // 偏向锁种使用hashcode
////            System.out.println(Integer.toHexString(obj.hashCode()));
////            System.out.println(Integer.toBinaryString(obj.hashCode()));
////            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
//        }
//        System.out.println("再次释放锁后hashCode前。。。");
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());


    }

}
