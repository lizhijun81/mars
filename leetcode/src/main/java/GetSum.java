//不使用运算符 + 和 - ，计算两整数 a 、b 之和。
//
// 示例 1:
//
// 输入: a = 1, b = 2
//输出: 3
//
//
// 示例 2:
//
// 输入: a = -2, b = 3
//输出: 1
// Related Topics 位运算

public class GetSum {
    public int getSum(int a, int b) {
        if (a==0) return b;
        if (b==0) return a;
        int lower;
        int carrier;
        while (true) {
            lower = a^b;    // 计算低位
            carrier = a&b;  // 计算进位
            if (carrier==0) break;
            a = lower;
            b = carrier<<1;
        }
        return lower;
    }

    public static void main(String[] args) {// 1111 & 0011 = 0010   0000
        GetSum getSum = new GetSum();
        System.out.println(getSum.getSum(1,2));// 0001|0010=0011
        System.out.println(getSum.getSum(-1,-2));// 1111*1110=1101 ;  1111 异或 1110 = 0001 | 1110

        System.out.println(~(-1));
//        System.out.println(getSum.getSum(-1,2));
    }
}
