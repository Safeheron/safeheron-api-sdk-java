package com.safeheron.client.config;

public enum ActionEnum {

    APPROVE("APPROVE"),
    REJECT("REJECT");

    ActionEnum(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return this.code;
    }

    public static ActionEnum valueByCode(String code) {
        for (ActionEnum item : ActionEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}

