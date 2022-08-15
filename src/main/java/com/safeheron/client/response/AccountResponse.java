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
     * Account index
     */
    private Long accountIndex;

    /**
     * Account type
     * VAULT_ACCOUNT: Vault account
     */
    private String accountType;

    /**
     * Whether display in Safeheron Console
     * True: not display
     * False: display
     */
    private Boolean hiddenOnUI;

    /**
     * Account balance, in USD when retrieve
     */
    private String usdBalance;

    /**
     * Frozen amount of this account, in USD when retrieve
     */
    private String frozenUsdBalance;

    /**
     * Frozen amount by AML of this account, in USD when retrieve
     */
    private String amlLockUsdBalance;

    /**
     * Account public key info
     */
    private List<PubKey> pubKeys;

    @Data
    @NoArgsConstructor
    static class PubKey{
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
