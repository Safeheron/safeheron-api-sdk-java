package com.safeheron.client.webhook;

import lombok.Data;

@Data
public class WhiteListRemovedParam {
    private String whitelistKey;
    private String whitelistName;
    private String chainType;
    private String address;
    private String memo;
    private String note;

    /**
     * Not included in the WHITELIST_REMOVED event, always null
     */
    @Deprecated
    private String previousWhitelistName;

    /**
     * Not included in the WHITELIST_REMOVED event, always null
     */
    @Deprecated
    private String previousAddress;

    /**
     * Not included in the WHITELIST_REMOVED event, always null
     */
    @Deprecated
    private Long approvalTime;
}
