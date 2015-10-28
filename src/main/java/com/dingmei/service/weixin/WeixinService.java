package com.dingmei.service.weixin;

/**
 * 微信相关服务
 * @author zhengzhichao 
 *
 */
public interface WeixinService{
	
	/**
	 * 很简单的一个方法，发送过来内容，返回内容
	 * @param message 请求聊天信息
	 * @return 回复的信息
	 */
	public String chat(String message);
	
	/**
	 * 此方法初始化微信accessToken，并更新accessToken有效期截止时间
	 */
	public void initAccessToken();
}
