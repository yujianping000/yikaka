package com.framework.loippi.plugins.pc.ccb;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.controller.admin.GenericController;
import com.framework.loippi.entity.PluginConfig;
import com.framework.loippi.plugins.pc.PaymentPlugin;
import com.framework.loippi.service.PluginConfigAttributeService;
import com.framework.loippi.service.PluginConfigService;
import com.framework.loippi.support.Message;

/**
 * Controller - 中国建设银行网上支付
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Controller("adminCcbPaymentController")
@RequestMapping("/admin/payment_plugin/ccb_payment")
public class CcbPaymentController extends GenericController {

	@Resource(name = "ccbPaymentPlugin")
	private CcbPaymentPlugin ccbPaymentPlugin;
	@Resource(name = "pluginConfigServiceImpl")
	private PluginConfigService pluginConfigService;
	@Resource
	private PluginConfigAttributeService pluginConfigAttributeService;
	/**
	 * 安装
	 */
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	public @ResponseBody
	Message install() {
		if (!ccbPaymentPlugin.getIsInstalled()) {
			PluginConfig pluginConfig = new PluginConfig();
			pluginConfig.setPluginId(ccbPaymentPlugin.getId());
			pluginConfig.setEnabled(0);
			pluginConfig.setAttributes(null);
			pluginConfigService.save(pluginConfig);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 卸载
	 */
	@RequestMapping(value = "/uninstall", method = RequestMethod.POST)
	public @ResponseBody
	Message uninstall() {
		if (ccbPaymentPlugin.getIsInstalled()) {
			PluginConfig pluginConfig = ccbPaymentPlugin.getPluginConfig();
			pluginConfigService.delete(pluginConfig.getId());
			pluginConfigAttributeService.deleteByPluginId(ccbPaymentPlugin.getId());
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 设置
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(ModelMap model) {
		PluginConfig pluginConfig = ccbPaymentPlugin.getPluginConfig();
		model.addAttribute("feeTypes", PaymentPlugin.FeeType.values());
		model.addAttribute("pluginConfig", pluginConfig);
		return "/net/shopxx/plugin/ccbPayment/setting";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(String paymentName, String branchId, String posId, String partner, String key, boolean isPhishing, PaymentPlugin.FeeType feeType, BigDecimal fee, String logo, String description, @RequestParam(defaultValue = "0") Integer isEnabled, Integer order,
			RedirectAttributes redirectAttributes) {
		PluginConfig pluginConfig = ccbPaymentPlugin.getPluginConfig();
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put(PaymentPlugin.PAYMENT_NAME_ATTRIBUTE_NAME, paymentName);
		attributes.put("branchId", branchId);
		attributes.put("posId", posId);
		attributes.put("partner", partner);
		attributes.put("key", key);
		attributes.put("isPhishing", String.valueOf(isPhishing));
		attributes.put(PaymentPlugin.FEE_TYPE_ATTRIBUTE_NAME, feeType.toString());
		attributes.put(PaymentPlugin.FEE_ATTRIBUTE_NAME, fee.toString());
		attributes.put(PaymentPlugin.LOGO_ATTRIBUTE_NAME, logo);
		attributes.put(PaymentPlugin.DESCRIPTION_ATTRIBUTE_NAME, description);
		pluginConfig.setEnabled(isEnabled);
		pluginConfigService.updatePluginConfig(pluginConfig, attributes);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/payment_plugin/list.jhtml";
	}

}