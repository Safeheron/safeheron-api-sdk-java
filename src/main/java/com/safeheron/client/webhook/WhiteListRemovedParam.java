package com.safeheron.client.webhook;

import lombok.Data;

@Data
public class WhiteListRemovedParam {
    private String whitelistKey;
    private String whitelistName;
    private String chainType;
    private String address;
    private String memo;
    private String previousWhitelistName;
    private String previousAddress;
    private Long approvalTime;
}
