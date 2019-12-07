

//Given a 32-bit signed integer, reverse digits of an integer.
//
// Example 1:
//
//
//Input: 123
//Output: 321
//
//
// Example 2:
//
//
//Input: -123
//Output: -321
//
//
// Example 3:
//
//
//Input: 120
//Output: 21
//
//
// Note:
//Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
//

public class ReverseInteger {

    public int reverse(int x) {
        int flag = 1;
        if (x < 0) {
            flag = -1;
        }

        x = Math.abs(x);

        long result = 0;

        for (; x > 0 ; ) {
            int mod = x % 10;
            x = x / 10;
            result = (result * 10) + mod;
        }

        result *= flag;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(123));
        System.out.println(Integer.MAX_VALUE);

        // 2147483647
        // 1534236469
    }
}
