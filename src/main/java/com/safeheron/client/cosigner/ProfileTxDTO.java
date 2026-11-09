package com.safeheron.client.cosigner;

import lombok.Data;

@Data
public class ProfileTxDTO {
    /**
     * Not included in the API Co-Signer callback, always null
     */
    @Deprecated
    private String workspacePortrait;

    /**
     * Not included in the API Co-Signer callback, always null. Use connectId instead
     */
    @Deprecated
    private String profileKey;

    private String name;

    /**
     * Not included in the API Co-Signer callback, always null
     */
    @Deprecated
    private String accountKey;

    /**
     * Not included in the API Co-Signer callback, always null
     */
    @Deprecated
    private String accountName;

    /**
     * Not included in the API Co-Signer callback, always null
     */
    @Deprecated
    private String address;

    private String connectId;

}
