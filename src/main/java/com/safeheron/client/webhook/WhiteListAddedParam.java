package com.safeheron.client.webhook;

import lombok.Data;

@Data
public class WhiteListAddedParam {
    private String whitelistKey;
    private String whitelistName;
    private String chainType;
    private String address;
    private Long approvalTime;
}
