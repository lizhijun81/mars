import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tools {

    private static final String PHONE_PATTERN = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    //新能源 + 普通
    private static final String LICENCE_PATTERN = "^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|" +
            "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})$";

    private Tools() {}

    /**
     * 验证手机号
     * @param phone 手机号
     */
    public static boolean checkPhone(String phone) {
        if (Strings.isNullOrEmpty(phone) || phone.length() != 11) {
            return false;
        }

        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean checkLicence(String licence) {
        if (Strings.isNullOrEmpty(licence)){
            return false;
        }
        Pattern pattern = Pattern.compile(LICENCE_PATTERN);
        Matcher matcher = pattern.matcher(licence.toUpperCase());
        return matcher.matches();
    }

    public static void main(String[] args) {
//        boolean b = checkLicence("陕a12314");
//        System.out.println(b);

//        System.out.println(checkPhone("18618394168"));
    }
}
