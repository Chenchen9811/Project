package com.project.revolvingcabinet.service;

import com.project.revolvingcabinet.entity.LoginInfo;

public interface LoginInfoService {

    /**
     * 通过用户id获取用户登录信息
     * @param userId
     * @return
     */
    LoginInfo getLoginInfoByUserId(long userId);

    void updateLoginInfo(LoginInfo loginInfo, long userId, boolean loginFlag) throws Exception;

}
