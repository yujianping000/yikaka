package com.framework.loippi.controller.admin;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.LeaveOffice;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.LeaveOfficeService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;

/**
 * Controller - 老师认证
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminTeacherAuthController")
@RequestMapping({ "/admin/teacher_auth" })
public class TeacherAuthController extends GenericController {

	@Resource
	private TeacherAuthService teacherAuthService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private UserService userService;
	
	@Resource
	private OrgnizationService orgnizationService;
	
	@Resource
	private OrgUserService orgUserService;
	
	@Resource
	private OrgDynamicService orgDynamicService;
	
	@Resource
	private UserUnreadService userUnreadService;
	
	@Resource
	private LeaveOfficeService leaveOfficeService;
	
	
	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/teacher_auth/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TeacherAuth teacherAuth, RedirectAttributes redirectAttributes) {
		teacherAuthService.save(teacherAuth);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		TeacherAuth teacherAuth = teacherAuthService.find(id);
		model.addAttribute("teacherAuth", teacherAuth);
		return "/admin/teacher_auth/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		TeacherAuth teacherAuth = teacherAuthService.find(id);
		Teacher teacher=teacherService.find(teacherAuth.getTeacherId());
		teacherAuth.setAccount(teacher.getAccount());
		teacherAuth.setAvatar(teacher.getAvatar());
		model.addAttribute("teacherAuth", teacherAuth);
		return "/admin/teacher_auth/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TeacherAuth teacherAuth, RedirectAttributes redirectAttributes) {
		teacherAuthService.update(teacherAuth);
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
		User user=userService.getCurrent();
		Long orgId=user.getOrgId();
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        } 
		  if(orgId!=null){
				map.put("orgId",  orgId);
			}
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		Page<TeacherAuth> teacherAuths=this.teacherAuthService.findByPage(pageable);
		List<TeacherAuth> teacherAuthss=teacherAuths.getContent();
		for(TeacherAuth teacherAuth:teacherAuthss){
			Teacher teacher=teacherService.find(teacherAuth.getTeacherId());
			if(teacher!=null){
				teacherAuth.setAvatar(teacher.getAvatar());
			}
			
		}
		model.addAttribute("page",teacherAuths );
		return "/admin/teacher_auth/list";
	}

	
	/**
	 * 同意操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmPass" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmPass(Long id) {
		TeacherAuth teacherAuth=teacherAuthService.find(id);
		teacherAuth.setStatus(1);
		teacherAuth.setReason("您的资料已经通过审核啦~");
		teacherAuthService.update(teacherAuth);
		
		//认证成功，若为公海教师将教师状态进行修改
		Teacher teacher = teacherService.find(teacherAuth.getTeacherId());
		if(teacher != null && teacher.getTeacherType() == 3) {
			teacher.setTeacherType(teacherAuth.getType());
			teacherService.update(teacher);
		}
		
		//若教师是第二次加入该机构，将该教师之前的离职信息删除
		Map map = new HashMap<>();
		map.put("orgId", teacherAuth.getOrgId());
		map.put("teacherId", teacherAuth.getTeacherId());
		List<LeaveOffice> leaveOfficeList = leaveOfficeService.findList(map);
		
		if(leaveOfficeList != null) {
			for (LeaveOffice leaveOffice : leaveOfficeList) {
				leaveOfficeService.deleteEntity(leaveOffice);
			}
		}
		
		//同意入职，则机构教师总数加一
		Orgnization orgnization = orgnizationService.find(teacherAuth.getOrgId());
		orgnization.setTeacherCount(orgnization.getTeacherCount()+1);
		orgnizationService.update(orgnization);
		
		//机构用户表添加一条记录
		OrgUser orgUser = new OrgUser();
		orgUser.setCreateTime(new Date());
		orgUser.setOrgId(teacherAuth.getOrgId());
		orgUser.setUid(teacherAuth.getTeacherId());
		orgUser.setType(2);
		orgUserService.save(orgUser);
		
		//发送消息
		OrgDynamic orgDynamic = new OrgDynamic();
		orgDynamic.setPrompt("入职申请通知");
		if(orgnization != null) {
			orgDynamic.setTextContent("您的入职申请已经通过了"+orgnization.getName()+"的审核，恭喜你加入了紫鲸互联机构，请知悉");
			orgDynamic.setOrgId(orgnization.getId());
			orgDynamic.setCreateDate(new Date());
		}
		orgDynamic.setTeacherId(teacherAuth.getTeacherId());
		orgDynamic.setTeacherName(teacherAuth.getName());
		orgDynamic.setUserId(teacherAuth.getTeacherId());
		orgDynamic.setType(5);
		orgDynamic.setUserType(2);
		
		orgDynamicService.save(orgDynamic);
		userUnreadService.setUserUnread(2, teacherAuth.getTeacherId(), 5);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 拒绝操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmRefuse" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmRefuse(Long id,String reason) {
		TeacherAuth teacherAuth=teacherAuthService.find(id);
		teacherAuth.setStatus(3);
		teacherAuth.setReason(reason);
		teacherAuthService.update(teacherAuth);
		
		Orgnization orgnization = orgnizationService.find(teacherAuth.getOrgId());
		//发送消息
		OrgDynamic orgDynamic = new OrgDynamic();
		orgDynamic.setPrompt("入职申请通知");
		if(orgnization != null) {
			orgDynamic.setTextContent("您的入职申请被"+orgnization.getName()+"拒绝，拒绝原因："+teacherAuth.getReason());
			orgDynamic.setOrgId(orgnization.getId());
			orgDynamic.setCreateDate(new Date());
		}
		orgDynamic.setTeacherId(teacherAuth.getTeacherId());
		orgDynamic.setTeacherName(teacherAuth.getName());
		orgDynamic.setUserId(teacherAuth.getTeacherId());
		orgDynamic.setType(5);
		orgDynamic.setUserType(2);
		
		orgDynamicService.save(orgDynamic);
		userUnreadService.setUserUnread(2, teacherAuth.getTeacherId(), 5);
		
		return SUCCESS_MESSAGE;
	}
	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.teacherAuthService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
