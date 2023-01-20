package com.project.revolvingcabinet.interceptor;

import com.project.revolvingcabinet.code.ResultCode;
import com.project.revolvingcabinet.common.CommonResult;
import com.project.revolvingcabinet.entity.SysUser;
import com.project.revolvingcabinet.service.LoginInfoService;
import com.project.revolvingcabinet.service.UserService;
import com.project.revolvingcabinet.utils.HostHolder;
import com.project.revolvingcabinet.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private HostHolder hostHolder;

    @Resource
    private LoginInfoService loginInfoService;

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        String token = request.getHeader("token");
        // token不为空时验证
        if (!StringUtils.isBlank(token) && !StringUtils.isBlank(userId)) {
            boolean isValidToken = false;
            try {
                isValidToken = TokenUtil.verify(token);
            } catch (Exception e) {
                // 第二步改变返回状态码为401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            if (isValidToken) {
                SysUser sysUser = userService.findSysUserByUserId(Long.valueOf(userId));
                hostHolder.setUser(sysUser);
                // 构建用户认证结果并存入SecurityContext，以便于Security进行授权
//                Authentication authentication = new UsernamePasswordAuthenticationToken(
//                        sysUser, sysUser.getPassword(), userService.getAuthorities(sysUser.getUserId())
//                );
//                SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
                return true;
            }
            else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                CommonResult.unauthorized(ResultCode.UNAUTHORIZED.getMessage());
                response.sendRedirect(request.getContextPath() + "/user/login");
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        CommonResult.unauthorized(ResultCode.UNAUTHORIZED.getMessage());
        response.sendRedirect(request.getContextPath() + "/user/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
        SecurityContextHolder.clearContext();
    }
}
