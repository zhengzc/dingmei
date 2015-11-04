package com.dingmei.spring.security;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/**
 * 自定义的权限过滤器，明显这是责任链的设计模式
 * @author zhengzhichao
 *
 */
public class FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(FilterSecurityInterceptor.class);
	//资源配置
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if (LOG.isDebugEnabled()) {
			LOG.debug("------------------>开始请求{}",httpRequest.getRequestURI()); 
		}
		//加入链条
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
		if (LOG.isDebugEnabled()) {
			LOG.debug("------------------>请求结束{}",httpRequest.getRequestURI());
		}
	}
	
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
	
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}
	
	/**
	 * 
	 * @param fi
	 * @throws IOException
	 * @throws ServletException
	 */
	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			//执行过滤
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}
	
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
	
	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}