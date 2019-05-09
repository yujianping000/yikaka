package com.framework.loippi.plugins.pc.abc;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.framework.loippi.entity.Payment;
import com.framework.loippi.entity.PluginConfig;
import com.framework.loippi.plugins.pc.PaymentPlugin;
import com.framework.loippi.support.Setting;
import com.framework.loippi.utils.SettingUtils;
import com.framework.loippi.utils.des.RSAUtils;

/**
 * Plugin - 中国农业银行网上支付
 * 
 * @author Loippi Team
 * @version 2.0
 */
@Component("abcPaymentPlugin")
public class AbcPaymentPlugin extends PaymentPlugin {

	/** 证书文件路径 */
	private static final String CERTIFICATE_FILE_PATH = AbcPaymentPlugin.class.getResource("").getFile() + "TrustPay.cer";

	/** 证书 */
	private static final Certificate CERTIFICATE;

	static {
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(CERTIFICATE_FILE_PATH));
			CERTIFICATE = RSAUtils.getCertificate("X.509", inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	@Override
	public String getName() {
		return "中国农业银行网上支付";
	}

	@Override
	public String getVersion() {
		return "2.0";
	}

	@Override
	public String getAuthor() {
		return "Loippi Team";
	}

	@Override
	public String getSiteUrl() {
		return "http://www.loippi.com";
	}

	@Override
	public String getInstallUrl() {
		return "abc_payment/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "abc_payment/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "abc_payment/setting.jhtml";
	}

	@Override
	public String getRequestUrl() {
		return "https://easyabc.95599.cn/b2c/trustpay/ReceiveMerchantIERequestServlet";
	}

	@Override
	public PaymentPlugin.RequestMethod getRequestMethod() {
		return PaymentPlugin.RequestMethod.post;
	}

	@Override
	public String getRequestCharset() {
		return "gb2312";
	}

	@Override
	public Map<String, Object> getParameterMap(String sn, String description, HttpServletRequest request) {
		Setting setting = SettingUtils.get();
		PluginConfig pluginConfig = getPluginConfig();
		Payment payment = getPayment(sn);

		Document document = DocumentHelper.createDocument();
		Element msg = document.addElement("MSG");
		Element message = msg.addElement("Message");

		Element merchant = message.addElement("Merchant");
		merchant.addElement("ECMerchantType").setText("B2C");
		merchant.addElement("MerchantID").setText(pluginConfig.getAttribute("merchantId"));

		Element trxRequest = message.addElement("TrxRequest");
		trxRequest.addElement("TrxType").setText("PayReq");

		Element order = trxRequest.addElement("Order");
		order.addElement("OrderNo").setText(sn);
		order.addElement("ExpiredDate").setText("7");
		order.addElement("OrderAmount").setText(payment.getAmount().setScale(2).toString());
		order.addElement("OrderDesc").setText(StringUtils.abbreviate(description, 60));
		order.addElement("OrderDate").setText(DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
		order.addElement("OrderTime").setText(DateFormatUtils.format(new Date(), "HH:mm:ss"));
		order.addElement("OrderURL").setText(setting.getSiteUrl());
		order.addElement("BuyIP").setText(request.getRemoteAddr());
		order.addElement("OrderItems");

		trxRequest.addElement("ProductType").setText("2");
		trxRequest.addElement("PaymentType").setText("A");
		trxRequest.addElement("NotifyType").setText("1");
		trxRequest.addElement("ResultNotifyURL").setText(getNotifyUrl(sn,PaymentPlugin.NotifyMethod.async));
		trxRequest.addElement("MerchantRemarks").setText("shopxx");
		trxRequest.addElement("PaymentLinkType").setText("1");

		msg.addElement("Signature-Algorithm").setText("SHA1withRSA");
		msg.addElement("Signature").setText(generateSign(merchant.asXML() + trxRequest.asXML()));

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("Signature", document.getRootElement().asXML());
		parameterMap.put("errorPage", setting.getSiteUrl());
		return parameterMap;
	}

	public String getSn(HttpServletRequest request) {
		try {
			String message = request.getParameter("MSG");
			Document document = DocumentHelper.parseText(message);
			return document.selectSingleNode("/MSG/Message/TrxResponse/OrderNo").getText().trim();
		} catch (DocumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 生成签名
	 * 
	 * @param message
	 *            消息
	 * @return 签名
	 */
	private String generateSign(String message) {
		try {
			PluginConfig pluginConfig = getPluginConfig();
			PrivateKey privateKey = RSAUtils.generatePrivateKey(pluginConfig.getAttribute("key"));
			return Base64.encodeBase64String(RSAUtils.sign("SHA1withRSA", privateKey, message.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 验证签名
	 * 
	 * @param message
	 *            消息
	 * @return 是否验证通过
	 */
	private boolean verifySign(String message) {
		try {
			Document document = DocumentHelper.parseText(message);
			byte[] sign = Base64.decodeBase64(document.selectSingleNode("/MSG/Signature").getText().trim());
			byte[] data = document.selectSingleNode("/MSG/Message").getText().trim().getBytes("gb2312");
			return RSAUtils.verify("SHA1withRSA", CERTIFICATE, sign, data);
		} catch (DocumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public boolean verifyNotify(String sn, NotifyMethod notifyMethod, HttpServletRequest request) {
		try {
			PluginConfig pluginConfig = getPluginConfig();
			String message = request.getParameter("MSG");
			Document document = DocumentHelper.parseText(message);
			String amount = document.selectSingleNode("/MSG/Message/TrxResponse/Amount").getText().trim();
			String returnCode = document.selectSingleNode("/MSG/Message/TrxResponse/ReturnCode").getText().trim();
			String merchantId = document.selectSingleNode("/MSG/Message/TrxResponse/MerchantID").getText().trim();
			Payment payment = getPayment(getSn(request));
			if (payment != null && verifySign(message) && pluginConfig.getAttribute("merchantId").equals(merchantId) && "0000".equals(returnCode) && payment.getAmount().compareTo(new BigDecimal(amount)) == 0) {
				return true;
			}
			return false;
		} catch (DocumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public String getNotifyMessage(String sn, NotifyMethod notifyMethod, HttpServletRequest request) {
		try {
			return PaymentPlugin.NotifyMethod.async.equals(notifyMethod) ? "<URL>" + getNotifyUrl(sn,PaymentPlugin.NotifyMethod.sync) + "?MSG=" + URLEncoder.encode(request.getParameter("MSG"), "UTF-8") + "</URL>" : null;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public Integer getTimeout() {
		return 21600;
	}

}