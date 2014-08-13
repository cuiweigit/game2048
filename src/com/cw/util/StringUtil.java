package com.cw.util;


public class StringUtil {

    public static boolean isBlankOrNull(String target) {
        if (null == target || "".equals(target))
            return true;
        return false;
    }

    public static boolean isBlankOrNullOrNullStr(String target) {
        return (isBlankOrNull(target) || "null".equals(target));
    }

    public static boolean isNotBlankOrNullOrNullStr(String target) {
        return !isBlankOrNullOrNullStr(target);
    }

    public static boolean isNotBlankOrNull(String target) {
        if (null == target || "".equals(target.trim()))
            return false;
        return true;
    }

}
