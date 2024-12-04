package com.safeheron.client.cosigner;

import java.util.Arrays;
import java.util.List;

public enum CoSignerTypeEnum {

    TRANSACTION(Arrays.asList("TRANSACTION","TX_TRANSACTION","CONNECT_TX_SEND","TX_CONNECT_TX_SEND","TRANSACTION_BATCH_UTXO")),
    MPC_SIGN(Arrays.asList("MPC_SIGN")),
    WEB3_SIGN(Arrays.asList("ETH_SIGN", "PERSONAL_SIGN","ETH_SIGN_TYPED_DATA","ETH_SIGNTRANSACTION"));

    CoSignerTypeEnum(List<String> typeList) {
        this.typeList = typeList;
    }

    private final List<String> typeList;

    public List<String> getTypeList() {
        return this.typeList;
    }
}

