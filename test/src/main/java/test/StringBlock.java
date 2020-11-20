package test;

import com.google.common.base.Strings;


/**
 * erty....hjk..
 */
public class StringBlock {
    public static void main(String[] args) {
        String str = "erty....hjk..";

        String revStr = rev(str);

        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < revStr.length(); i++) {
            startIndex++;
            if (revStr.charAt(i) != '.') {
                for (int j = startIndex; j < revStr.length(); j++) {
                    if (revStr.charAt(j) == '.' || j == revStr.length() - 1) {
                        endIndex = j;
                        break;
                    }
                }

                String subStr = revStr.substring(startIndex, endIndex);
                String revSubStr = rev(subStr);

                revStr = revStr.replace(subStr, revSubStr);
                i = endIndex;
                startIndex = ++endIndex;
            }
        }

        System.out.println(revStr);
    }

    public static String rev(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }

        char[] chars = str.toCharArray();

        char[] revChars = new char[chars.length];

        for (int i = chars.length - 1; i >= 0; i--) {
            revChars[chars.length - i - 1] = chars[i];
        }
        return new String(revChars);
    }
}
