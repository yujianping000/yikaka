package com.framework.loippi.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.RandomGUIDUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.controller.app.model.OrderUid;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrderInfo;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.User;
import com.framework.loippi.entity.WithdrawView;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 订单表
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminOrderController")
@RequestMapping({ "/admin/order" })
public class OrderController extends GenericController {

	@Resource
	private OrderService orderService;

	@Resource
	private UserService userService;
	@Resource
	private CourseService courseService;
	@Resource
	private TParentService tParentService;

	@Resource
	private OrgnizationService orgnizationService;

	@Resource
	private OrgUserService orgUserService;

	@Resource
	private StudentService studentService;

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
			List<Course> courses = courseService.findList("orgId", orgId);
			model.addAttribute("courses", courses);
			List<OrderUid> uidList = orderService.findOrderUid(orgId);
			List<TParent> tParents = new ArrayList<>();
			for (OrderUid orderUid : uidList) {
				Long uid = orderUid.getUid();
				TParent tParent = tParentService.find(uid);
				tParents.add(tParent);
			}
			// List<TParent> tParents = tParentService.findList("orgId", orgId);
			model.addAttribute("tParents", tParents);
		}
		return "/admin/order/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save(Order order, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String payTime1 = request.getParameter("payTime1");
		if (!StringUtil.isEmpty(payTime1)) {
			payTime1 = payTime1 + ":00";
			order.setPayTime(StringUtil.StringDateTimeToDate(payTime1));
		}
		User user = userService.getCurrent();
		Long orgId = user.getOrgId();
		if (orgId != null) {
			order.setOrgId(orgId);
			Orgnization orgnization = orgnizationService.find(orgId);
			order.setOrgName(orgnization.getName());
		}
		Course course = courseService.find(order.getCourseId());
		order.setCourseName(course.getName());
		order.setTeacherId(course.getTeacherId());
		order.setPayment(order.getTotalFee());
		order.setImage(course.getImage());
		order.setOrderType(2);
		order.setCreatedTime(new Date());
		order.setUpdateTime(new Date());
		order.setType(1);
		order.setTargetId(course.getId());
		order.setTargetName(course.getName());
		order.setClasstimes(course.getPeriods());
		order.setTerm(course.getTerm());

		if (order.getStuId() != null) {
			Student student = studentService.find(order.getStuId());
			if (student != null) {
				order.setStuBirthday(student.getBirtchday());
				order.setStuSex(student.getSex());
				order.setStuName(student.getNickname());
				order.setStuAvatar(student.getAvatar());
			}
		}

		RandomGUIDUtil myGUID = new RandomGUIDUtil();
		String uuid = myGUID.valueAfterMD5;
		order.setUuid(uuid);
		order.setStatus(2);

		orderService.toBuyNow2(order, user, course);

		// 订单付款成功，则机构学生总数加一
		Orgnization orgnization = orgnizationService.find(user.getOrgId());
		orgnization.setStudentCount(orgnization.getStudentCount() + 1);
		orgnizationService.update(orgnization);

		// 机构用户表添加一条记录
		OrgUser orgUser = new OrgUser();
		orgUser.setCreateTime(new Date());
		orgUser.setOrgId(user.getOrgId());
		orgUser.setUid(order.getStuId());
		orgUser.setType(1);
		orgUserService.save(orgUser);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		Order order = orderService.find(id);
		model.addAttribute("order", order);
		return "/admin/order/edit";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		Order order = orderService.find(id);
		model.addAttribute("order", order);
		return "/admin/order/view";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Order order, RedirectAttributes redirectAttributes) {
		orderService.update(order);
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
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}
		if (map.get("tallyType") == null) {
			map.put("tallyType", 1);
			model.addAttribute("tallyType", 1);
		} else {
			map.put("tallyType", map.get("tallyType"));
			model.addAttribute("tallyType", map.get("tallyType"));
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

		if (orgId != null) {
			map.put("orgId", orgId);
		}
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);

		if (orgId != null) {
			Map map1 = new HashMap();
			map1.put("orgId", orgId);
			String totalfee = orderService.findTotalFee(map1);
			model.addAttribute("totalfee", totalfee);

			Map map2 = new HashMap();
			map2.put("orgId", orgId);
			map2.put("orderType", 1);
			String totalOn = orderService.findTotalFee(map2);
			model.addAttribute("totalOn", totalOn);

			Map map3 = new HashMap();
			map3.put("orgId", orgId);
			map3.put("orderType", 2);
			String totalOff = orderService.findTotalFee(map3);
			model.addAttribute("totalOff", totalOff);

			Map map4 = new HashMap();
			map4.put("orgId", orgId);
			String totalNum = orderService.findTotaNum(map4);
			model.addAttribute("totalNum", totalNum);

			Map map5 = new HashMap();
			map5.put("orgId", orgId);
			map5.put("orderType", 1);
			String totalNumOn = orderService.findTotaNum(map5);
			model.addAttribute("totalNumOn", totalNumOn);

			Map map6 = new HashMap();
			map6.put("orgId", orgId);
			map6.put("orderType", 2);
			String totalNumOff = orderService.findTotaNum(map6);
			model.addAttribute("totalNumOff", totalNumOff);

			Map map7 = new HashMap();
			map7.put("orgId", orgId);
			map7.put("today", 1);
			String totalfeeday = orderService.findTotalFee(map7);
			model.addAttribute("totalfeeday", totalfeeday);

			Map map8 = new HashMap();
			map8.put("orgId", orgId);
			map8.put("orderType", 1);
			map8.put("today", 1);
			String totalOnday = orderService.findTotalFee(map8);
			model.addAttribute("totalOnday", totalOnday);

			Map map9 = new HashMap();
			map9.put("orgId", orgId);
			map9.put("orderType", 2);
			map9.put("today", 1);
			String totalOffday = orderService.findTotalFee(map9);
			model.addAttribute("totalOffday", totalOffday);

			Map map10 = new HashMap();
			map10.put("orgId", orgId);
			map10.put("today", 1);
			String totalNumday = orderService.findTotaNum(map10);
			model.addAttribute("totalNumday", totalNumday);

			Map map11 = new HashMap();
			map11.put("orgId", orgId);
			map11.put("orderType", 1);
			map11.put("today", 1);
			String totalNumOnday = orderService.findTotaNum(map11);
			model.addAttribute("totalNumOnday", totalNumOnday);

			Map map12 = new HashMap();
			map12.put("orgId", orgId);
			map12.put("orderType", 2);
			map12.put("today", 1);
			String totalNumOffday = orderService.findTotaNum(map12);
			model.addAttribute("totalNumOffday", totalNumOffday);

		}
		OrderInfo orderInfo = orderService.SeachTotalFeeSeach(map);

		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("page", this.orderService.findByPage(pageable));
		return "/admin/order/list";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/plath_list" }, method = { RequestMethod.GET })
	public String plath_list(Pageable pageable, HttpServletRequest request, ModelMap model) {
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_sDate = request.getParameter("filter_sDate");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}
		if (map.get("tallyType") == null) {
			map.put("tallyType", 1);
			model.addAttribute("tallyType", 1);
		} else {
			map.put("tallyType", map.get("tallyType"));
			model.addAttribute("tallyType", map.get("tallyType"));
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

		Map map1 = new HashMap();
		String totalfee = orderService.findTotalFee(map1);
		model.addAttribute("totalfee", totalfee);

		Map map2 = new HashMap();
		map2.put("orderType", 1);
		String totalOn = orderService.findTotalFee(map2);
		model.addAttribute("totalOn", totalOn);

		Map map3 = new HashMap();
		map3.put("orderType", 2);
		String totalOff = orderService.findTotalFee(map3);
		model.addAttribute("totalOff", totalOff);

		Map map4 = new HashMap();
		String totalNum = orderService.findTotaNum(map4);
		model.addAttribute("totalNum", totalNum);

		Map map5 = new HashMap();
		map5.put("orderType", 1);
		String totalNumOn = orderService.findTotaNum(map5);
		model.addAttribute("totalNumOn", totalNumOn);

		Map map6 = new HashMap();
		map6.put("orderType", 2);
		String totalNumOff = orderService.findTotaNum(map6);
		model.addAttribute("totalNumOff", totalNumOff);

		Map map7 = new HashMap();
		map7.put("today", 1);
		String totalfeeday = orderService.findTotalFee(map7);
		model.addAttribute("totalfeeday", totalfeeday);

		Map map8 = new HashMap();
		map8.put("orderType", 1);
		map8.put("today", 1);
		String totalOnday = orderService.findTotalFee(map8);
		model.addAttribute("totalOnday", totalOnday);

		Map map9 = new HashMap();
		map9.put("orderType", 2);
		map9.put("today", 1);
		String totalOffday = orderService.findTotalFee(map9);
		model.addAttribute("totalOffday", totalOffday);

		Map map10 = new HashMap();
		map10.put("today", 1);
		String totalNumday = orderService.findTotaNum(map10);
		model.addAttribute("totalNumday", totalNumday);

		Map map11 = new HashMap();
		map11.put("orderType", 1);
		map11.put("today", 1);
		String totalNumOnday = orderService.findTotaNum(map11);
		model.addAttribute("totalNumOnday", totalNumOnday);

		Map map12 = new HashMap();
		map12.put("orderType", 2);
		map12.put("today", 1);
		String totalNumOffday = orderService.findTotaNum(map12);
		model.addAttribute("totalNumOffday", totalNumOffday);

		OrderInfo orderInfo = orderService.SeachTotalFeeSeach(map);

		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("page", this.orderService.findByPage(pageable));
		return "/admin/order/plath_list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.orderService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 修改备注操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/editRemark" }, method = { RequestMethod.GET })
	public @ResponseBody Message editRemark(Long id, String remark) {
		Order order = this.orderService.find(id);
		order.setRemark(remark);
		orderService.update(order);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 过滤
	 */
	private Map RequestMap(HttpServletRequest request) {
		User user = userService.getCurrent();
		Long orgId = user.getOrgId();

		Map<String, Object> paramter = new HashMap<>();

		String filter_payType = request.getParameter("filter_payType");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_tallyType = request.getParameter("filter_tallyType");

		if (!StringUtil.isEmpty(filter_payType)) {
			paramter.put("payType", StringUtil.filterSQL(filter_payType));
		}

		if (!StringUtil.isEmpty(filter_sDate)) {
			// paramter.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
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
			// paramter.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
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

		if (!StringUtil.isEmpty(filter_tallyType)) {
			paramter.put("tallyType", Integer.parseInt(StringUtil.filterSQL(filter_tallyType)));
		}

		return paramter;
	}

	/**
	 * 导出机构收入列表
	 */
	@RequestMapping(value = "tOrderExport", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView expenseExport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.getCurrent();
		Long orgId = user.getOrgId();
		Map paramter = RequestMap(request);
		paramter.put("orgId", orgId);
		List<Order> orderList = orderService.findList(paramter);

		String fileName = "收入管理表格" + ".xls";

		return new ModelAndView(
				new ExcelView(fileName, fileName,
						new String[] { "bn", "phone", "unickname", "courseName", "totalFee", "procedureRate",
								"payTypeString", "serialNumber", "payTimeString2" },
						new String[] { "订单编号", "帐号", "姓名", "课程名称", "订单金额", "手续费", "支付方式", "交易流水号", "支付时间" },
						new Integer[] { 7000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000 }, null, orderList, null),
				model);

	}

	/**
	 * 导出平台收入列表
	 */
	@RequestMapping(value = "tOrderExport2", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView expenseExport2(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map paramter = RequestMap(request);
		List<Order> orderList = orderService.findList(paramter);

		String fileName = "收入管理表格" + ".xls";

		return new ModelAndView(
				new ExcelView(fileName, fileName,
						new String[] { "bn", "phone", "unickname", "courseName", "totalFee", "procedureRate",
								"payTypeString", "serialNumber", "payTimeString2" },
						new String[] { "订单编号", "帐号", "姓名", "课程名称", "订单金额", "手续费", "支付方式", "交易流水号", "支付时间" },
						new Integer[] { 7000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000 }, null, orderList, null),
				model);

	}

}
