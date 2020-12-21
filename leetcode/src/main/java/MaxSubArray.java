//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例:
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//
//
// 进阶:
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
// Related Topics 数组 分治算法 动态规划


/**
 * 状态表示：dp[n] 表示前n个元素的最大和
 * 状态转移：dp[n] = max{dp[n-1] + n, arr[n]}, dp[0] = arr[0];
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] a = new int[nums.length];
        int max = Integer.MIN_VALUE;
        a[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (a[i - 1] < 0) {
                a[i] = nums[i];
            } else {
                a[i] = nums[i] + a[i - 1];
            }
            max = Math.max(max, a[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        System.out.println(maxSubArray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
