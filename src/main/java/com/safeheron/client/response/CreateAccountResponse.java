package com.safeheron.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateAccountResponse {

    /**
     * Wallet account key
     */
    private String accountKey;

    /**
     * Account public key info
     */
    private List<PubKey> pubKeys;

    /**
     * Account public key info
     */
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
