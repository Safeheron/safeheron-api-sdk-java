package com.safeheron.demo.api.mpcsign;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class RetrieveMpcSignRequest {
    private String customerRefId;
    private String txKey;

}
