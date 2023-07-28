package com.safeheron.client.webhook;

import java.util.Arrays;
import java.util.List;

public enum WebhookEventTypeEnum {

    TRANSACTION(Arrays.asList("TRANSACTION_CREATED", "TRANSACTION_STATUS_CHANGED")),
    MPC_SIGN(Arrays.asList("MPC_SIGN_CREATED", "MPC_SIGN_STATUS_CHANGED")),
    WEB3_SIGN(Arrays.asList("WEB3_SIGN_CREATED", "WEB3_SIGN_STATUS_CHANGED"));

    WebhookEventTypeEnum(List<String> eventTypeList) {
        this.eventTypeList = eventTypeList;
    }

    private final List<String> eventTypeList;

    public List<String> getEventTypeList() {
        return this.eventTypeList;
    }
}

