package com.dingmei.common.tool;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dingmei.core.SpringAppContext;

/**
 * 将原序列生成策略稍作修改，原策略如果使用不当可能会导致产生重复序列的bug
 * 另外添加直接获取带有时间戳序列的方法
 * @author zhengzhichao
 *
 */
public class SequenceHelp {
	private static Map<String,SequencePool> seqMap = new HashMap<String,SequencePool>();
//	private static SequenceGenerator sequenceGenerator = new SequenceGenerator();
	
	private static ISequenceService sequenceService = SpringAppContext.getBean("sequenceService");
	//每次请求分配的序列数个数
	private static final int allotment = 100;
	
	/**
	 * 根据序列名字，获取新的序列
	 * 序列名字需要去tops_journey.sequence表中配置，否则将会抛出找不到序列的异常
	 * 配置序列的时候，需指定序列名字：seq_name  当前序列值：current_value  递增步长：increment  最大值：max_value
	 * 按照oracle序列生成规则，超过最大值的序列将重新开始
	 * @param sequenceName
	 * @return
	 * @
	 */
	public static synchronized String getNextSeq(String sequenceName) {
		SequencePool pool = seqMap.get(sequenceName);
		if(seqMap.get(sequenceName) == null || pool.isEmpty()){
			pool = refillPool(sequenceName);
			seqMap.put(sequenceName, pool);
		}
		return formatSequence(String.valueOf(pool.next()),pool.getMaxValue());
	}
	
	/**
	 * 格式化时间
	 * @param formatStr  输入参数为java的标准格式化日期字符串 类似 yyyy-MM-dd hh:mm:ss
	 * @return 返回系统当前时间
	 */
	private static String getFormatDateTime(String formatStr) {
//		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formatDate = null;
		DateFormat format = null;
		try{
			format = new java.text.SimpleDateFormat(formatStr);
		}catch (Exception e) {
			throw new RuntimeException("请确认日期格式化字符串输入正确！",e);
		}
		formatDate = format.format(new Date());
	    return formatDate;
	}
	/**
	 * 根据序列名字获取序列，在序列头部加上8位时间维度，精确到天(yyyyMMdd)
	 * @param sequenceName
	 * @return 带有时间维度的序列
	 * @
	 */
	
	public static String getNextSeqWithDate(String sequenceName) {
		StringBuilder ret = new StringBuilder();
		ret.append(getFormatDateTime("yyyyMMdd"));
		ret.append(getNextSeq(sequenceName));
		return ret.toString();
	}
	
	/**
	 * 根据序列名字获取序列，在序列头部加上12位时间维度，精确到秒(yyMMddHHmmss)
	 * @param sequenceName
	 * @return 带有时间维度的序列
	 * @
	 */	
	public static String getNextSeqWithDate12(String sequenceName) {
		StringBuilder ret = new StringBuilder();
		ret.append(getFormatDateTime("yyMMddHHmmss"));
		ret.append(getNextSeq(sequenceName));
		return ret.toString();
	}
	
	
	/**
	 * 批量获取序列，减少访问数据库次数
	 * @param sequenceName
	 * @return
	 * @
	 */
	private static SequencePool refillPool(String sequenceName) {
		long nextSeq = sequenceService.getNextSequence(sequenceName,allotment);
		long seqMaxValue = sequenceService.getSeqMaxValue(sequenceName);
		return new SequencePool(nextSeq, nextSeq + allotment -1,seqMaxValue); 
	}
	/**
	 * 
	 * description: 根据当前序列的最大值，格式序列，不足的位数左侧补零
	 * 
	 * 修改为用StringBuilder拼接字符串，增加拼接速度！
	 * @param is 当前序列
	 * @param maxValue	序列最大值
	 * @return
	 */
	private static String formatSequence(String is, long maxValue) {
		int width = new Long(maxValue).toString().length();
		StringBuilder tempStr = new StringBuilder();
		int count = width-is.length();
		for(int i=0;i<count;i++){
			tempStr.append(0);
		}
		return tempStr.append(is).toString();
	}
	
	
	
}
