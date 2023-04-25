package com.safeheron.client.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Cancel Signature
 *
 * @author safeheron
 */
@Data
public class CancelWeb3SignRequest {
    /**
     * Transaction key
     */
    private String txKey;
}
