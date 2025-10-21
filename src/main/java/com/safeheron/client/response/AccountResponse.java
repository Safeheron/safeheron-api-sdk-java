package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */

@Data
public class AccountResponse {
    /**
     * Account Key, the only account identifier
     */
    private String accountKey;

    /**
     * Account name
     */
    private String accountName;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;

    /**
     * Account index
     */
    private Long accountIndex;

    /**
     * Account type
     * VAULT_ACCOUNT: Vault account
     */
    private String accountType;

    /**
     * Account tag
     */
    private String accountTag;

    /**
     * Whether display in Safeheron Console
     * True: not display
     * False: display
     */
    private Boolean hiddenOnUI;

    /**
     * When initiating a transaction, Gas Service automatically supplements the Gas fee for wallets where autoFuel is true
     */
    private Boolean autoFuel;

    /**
     * Wallet's archive status in Safeheron App and Web Console
     * True: Archived
     * False: Unarchived
     */
    private Boolean archived;

    /**
     * Account balance, in USD when retrieve
     */
    private String usdBalance;

    /**
     * Account public key info
     */
    private List<PubKey> pubKeys;

    @Data
    @NoArgsConstructor
    public static class PubKey{
        /**
         * Signature algorithm, currently supports secp256k1
         */
        private String signAlg;

        /**
         * Account compressed public key
         */
        private String pubKey;
    }
}
