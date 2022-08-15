package com.safeheron.client.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author safeheron
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SafeheronException extends RuntimeException {

    private Integer code;
    private String message;

    public SafeheronException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public SafeheronException(String message){
        super(message);
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
