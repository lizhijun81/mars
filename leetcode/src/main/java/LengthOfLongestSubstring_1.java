//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// Related Topics 哈希表 双指针 字符串 Sliding Window


import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring_1 {

    public int lengthOfLongestSubstring_1(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }

        Set<Character> window = new HashSet<>();
        int left = 0, right = 0;
        int max = Integer.MIN_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);

            right++;// 增加窗口
            if (!window.contains(c)) {// 缩小窗口的条件
                window.add(c);
            } else {
                left++;// 缩小窗口
                right = left;// 回溯
                window.clear();
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    /**
     * pwwkew
     *
     * cur_len += 1
     * while s[i] in lookup:
     *     lookup.remove(s[left])
     *     left += 1
     *     cur_len -= 1
     * if cur_len > max_len:max_len = cur_len
     * lookup.add(s[i])
     * @param s
     * @return
     */

    public int lengthOfLongestSubstring_2(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }

        Set<Character> window = new HashSet<>();
        int left = 0, right = 0;
        int max = Integer.MIN_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;// 增加窗口

            while (window.contains(c)) {// p w
                window.remove(s.charAt(left));
                left++;
            }
            window.add(c);

            max = Math.max(max, right - left);
        }
        return max;
    }

    public static void main(String[] args) {// c=pw  cl=2, r=2;    cl=2, r=3   c=pw;    cl=3, r=4   c=pwk;  cl=4, r=5   c=pwke;
        LengthOfLongestSubstring_1 lengthOfLongestSubstring = new LengthOfLongestSubstring_1();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring_2("pwwkew"));
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring_2("dvdfe"));
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring_2("abcabcbb"));
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring_2("1111111"));
    }
// left = 0; right = 0;
// a, r=1
// b, r=2
// b, le=2-0=2, l=2, r=3
// a, r=4

}
