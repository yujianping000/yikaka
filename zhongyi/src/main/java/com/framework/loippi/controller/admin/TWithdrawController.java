package com.framework.loippi.controller.admin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.AccountView;
import com.framework.loippi.entity.OffWork;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrderInfo;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.PaymentStatistics;
import com.framework.loippi.entity.TBank;
import com.framework.loippi.entity.TWithdraw;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.User;
import com.framework.loippi.entity.ValidTotal;
import com.framework.loippi.entity.WithdrawInfo;
import com.framework.loippi.entity.WithdrawView;
import com.framework.loippi.service.AccountViewService;
import com.framework.loippi.service.KeepAccountsService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.PaymentStatisticsService;
import com.framework.loippi.service.TBankService;
import com.framework.loippi.service.TWithdrawService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.ValidTotalService;
import com.framework.loippi.service.WithdrawViewService;
import com.framework.loippi.service.impl.UserServiceImpl;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.XDigestUtils;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - tWithdraw
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminTWithdrawController")
@RequestMapping({ "/admin/twithdraw" })
public class TWithdrawController extends GenericController {

	@Resource
	private OrderService orderService;
	@Resource
	private TWithdrawService tWithdrawService;
	@Resource
	private TBankService tBankService;
	@Resource
	private UserService userService;
	@Resource
	private ValidTotalService validTotalService;
	@Resource
	private PaymentStatisticsService paymentStatisticsService;
	@Resource
	private WithdrawViewService withdrawViewService;
	@Resource
	private AccountViewService keepAccountsViewService;
	@Resource
	private KeepAccountsService keepAccountsService;
	@Resource
	private OrgnizationService orgnizationService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		User user = userService.getCurrent();
		Long orgId = user.getOrgId();
		if (orgId != null) {
			Map map = new HashMap();
			map.put("orgId", orgId);
			List<TBank> lists = tBankService.findList(map);
			for (TBank tBank : lists) {
				String num = tBank.getBankAccount();
				tBank.setNumber(num.substring(num.length() - 4, num.length()));
			}
			model.addAttribute("lists", lists);
		}
		ValidTotal validTotal = validTotalService.find("orgId", orgId);
		model.addAttribute("validTotal", validTotal);
		Orgnization orgnization = orgnizationService.find(orgId);
		model.addAttribute("orgnization", orgnization);
		return "/admin/twithdraw/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save(TWithdraw tWithdraw, RedirectAttributes redirectAttributes, Long tBankId) {
		User user = userService.getCurrent();
		Long orgId = user.getOrgId();
		TBank tBank = tBankService.find(tBankId);
		tWithdraw.setBankName(tBank.getBankName());
		tWithdraw.setBankUserName(tBank.getBankUserName());
		tWithdraw.setBankNum(tBank.getBankAccount());
		tWithdraw.setOrgId(orgId);
		tWithdraw.setWithdrawTime(new Date());
		// 提现金额
		BigDecimal withdrawTotal = tWithdraw.getWithdrawTotal();
		// 手续费
		BigDecimal procedureTotal = tWithdraw.getProcedureTotal();
		if (tWithdraw.getProcedureTotal() != null) {
			tWithdraw.setToAccountTotal(
					withdrawTotal.subtract(withdrawTotal.multiply(procedureTotal.divide(new BigDecimal(100)))));
		}
		tWithdrawService.save(tWithdraw);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		TWithdraw tWithdraw = tWithdrawService.find(id);
		model.addAttribute("tWithdraw", tWithdraw);
		return "/admin/twithdraw/edit";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		TWithdraw tWithdraw = tWithdrawService.find(id);
		model.addAttribute("tWithdraw", tWithdraw);
		return "/admin/twithdraw/view";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TWithdraw tWithdraw, RedirectAttributes redirectAttributes) {
		tWithdrawService.update(tWithdraw);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {
		User user = userService.getCurrent();
		Long orgId = user.getOrgId();
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_sDate = request.getParameter("filter_sDate");
		Map map = new HashMap();
		if (orgId != null) {
			map.put("orgId", orgId);
			ValidTotal validTotal = validTotalService.find("orgId", orgId);
			model.addAttribute("validTotal", validTotal);
		}
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}

		if (!StringUtil.isEmpty(filter_eDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_eDate = (new SimpleDateFormat("yyyyMMdd")).format(date);

			map.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		if (!StringUtil.isEmpty(filter_sDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_sDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_sDate = (new SimpleDateFormat("yyyyMMdd")).format(date);

			map.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
		}

		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.tWithdrawService.findByPage(pageable));
		return "/admin/twithdraw/list";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/platform_list" }, method = { RequestMethod.GET })
	public String platform_list(Pageable pageable, HttpServletRequest request, ModelMap model) {

		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_sDate = request.getParameter("filter_sDate");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}

		if (!StringUtil.isEmpty(filter_eDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_eDate = (new SimpleDateFormat("yyyyMMdd")).format(date);

			map.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		if (!StringUtil.isEmpty(filter_sDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_sDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_sDate = (new SimpleDateFormat("yyyyMMdd")).format(date);

			map.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
		}

		WithdrawInfo tWithdraw = tWithdrawService.seachListByPage(map);
		model.addAttribute("tWithdraw", tWithdraw);
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.tWithdrawService.findByPage(pageable));
		return "/admin/twithdraw/platform_list";
	}

	/**
	 * 支出查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/platform_expense" }, method = { RequestMethod.GET })
	public String platform_expense(Pageable pageable, HttpServletRequest request, ModelMap model) {

		User user = userService.getCurrent();
		Long orgId = user.getOrgId();

		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}
		Map map1 = new HashMap();
		String olSum = paymentStatisticsService.findSumOw(map1);
		model.addAttribute("olSum", olSum);
		Map map2 = new HashMap();
		String ofaum = paymentStatisticsService.findSumOa(map2);
		model.addAttribute("ofaum", ofaum);
		Map map3 = new HashMap();
		String ototalSum = paymentStatisticsService.findSumOaow(map3);
		model.addAttribute("ototalSum", ototalSum);
		Map map4 = new HashMap();
		map4.put("today", 1);
		String olSumday = paymentStatisticsService.findSumOw(map4);
		model.addAttribute("olSumday", olSumday);
		Map map5 = new HashMap();
		map5.put("today", 1);
		String ofaumday = paymentStatisticsService.findSumOa(map5);
		model.addAttribute("ofaumday", ofaumday);
		Map map6 = new HashMap();
		map6.put("today", 1);
		String ototalSumday = paymentStatisticsService.findSumOaow(map6);
		model.addAttribute("ototalSumday", ototalSumday);
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		if (map.get("tallyType") == null) {
			map.put("tallyType", 1);
			model.addAttribute("tallyType", 1);
			model.addAttribute("page", this.withdrawViewService.findByPage(pageable));
		} else {
			map.put("tallyType", map.get("tallyType"));
			model.addAttribute("tallyType", map.get("tallyType"));
			if (map.get("tallyType").equals("1")) {
				model.addAttribute("page", this.withdrawViewService.findByPage(pageable));
			} else {
				model.addAttribute("page", this.keepAccountsViewService.findByPage(pageable));
			}

		}

		return "/admin/twithdraw/platformExpense";
	}

	/**
	 * 订单列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/order_list" }, method = { RequestMethod.GET })
	public String order_list(Pageable pageable, HttpServletRequest request, ModelMap model, Long orgId) {

		Map paramter = RequestMap(request, orgId);
		pageable.setParameter(paramter);

		model.addAttribute("paramter", paramter);

		Page<Order> pages = this.orderService.findByPage(pageable);

		model.addAttribute("page", pages);

		return "/admin/twithdraw/orderList";
	}

	/**
	 * 详情查询列表
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/keep_accounts_list" }, method = { RequestMethod.GET })
	public String keep_accounts_list(Pageable pageable, HttpServletRequest request, ModelMap model, Long orgId) {

		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}
		map.put("orgId", orgId);
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.keepAccountsService.findByPage(pageable));
		return "/admin/twithdraw/keepAccountlist";

	}

	/**
	 * 确认操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmPass" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmPass(Long id, Integer dayNum) {
		TWithdraw tWithdraw = tWithdrawService.find(id);
		tWithdraw.setStatus(1);
		tWithdrawService.update(tWithdraw);

		ValidTotal validTotal = validTotalService.find("orgId", tWithdraw.getOrgId());
		validTotal.setTotal(validTotal.getTotal().subtract(tWithdraw.getWithdrawTotal()));
		validTotalService.update(validTotal);
		PaymentStatistics paymentStatistics = new PaymentStatistics();
		paymentStatistics.setOrgId(tWithdraw.getOrgId());
		paymentStatistics.setOnlineWithdraw(tWithdraw.getToAccountTotal());
		paymentStatistics.setCreateTime(new Date());
		paymentStatisticsService.save(paymentStatistics);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 拒绝操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmRefuse" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmRefuse(Long id, String reason) {
		TWithdraw tWithdraw = tWithdrawService.find(id);
		tWithdraw.setStatus(3);
		tWithdraw.setNote(reason);
		tWithdrawService.update(tWithdraw);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.tWithdrawService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 过滤
	 */
	private Map RequestMap(HttpServletRequest request, Long orgId) {
		Map<String, Object> paramter = new HashMap<>();

		String filter_bn = request.getParameter("filter_bn");
		String filter_phone = request.getParameter("filter_phone");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_status = request.getParameter("filter_status");
		// 订单类型 1-课程 2-活动报名
		int type = 1;
		paramter.put("orgId", orgId);
		if (!StringUtil.isEmpty(filter_bn)) {
			paramter.put("filter_bn", StringUtil.filterSQL(filter_bn));
		}

		if (!StringUtil.isEmpty(filter_phone)) {
			paramter.put("filter_phone", StringUtil.filterSQL(filter_phone));
		}

		if (!StringUtil.isEmpty(filter_sDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_sDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_sDate = (new SimpleDateFormat("yyyyMMdd")).format(date);

			paramter.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
		}

		if (!StringUtil.isEmpty(filter_eDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_eDate = (new SimpleDateFormat("yyyyMMdd")).format(date);

			paramter.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		if (!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}

		paramter.put("type", type);

		return paramter;
	}

	/**
	 * 更新手续费
	 */
	@RequestMapping(value = "/updateProcedureTotal", method = RequestMethod.POST)
	public String updateProcedureTotal(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String orgId = request.getParameter("orgId");
		String procedureTotal = request.getParameter("procedure_total");

		if (!StringUtil.isEmpty(orgId)) {
			Orgnization orgnization = orgnizationService.find("id", Long.parseLong(orgId));

			if (orgnization != null) {
				orgnization.setFee(new BigDecimal(procedureTotal));

				orgnizationService.update(orgnization);
			}

			TWithdraw tWithdraw = tWithdrawService.find("orgId", Long.parseLong(orgId));

			if (tWithdraw != null) {
				tWithdraw.setProcedureTotal(new BigDecimal(procedureTotal));

				tWithdrawService.update(tWithdraw);
			}

			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		}

		return "redirect:/admin/twithdraw/platform_expense.jhtml";

	}
	/**
	 *过滤 
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();
		
		String filter_userAccnout = request.getParameter("filter_userAccnout");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_status = request.getParameter("filter_status");
				
		if(!StringUtil.isEmpty(filter_userAccnout)) {
			paramter.put("filter_userAccnout", StringUtil.filterSQL(filter_userAccnout));
		}
		
		if(!StringUtil.isEmpty(filter_sDate)) {
			paramter.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
		}
		
		if(!StringUtil.isEmpty(filter_eDate)) {
			paramter.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}
		
		if(!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}
		
		return paramter;
	}

	/**
	 * 导出提现列表
	 */
	@RequestMapping(value = "tWithdrawExport", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tWithdrawExport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map paramter = RequestMap(request);

		List<TWithdraw> tWithdrawList = tWithdrawService.findList(paramter);

		String fileName = "提现列表" + ".xls";

		return new ModelAndView(new ExcelView(fileName, fileName,
				new String[] { "id", "userAccnout","withdrawTimeString", "withdrawTotal", "procedureTotal", "toAccountTotal", "bankName",
						"bankNum", "bankUserName", "note", "statusString" },
				new String[] { "ID","账号", "提现时间", "提现金额(元)", "手续费(%)", "到账金额(元)", "银行名称", "银行卡号", "持卡人", "备注", "状态"},
				new Integer[] { 3000, 5000, 5000, 5000,5000, 5000, 5000, 7000, 4000, 3000, 3000 }, null, tWithdrawList, null), model);

	}
	/**
	 * 导出支出列表
	 */
	@RequestMapping(value = "expenseExport", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView expenseExport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map paramter = RequestMap(request);
		List<WithdrawView>	withdrawView=withdrawViewService.findListByPage(paramter);

		String fileName = "支出表格" + ".xls";

		return new ModelAndView(new ExcelView(fileName, fileName,
				new String[] { "account", "name","serviceName", "servicePhone", "fee", "totalday", "withdrawTotal"},
				new String[] { "机构账号","机构名称", "联系人", "联系电话", "手续费(%)", "今日提现金额（元）", "累计提现金额（元）"},
				new Integer[] {5000, 5000, 5000,5000, 5000, 5000 }, null, withdrawView, null), model);

	}
	
	

}
