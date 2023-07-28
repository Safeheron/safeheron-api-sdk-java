/**
 * Copyright © 2022 Safeheron All Rights Reserved
 */
package com.safeheron.client.webhook;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class TransactionRawData {
    private String note;
    private String data;
    private String sig;
}
