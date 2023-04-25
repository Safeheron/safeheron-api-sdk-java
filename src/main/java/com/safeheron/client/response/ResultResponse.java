package com.safeheron.client.response;

import lombok.Data;


/**
 * @author safeheron
 */
@Data
public class ResultResponse {
    /**
     * Execution result
     * True: success
     * False: fail
     */
    private Boolean result;
}
