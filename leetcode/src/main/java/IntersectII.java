//给定两个数组，编写一个函数来计算它们的交集。
//
//
//
// 示例 1：
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
//
//
// 示例 2:
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9]
//
//
//
// 说明：
//
//
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
// 我们可以不考虑输出结果的顺序。
//
//
// 进阶：
//
//
// 如果给定的数组已经排好序呢？你将如何优化你的算法？
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
//
// Related Topics 排序 哈希表 双指针 二分查找

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectII {


    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }

        Map<Integer, Integer> s1 = new HashMap<>();
        Map<Integer, Integer> s2 = new HashMap<>();

        if (nums1.length < nums2.length) {
            intersect(nums2, nums1);
        }

        for (int value : nums1) {
            s1.put(value, s1.getOrDefault(value, 0) + 1);
        }

        for (int value : nums2) {
            s2.put(value, s2.getOrDefault(value, 0) + 1);
        }

        List<Integer> r = new ArrayList<Integer>();
        for (Integer t : s1.keySet()) {
            if (s2.containsKey(t)) {
                int count = Math.min(s1.get(t), s2.get(t));
                for (int i = 0; i < count; i++) {
                    r.add(t);
                }
            }
        }

        int[] result = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            result[i] = r.get(i);
        }

        return result;
    }

    public int[] intersect_1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }

        if (nums1.length < nums2.length) {
            return intersect_1(nums2, nums1);
        }

        Map<Integer, Integer> s1 = new HashMap<>();

        for (int value : nums1) {
            s1.put(value, s1.getOrDefault(value, 0) + 1);
        }

        List<Integer> r = new ArrayList<>();

        for (int t : nums2) {
            if (s1.containsKey(t)) {
                r.add(t);
                int count = s1.get(t) - 1;
                s1.put(t, count);
                if (count == 0) {
                    s1.remove(t);
                }
            }
        }

        int[] result = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            result[i] = r.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        IntersectII intersection = new IntersectII();
        Intersection.print(intersection.intersect_1(new int[]{1,2,2,1}, new int[]{2}));
        Intersection.print(intersection.intersect_1(new int[]{1,2,2,1}, new int[]{2,2,2}));
        Intersection.print(intersection.intersect_1(new int[]{4,9,5}, new int[]{9,4,9,8,4}));
    }
}
