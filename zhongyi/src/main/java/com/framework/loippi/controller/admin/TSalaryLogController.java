package com.framework.loippi.controller.admin;

import java.math.BigDecimal;
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

import com.aliyun.common.comm.ServiceClient.Request;
import com.framework.loippi.entity.KeepAccounts;
import com.framework.loippi.entity.PaymentStatistics;
import com.framework.loippi.entity.TSalary;
import com.framework.loippi.entity.TSalaryLog;
import com.framework.loippi.entity.User;
import com.framework.loippi.entity.WithdrawView;
import com.framework.loippi.service.KeepAccountsService;
import com.framework.loippi.service.PaymentStatisticsService;
import com.framework.loippi.service.TSalaryLogService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import java.util.HashMap;
import java.util.List;

/**
 * Controller - 工资记录表
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminTSalaryLogController")
@RequestMapping({ "/admin/tsalary_log" })
public class TSalaryLogController extends GenericController {

	@Resource
	private TSalaryLogService tSalaryLogService;
	@Resource
	private UserService userService;

	@Resource
	private PaymentStatisticsService paymentStatisticsService;
	@Resource
	private KeepAccountsService keepAccountsService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/tsalary_log/add";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add_keep_accounts" }, method = { RequestMethod.GET })
	public String add_keep_accounts(ModelMap model) {
		return "/admin/tsalary_log/addKeepAccounts";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save_keep_accounts", method = RequestMethod.POST)
	public String save_keep_accounts(KeepAccounts keepAccounts, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		User user = userService.getCurrent();
		Long orgId = user.getOrgId();
		String payTime = request.getParameter("payTime");
		String tallyTime = request.getParameter("tallyTime");
		keepAccounts.setPayTime(StringUtil.StringDateToDate(payTime));
		keepAccounts.setTallyTime(StringUtil.StringDateToDate(tallyTime));
		keepAccounts.setOrgId(orgId);
		keepAccountsService.save(keepAccounts);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		PaymentStatistics paymentStatistics = new PaymentStatistics();
		paymentStatistics.setOrgId(orgId);
		paymentStatistics.setOfflineAccount(keepAccounts.getAmountMoney());
		paymentStatistics.setCreateTime(new Date());
		paymentStatisticsService.save(paymentStatistics);

		return "redirect:list.jhtml?filter_tallyType=2";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TSalaryLog tSalaryLog, RedirectAttributes redirectAttributes) {
		tSalaryLogService.save(tSalaryLog);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		TSalaryLog tSalaryLog = tSalaryLogService.find(id);
		model.addAttribute("tSalaryLog", tSalaryLog);
		return "/admin/tsalary_log/edit";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		TSalaryLog tSalaryLog = tSalaryLogService.find(id);
		model.addAttribute("tSalaryLog", tSalaryLog);
		return "/admin/tsalary_log/view";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TSalaryLog tSalaryLog, RedirectAttributes redirectAttributes) {
		tSalaryLogService.update(tSalaryLog);
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
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}
		map.put("orgId", orgId);
		if (orgId != null) {
			Map map1 = new HashMap();
			map1.put("orgId", orgId);
			String olSum = paymentStatisticsService.findSumOs(map1);
			model.addAttribute("olSum", olSum);
			Map map2 = new HashMap();
			map2.put("orgId", orgId);
			String ofaum = paymentStatisticsService.findSumOa(map2);
			model.addAttribute("ofaum", ofaum);
			Map map3 = new HashMap();
			map3.put("orgId", orgId);
			String ototalSum = paymentStatisticsService.findSumOaos(map3);
			model.addAttribute("ototalSum", ototalSum);
			Map map4 = new HashMap();
			map4.put("orgId", orgId);
			map4.put("today", 1);
			String olSumday = paymentStatisticsService.findSumOs(map4);
			model.addAttribute("olSumday", olSumday);
			Map map5 = new HashMap();
			map5.put("orgId", orgId);
			map5.put("today", 1);
			String ofaumday = paymentStatisticsService.findSumOa(map5);
			model.addAttribute("ofaumday", ofaumday);
			Map map6 = new HashMap();
			map6.put("orgId", orgId);
			map6.put("today", 1);
			String ototalSumday = paymentStatisticsService.findSumOaos(map6);
			model.addAttribute("ototalSumday", ototalSumday);
		}
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		if (map.get("tallyType") == null || map.get("tallyType").equals("1")) {
			map.put("tallyType", 1);
			model.addAttribute("tallyType", 1);
			model.addAttribute("page", this.tSalaryLogService.findByPage(pageable));
		} else {
			map.put("tallyType", 2);
			model.addAttribute("tallyType", map.get("tallyType"));
			model.addAttribute("page", this.keepAccountsService.findByPage(pageable));
		}
		return "/admin/tsalary_log/list";

	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.tSalaryLogService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 修改备注
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmRefuse" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmRefuse(Long id, String reason) {
		KeepAccounts tSalaryLog = keepAccountsService.find(id);
		tSalaryLog.setNote(reason);
		keepAccountsService.update(tSalaryLog);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 发放工资
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/issue" }, method = { RequestMethod.GET })
	public @ResponseBody Message issue(Long id) {
		TSalaryLog tSalaryLog = tSalaryLogService.find(id);
		tSalaryLog.setStatus(1);
		tSalaryLog.setPayTime(new Date());
		tSalaryLogService.update(tSalaryLog);

		PaymentStatistics paymentStatistics = new PaymentStatistics();
		paymentStatistics.setOnlineSalary(tSalaryLog.getTotalSalary());
		paymentStatistics.setOrgId(tSalaryLog.getOrgId());
		paymentStatistics.setCreateTime(new Date());
		paymentStatisticsService.save(paymentStatistics);

		return SUCCESS_MESSAGE;
	}

	/**
	 * 过滤
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();

		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_account = request.getParameter("filter_account");
		String filter_user_name = request.getParameter("filter_user_name");
		String filter_status = request.getParameter("filter_status");
		String filter_tallyType = request.getParameter("filter_tallyType");

		if (!StringUtil.isEmpty(filter_sDate)) {
			paramter.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
		}

		if (!StringUtil.isEmpty(filter_eDate)) {
			paramter.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		if (!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		if (!StringUtil.isEmpty(filter_user_name)) {
			paramter.put("filter_user_name", StringUtil.filterSQL(filter_user_name));
		}

		if (!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}
		if (!StringUtil.isEmpty(filter_tallyType)) {
			paramter.put("filter_tallyType", Integer.parseInt(StringUtil.filterSQL(filter_tallyType)));
		}
		return paramter;
	}

	/**
	 * 导出支出列表
	 */
	@RequestMapping(value = "salaryLogExport", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView expenseExport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map paramter = RequestMap(request);
		List<TSalaryLog> salaryLogList = tSalaryLogService.findList(paramter);

		String fileName = "支出表格" + ".xls";

		return new ModelAndView(new ExcelView(fileName, fileName,
				new String[] { "createTimeString", "account", "userName", "basicSalary", "courseSalary", "performanceSalary", "allTsutomu" , "bonus" , "pushMoney" , "reimburse" , "other" , "totalSalary" , "payTimeString" , "statusString" },
				new String[] { "工资日期", "帐号", "姓名", "底薪", "课时", "绩效", "全勤", "奖金", "提成", "报销", "其他", "合计", "发放时间", "状态" },
				new Integer[] { 5000, 5000, 5000, 5000, 5000, 5000,5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000 }, null, salaryLogList, null), model);

	}
}
