package jvm.object;

import org.openjdk.jol.info.ClassLayout;

/**
 * 通过JOL分析 Object 内存布局
 ********************************** ******************************** ******************************** ********************************
 *  *  -XX:+UseCompressedClassPointers
 ********************************** ******************************** ******************************** ********************************
 * JVM默认开启类压缩指针的情况下：(8 MarkWorld + 4 klassPointer + 4 padding)类元信息指针 只占用了4个字节： -XX:+UseCompressedClassPointers
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 *
 * 关闭类指针压缩后：-XX:-UseCompressedClassPointers 情况下：(8 MarkWorld + 8 klassPointer)类元信息指针 只占用了8个字节
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           00 9c 04 17 (00000000 10011100 00000100 00010111) (386178048)
 *      12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 *
 ********************************* ******************************** ******************************** ********************************
 *  -XX:+UseCompressedOops
 ********************************* ******************************** ******************************** ********************************
 * JVM默认开启普通指针压缩的情况下，引用的长度占用4个字节
 * jvm.object.JOLObject object internals:
 *  OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
 *       0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4                     (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
 *      12     4   java.lang.Boolean JOLObject.b                               false
 *      16     4    java.lang.Object JOLObject.obj                             (object)
 *      20     4                     (loss due to the next object alignment)
 * Instance size: 24 bytes
 *
 * -XX:-UseCompressedOops 关闭普通指针压缩后，引用的长度占用8个字节
 * jvm.object.JOLObject object internals:
 *  OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
 *       0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4                     (object header)                           58 35 80 a0 (01011000 00110101 10000000 10100000) (-1602210472)
 *      12     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *      16     8   java.lang.Boolean JOLObject.b                               false
 *      24     8    java.lang.Object JOLObject.obj                             (object)
 */
public class JOLObjectTest {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new JOLObject()).toPrintable());
    }
}

class JOLObject {
    private Boolean b = Boolean.FALSE;

    private Object obj = new Object();
}
