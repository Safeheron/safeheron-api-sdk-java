package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class AmlCheckerRequestResponse {

    /**
     * Risk assessment request ID. AML risk assessment is processed asynchronously. Please use the Retrieve AML Risk Assessment Result interface, passing in the requestId, to query the assessment result.
     */
    private String requestId;
}
