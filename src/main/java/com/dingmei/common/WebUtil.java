package com.dingmei.common;

import com.dingmei.spring.security.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by ying on 15/11/3.
 * web相关的工具类
 */
public class WebUtil {
    /**
     * 获取session信息
     * @return
     */
    public static UserInfo getSessionInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            return (UserInfo)authentication.getPrincipal();
        }
        return null;
    }
}
