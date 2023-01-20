package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

@Mapper
public interface SysUserMapper {

    /**
     * 通过姓名查找用户
     * @param realName 姓名
     * @return
     */
    SysUser selectSysUserByRealName(String realName);

    /**
     * 通过账号查找用户
     * @param account 账号
     * @return
     */
    SysUser selectSysUserByAccount(String account);

    /**
     * 通过用户UserId查找用户
     * @param userId 用户id
     * @return
     */
    SysUser selectSysUserByUserId(Long userId);

    /**
     * 更新用户的登录信息
     * @param lastLoginIp
     * @param lastLoginTime
     * @return
     */
    public int updateSysUserLoginInfo(@Param("userId") Long userId,
                                      @Param("lastLoginIp") String lastLoginIp,
                                      @Param("lastLoginTime") Timestamp lastLoginTime);


    /**
     * 通过用户id查询用户的角色
     * @param userId
     * @return
     */
    public String selectRoleNameByRoleId(Long userId);

}
