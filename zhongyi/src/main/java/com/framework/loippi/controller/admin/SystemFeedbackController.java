package com.framework.loippi.controller.admin;
import java.text.ParseException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.framework.loippi.entity.SystemFeedback;
import com.framework.loippi.service.SystemFeedbackService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 意见反馈
 * 
 * @author wyr
 * @version 2.0
 */
@Controller("adminSystemFeedbackController")
@RequestMapping({ "/admin/system_feedback" })
public class SystemFeedbackController extends GenericController {

	@Resource
	private SystemFeedbackService systemFeedbackService;
	

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/system_feedback/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SystemFeedback systemFeedback, RedirectAttributes redirectAttributes) {
		systemFeedbackService.save(systemFeedback);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		SystemFeedback systemFeedback = systemFeedbackService.find(id);
		model.addAttribute("systemFeedback", systemFeedback);
		return "/admin/system_feedback/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		SystemFeedback systemFeedback = systemFeedbackService.find(id);
		
		if(systemFeedback != null) {
			model.addAttribute("systemFeedback", systemFeedback);
		}
		return "/admin/system_feedback/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SystemFeedback systemFeedback, RedirectAttributes redirectAttributes) {
		systemFeedbackService.update(systemFeedback);
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
		
		String mobile = (String) paramter.get("mobile");
		String content = (String) paramter.get("content");
		String sDate = (String) paramter.get("sDate");
		String eDate = (String) paramter.get("eDate");
		
		Map map=new HashMap();
		 if(!StringUtil.isEmpty(mobile)) {
			 map.put("mobile", StringUtil.filterSQL(mobile));
		 }
		 
		 if(!StringUtil.isEmpty(content)) {
			 map.put("content", StringUtil.filterSQL(content));
		 }
		 
		 if(!StringUtil.isEmpty(sDate)) {
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 Date date = null;
			 try {
				 date = format.parse(sDate);
			 } catch(ParseException e) {
				 e.printStackTrace();
			 }
			 sDate = (new SimpleDateFormat("yyyyMMdd")).format(date);
			
			 map.put("sDate", StringUtil.filterSQL(sDate));
		 }
		 
		 if(!StringUtil.isEmpty(eDate)) {
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 Date date = null;
			 try {
				date = format.parse(eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 eDate = (new SimpleDateFormat("yyyyMMdd")).format(date);
			 
			 map.put("eDate", StringUtil.filterSQL(eDate));
		 }
		  
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		Page<SystemFeedback> pages = this.systemFeedbackService.findByPage(pageable);
		
		model.addAttribute("page", pages);

		return "/admin/system_feedback/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.systemFeedbackService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
