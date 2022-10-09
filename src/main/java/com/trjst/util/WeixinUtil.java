package com.trjst.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 公众平台通用接口工具类
 * 
 * @author zac
 *
 *@date 2014年6月12日 上午11:48:35 
 */
public class WeixinUtil {

	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);


    /**
     * 调用微信统一下单接口，返回订单支付信息
     * @param request  请求对象
     * @param order_code  订单号
     * @param amount   订单金额
     * @param openId   支付用户的微信号
     * @param goods_desc  商品描述
     * @param pay_notify  支付结果通知地址
     * @return  微信订单支付对象
     * @throws Exception
     */
    public static Map<String, String> configPayParam(HttpServletRequest request,String order_code,
                                                     String amount,String openId, String goods_desc,String pay_notify,String openid) throws Exception {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        Map<String, String> params = new HashMap<String, String>();
        String nonce_str = StringUtil.get32UUID();

        params.put("appid", ServerConfig.getAPP_ID());
        params.put("body", goods_desc);
        params.put("mch_id", ServerConfig.getPartnerId());
        params.put("nonce_str", nonce_str);

        params.put("notify_url", pay_notify);

        params.put("openid", openid);// 小程序支付时用得到
        params.put("out_trade_no", order_code);
        params.put("spbill_create_ip", IpAndMac.getLocalIP());
        params.put("total_fee", amount);

        params.put("trade_type", "JSAPI"); //JSAPI APP

        String sign = WxSign.createSign(params,ServerConfig.getPartnerSecert());

        params.put("sign", sign);
        log.info(params.toString());
        log.info("--------------------微信支付页面请求开始---------------------------");

        String xml = configXml(params);

        log.info("签名请求报文：" + xml);

        String result = HttpClientTool.post(url, xml, "utf-8");
        log.info("返回报文：" + result);
        System.out.println("开始转换");
        PayResData payReseData = (PayResData) XmlUtil.getObjectFromXML(result, PayResData.class);
        log.info("签名请求报文2：" + payReseData);
        Map<String, String> payParam = new HashMap<String, String>();
        payParam.put("appId", payReseData.getAppid());
        payParam.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        payParam.put("nonceStr", nonce_str);
        payParam.put("package", "prepay_id=" + payReseData.getPrepay_id());
        payParam.put("signType", "MD5");
        String paySign = WxSign.createSign(payParam,ServerConfig.getPartnerSecert());
        payParam.put("paySign", paySign);
        return payParam;
    }
	
	/**
	 * 调用微信统一下单接口，返回订单支付信息
	 * @param request  请求对象
	 * @param order_code  订单号
	 * @param amount   订单金额
	 * @param openId   支付用户的微信号
	 * @param goods_desc  商品描述
	 * @param pay_notify  支付结果通知地址
	 * @return  微信订单支付对象
	 * @throws Exception
	 */
	public static Map<String, String> configPayParam2(HttpServletRequest request,String order_code,
			String amount,String openId, String goods_desc,String pay_notify,String openid) throws Exception {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        Map<String, String> params = new HashMap<String, String>();
        String nonce_str = StringUtil.get32UUID();
        
        params.put("appid", ServerConfig.getAPP_ID());
        params.put("body", goods_desc);
        params.put("mch_id", ServerConfig.getPartnerId());
        params.put("nonce_str", nonce_str);
        
        params.put("notify_url", pay_notify);
        
        params.put("openid", openid);// 小程序支付时用得到
        params.put("out_trade_no", order_code);
        params.put("spbill_create_ip", IpAndMac.getLocalIP());
        params.put("total_fee", amount);

        params.put("trade_type", "JSAPI"); //JSAPI APP

        String sign = WxSign.createSign(params,ServerConfig.getPartnerSecert());

        params.put("sign", sign);
        log.info(params.toString());
        log.info("--------------------微信支付页面请求开始---------------------------");

        String xml = configXml(params);

        log.info("签名请求报文：" + xml);

        String result = HttpClientTool.post(url, xml, "utf-8");
        log.info("返回报文：" + result);
        System.out.println("开始转换");
        PayResData payReseData = (PayResData) XmlUtil.getObjectFromXML(result, PayResData.class);
        log.info("签名请求报文2：" + payReseData);
        Map<String, String> payParam = new HashMap<String, String>();
        payParam.put("appid", payReseData.getAppid());
        payParam.put("partnerid", ServerConfig.getPartnerId());
        payParam.put("prepayid", payReseData.getPrepay_id());
        payParam.put("noncestr", nonce_str);
        payParam.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        payParam.put("package", "Sign=WXPay");
        String paySign = WxSign.createSign(payParam,ServerConfig.getPartnerSecert());
        payParam.put("sign", paySign);
        return payParam;
    }

    //支付结果查询
    public static Map<String, String> wxpayquery(HttpServletRequest request,String order_code) throws Exception {
        String url = "https://api.mch.weixin.qq.com/pay/orderquery";

        Map<String, String> params = new HashMap<String, String>();
        String nonce_str = StringUtil.get32UUID();

        params.put("appid", ServerConfig.getAPP_ID());
        params.put("mch_id", ServerConfig.getPartnerId());
        params.put("out_trade_no", order_code);//商户系统内部订单号
        params.put("nonce_str", nonce_str);
        String sign = WxSign.createSign(params,ServerConfig.getPartnerSecert());
        params.put("sign", sign);

        log.info("--------------------微信支付结果查询---------------------------");

        String xml = configXml(params);

        log.info("签名请求报文：" + xml);

        String result = HttpClientTool.post(url, xml, "utf-8");
        PayResData payReseData = (PayResData) XmlUtil.getObjectFromXML(result, PayResData.class);
        Map<String, String> payParam = new HashMap<String, String>();
        System.out.println(result);
        payParam.put("trade_state", payReseData.getTrade_state());
        return payParam;
    }
    
    public static String configXml(Map<String, String> params) throws IOException {
        StringBuffer buffer=new StringBuffer();
        
        buffer.append("<xml>");
        for(Entry<String,String> entry:params.entrySet()){
            buffer.append("<").append(entry.getKey()).append(">");
            buffer.append(entry.getValue());
            buffer.append("</").append(entry.getKey()).append(">");
        }
        buffer.append("</xml>");
        
        return buffer.toString();
     }

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 获取access_token
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static String getAccessToken(String appid, String appsecret) {

        AccessToken accessToken = null;
        //System.out.println(appid+"-------------------"+appsecret);
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);

        // 如果请求成功
        if (null != jsonObject) {
            try {

                AccessToken.setToken(jsonObject.getString("access_token"));


            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsonObject.getString("access_token");

    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        System.setProperty("jsse.enableSNIExtension", "false");
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }
}