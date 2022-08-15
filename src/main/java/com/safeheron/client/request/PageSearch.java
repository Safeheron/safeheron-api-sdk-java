package com.safeheron.client.request;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class PageSearch {
    /**
     * Page number, start from 1 (default)
     */
    private Long pageNumber;

    /**
     * The number of bars per page, the default is 10, max is 100
     */
    private Long pageSize;
}
