package com.framework.loippi.utils.alipay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.entity.Order;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.wechat.utils.AzuraWechatUtil;

import net.sf.json.JSONObject;


public class AliPay {

	public static final String BASE_URL = "http://112.74.135.217:7026/zhongyi";
	//商户PID(合作身份者id，以2088开头的16位纯数字)
	public static final String PARTNER = "2088612669854557";
	//商户收款账号(收款支付宝账号)
	public static final String SELLER = "2633206425@qq.com";
	//商户私钥，pkcs8格式(商户私钥，自助生成)
	
	//app支付的 ,扫码支付都配置了同样秘钥公钥
	public static final String RSA_PRIVATE ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJzhyQxOMNJkWQrIO6iX+tPvSz53vmkMWKVmwBCXbUNor6xoFMYdeQ0g+vEn8SWwtaGRmh9ziyJ2PvehAXuu7iUSyJgwjY4mxevQHiyiZRgjmeOM9Xz8aOaH4flb3b7YflEaQPLMvMI6NEgpFNTSV9CCNoT62BFGP9NU8+wxPJZ5AgMBAAECgYAeK946m58QF3R4emHe3rvCnrbd4LGiClnSFZzClBzTZgLDKZE1ON3bBIi+cGwUT5Pe6t21RsdvbUZW4YQjgVUTRZKdICBtanVNb6lRsTtB7gCrh6mQ03K/JsRhiVP6MjyqbGghm2WS24J8Oyr6Y+LNxeG/7Byid8cAXMGzWUhtMQJBAPTcBWlhUvANy+fzlR+6unxsk0igI4pFW+Cmtxw6kbMZoLDPGXgcF3qJEnUS0HUHCn6/318XAh3eiiLmjQ34eX8CQQCkBQ3jagbVfwMgfmh/4kdd15Hnxi/9pL+DKyqSZPP8jPkFtNWaWgUmvX61Phsiy6l7ohNpnYDPToQFaEgVyLwHAkB5wV4/hFGFZJbcA9dHK8bHUAX8uwRxIQQKtVugAc+pSzkIMjLr8qwKFlbDOFPHnXSuiiI8WmiHCb2OlS3WoJNpAkAB/f25MWR721sSXfau9vSe8ytWgVW74vBgi3ms5YUBIEU2CIg/o7BEVDDhyqtRtN+OHN3Xfo499losJqQZf6KpAkEA2OsJLBEMuAcCERwl8Elvah2mboNb/qhdsiWNQYlKG+68t2qGFDng2KT7ZPYEH8wD6cb3EfG31BEgC1MzS2FRSQ==";
			//支付宝公钥                                                                              
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCc4ckMTjDSZFkKyDuol/rT70s+d75pDFilZsAQl21DaK+saBTGHXkNIPrxJ/ElsLWhkZofc4sidj73oQF7ru4lEsiYMI2OJsXr0B4somUYI5njjPV8/Gjmh+H5W92+2H5RGkDyzLzCOjRIKRTU0lfQgjaE+tgRRj/TVPPsMTyWeQIDAQAB";			/**支付宝公钥-从支付宝服务窗获取*/
	//public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB"	;
	/** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的服务窗id  这里只是个测试id
   public static final String APPID  = "2016122704651934";
    public static final String F2FAPP_ID= "";
	

	
	private static final int SDK_PAY_FLAG = 1;
	// 签名方式 不需修改
	public static String sign_type = "RSA";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	
    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "https://openapi.alipay.com/gateway.do";

  
    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "GBK";
    /**支付宝公钥-从支付宝服务窗获取*/

	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 创建订单信息
	 * @param subject 商品名称
	 * @param body 商品详情
	 * @param outTradeNo 商品名称
	 * @param price 商品金额
	 */
	public static String pay(String subject, String body, String outTradeNo,String sessionid,String price) {

		
		Map<String, String> biz_contentmap=new HashMap();
		biz_contentmap.put("timeout_express", "30m");
		biz_contentmap.put("product_code", "QUICK_MSECURITY_PAY");
		biz_contentmap.put("total_amount", price);
		biz_contentmap.put("subject", subject);
		biz_contentmap.put("body", body);
		biz_contentmap.put("out_trade_no", outTradeNo);
		
		Map<String, String> keyValues = new HashMap<String, String>();

		keyValues.put("app_id", APPID);

		keyValues.put("biz_content", JSONObject.fromObject(biz_contentmap).toString());
		
		keyValues.put("charset", "utf-8");

		keyValues.put("method", "alipay.trade.app.pay");

		keyValues.put("sign_type",  "RSA");

		keyValues.put("timestamp", StringUtil.date2StringTime(new Date()));

		keyValues.put("version", "1.0");
		keyValues.put("notify_url",  AzuraWechatUtil.BASE_URL + "/api/app/order/alipay/"+sessionid+"/alipay.json");

		
		String orderParam = OrderInfoUtil2_0.buildOrderParam(keyValues);

		String sign = OrderInfoUtil2_0.getSign(keyValues, RSA_PRIVATE, false);
	   String orderInfo = orderParam + "&" + sign;
		
		
		return orderInfo;
	}
	


	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	private String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}
	
	
	public static void refund(){
		
	}
	
	public static void main(String[] args) {
		
		Order order=new Order();
		order.setBn(System.currentTimeMillis()+"");
		order.setUuid(System.currentTimeMillis()+"");
		order.setPayment(new BigDecimal(0.01));
		String pay=	AliPay.pay(order.getBn(), order.getBn(), order.getBn(),order.getUuid(), order.getPayment().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
		System.err.println( pay);
	}
	
	
}
