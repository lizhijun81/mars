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


import java.util.*;

public class MinWindow {
    public String minWindow(String s, String t) {
        if (s == null || "".equals(s) || t == null || "".equals(t)) {
            return "";
        }

        Map<Character, List<Integer>> content = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            List<Integer> index = content.get(s.charAt(i));
            if (index == null) {
                index = new ArrayList<>();
            }
            index.add(i);
            content.put(s.charAt(i), index);
        }

        int x_l = Integer.MAX_VALUE, x_r = -1;
        int i_l = Integer.MAX_VALUE, i_r = -1;
        for (int j = 0; j < t.length(); j++) {
            char c = t.charAt(j);
            if (content.containsKey(c)) {
                List<Integer> index = content.get(c);
                x_l = Math.min(x_l, index.get(index.size() - 1));
                x_r = Math.max(x_r, index.get(index.size() - 1));

                i_l = Math.min(i_l, index.get(0));
                i_r = Math.max(i_r, index.get(0));
            } else {
                return "";
            }
            // a 1 b 2 c 3 d 4    = bc
            //
        }

        if (x_r - x_l > i_r - i_l) {
            return s.substring(i_l, i_r);
        }
        return s.substring(x_l, x_r);
    }
}
