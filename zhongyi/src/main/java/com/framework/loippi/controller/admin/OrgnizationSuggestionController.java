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

import com.framework.loippi.entity.OrgnizationSuggestion;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.OrgnizationSuggestionService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 机构意见
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminOrgnizationSuggestionController")
@RequestMapping({ "/admin/orgnization_suggestion" })
public class OrgnizationSuggestionController extends GenericController {

	@Resource
	private OrgnizationSuggestionService orgnizationSuggestionService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private UserService userService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/orgnization_suggestion/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(OrgnizationSuggestion orgnizationSuggestion, RedirectAttributes redirectAttributes) {
		orgnizationSuggestionService.save(orgnizationSuggestion);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		OrgnizationSuggestion orgnizationSuggestion = orgnizationSuggestionService.find(id);
		model.addAttribute("orgnizationSuggestion", orgnizationSuggestion);
		return "/admin/orgnization_suggestion/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		OrgnizationSuggestion orgnizationSuggestion = orgnizationSuggestionService.find(id);
		
		model.addAttribute("orgnizationSuggestion", orgnizationSuggestion);
		return "/admin/orgnization_suggestion/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(OrgnizationSuggestion orgnizationSuggestion, RedirectAttributes redirectAttributes) {
		orgnizationSuggestionService.update(orgnizationSuggestion);
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
		
		String phone = (String) paramter.get("phone");
		String content = (String) paramter.get("content");
		String createDate = (String) paramter.get("createDate");
		String sDate = (String) paramter.get("sDate");
		String eDate = (String) paramter.get("eDate");
		
		Map map=new HashMap();
		
		User user = userService.getCurrent();
		if(user != null) {
			map.put("orgId", user.getOrgId());
		}
		
		if(!StringUtil.isEmpty(phone)) {
			map.put("phone", StringUtil.filterSQL(phone));
		}
		
		if(!StringUtil.isEmpty(content)) {
			map.put("content", StringUtil.filterSQL(content));
		}
		
		if(!StringUtil.isEmpty(createDate)) {
			map.put("createDate", StringUtil.filterSQL(createDate));
		}
		
		if(!StringUtil.isEmpty(sDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(sDate);
			} catch (ParseException e) {
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
		Page<OrgnizationSuggestion> pages = this.orgnizationSuggestionService.findByPage(pageable);
		
		model.addAttribute("page",pages);
		
		return "/admin/orgnization_suggestion/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.orgnizationSuggestionService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
