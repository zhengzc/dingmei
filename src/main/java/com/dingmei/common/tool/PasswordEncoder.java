package com.dingmei.common.tool;

/**
 * 密码编码
 * @author zhengzhichao
 *
 */
public interface PasswordEncoder {
	
	/**
	 * MD5加密 
	 * @param originalPwd 明文
	 * @return encPassword 密文
	 */
	public String encodePassword(String originalPwd);
	
	/**
	 * MD5校验
	 * @param encPassword 密文
	 * @param originalPwd 明文
	 * @return
	 */
	public boolean isPasswordValid(String encPassword,String originalPwd);
	
	/**
	 * TEA 加密
	 * @param strOriginal
	 * @return
	 */
	public String encrypt(String strOriginal);
	
	/**
	 * TEA 解密
	 * @param strEncrypt
	 * @return
	 */
	public String decrypt(String strEncrypt);
}
