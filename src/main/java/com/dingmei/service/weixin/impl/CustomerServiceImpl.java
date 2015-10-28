package com.dingmei.service.weixin.impl;

import java.util.concurrent.Callable;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dingmei.common.weixin.CustomerSendTextMsg;

public class CustomerServiceImpl implements Callable<String> {
	private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private static HttpClient httpClient = new HttpClient();
	private final String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	private String access_token;
	
	private CustomerSendTextMsg msg;

	public CustomerServiceImpl(CustomerSendTextMsg msg,String access_token){
		this.msg = msg;
		this.access_token = access_token;
	}
	
	@Override
	public String call() throws Exception {
		JSONObject sendMsg = JSONObject.fromObject(this.msg);
		sendMsg.put("access_token", this.access_token);
		
		//构造post请求
		PostMethod post = new PostMethod(this.url+"?access_token="+this.access_token);
//		NameValuePair[] nameValuePairs = new NameValuePair[]{
//				new NameValuePair("","")
//		};
//		post.addParameter;
		RequestEntity requestEntity = new StringRequestEntity(sendMsg.toString(), "text/html", "UTF-8");
		post.setRequestEntity(requestEntity);
		
		//调用http服务，推送客服信息
		logger.info("----->send message to weixin:"+sendMsg.toString());
		int httpStatus = httpClient.executeMethod(post);
		logger.debug("----->response message from weixin:"+post.getResponseBodyAsString());
		if(httpStatus == HttpStatus.SC_OK){
			logger.info("----->customer message send success:"+sendMsg.toString());
			return "ok";
		}else{
			logger.info("----->customer message send failed:"+sendMsg.toString());
			return "error";
		}
		
	}

}
