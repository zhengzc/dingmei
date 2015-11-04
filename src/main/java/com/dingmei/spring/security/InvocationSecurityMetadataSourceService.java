package com.dingmei.spring.security;

import com.dingmei.core.SpringAppContext;
import com.dingmei.dao.entity.Resource;
import com.dingmei.dao.mapper.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 注意，我例子中使用的是AntUrlPathMatcher这个path matcher来检查URL是否与资源定义匹配，
 * 事实上你还要用正则的方式来匹配，或者自己实现一个matcher。
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 * 说明：对于方法的spring注入，只能在方法和成员变量里注入，
 * 如果一个类要进行实例化的时候，不能注入对象和操作对象，
 * 所以在构造函数里不能进行操作注入的数据。
 */
public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	private static final Logger SECURITY_LOG = LoggerFactory.getLogger("SECURITY_LOG");

    /**
     * 缓存资源信息
     */
    private final ConcurrentHashMap<String, Collection<ConfigAttribute>> cacheResources = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
	
	@Autowired
	private ResourceMapper resourceMapper;
	
	/**
	 * 加载资源配置
	 * 此方法会加载所有Url与角色相关的资源配置
	 * @throws Exception
	 */
	public synchronized void loadResourceDefine(){
		
		if(SECURITY_LOG.isDebugEnabled()){
			SECURITY_LOG.debug("----------------开始加载权限资源--------------");
		}
		
        List<Resource> resources = this.resourceMapper.queryAll();
        for(Resource resource : resources){
            List<String> roleIds = this.resourceMapper.queryRoleStrsByResourceId(resource.getId());

            Collection<ConfigAttribute> roles = new ArrayList<ConfigAttribute>();
            for(String roleId : roleIds){
                roles.add(new SecurityConfig(roleId));
            }

            this.cacheResources.put(resource.getContent(),roles);
        }

		if(SECURITY_LOG.isDebugEnabled()){
			SECURITY_LOG.debug("----------------权限资源加载完毕--------------");
		}
	}
	
	/**
	 * 提供刷新权限的功能
	 * @author zhengzhichao
	 */
	public static void refreshResource(){
		InvocationSecurityMetadataSourceService invocationSecurityMetadataSourceService = SpringAppContext.getBean(InvocationSecurityMetadataSourceService.class);
		invocationSecurityMetadataSourceService.loadResourceDefine();
	}
	
	/**
	 * 根据url，获取相关的权限配置
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException{
		if (SECURITY_LOG.isDebugEnabled()) {
			SECURITY_LOG.debug("根据请求URL获得允许访问此URL的角色信息开始");
		}

		if(cacheResources == null || cacheResources.isEmpty()) {
			if (SECURITY_LOG.isDebugEnabled()) {
				SECURITY_LOG.debug("############# 权限信息为空 ，开始重新获取 ##############");
			}
			//加载资源配置
			loadResourceDefine();
			//重新获取
			if(cacheResources == null || cacheResources.isEmpty()){
				SECURITY_LOG.error("############# 无法获取权限信息，请检查资源配置 ##############");
			}else{
				SECURITY_LOG.debug("############# 成功加载 {} 条权限信息 ##############", cacheResources.size());
			}
		}
		
		//获取请求信息
		FilterInvocation filterInvocation = (FilterInvocation) object;
		@SuppressWarnings("unused")
		HttpServletRequest request = filterInvocation.getHttpRequest();
		//url信息
		String requestUrl = filterInvocation.getRequestUrl();
		//删掉url后面的参数 get请求
		int check = requestUrl.indexOf("?");
		if(-1 != check){
			requestUrl = requestUrl.substring(0, check);
		}
		
		if (SECURITY_LOG.isDebugEnabled()) {
			SECURITY_LOG.debug("当前请求的url地址为：{}",requestUrl);
		}
        
        //精确匹配到直接返回url对应的角色信息
        if(cacheResources.get(requestUrl) != null){
        	SECURITY_LOG.debug("----->精确匹配url成功："+requestUrl);
        	Collection<ConfigAttribute> attributes = cacheResources.get(requestUrl);
        	if(SECURITY_LOG.isDebugEnabled()){
        		if(attributes.size() == 0){
        			SECURITY_LOG.debug("当前url对应的角色为空，不拦截！");
        		}else{
        			SECURITY_LOG.debug("当前url对应的角色为：" + attributes.toString());
        		}
        	}
        	return attributes;
        }else{
            if (SECURITY_LOG.isDebugEnabled()) {
                SECURITY_LOG.debug("URL:{},匹配失败，返回空角色列表，不允许访问！",requestUrl);
            }
            return new ArrayList<ConfigAttribute>();
        }
	}
	
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}