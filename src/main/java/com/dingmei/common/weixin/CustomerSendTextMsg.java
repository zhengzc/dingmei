package com.dingmei.common.weixin;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送客服消息的实体
 * @author zhengzhihcao
 *
 */
public class CustomerSendTextMsg {
	private String touser;
	private String msgtype;
	private Map<String,String> text;
	
	public CustomerSendTextMsg(String touser,String content){
		this.touser = touser;
		this.msgtype = "text";
		this.text = new HashMap<String,String>();
		this.text.put("content", content);
	}

	public String getTouser() {
		return touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public Map<String, String> getText() {
		return text;
	}
}
