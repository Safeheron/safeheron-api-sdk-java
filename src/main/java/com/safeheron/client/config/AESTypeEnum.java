package com.safeheron.client.config;

public enum AESTypeEnum {

    CBC("CBC_PKCS7PADDING"),
    GCM("GCM_NOPADDING");

    AESTypeEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return this.code;
    }

    public static AESTypeEnum valueByCode(String code) {
        for (AESTypeEnum item : AESTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}

