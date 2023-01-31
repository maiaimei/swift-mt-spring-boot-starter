package cn.maiaimei.framework.swift.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Field25FTextPurposeEnum {
    DRAFT,
    FINAL;

    public static boolean matches(String value) {
        for (Field25FTextPurposeEnum item : Field25FTextPurposeEnum.values()) {
            if (item.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getCodes() {
        return Arrays.stream(Field25FTextPurposeEnum.values()).map(Enum::name).collect(Collectors.toList());
    }
}
