package com.framework.loippi.plugins.pc.paypal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.framework.loippi.entity.Payment;
import com.framework.loippi.entity.PluginConfig;
import com.framework.loippi.plugins.pc.PaymentPlugin;
import com.framework.loippi.utils.web.WebUtils;

/**
 * Plugin - Paypal
 * 
 * @author Loippi Team
 * @version 2.0
 */
@Component("paypalPaymentPlugin")
public class PaypalPaymentPlugin extends PaymentPlugin {

	/**
	 * 货币
	 */
	public enum Currency {

		/** 美元 */
		USD,

		/** 澳大利亚元 */
		AUD,

		/** 加拿大元 */
		CAD,

		/** 捷克克郎 */
		CZK,

		/** 丹麦克朗 */
		DKK,

		/** 欧元 */
		EUR,

		/** 港元 */
		HKD,

		/** 匈牙利福林 */
		HUF,

		/** 新西兰元 */
		NZD,

		/** 挪威克朗 */
		NOK,

		/** 波兰兹罗提 */
		PLN,

		/** 英镑 */
		GBP,

		/** 新加坡元 */
		SGD,

		/** 瑞典克朗 */
		SEK,

		/** 瑞士法郎 */
		CHF,

		/** 日元 */
		JPY
	}

	@Override
	public String getName() {
		return "Paypal";
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
		return "paypal_payment/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "paypal_payment/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "paypal_payment/setting.jhtml";
	}

	@Override
	public String getRequestUrl() {
		return "https://www.paypal.com/cgi-bin/webscr";
	}

	@Override
	public PaymentPlugin.RequestMethod getRequestMethod() {
		return PaymentPlugin.RequestMethod.post;
	}

	@Override
	public String getRequestCharset() {
		return "UTF-8";
	}

	@Override
	public Map<String, Object> getParameterMap(String sn, String description, HttpServletRequest request) {
		PluginConfig pluginConfig = getPluginConfig();
		Payment payment = getPayment(sn);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("cmd", "_xclick");
		parameterMap.put("business", pluginConfig.getAttribute("partner"));
		parameterMap.put("item_name", StringUtils.abbreviate(description.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 100));
		parameterMap.put("amount", payment.getAmount().setScale(2).toString());
		parameterMap.put("currency_code", pluginConfig.getAttribute("currency"));
		parameterMap.put("return", getNotifyUrl(sn, PaymentPlugin.NotifyMethod.sync));
		parameterMap.put("notify_url", getNotifyUrl(sn, PaymentPlugin.NotifyMethod.async));
		parameterMap.put("invoice", sn);
		parameterMap.put("charset", "UTF-8");
		parameterMap.put("no_shipping", "1");
		parameterMap.put("no_note", "0");
		parameterMap.put("rm", "2");
		parameterMap.put("custom", "shopxx");
		return parameterMap;
	}

	@Override
	public boolean verifyNotify(String sn, PaymentPlugin.NotifyMethod notifyMethod, HttpServletRequest request) {
		PluginConfig pluginConfig = getPluginConfig();
		Payment payment = getPayment(request.getParameter("invoice"));
		if (payment != null && pluginConfig.getAttribute("partner").equals(request.getParameter("receiver_email")) && pluginConfig.getAttribute("currency").equals(request.getParameter("mc_currency")) && "Completed".equals(request.getParameter("payment_status"))
				&& payment.getAmount().compareTo(new BigDecimal(request.getParameter("mc_gross"))) == 0) {
			Map<String, Object> parameterMap = new LinkedHashMap<String, Object>();
			parameterMap.put("cmd", "_notify-validate");
			parameterMap.putAll(request.getParameterMap());
			if ("VERIFIED".equals(WebUtils.post("https://www.paypal.com/cgi-bin/webscr", parameterMap))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getNotifyMessage(String sn ,PaymentPlugin.NotifyMethod notifyMethod, HttpServletRequest request) {
		return null;
	}

	@Override
	public Integer getTimeout() {
		return 21600;
	}

}