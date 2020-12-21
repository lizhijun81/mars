//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
//
//
// 示例 1:
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3
//解释: 11 = 5 + 5 + 1
//
// 示例 2:
//
// 输入: coins = [2], amount = 3
//输出: -1
//
//
//
// 说明:
//你可以认为每种硬币的数量是无限的。
// Related Topics 动态规划


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [1, 2, 5] ,
 * F(1)=1;
 * F(2)=1;
 * F(3)=min{F(1)+F(2)=2}
 * F(6)=2;
 * F(7)=2;
 * F(8)=3;
 * F(9)=2;
 * F(10)=2;
 * F(11)=3;
 * F(12)=min{F(11)+F(1)=4,F(10)+F(2)=3,F(7)+F(5)=3]}=3
 * F(14)=4;
 * F(15)=3;
 * F(16)=min{F(15)+F(1)=4,F(14)+F(2)=5,F(11)+F(5)=4}=4;
 * F(17)=min{F(16)+F(1)=5,F(15)+F(2)=4,F(12)+F(5)=4}=4
 *
 * [2,5]  11;   1+c(9)+c(7)+c(5)+c(3)+c(1)+c(-1)
 */
public class CoinChange {
    Map<Integer, Integer> dp = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        if (dp.get(amount) != null) {
            return dp.get(amount);
        }

        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            int remain = amount - coin;
            int c = coinChange(coins, remain);// 子问题的解
            if (c >= 0) {// 子问题是否有效
                c++;
                count = Math.min(count, c);// 多个子问题之间取最优解
            }
        }

        dp.put(amount, count == Integer.MAX_VALUE ? -1 : count);
        return dp.get(amount);
    }

    public int coinChange_1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i < coins.length; i++) {
                if (j - coins[i] < 0) {// 无解
                    continue;
                }
                dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);// 从已有的子问题推导出问题的解
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(new int[]{1,2,5}, 11));
    }
}
