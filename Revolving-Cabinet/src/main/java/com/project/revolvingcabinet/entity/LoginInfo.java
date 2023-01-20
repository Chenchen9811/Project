package com.project.revolvingcabinet.entity;

import java.sql.Timestamp;

public class LoginInfo {
    private Long userId;
    private String loginState;
    private Timestamp loginTime;
    private Integer version;

    @Override
    public String toString() {
        return "LoginInfo{" +
                "userId=" + userId +
                ", loginState='" + loginState + '\'' +
                ", loginTime=" + loginTime +
                ", version=" + version +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
