package com.project.revolvingcabinet.controller;

import com.project.revolvingcabinet.Vo.LoginVo;
import com.project.revolvingcabinet.common.CommonResult;
import com.project.revolvingcabinet.common.Constants;
import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.entity.LoginInfo;
import com.project.revolvingcabinet.entity.SysUser;
import com.project.revolvingcabinet.service.LoginInfoService;
import com.project.revolvingcabinet.service.UserService;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.project.revolvingcabinet.utils.TokenUtil;
import com.project.revolvingcabinet.validate.LoginValidate;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private LoginValidate loginValidate;

    @Resource
    private LoginInfoService loginInfoService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        // 参数验证
        if (loginVo == null || StringUtils.isBlank(loginVo.getAccount())) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_ALI_001));
            return CommonResult.validateFailed(Messages.getErrorMsg(Messages.MSG_E_LOGIN_001));
        }

        // 检索用户
        SysUser sysUser = new SysUser();
        try {
            sysUser = userService.findSysUserByAccount(loginVo.getAccount());
        } catch (Exception e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_002), e);
            return CommonResult.failed(Messages.getErrorMsg(Messages.MSG_E_LOG_008));
        }

        // 验证账号密码
        String message = loginValidate.loginValidate(loginVo, sysUser);
        // message不为空那么代表验证失败
        if (!StringUtils.isBlank(message)) {
            logger.error(message);
            return CommonResult.validateFailed(message);
        }

        try {
            // 获取登录信息
            LoginInfo loginInfoByUserId = loginInfoService.getLoginInfoByUserId(sysUser.getUserId());

            // 更新登录信息
            loginInfoService.updateLoginInfo(loginInfoByUserId, sysUser.getUserId(), true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return CommonResult.failed(e.getMessage());
        }

        HashMap<String, Object> tokenMap = new HashMap<>();
        // 生成TOKEN
        try {
            String token = TokenUtil.getToken(String.valueOf(sysUser.getUserId()));
            tokenMap.put(Constants.USER_ID, sysUser.getUserId());
            tokenMap.put(Constants.TOKEN, token);
            logger.info(Messages.getSuccessMsg(Messages.MSG_S_LOG_001)
                + "登录用户id：" + sysUser.getUserId() + "；登录用户名：" + sysUser.getAccount());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return CommonResult.failed(e.getMessage());
        }

        // 获取登录id
        String loginIp = CommonUtil.getIpAddr(request);
        logger.info("lastLoginIp:{}", loginIp);
        // 更新登录信息
        userService.updateSysUserLoginInfo(sysUser.getUserId(), loginIp, new Timestamp(System.currentTimeMillis()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                sysUser, sysUser.getPassword(), userService.getAuthorities(sysUser.getUserId())
        );
        // 登录信息存入Security上下文，方便授权。
        SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
        return CommonResult.success(tokenMap, Messages.getSuccessMsg(Messages.MSG_S_LOG_001));
    }

    @ResponseBody
    @RequestMapping("/logout")
    public CommonResult logout(@RequestParam("userId") Long userId) {
        if (userId == null) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_001));
            return CommonResult.validateFailed(Messages.getErrorMsg(Messages.MSG_E_LOG_001));
        }

        LoginInfo loginInfo = null;
        // 搜索登录信息
        try {
            // 获取登录信息
            loginInfo = loginInfoService.getLoginInfoByUserId(userId);
            // 更新用户登录状态
            loginInfoService.updateLoginInfo(loginInfo, userId, false);
        } catch (Exception e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_008), e);
            return CommonResult.failed(Messages.getErrorMsg(Messages.MSG_E_LOG_008));
        }

        logger.info(Messages.getSuccessMsg(Messages.MSG_S_LOG_002)
                + "退出用户："
                + userId);
        SecurityContextHolder.clearContext();
        return CommonResult.success("", Messages.getSuccessMsg(Messages.MSG_S_LOG_002));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "please login";
    }
}
