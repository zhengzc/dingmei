package com.dingmei.spring.security;

import com.dingmei.dao.entity.User;
import com.dingmei.dao.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 用户信息获取
 */
public class CustomerSpringSecurityUserDetailService implements UserDetailsService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 增加一个通用的角色
     */

    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserInfo userInfo = null;

        User user = this.userMapper.loadUserByUserName(userName);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        try{
            userInfo = new UserInfo(
                    userName,user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    getUserAuthorities(userMapper.queryUserRoleStrs(user.getId())));
            userInfo.setId(user.getId());
            userInfo.setRealName(user.getRealName());
            userInfo.setRoles(this.userMapper.queryUserRoleStrs(user.getId()));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new UsernameNotFoundException("用户名解析错误");
        }

        return userInfo;
    }

    /**
     * 生成授权列表
     * @param roles
     * @return
     */
    private Collection<GrantedAuthority> getUserAuthorities(List<String> roles){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        authorities.add(new SimpleGrantedAuthority(SecurityConstants.LOGIN_ROLE));

        return authorities;
    }

}
