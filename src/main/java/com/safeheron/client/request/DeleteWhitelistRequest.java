package com.safeheron.client.request;

import lombok.Data;

/**
 * Delete a Whitelist
 *
 * @author safeheron
 */
@Data
public class DeleteWhitelistRequest {

    /**
     * Whitelist unique identifier
     */
    private String whitelistKey;
}
