package com.safeheron.client.cosigner;

import lombok.Data;

import java.util.List;

@Data
public class MPCSignApproval {

    private String txKey;

    private String transactionStatus;

    private String transactionSubStatus;

    private Long createTime;

    private String sourceAccountKey;

    private String note;

    private String auditUserKey;

    private String createdByUserKey;

    private String customerRefId;

    private String customerExt1;

    private String customerExt2;

    private String signAlg;

    private List<SignSourceAccount> hashs;

    private List<DataSourceAccount> dataList;

    private List<String> hashList;

    @Data
    public static class SignSourceAccount {

        private String sourceAccountKey;

        private String hash;
    }

    @Data
    public static class DataSourceAccount {

        private String sourceAccountKey;

        private String data;
    }
}
