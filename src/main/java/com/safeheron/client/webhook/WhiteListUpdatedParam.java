package com.safeheron.client.webhook;

import lombok.Data;

@Data
public class WhiteListUpdatedParam {
    private String whitelistKey;
    private String whitelistName;
    private String chainType;
    private String address;
    private String memo;
    private String note;
    private String previousWhitelistName;
    private String previousAddress;
    private String previousMemo;
    private String previousNote;
    private Long approvalTime;
}
