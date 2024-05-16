package com.safeheron.client.config;

public enum RSATypeEnum {

    RSA("RSA"), ECB_OAEP( "ECB_OAEP");

    RSATypeEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return this.code;
    }

    public static RSATypeEnum valueByCode(String code) {
        for (RSATypeEnum item : RSATypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}

