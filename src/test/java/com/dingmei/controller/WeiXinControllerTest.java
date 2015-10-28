package com.dingmei.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;

public class WeiXinControllerTest{
	@Test
	public void testCheckSignature(){
		HttpClient httpClient = new HttpClient();
		
		PostMethod postMethod = new PostMethod("http://localhost:8080/tool/weixin/pubItf?timestamp=1388584523&nonce=1389030491&signature=39374c75cdc2f00a65ef50752ac16bce37169c3f");
		RequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity("<xml><ToUserName><![CDATA[gh_9d50a5920b14]]></ToUserName>"
				+"<FromUserName><![CDATA[oGl1rt16V_u-HzgOuZA5RYHzaEyk]]></FromUserName>"
				+"<CreateTime>1388898736</CreateTime>"
				+"<MsgType><![CDATA[text]]></MsgType>"
				+"<Content><![CDATA[牛大傻]]></Content>"
				+"<MsgId>5965274648585737861</MsgId>"
				+"</xml> ","text/html", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postMethod.setRequestEntity(requestEntity);
		
		try {
			httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
