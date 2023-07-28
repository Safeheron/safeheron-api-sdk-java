package com.safeheron.client.webhook;

import lombok.Data;

import java.util.List;

@Data
public class MPCSignParam {
    private String txKey;
    private String transactionStatus;
    private String transactionSubStatus;
    private Long createTime;
    private String auditUserKey;
    private String createdByUserKey;
    private String customerRefId;
    private String customerExt1;
    private String customerExt2;
    private String signAlg;
    private String sourceAccountKey;
    private List<TransactionRawData> dataList;
}
