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

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CheckInclusion {
//    public boolean checkInclusion(String s1, String s2) {
//        if (s1 == null || s1.equals("") || s2 == null || s2.equals("")) {
//            return false;
//        }
//
//        Map<Character, Integer> needs = new HashMap<>();
//        for (int i = 0; i < s1.length(); i++) {
//            needs.put(s1.charAt(i), needs.getOrDefault(s1.charAt(i), 0) + 1);
//        }
//
//        int left = 0, right = s1.length();
//        while(right <= s2.length()) {
//            boolean condition = true;
//            // window 内的每一个数据都存在 s2 中: 窗口中每个字符的数量等于s2中每个字符的数量
//            Map<Character, Integer> window = new HashMap<>();
//            for (int l = left; l < right; l++) {
//                window.put(s2.charAt(l), window.getOrDefault(s2.charAt(l), 0) + 1);
//            }
//
//            for (Character character : window.keySet()) {
//                if (!needs.containsKey(character)) {
//                    condition = false;
//                    break;
//                }
//                if (!needs.get(character).equals(window.get(character))) {
//                    condition = false;
//                    break;
//                }
//            }
//
//            if (condition) {// 都存在则返回
//                return true;
//            }
//
//            // 不存在则窗口后移
//            left++;
//            right++;
//        }
//        return false;
//    }


    public boolean checkInclusion_1(String s1, String s2) {
        if (s1 == null || s1.equals("") || s2 == null || s2.equals("")) {
            return false;
        }

        Map<Character, Integer> needs = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            needs.put(s1.charAt(i), needs.getOrDefault(s1.charAt(i), 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int count = 0;
        while(right < s2.length()) {
            char c = s2.charAt(right);
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) <= needs.get(c)) {// 增加窗口内满足条件的字符数量
                    count++;
                }
            }

            right++;

            if (right - left >= s1.length()) {// 当窗口大于固定窗口时，窗口缩小
                if (right - left == s1.length() && count == s1.length()) {// 窗口大小刚好等于固定窗口大小  并且  字符条件满足
                    return true;
                }

                char l = s2.charAt(left);
                if (needs.containsKey(l)) {
                    window.put(l, window.getOrDefault(l, 0) - 1);
                    if (window.get(l) < needs.get(l)) {// 只有相等的情况下才减少窗口内满足条件的数量
                        count--;
                    }
                }
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckInclusion checkInclusion = new CheckInclusion();
        System.out.println(checkInclusion.checkInclusion_1("hello", "ooolleoooleh"));
        System.out.println(checkInclusion.checkInclusion_1("aaa", "aaa"));
    }
}
