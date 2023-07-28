package com.safeheron.client.webhook;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class WebHookResponse {
    /**
     * Webhook success response code: 200
     */
    private String code;
    /**
     * Returned information from Webhook, successful is fixed as SUCCESS
     */
    private String message;
}
