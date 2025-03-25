package com.safeheron.client.cosigner;

import lombok.Data;

@Data
public class CoSignerBizContentV3 {
    private String approvalId;
    private String type;
    private Object detail;
}
