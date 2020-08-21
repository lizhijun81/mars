//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划

import java.util.HashMap;
import java.util.Map;

/**
 * n=1, 1   1
 * n=2, 2   11,2
 * n=3, 3   111,12,21
 * n=4, 5   1111,121,211,112,22;  F(n)=F(n-1)+F(n-2); 从第三个台阶到第四个台阶有3种，从第二个台阶到第四个台阶有2种，总共5种；
 * n=5, 8   11111,1211,2111,1121,221,1112,122,212
 */
public class ClimbStairs {
    Map<Integer, Integer> dp = new HashMap<>();

    /**
     * 递归从顶层往底层
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        if (dp.containsKey(n)) {
            return dp.get(n);
        } else {
            int t1 = climbStairs(n - 1);
            int t2 = climbStairs(n - 2);
            dp.put(n - 1, t1);
            return t1 + t2;
        }
    }

    /**
     * 从底层往顶层
     */
    public int climbStairs_1(int n) {
        int[] sum = new int[n];
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == 2) {
                sum[i-1] = i;
                continue;
            }
            sum[i-1] = sum[i-2] + sum[i-3];
        }
        return sum[n-1];
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.climbStairs_1(5));
        System.out.println(climbStairs.climbStairs_1(1));
        System.out.println(climbStairs.climbStairs_1(2));
        System.out.println(climbStairs.climbStairs_1(3));
        System.out.println(climbStairs.climbStairs_1(4));
    }
}
