package com.safeheron.client.webhook;

import lombok.Data;

@Data
public class WebHookBizContent {
    private String eventType;
    private Object eventDetail;
}
