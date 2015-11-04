package com.dingmei.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 决策管理器
 * @author zhengzhichao
 *
 */
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {
	/**
	 * Logger for this class
	 */
	private static final Logger SECURITY_ACCESS_DECISION_LOG = LoggerFactory.getLogger("SECURITY_ACCESS_DECISION");
	
	/**
	 * 主要方法，在此方法中，会匹配authentication与configAttributes
	 * @param authentication 认证信息，当前用户所拥有的角色（授权）
	 * @param object 可以认为是验证的url
	 * @param configAttributes 配置信息，当前url所需要的角色
	 */
    @Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
	
		if (configAttributes == null || configAttributes.size() <= 0) {
			if (SECURITY_ACCESS_DECISION_LOG.isDebugEnabled()) {
				SECURITY_ACCESS_DECISION_LOG.debug("当前url没有对应角色，不执行拦截");
				SECURITY_ACCESS_DECISION_LOG.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end");
			}
            return;
		}
		//循环校验
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		
		List<String> needRoleIds = null;
		List<String> currentRoleIds = null;
		
		if(SECURITY_ACCESS_DECISION_LOG.isDebugEnabled()){
			needRoleIds = new ArrayList<String>();
			currentRoleIds = new ArrayList<String>();
		}
		
		
		while (ite.hasNext()) {
			ConfigAttribute configAttribute = ite.next();
			String needRoleId = ((SecurityConfig) configAttribute).getAttribute();
			if(SECURITY_ACCESS_DECISION_LOG.isDebugEnabled()){
				needRoleIds.add(needRoleId);
			}
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if(SECURITY_ACCESS_DECISION_LOG.isDebugEnabled()){
					currentRoleIds.add(grantedAuthority.getAuthority());
				}
				if (needRoleId.equals(grantedAuthority.getAuthority())) { // grantedAuthority 是当前用户的所具有的角色
					if (SECURITY_ACCESS_DECISION_LOG.isDebugEnabled()) {
						SECURITY_ACCESS_DECISION_LOG.debug("判断到需要的角色={}，当前具有的角色={},URL={},授权数据相匹配成功！",needRoleId,grantedAuthority.getAuthority(),object.toString());
					}
					return;
				}
			}
		}
		
		if(SECURITY_ACCESS_DECISION_LOG.isDebugEnabled()){
			try{
				UserInfo userInfo = (UserInfo) authentication.getPrincipal();
				Object[] params = new  Object[]{
						object.toString(),
						userInfo.getUsername(),
						configAttributes.size(),
						needRoleIds,
						currentRoleIds
				};
				SECURITY_ACCESS_DECISION_LOG.debug("[URL = {}][User Name = {}][可匹配的角色 = {}][NEED_ROLEIDS = {}][CURRENT_ROLEIDS = {}][]",params);
			}catch(ClassCastException e){
				SECURITY_ACCESS_DECISION_LOG.debug("Authentication GetPrincipal = {}",authentication.getPrincipal());
			}
		}
		throw new AccessDeniedException("没有访问当前功能的权限。");
	}
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
