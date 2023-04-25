package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class BatchCreateAccountResponse {
    /**
     * Wallet account key
     */
    private List<String> accountKeyList;
}
