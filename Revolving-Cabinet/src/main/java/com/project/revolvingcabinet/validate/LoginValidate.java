package com.project.revolvingcabinet.validate;

import com.project.revolvingcabinet.Vo.LoginVo;
import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.entity.SysUser;
import com.project.revolvingcabinet.utils.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 登录页面参数认证
 */
@Repository
public class LoginValidate {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(LoginValidate.class.getName());

    /**
     * login页面检证
     * @param loginVo 页面传入的参数
     * @param sysUser 数据库中的参数
     * */
    public String loginValidate(LoginVo loginVo, SysUser sysUser) {
        // 先验证用户是否存在，若存在，则查看密码是否正确。
        if (sysUser == null) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOGIN_002));
            return Messages.getErrorMsg(Messages.MSG_E_LOGIN_002);
        } else {
            if (!sysUser.getPassword().equals(PasswordUtil.encrypt(loginVo.getPassword()))) {
                logger.error(Messages.getErrorMsg(Messages.MSG_E_LOGIN_003));
                return Messages.getErrorMsg(Messages.MSG_E_LOGIN_003);
            }
        }
        return null;
    }
}
