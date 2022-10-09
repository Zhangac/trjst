package com.trjst.util;

import java.io.Serializable;


public class ServerConfig implements Serializable {

	private static final long serialVersionUID = 19386536267359832L;

	private static String APP_ID ="wxe3bf6f6c2d88acd6";//appid
	private static String APP_SECRET ="fe6dc6e45af08a06fcf7355289de49dd";//秘钥
	private static String partnerId ="1615024399";//商户ID
	private static String partnerSecert ="trjishitong12111trjishitong12111";//商户秘钥
	private static String paynotify ="https://www.trjst.com/jst/wxpayquery";//回调地址
	private static String paynotify2 ="https://www.trjst.com/jst/wxpayquery2";//回调地址

	public static String getAPP_ID() {
		return APP_ID;
	}
	public static String getAPP_SECRET() {
		return APP_SECRET;
	}


	public static String getPartnerId() {
		return partnerId;
	}



	public static String getPartnerSecert() {
		return partnerSecert;
	}

	public static String getPaynotify() {
		return paynotify;
	}

	public static String getPaynotify2() {
		return paynotify2;
	}


}
