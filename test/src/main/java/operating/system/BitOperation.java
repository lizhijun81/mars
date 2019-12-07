package operating.system;


/**
 * ||、&&、！都为逻辑操作符
 *
 * & | ^ ~  都为位操作符；当 未操作符的操作是为 true 和 false 时，效果和 逻辑操作符相同
 */
public class BitOperation {
    public static void main(String[] args) {
        // & | ^ ~  都为位操作符；当 未操作符的操作是为 true 和 false 时，效果和 逻辑操作符相同
//        System.out.println(0x5 & 0x7);// 按位取且
//
//        System.out.println(0x1 | 0x2);// 按位取或
//
//        System.out.println(~0xffffffff);//按位取反
//
//        System.out.println(0x2^0x1);// 按位异或
//
//        System.out.println(false | true);//
//
//        System.out.println(false & true); //
//
//        System.out.println(0x80000000);
//        System.out.println(~0x80000000);
//
//        System.out.println(0x01);
//        System.out.println(~0x01);

//        System.out.println(0x1 && 0x2);  ||、&&、！都为逻辑操作符

        System.out.println(0xffffffff);
        System.out.println(0x80000000);
        System.out.println(0x80000000 >> 1);
        System.out.println(String.format("%X", 0x80000000 >> 1));// 算数右移，在左边按最高位的值填充
        System.out.println(String.format("%X", 0x80000000 >>> 1));// 逻辑右移，在左边填充0

//        System.out.println(0x1 << 34);// 当 移动的位数(k)超过 数据的总位数(w)的时候，按 k % w 计算移动的总位数
//
//
//        // java 中 负数采用补码的方式 表示
//        int i = 0x80000000;
//        System.out.println(i + "---" + String.format("%X", i));
//
//
//        //  基本数据类型： 小数据类型扩展成大数据类型； 在数据左边 按最高位的有效值填充
//        byte a = (byte) 0xf1;
//        int aa = a;
//
//        System.out.println(String.format("a=%X", a));
//        System.out.println(String.format("aa=%X", aa));
//
//        byte a_1 = 0x71;
//        int aa_1 = a_1;
//        System.out.println(String.format("a_1=%X", a_1));
//        System.out.println(String.format("aa_1=%X", aa_1));
//
//        // 基本数据类型：大数据转成小数据；进行截断
//        short a_2 = (short) 0x8002;
//        byte aa_2 = (byte) a_2;
//        System.out.println("a_2=" + a_2 + "====" + String.format("aa_2=%X", aa_2));


        byte a_3 = (byte) 0xf8;
        byte aa_3 = 0x08;
        byte aaa_3 = (byte) (a_3 * aa_3);
        System.out.println(aaa_3 + "===" + String.format("%X", aaa_3));

        /////// 除法所得的值，是 靠近 0 的；而不是 向下取整。对于负整数：(x + (1<<k) - 1) >> k
        byte a_4 = (byte) 0xfb;
        byte aa_4 = ((byte) 0xfb) / 2;
        byte aaa_4 = (byte) (a_4 >> 1);
        System.out.println(a_4);
        System.out.println(aa_4);
        System.out.println(aaa_4);

        byte a_5 = 0x05;
        byte aa_5 = (byte) (a_5 / 2);
        byte aaa_5 = (byte) (a_5 >> 1);
        System.out.println(aa_5);
        System.out.println(aaa_5);

        ///////
        double i = -1 / -1.1;

        System.out.println(i);


    }
}
