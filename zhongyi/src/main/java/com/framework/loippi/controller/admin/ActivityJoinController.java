package com.framework.loippi.controller.admin;
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

import com.framework.loippi.entity.ActivityJoin;
import com.framework.loippi.service.ActivityJoinService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import java.util.HashMap;

/**
 * Controller - 活动报名
 * 
 * @author wyr
 * @version 2.0
 */
@Controller("adminActivityJoinController")
@RequestMapping({ "/admin/activity_join" })
public class ActivityJoinController extends GenericController {

	@Resource
	private ActivityJoinService activityJoinService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/activity_join/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ActivityJoin activityJoin, RedirectAttributes redirectAttributes) {
		activityJoinService.save(activityJoin);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		ActivityJoin activityJoin = activityJoinService.find(id);
		model.addAttribute("activityJoin", activityJoin);
		return "/admin/activity_join/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		ActivityJoin activityJoin = activityJoinService.find(id);
		model.addAttribute("activityJoin", activityJoin);
		return "/admin/activity_join/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ActivityJoin activityJoin, RedirectAttributes redirectAttributes) {
		activityJoinService.update(activityJoin);
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
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.activityJoinService.findByPage(pageable));
		return "/admin/activity_join/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.activityJoinService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
