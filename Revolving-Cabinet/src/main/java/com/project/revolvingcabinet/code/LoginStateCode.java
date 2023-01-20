package com.project.revolvingcabinet.code;

/**
 * 登录状态code
 */
public enum LoginStateCode {
    LOGIN("1", "未登录"),
    LOGOUT("0", "登陆中");

    private String code;
    private String message;

    LoginStateCode(String code, String message) {
        this.code = code;
        message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
