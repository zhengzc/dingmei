package com.dingmei.common.tool;

/**
 * 序列生成相关接口
 * @author zhengzhichao
 *
 */
public interface ISequenceService {
	/**
	 * 从数据库获取序列，能够批量获取
	 * @param sequenceName
	 * @param allotment 获取序列长度
	 * @return 正常情况下返回序列值 找不到序列的时候抛异常
	 */
	public long getNextSequence(String sequenceName,int allotment);
	/**
	 * 获取序列最大值
	 * @param seqName
	 * @return 找不到序列的时候抛异常
	 */
	public long getSeqMaxValue(String seqName);
}