package com.safeheron.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author safeheron
 */
@Data
@JsonIgnoreProperties
public class ApiResult<T> implements Serializable {
    /**
     * Response result code
     */
    private Integer code;

    /**
     * Response result description
     */
    private String message;

    /**
     * Response timestamp, UNIX millisecond-format string
     */
    private String timestamp;

    /**
     * Signature data after signing response parameters by Safeheron API RSA Private Key
     */
    private String sig;

    /**
     * Encrypted data of random AES key by your API RSA Public Key
     */
    private String key;

    /**
     * AES-encrypted data of response parameters
     */
    private String bizContent;

    private String rsaType;

    private String aesType;

    /**
     * AES-decrypted data of response parameters
     */
    private T rawData;

    public ApiResult() {
    }

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis() + "";
    }

    public ApiResult(T rawData) {
        this.code = 200;
        this.rawData = rawData;
    }

    public static <T> ApiResult<T> defaultFailure() {
        return new ApiResult<T>(500, "internal error.");
    }

    public static <T> ApiResult<T> success(T rawData) {
        return new ApiResult<T>(rawData);
    }

}
