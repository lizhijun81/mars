//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
// 示例 1:
//
// 输入: [3,3,5,0,0,3,1,4]
//输出: 6
//解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
//
// 示例 2:
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
// 示例 3:
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
// Related Topics 数组 动态规划

/**
 * F(m, m+1=n)=max{F(m)+F(m+1)}
 */
public class MaxProfitIII {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {// 第一次交易，选择第i天
            int max_2 = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                max_2 = Math.max(prices[i] - prices[j], max_2);
            }

            if (i >= prices.length - 2) {
                max = Math.max(max_2, max);
                continue;
            }

            int max_3 = Integer.MIN_VALUE;
            for (int j = i + 1; j < prices.length - 1; j++) {// 第二次交易，选择第i+1天买
                for (int k = j + 1; k < prices.length; k++) {
                    max_3 = Math.max(prices[k] - prices[j], max_3);
                }
            }

            if (max_3 > 0 && max_2 > 0) {
                max = Math.max(max_2 + max_3, max);
            }
            if (max_2 > 0 && max_3 < 0) {
                max = Math.max(max_2, max);
            }
            if (max_2 < 0 && max_3 > 0) {
                max = Math.max(max_3, max);
            }
        }

        return Math.max(max, 0);
    }

    public static void main(String[] args) {
        MaxProfitIII maxProfitIII = new MaxProfitIII();
        System.out.println(maxProfitIII.maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(maxProfitIII.maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(maxProfitIII.maxProfit(new int[]{7,6,4,3,1}));
        System.out.println(maxProfitIII.maxProfit(new int[]{1,7,4,2}));
    }
}
