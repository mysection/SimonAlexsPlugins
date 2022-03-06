package com.simonalexs.tools.plugins.utils;

/**
 * @ClassName: StringUtil
 * @Description: TODO-wcy
 * @Author: wcy
 * @Date: 2022/3/4 11:29
 * @Version: 1.0
 */
public class StringUtil {
    public static String replaceLast(String text, String strToReplace, String replaceWithThis) {
        return text.replaceFirst("(?s)" + strToReplace + "(?!.*?" + strToReplace + ")", replaceWithThis);
    }
}
