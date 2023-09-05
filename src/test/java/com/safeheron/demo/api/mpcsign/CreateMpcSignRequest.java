package com.safeheron.demo.api.mpcsign;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateMpcSignRequest {
    private String customerRefId;
    private String sourceAccountKey;
    private String signAlg;
    private List<Hash> hashs;

    @Data
    public static class Hash {
        private String hash;
        private String note;
    }

}

