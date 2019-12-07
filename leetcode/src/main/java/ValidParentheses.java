//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[]}"
//输出: true
//

import java.util.LinkedList;

public class ValidParentheses {

    public static boolean isValid(String s) {
        LinkedList<Character> list = new LinkedList<>();
        char[] cs = s.toCharArray();

        for (char c : cs) {

            if (c == '(') {
                list.addFirst(c);
            }

            if (c == '[') {
                list.addFirst(c);
            }

            if (c == '{') {
                list.addFirst(c);
            }

            if (c == ')') {
                if (list.size() == 0) {
                    return false;
                }

                if (list.getFirst() == '(') {
                    list.removeFirst();
                } else {
                    return false;
                }
            }

            if (c == ']') {
                if (list.size() == 0) {
                    return false;
                }

                if (list.getFirst() == '[') {
                    list.removeFirst();
                } else {
                    return false;
                }
            }

            if (c == '}') {
                if (list.size() == 0) {
                    return false;
                }

                if (list.getFirst() == '{') {
                    list.removeFirst();
                } else {
                    return false;
                }
            }
        }
        return list.size() == 0;
    }

    public static void main(String[] args) {
        System.out.println(isValid("{[]}[]{}"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("]"));
        System.out.println(isValid("{[}]"));
    }

}
