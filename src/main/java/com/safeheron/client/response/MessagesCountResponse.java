package com.safeheron.client.response;

import lombok.Data;


/**
 * @author safeheron
 */
@Data
public class MessagesCountResponse {
    /**
     * The number of failed webhook events to be re-pushed
     */
    private Integer messagesCount;
}
