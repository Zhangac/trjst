package com.trjst.util;

/**
 * 微信通用接口凭证
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:42:48 
 */
public class AccessToken {
	// 获取到的凭证
	private static String token;
	// 凭证有效时间，单位：秒
	private static int expiresIn;
	
    private AccessToken() {}  
    
    private static AccessToken instance  = new AccessToken();  
    
    public static AccessToken getInstance() {  
        return instance;  
    }  
	

	public static String getToken() {
		return token;
	}


	public static void setToken(String token) {
		AccessToken.token = token;
	}


	public static int getExpiresIn() {
		return expiresIn;
	}


	public static void setExpiresIn(int expiresIn) {
		AccessToken.expiresIn = expiresIn;
	}



}