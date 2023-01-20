package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.SysUserMapper;
import com.project.revolvingcabinet.entity.SysUser;
import com.project.revolvingcabinet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findSysUserByRealName(String realName) {
        return sysUserMapper.selectSysUserByRealName(realName);
    }

    @Override
    public SysUser findSysUserByAccount(String account) {
        return sysUserMapper.selectSysUserByAccount(account);
    }

    @Override
    public int updateSysUserLoginInfo(Long userId, String lastLoginIp, Timestamp lastLoginTime) {
        return sysUserMapper.updateSysUserLoginInfo(userId, lastLoginIp, lastLoginTime);
    }

    @Override
    public SysUser findSysUserByUserId(Long userId) {
        return sysUserMapper.selectSysUserByUserId(userId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(Long userId) {
        SysUser sysUser = this.findSysUserByUserId(userId);
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return sysUserMapper.selectRoleNameByRoleId(userId);
            }
        });
        return list;
    }

    @Override
    public String selectRoleNameByRoleId(Long userId) {
        return sysUserMapper.selectRoleNameByRoleId(userId);
    }
}
