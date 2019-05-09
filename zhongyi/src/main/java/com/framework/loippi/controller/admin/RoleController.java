package com.framework.loippi.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.Acl;
import com.framework.loippi.entity.Role;
import com.framework.loippi.entity.SystemFeedback;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.AclService;
import com.framework.loippi.service.RoleService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
/**
 * Controlelr - 角色
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Controller("adminRoleController")
@RequestMapping("/admin/role")
public class RoleController extends GenericController {

	@Resource
	private AclService aclService;

	@Resource
	private RoleService roleService;

	@Resource
	private UserService userService;
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<Acl> roots = aclService.findRoots();
		for (Acl acl : roots) {
			List<Acl> childrens = aclService.findChildrens(acl.getId());
			for (Acl child : childrens) {
				List<Acl> btns = aclService.findChildrens(child.getId());
				child.getChildren().addAll(btns);
			}
			acl.getChildren().addAll(childrens);
			
		}
		model.addAttribute("roots", roots);
		return "/admin/role/add";
	}

	/**
	 * 平台 -保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Role role, Long[] ids, RedirectAttributes redirectAttributes) {
		
		role.setType(1);
		role.setCreateDate(new Date());
		
		//添加菜单的父级菜单
		if(ids!=null&&ids.length>0){
			List listids=new ArrayList();
			for (int i = 0; i < ids.length; i++) {
				listids.add(ids[i]);
			}
			
			Map params=new HashMap();
			params.put("acls", "1");
			params.put("ids",listids);
			List<Acl> 	acls=aclService.findList(params);
			if(acls!=null){
				for (int i = 0; i < acls.size(); i++) {
					listids.add(acls.get(i).getId());
				}
			}
			


			Long[] longs = new Long[listids.size()];

			listids.toArray(longs);
			
			roleService.save(role,(Long[])  longs);
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		List<Acl> roots = aclService.findRoots();
		for (Acl acl : roots) {
			List<Acl> childrens = aclService.findChildrens(acl.getId());
			for (Acl child : childrens) {
				List<Acl> btns = aclService.findChildrens(child.getId());
				child.getChildren().addAll(btns);
			}
			acl.getChildren().addAll(childrens);
			
		}
		model.addAttribute("roots", roots);
		model.addAttribute("role", roleService.findRoleAndAcls(id));
		return "/admin/role/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Role role, Long[] ids, RedirectAttributes redirectAttributes) {
		
		//添加菜单的父级菜单
				if(ids!=null&&ids.length>0){
					List listids=new ArrayList();
					for (int i = 0; i < ids.length; i++) {
						listids.add(ids[i]);
					}
					
					Map params=new HashMap();
					params.put("acls", "1");
					params.put("ids",listids);
					List<Acl> 	acls=aclService.findList(params);
					if(acls!=null){
						for (int i = 0; i < acls.size(); i++) {
							listids.add(acls.get(i).getId());
						}
					}
					Long[] longs = new Long[listids.size()];

					listids.toArray(longs);
					
					roleService.update(role,longs);
				}
		
		
	
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequiresPermissions("admin:system:role")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Pageable pageable, ModelMap model) {
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		paramter.put("type", 1);
		pageable.setParameter(paramter);

		model.addAttribute("page", roleService.findByPage(pageable));

		return "/admin/role/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Long[] ids) {
		for (Long id : ids) {
			roleService.delete(id);
		}

		return SUCCESS_MESSAGE;
	}
	
	//============机构
	/**
	 * 添加
	 */
	@RequestMapping(value = "/addorg", method = RequestMethod.GET)
	public String addorg(ModelMap model) {
	
		return "/admin/role/addorg";
	}

	/**
	 * 机构 -保存
	 */
	@RequestMapping(value = "/saveorg", method = RequestMethod.POST)
	public String saveorg(Role role, Long[] ids, RedirectAttributes redirectAttributes) {
		User	user=userService.getCurrent();
		role.setType(3);
		role.setCreateDate(new Date());
		role.setOrgId(user.getOrgId());
		//添加菜单的父级菜单
		if(ids!=null&&ids.length>0){
			List listids=new ArrayList();
			for (int i = 0; i < ids.length; i++) {
				listids.add(ids[i]);
			}
			
			Map params=new HashMap();
			params.put("acls", "1");
			params.put("ids",listids);
			List<Acl> 	acls=aclService.findList(params);
			if(acls!=null){
				for (int i = 0; i < acls.size(); i++) {
					listids.add(acls.get(i).getId());
				}
			}
			


			Long[] longs = new Long[listids.size()];

			listids.toArray(longs);
			
			roleService.save(role,(Long[])  longs);
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editorg", method = RequestMethod.GET)
	public String editorg(Long id, ModelMap model) {

		model.addAttribute("role", roleService.findRoleAndAcls(id));
		return "/admin/role/editorg";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updateorg", method = RequestMethod.POST)
	public String updateorg(Role role, Long[] ids, RedirectAttributes redirectAttributes) {
		
		//添加菜单的父级菜单
				if(ids!=null&&ids.length>0){
					List listids=new ArrayList();
					for (int i = 0; i < ids.length; i++) {
						listids.add(ids[i]);
					}
					
					Map params=new HashMap();
					params.put("acls", "1");
					params.put("ids",listids);
					List<Acl> 	acls=aclService.findList(params);
					if(acls!=null){
						for (int i = 0; i < acls.size(); i++) {
							listids.add(acls.get(i).getId());
						}
					}
					Long[] longs = new Long[listids.size()];

					listids.toArray(longs);
					
					roleService.update(role,longs);
				}
		
		
	
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	/**
	 * 列表
	 */
	@RequiresPermissions("admin:org:role")
	@RequestMapping(value = "/listorg", method = RequestMethod.GET)
	public String listorg(HttpServletRequest request,Pageable pageable, ModelMap model) {
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		User	user=userService.getCurrent();

		paramter.put("type", 3);
		paramter.put("orgId", user.getOrgId());
		
		pageable.setParameter(paramter);

		
		model.addAttribute("page", roleService.findByPage(pageable));

		return "/admin/role/listorg";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/deleteorg", method = RequestMethod.POST)
	public @ResponseBody Message deleteorg(Long[] ids) {
		for (Long id : ids) {
			roleService.delete(id);
		}

		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 平台角色——查看
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		Role role = roleService.findRoleAndAcls(id);
		
		if(role != null) {
			model.addAttribute("role", role);
		}
		return "/admin/role/view";
	}
	
	
	/**
	 * 机构角色——查看
	 */
	@RequestMapping(value = "/viewOrg/{id}", method = RequestMethod.GET)
	public String viewOrg(@PathVariable Long id, ModelMap model) {
		Role role = roleService.findRoleAndAcls(id);
		
		if(role != null) {
			model.addAttribute("role", role);
		}
		return "/admin/role/vieworg";
	}
}
