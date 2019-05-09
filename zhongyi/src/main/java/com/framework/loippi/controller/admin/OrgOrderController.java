package com.framework.loippi.controller.admin;
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

import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 课程订单
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminOrgOrderController")
@RequestMapping({ "/admin/org_order" })
public class OrgOrderController extends GenericController {

	@Resource
	private OrderService orderService;
	
	@Resource
	private UserService userService;
	
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
		return "/admin/org_order/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Order order, RedirectAttributes redirectAttributes) {
		orderService.save(order);
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
		return "/admin/org_order/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		Order order = orderService.find(id);
		model.addAttribute("order", order);
		return "/admin/org_order/view";
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
	public String list(Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        } 
		
		Map paramter2 = RequestMap(request);
		pageable.setParameter(paramter2);
		
		model.addAttribute("paramter", paramter);
		
		Page<Order> pages = this.orderService.findByPage(pageable);
		
		model.addAttribute("page", pages);
		
		return "/admin/org_order/list";
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
	 *过滤 
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();
		
		String filter_bn = request.getParameter("filter_bn");
		String filter_phone = request.getParameter("filter_phone");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		String filter_status = request.getParameter("filter_status");
	
		User user = userService.getCurrent();
		if(user != null) {
			paramter.put("orgId", user.getOrgId());
		}
				
		if(!StringUtil.isEmpty(filter_bn)) {
			paramter.put("filter_bn", StringUtil.filterSQL(filter_bn));
		}
		
		if(!StringUtil.isEmpty(filter_phone)) {
			paramter.put("filter_phone", StringUtil.filterSQL(filter_phone));
		}
		
		if(!StringUtil.isEmpty(filter_sDate)) {
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
		
		if(!StringUtil.isEmpty(filter_eDate)) {
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
		
		if(!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}
		
		return paramter;
	}
	
	
	/**
	 * 启用课程
	 */
	@RequestMapping(value={"/startRate"},method={RequestMethod.POST})
	@ResponseBody
	public Message startParent(Long id) {
		Order order = this.orderService.find(id);
		order.setRateStatus(1);
		orderService.update(order);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 禁用家长
	 */
	@RequestMapping(value={"/forbidRate"},method={RequestMethod.POST})
	@ResponseBody
	public Message forbidParent(Long id) {
		Order order = this.orderService.find(id);
		order.setRateStatus(2);
		orderService.update(order);
		
		return SUCCESS_MESSAGE;
		
	}
}
