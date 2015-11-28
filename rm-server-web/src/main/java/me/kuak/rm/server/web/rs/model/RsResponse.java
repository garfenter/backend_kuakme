package me.kuak.rm.server.web.rs.model;

import java.io.Serializable;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 * @param <T>
 */
public class RsResponse<T extends Serializable> implements Serializable {

    public static final int CODE_OK = 0;
    public static final int CODE_SERVICE_ERROR = 100;
    public static final int CODE_UNKNOWN_ERROR = 500;
    public static final int CODE_USER_ERROR = 400;
    public static final int CODE_VALIDATION_ERROR = 300;

    private final T result;
    private int code;
    private String description;

    public RsResponse(T result) {
        this.result = result;
        this.code = CODE_OK;
    }

    public RsResponse(T result, int code, String description) {
        this.result = result;
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getResult() {
        return result;
    }

}
