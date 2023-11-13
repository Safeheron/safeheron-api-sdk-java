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
    private List<Entry> dataList;

    @Data
    public static class Entry {
        private String data;
        private String note;
    }

}

