package com.innovate.media.utils;

/**
 * 通用返回结果集
 *
 * @author sjh
 */
public class BaseResult {
    /**
     * 状态码
     */
    private String status_code;
    /**
     * 信息
     */
    private String message;

    public BaseResult() {
    }

    public BaseResult(String status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public static BaseResult result(String status_code, String message) {
        return new BaseResult(status_code, message);
    }


    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
