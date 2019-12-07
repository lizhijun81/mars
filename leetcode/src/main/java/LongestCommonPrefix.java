//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
//
// 所有输入只包含小写字母 a-z 。
//

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String first = strs[0];

        String result = null;
        int templateLength = 1;

        int startIndex = 0;

        int endIndex = first.length();

        while (startIndex + templateLength <= endIndex) {
            String template = first.substring(startIndex, startIndex + templateLength);

            boolean isTrue = true;
            for (int i = 0; i < strs.length; i++) {
                if (!strs[i].startsWith(template)) {
                    isTrue = false;
                }
            }

            if (isTrue) {
                if (result == null) {
                    result = template;
                }

                if (template.length() > result.length()) {
                    result = template;
                }
            }
//          else {
//                templateLength = 1;
//                startIndex++;
//            }
            templateLength++;
        }
        if (result == null) {
            return "";
        }
        return result;
    }

    public static void main(String[] args) {
//        String a = "3456789";
//        System.out.println(a.substring(0, a.length()));
        System.out.println(LongestCommonPrefix.longestCommonPrefix(new String[]{"flowerabcd","flowabce","flightabcf"}));
        System.out.println(LongestCommonPrefix.longestCommonPrefix(new String[]{"ca"}));
    }
}
