//给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
//
// 示例：
//
// 输入: S = "ADOBECODEBANC", T = "ABC"
//输出: "BANC"
//
// 说明：
//
//
// 如果 S 中不存这样的子串，则返回空字符串 ""。
// 如果 S 中存在这样的子串，我们保证它是唯一的答案。
//
// Related Topics 哈希表 双指针 字符串 Sliding Window

import java.util.HashMap;
import java.util.Map;

public class MinWindow_1 {

    public String minWindow(String s, String t) {
        if (s == null || "".equals(s) || t == null || "".equals(t)) {
            return "";
        }

        int start = -1, length = Integer.MAX_VALUE;// 结果集

        int left = 0, right = 0;// 左、右索引

        Map<Character, Integer> t_m = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            t_m.put(t.charAt(i), t_m.getOrDefault(t.charAt(i), 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();// 窗口内满足条件的字符
        int count = 0;// 满足条件的字符数
        while (right < s.length()) {
            char c = s.charAt(right);
            if (t_m.containsKey(c)) {// 窗口中的数据
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) <= t_m.get(c)) {// 去重
                    count++;// 增加满足的字符数
                }
            }

            right++;

            while (count == t.length()) {// 窗口中的数据满足条件(窗口中包含全部的t字符串)，则缩小窗口，查找最优解
                int w_size = right - left; // 窗口大小
                if (w_size < length) {// 记录最优解
                    start = left;
                    length = w_size;
                }

                char move = s.charAt(left);// 左窗口移除的数据
                if (window.get(move) != null && window.get(move) > 0) {// 移除的数据在满足条件中，需要修改窗口内的数据
                    if (window.get(move).equals(t_m.get(move))) {
                        count--;// 减少满足条件的字符数
                    }
                    window.put(move, window.get(move) - 1);
                }
                left++;
            }
        }
        return start == -1 ? "" : s.substring(start, start + length);
    }

    public static void main(String[] args) {
        MinWindow_1 minWindow_1 = new MinWindow_1();
//        System.out.println(minWindow_1.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow_1.minWindow("aa", "aa"));
    }
}
