package com.safeheron.client.response;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class PageResult<T> {
    /**
     * Page number
     */
    private Long pageNumber;

    /**
     * The number of bars per page
     */
    private Long pageSize;

    /**
     * Total number of records
     */
    private Long totalElements;

    /**
     * Data lists per page
     */
    private List<T> content;
}
