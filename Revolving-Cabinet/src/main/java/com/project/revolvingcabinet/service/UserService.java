package com.project.revolvingcabinet.service;

import com.project.revolvingcabinet.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.Collection;

public interface UserService {
    /**
     * 通过姓名查找用户
     * @param realName 姓名
     * @return
     */
    SysUser findSysUserByRealName(String realName);

    /**
     * 通过账号查找用户
     * @param account 账号
     * @return
     */
    SysUser findSysUserByAccount(String account);

    /**
     * 更新用户的登录信息
     * @param lastLoginIp
     * @param lastLoginTime
     * @return
     */
    public int updateSysUserLoginInfo(Long userId, String lastLoginIp, Timestamp lastLoginTime);

    /**
     * 通过用户UserId查找用户
     * @param userId 用户id
     * @return
     */
    SysUser findSysUserByUserId(Long userId);


    /**
     * 获取用户权限
     * @param userId 用户id
     * @return
     */
    public Collection<? extends GrantedAuthority> getAuthorities(Long userId);

    /**
     * 通过用户id查询用户的角色
     * @param userId
     * @return
     */
    public String selectRoleNameByRoleId(Long userId);
}
