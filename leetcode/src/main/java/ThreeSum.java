//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例：
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//
// Related Topics 数组 双指针

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);
        // -4 -1 -1 0 1 2

        for (int k = 0; k < nums.length - 2; k++) {// 0   1
            if (nums[k] > 0) {
                continue;
            }

            if (k > 0 && nums[k] == nums[k - 1]) {// 只要a不重复，则b、c肯定不会重复
                continue;
            }

            int left = k + 1;// 1
            int right = nums.length - 1;// 2

            while (left < right) {
                if (left > k + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }

                if (-nums[k] == nums[left] + nums[right]) {
                    result.add(Arrays.asList(nums[k], nums[left], nums[right]));
                    left++;
                    right--;
                    continue;
                }

                if (nums[left] + nums[right] > -nums[k]) {
                    right--;
                }

                if (nums[left] + nums[right] < -nums[k]) {
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {0,0,0};
//        int[] nums = {-2,0,0,2,2};
        int[] nums = {-1,0,1,2,-1,-4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums);
        System.out.println(lists);
    }
}
