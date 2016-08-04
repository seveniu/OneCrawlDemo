package com.seveniu.def;

public enum FieldType {
    //HTML_TEXT 文本
    // TARGET_LINK 跳转链接
    // NEXT_LINK 下一页链接
    // TEXT_LINK 抓取的链接文本
    HTML_TEXT(1), TARGET_LINK(2), NEXT_LINK(3), TEXT_LINK(4), PURE_TEXT(5);
    private int value;

    FieldType(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public static FieldType getType(int value) {
        if (value == FieldType.HTML_TEXT.getValue()) {
            return HTML_TEXT;
        } else if (value == FieldType.TARGET_LINK.getValue()) {
            return TARGET_LINK;
        } else if (value == FieldType.NEXT_LINK.getValue()) {
            return NEXT_LINK;
        } else if (value == FieldType.TEXT_LINK.getValue()) {
            return TEXT_LINK;
        } else if (value == FieldType.PURE_TEXT.getValue()) {
            return PURE_TEXT;
        }

        return null;
    }
}