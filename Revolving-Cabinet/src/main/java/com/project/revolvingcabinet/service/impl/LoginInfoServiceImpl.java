package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.code.LoginStateCode;
import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.dao.LoginInfoMapper;
import com.project.revolvingcabinet.entity.LoginInfo;
import com.project.revolvingcabinet.service.LoginInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class LoginInfoServiceImpl implements LoginInfoService {
    private static final Logger logger = LoggerFactory.getLogger(LoginInfoServiceImpl.class);

    @Resource
    private LoginInfoMapper loginInfoMapper;

    @Override
    public LoginInfo getLoginInfoByUserId(long userId) {
        return loginInfoMapper.selectLoginInfoByUserId(userId);
    }

    @Override
    public void updateLoginInfo(LoginInfo loginInfo, long userId, boolean loginFlag) throws Exception {
        // 如果没有登录信息就插入
        if (loginInfo == null) {
            loginInfo = new LoginInfo();
            loginInfo.setUserId(userId);
            loginInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
            loginInfo.setVersion(0);
            loginInfo.setLoginState(LoginStateCode.LOGIN.getCode());
            try {
                loginInfoMapper.insertLoginInfo(loginInfo);
            } catch (Exception e) {
                logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_006));
                e.printStackTrace();
                throw new Exception();
            }
        } else {
            if (loginFlag) {
                // 如果是登录，那么更新登录状态
                loginInfo.setLoginState(LoginStateCode.LOGIN.getCode());
                loginInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
            } else {
                // 如果是登出，那么更新为登出状态
                loginInfo.setLoginState(LoginStateCode.LOGOUT.getCode());
                loginInfo.setVersion(loginInfo.getVersion() + 1);
            }
            // 更新数据
            try {
                loginInfoMapper.updateLoginInfo(loginInfo);
            } catch (Exception e) {
                logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_007));
                e.printStackTrace();
                throw new Exception();
            }
        }
    }
}
