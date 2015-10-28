package com.dingmei.common.tool;
public class SequencePool{
		private long low;
		private long high;
		private long maxValue;//索引最大值 超过最大值将从1开始
		private long currentValue;//索引当前值
		public SequencePool(long low, long high,long maxValue){
			this.low = low;
			this.high = high;
			this.maxValue = maxValue;
		}
		
		/**
		 * 获取下一个索引
		 * @return
		 */
		public long next() {
			if(low != this.maxValue){
				currentValue = low%maxValue;
			}else{
				currentValue = low;
			}
			low++;
			return currentValue;
		}
		public boolean isEmpty(){
			return low > high;
		}
		
		public long getMaxValue(){
			return this.maxValue;
		}
	}