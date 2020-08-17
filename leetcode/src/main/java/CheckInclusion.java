//给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
//
// 换句话说，第一个字符串的排列之一是第二个字符串的子串。
//
// 示例1:
//
//
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
//
//
//
//
// 示例2:
//
//
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
//
//
//
//
// 注意：
//
//
// 输入的字符串只包含小写字母
// 两个字符串的长度都在 [1, 10,000] 之间
//
// Related Topics 双指针 Sliding Window

import java.util.HashMap;
import java.util.Map;

public class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.equals("") || s2 == null || s2.equals("")) {
            return false;
        }

        Map<Character, Integer> needs = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            needs.put(s1.charAt(i), needs.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int left = 0, right = s1.length();
        while(right <= s2.length()) {
            boolean condition = true;
            // window 内的每一个数据都存在 s2 中: 窗口中每个字符的数量等于s2中每个字符的数量
            Map<Character, Integer> window = new HashMap<>();
            for (int l = left; l < right; l++) {
                window.put(s2.charAt(l), needs.getOrDefault(s2.charAt(l), 0) + 1);
            }

            for (Character character : window.keySet()) {
                condition = 
            }

            if (condition) {// 都存在则返回
                return true;
            }

            // 不存在则窗口后移
            left++;
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
        CheckInclusion checkInclusion = new CheckInclusion();
        System.out.println(checkInclusion.checkInclusion("ab", "eidboaoo"));
    }
}
