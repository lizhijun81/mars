//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1：
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//
//
// 示例 2：
//
// 输入: "cbbd"
//输出: "bb"
//
// Related Topics 字符串 动态规划

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int max = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < s.length(); i++) {
            int length1 = getCenterLength(s, i);
            int length2 = getCenterLength(s, i, i + 1);
            int length = Math.max(length1, length2);
            if (length > max) {
                max = length;

                start = i - ((length - 1) / 2);// 0 1   (0 - 1 / 2) = 0
                end = i + ((length) / 2);//  0 1   (0 + 2 / 2) = 0
            }
        }
        return s.substring(start, end + 1);
    }

    private int getCenterLength(String s, int index) {
        int left = index;
        int right = index;

        while (left - 1 >= 0 && right + 1 < s.length() && s.charAt(left - 1) == s.charAt(right + 1)) {
            left--;
            right++;
        }
        return right - left + 1;
    }

    private int getCenterLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; // -1
            right++;// 2   2 + 1 - 1 = 2;  1 - 0 - 1 = 0
        }

        return right - left - 1;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
//        System.out.println(longestPalindrome.longestPalindrome("1123211"));
        System.out.println(longestPalindrome.longestPalindrome("cbbd"));
    }
}
