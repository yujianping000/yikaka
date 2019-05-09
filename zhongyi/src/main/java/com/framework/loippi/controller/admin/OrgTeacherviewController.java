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

import com.framework.loippi.entity.OrgTeacherview;
import com.framework.loippi.entity.TcommentScope;
import com.framework.loippi.entity.TcontinueClass;
import com.framework.loippi.entity.TremarkOn;
import com.framework.loippi.service.OrgTeacherviewService;
import com.framework.loippi.service.TcommentScopeService;
import com.framework.loippi.service.TcontinueClassService;
import com.framework.loippi.service.TremarkOnService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import java.util.HashMap;
import java.util.List;

/**
 * Controller - 机构教师
 * 
 * @author wyr
 * @version 2.0
 */
@Controller("adminOrgTeacherviewController")
@RequestMapping({ "/admin/org_teacherview" })
public class OrgTeacherviewController extends GenericController {

	@Resource
	private OrgTeacherviewService orgTeacherviewService;
	@Resource
	private TcommentScopeService tcommentScopeService;
	@Resource
	private TcontinueClassService tcontinueClassService;
	
	@Resource
	private TremarkOnService tremarkOnService;
	
	
	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/org_teacherview/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(OrgTeacherview orgTeacherview, RedirectAttributes redirectAttributes) {
		orgTeacherviewService.save(orgTeacherview);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		OrgTeacherview orgTeacherview = orgTeacherviewService.find(id);
		model.addAttribute("orgTeacherview", orgTeacherview);
		List<TcommentScope> tcommentScopes=tcommentScopeService.findList("uid", id);
		model.addAttribute("tcommentScopes", tcommentScopes);
		List<TcontinueClass> tcontinueClasss=tcontinueClassService.findList("uid", id);
		model.addAttribute("tcontinueClasss", tcontinueClasss);
		List<TremarkOn> tremarkOns=tremarkOnService.findList("uid", id);
		model.addAttribute("tremarkOns", tremarkOns);
		return "/admin/org_teacherview/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		OrgTeacherview orgTeacherview = orgTeacherviewService.find(id);
		model.addAttribute("orgTeacherview", orgTeacherview);
		return "/admin/org_teacherview/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(OrgTeacherview orgTeacherview, RedirectAttributes redirectAttributes) {
		orgTeacherviewService.update(orgTeacherview);
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
		model.addAttribute("page", this.orgTeacherviewService.findByPage(pageable));
		return "/admin/org_teacherview/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.orgTeacherviewService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
