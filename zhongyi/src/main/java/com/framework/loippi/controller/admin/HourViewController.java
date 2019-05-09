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

import com.framework.loippi.entity.HourView;
import com.framework.loippi.service.HourViewService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;

/**
 * Controller - VIEW
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminHourViewController")
@RequestMapping({ "/admin/hour_view" })
public class HourViewController extends GenericController {

	@Resource
	private HourViewService hourViewService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/hour_view/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HourView hourView, RedirectAttributes redirectAttributes) {
		hourViewService.save(hourView);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		HourView hourView = hourViewService.find(id);
		model.addAttribute("hourView", hourView);
		return "/admin/hour_view/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		HourView hourView = hourViewService.find(id);
		model.addAttribute("hourView", hourView);
		return "/admin/hour_view/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HourView hourView, RedirectAttributes redirectAttributes) {
		hourViewService.update(hourView);
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
	@RequestMapping(value = { "/listorg/{orgId}/{teaId}" }, method = { RequestMethod.GET })
	public String list(@PathVariable Long orgId,@PathVariable Long teaId,Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
			model.addAttribute("paramter", paramter);
			
			String filter_sDate = request.getParameter("filter_sDate");
			String filter_eDate = request.getParameter("filter_eDate");
		  
			if(!StringUtil.isEmpty(filter_sDate)) {
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
			
			if(!StringUtil.isEmpty(filter_eDate)) {
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
			map.put("orgId", orgId);
			map.put("teacherId", teaId);
		pageable.setParameter(map);
		model.addAttribute("orgId", orgId);
		model.addAttribute("teaId", teaId);
		model.addAttribute("page", this.hourViewService.findByPage(pageable));
		return "/admin/hour_view/listorg";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.hourViewService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
