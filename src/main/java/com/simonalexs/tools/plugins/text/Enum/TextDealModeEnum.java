package com.simonalexs.tools.plugins.text.Enum;

/**
 * @ClassName: TextDealModeEnum
 * @Description: TODO-wcy
 * @Author: wcy
 * @Date: 2022/3/5 15:44
 * @Version: 1.0
 */
public enum TextDealModeEnum {
    REPLACE(1, "REPLACE", "REPLACE"),
    LINE_COPY(2, "LINE_COPY", "LINE_COPY");

    private int code;

    private String name;

    private String desc;

    TextDealModeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static TextDealModeEnum getTextDealModeByName(String name) {
        for (TextDealModeEnum textDealModeEnum : TextDealModeEnum.values()) {
            if (textDealModeEnum.getName().equals(name)) {
                return textDealModeEnum;
            }
        }
        return null;
    }

    public static Boolean existsByName(String name) {
        for (TextDealModeEnum textDealModeEnum : TextDealModeEnum.values()) {
            if (textDealModeEnum.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
