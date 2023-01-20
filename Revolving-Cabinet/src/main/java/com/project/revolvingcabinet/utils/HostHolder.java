package com.project.revolvingcabinet.utils;

import com.project.revolvingcabinet.entity.SysUser;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<SysUser> users = new ThreadLocal<>();

    public void setUser(SysUser sysUser) {
        users.set(sysUser);
    }

    public SysUser getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
