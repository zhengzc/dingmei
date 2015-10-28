package com.dingmei.common.weixin;

/**
 * 微信公众平台，回复消息   文本结构
 * @author zhengzhichao
 *
 */
public class ReplayTextMsg {
	private String toUserName;
	private String fromUserName;
	private long createTime;
	private String msgType;
	private String content;
	
	public ReplayTextMsg(String toUserName,String fromUserName,String content){
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = System.currentTimeMillis();
		this.msgType = "text";
		this.content = content;
	}
	
	/**
	 * 反正格式是固定的... 懒的用一堆开源的，直接拼出来就挺好的
	 */
	@Override
	public String toString(){
		StringBuilder ret = new StringBuilder();
		ret.append("<xml>");
		ret.append("<ToUserName>");
		ret.append("<![CDATA["+this.getToUserName()+"]]>");
		ret.append("</ToUserName>");
		ret.append("<FromUserName>");
		ret.append("<![CDATA["+this.getFromUserName()+"]]>");
		ret.append("</FromUserName>");
		ret.append("<CreateTime>");
		ret.append("<![CDATA["+this.getCreateTime()+"]]>");
		ret.append("</CreateTime>");
		ret.append("<MsgType>");
		ret.append("<![CDATA["+this.getMsgType()+"]]>");
		ret.append("</MsgType>");
		ret.append("<Content>");
		ret.append("<![CDATA["+this.getContent()+"]]>");
		ret.append("</Content>");
		ret.append("</xml>");
		return ret.toString();
	}

	public String getToUserName() {
		return toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public String getContent() {
		return content;
	}
}
