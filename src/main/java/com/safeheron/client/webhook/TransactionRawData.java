/**
 * Copyright © 2022 Safeheron All Rights Reserved
 */
package com.safeheron.client.webhook;

import lombok.Data;

/**
 *
 */
@Data
public class TransactionRawData {
    private String note;
    private String data;
    private String sig;
}
