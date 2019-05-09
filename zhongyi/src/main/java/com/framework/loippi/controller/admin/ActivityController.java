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

import com.framework.loippi.entity.Activity;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.ActivityService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.qiniu.FileServiceImpl;

import java.util.HashMap;

/**
 * Controller - 活动
 * 
 * @author wyr
 * @version 2.0
 */
@Controller("adminActivityController")
@RequestMapping({ "/admin/activity" })
public class ActivityController extends GenericController {
	@Resource
	private UserService userService;
	@Resource
	private ActivityService activityService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		String token = FileServiceImpl.getToken();
		
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
		
		return "/admin/activity/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Activity activity, RedirectAttributes redirectAttributes) {
		User user=	userService.getCurrent();
		activity.setUid(user.getId());
		activity.setTotalFeez(activity.getTotalSum());
		activity.setAlready(0);
		activity.setAlready2(0);
		activityService.save(activity);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		
		String token = FileServiceImpl.getToken();
		
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
		
		Activity activity = activityService.find(id);
		
		if(activity.getAlready2()==null){
			activity.setAlready2(0);
		}
		if(activity.getAlready()==null){
			activity.setAlready(0);
		}
		
		model.addAttribute("activity", activity);
		return "/admin/activity/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		Activity activity = activityService.find(id);
		if(activity.getAlready2()==null){
			activity.setAlready2(0);
		}
		if(activity.getAlready()==null){
			activity.setAlready(0);
		}
		model.addAttribute("activity", activity);
		return "/admin/activity/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Activity activity, RedirectAttributes redirectAttributes) {
		activityService.update(activity);
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
	            	map.put("filter_"+key,  paramter.get(key));
	            }
	        }  
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.activityService.findByPage(pageable));
		return "/admin/activity/list"; 
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.activityService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
