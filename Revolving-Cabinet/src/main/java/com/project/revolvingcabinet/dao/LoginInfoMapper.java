package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.LoginInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginInfoMapper {

    /**
     * 插入登录信息
     * @param loginInfo 登录信息
     * @return
     */
    int insertLoginInfo(LoginInfo loginInfo);

    /**
     * 更新登录信息
     * @param loginInfo 登录信息
     * @return
     */
    int updateLoginInfo(LoginInfo loginInfo);

    /**
     * 根据用户id查询用户登录信息
     * @param userId 用户id
     * @return
     */
    LoginInfo selectLoginInfoByUserId(long userId);
}
