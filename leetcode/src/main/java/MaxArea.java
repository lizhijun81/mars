//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器。
//
//
//
// 示例 1：
//
//
//
//
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
// 示例 2：
//
//
//输入：height = [1,1]
//输出：1
//
//
// 示例 3：
//
//
//输入：height = [4,3,2,1,4]
//输出：16
//
//
// 示例 4：
//
//
//输入：height = [1,2,1]
//输出：2
//
//
//
//
// 提示：
//
//
// n = height.length
// 2 <= n <= 3 * 104
// 0 <= height[i] <= 3 * 104
//
// Related Topics 数组 双指针


import java.util.HashMap;
import java.util.Map;

public class MaxArea {

    Map<String, Integer> cache = new HashMap<>();

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int x = j - i;
                int y = Math.min(height[i], height[j]);
                Integer ca = cache.computeIfAbsent(i + "_" + j, k -> x * y);
                max = Math.max(ca, max);
            }
        }
        return max;
    }

    public int maxArea_1(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int x = right - left;
            int y = Math.min(height[left], height[right]);
            max = Math.max(max, x * y);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea.maxArea(arr));
        System.out.println(maxArea.maxArea_1(arr));
    }
}
