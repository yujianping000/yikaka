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
import com.framework.loippi.entity.TeacherComment;
import com.framework.loippi.entity.TeacherCommentRemark;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.LeaveOfficeService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherCommentRemarkService;
import com.framework.loippi.service.TeacherCommentService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;

/**
 * Controller - 离职审核表
 * 
 * @author wmj
 * @version 2.0
 */
@Controller("adminLeaveOfficeController")
@RequestMapping({ "/admin/leave_office" })
public class LeaveOfficeController extends GenericController {

	@Resource
	private LeaveOfficeService leaveOfficeService;
	@Resource
	private TeacherCommentRemarkService teacherCommentRemarkService;
	@Resource
	private OrgnizationService orgnizationService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private TeacherCommentService teacherCommentService;
	@Resource
	private UserService userService;
	
	@Resource
	private OrgUserService orgUserService;
	
	@Resource
	private TeacherAuthService teacherAuthService;
	
	@Resource
	private OrgDynamicService orgDynamicService;
	
	@Resource
	private UserUnreadService userUnreadService;
	
	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/leave_office/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(LeaveOffice leaveOffice, RedirectAttributes redirectAttributes) {
		leaveOfficeService.save(leaveOffice);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		LeaveOffice leaveOffice = leaveOfficeService.find(id);
		model.addAttribute("leaveOffice", leaveOffice);
		return "/admin/leave_office/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{teacherId}/{orgId}", method = RequestMethod.GET)
	public String view(@PathVariable Long teacherId,@PathVariable Long orgId, ModelMap model) {
		
		Map map = new HashMap<>();
		map.put("teacherId", teacherId);
		map.put("orgId", orgId);
		
		List<TeacherComment> teacherCommentList = teacherCommentService.findList(map);
		if(teacherCommentList != null) {
			TeacherComment teacherComment = teacherCommentList.get(0);
			if(teacherComment.getMark() != null) {
				teacherComment.setMark(teacherComment.getMark().replace("##", "  "));	
			}
			
			model.addAttribute("teacherComment", teacherComment);
		}
		
		return "/admin/leave_office/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(LeaveOffice leaveOffice, RedirectAttributes redirectAttributes) {
		leaveOfficeService.update(leaveOffice);
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
		model.addAttribute("page", this.leaveOfficeService.findByPage(pageable));
		List<TeacherCommentRemark>  teacherCommentRemarks=teacherCommentRemarkService.findAll();
		model.addAttribute("teacherCommentRemarks", teacherCommentRemarks);
		return "/admin/leave_office/list";
	}

	
	
	
	/**
	 * 评论操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmAppraise" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmAppraise(Long id,String commen,String remarks,Integer stars) {
	 LeaveOffice leaveOffice=leaveOfficeService.find(id);
	 leaveOffice.setStatus(1);
	 leaveOfficeService.update(leaveOffice);
	 
	TeacherComment teacherComment=new TeacherComment();
	Orgnization orgnization=orgnizationService.find("id", leaveOffice.getOrgId());
	teacherComment.setAccount(orgnization.getAccount());
	teacherComment.setAvatar(orgnization.getBackgroundLogo());
	teacherComment.setCommentType(2);
	teacherComment.setContent(commen);
	teacherComment.setCreateDate(new Date());
	teacherComment.setFavStar(stars);
	teacherComment.setMark(remarks);
	teacherComment.setOrgId(orgnization.getId());
	teacherComment.setOrgName(orgnization.getName());
	teacherComment.setStatus(1);
	teacherComment.setTeacherId(leaveOffice.getTeacherId());
	Teacher teacher=teacherService.find(leaveOffice.getTeacherId());
	teacherComment.setTeacherName(teacher.getNickname());
	teacherCommentService.save(teacherComment);
	
	//离职同意则机构教师数量减一
	orgnization.setTeacherCount(orgnization.getTeacherCount()-1);
	orgnizationService.update(orgnization);
	
	//机构用户表删除一条记录
	Map map = new HashMap<>();
	map.put("orgId", leaveOffice.getOrgId());
	map.put("uid", leaveOffice.getTeacherId());
	map.put("type", 2);
	List<OrgUser> OrgUserList = orgUserService.findList(map);
	if(OrgUserList != null) {
		orgUserService.deleteEntity(OrgUserList.get(0));
	}
	
	//将教师认证记录状态设为离职
	TeacherAuth teacherAuth = teacherAuthService.find("teacherId", leaveOffice.getTeacherId());
	if(teacherAuth != null) {
		teacherAuth.setStatus(4);
		teacherAuthService.update(teacherAuth);
	}
	
	//若为全职教师离职成功后将教师状态改为公海教师
	if(teacher.getTeacherType() == 1) {
		teacher.setTeacherType(3);
		teacherService.update(teacher);
	}
	
	//若为兼职教师离职成功且没有加入其他机构的，教师状态改为公海教师
	Map map2 = new HashMap<>();
	map.put("uid", leaveOffice.getTeacherId());
	map.put("type", 2);
	List<OrgUser> OrgUserList2 = orgUserService.findList(map2);
	
	if(OrgUserList2.size() == 0 && teacher.getTeacherType() == 2) {
		teacher.setTeacherType(3);
		teacherService.update(teacher);
	}
	
	//发送消息
	OrgDynamic orgDynamic = new OrgDynamic();
	orgDynamic.setPrompt("离职申请通知");
	if(orgnization != null) {
		orgDynamic.setTextContent("您的离职申请已经通过了"+orgnization.getName()+"的审核，现已与该机构解除合作关系，请知悉");
		orgDynamic.setOrgId(orgnization.getId());
		orgDynamic.setCreateDate(new Date());
	}
	orgDynamic.setTeacherId(leaveOffice.getTeacherId());
	orgDynamic.setTeacherName(leaveOffice.getName());
	orgDynamic.setUserId(leaveOffice.getTeacherId());
	orgDynamic.setType(5);
	orgDynamic.setUserType(2);
	
	orgDynamicService.save(orgDynamic);
	userUnreadService.setUserUnread(2, leaveOffice.getTeacherId(), 5);
	
	
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
		LeaveOffice leaveOffice=leaveOfficeService.find(id);
		leaveOffice.setStatus(2);
		leaveOffice.setBackReason(reason);
		leaveOffice.setBackTime(new Date());
		leaveOfficeService.update(leaveOffice);
		
		
		Orgnization orgnization = orgnizationService.find(leaveOffice.getOrgId());
		//发送消息
		OrgDynamic orgDynamic = new OrgDynamic();
		orgDynamic.setPrompt("离职申请通知");
		if(orgnization != null) {
			orgDynamic.setTextContent("您的离职申请被"+orgnization.getName()+"拒绝，拒绝原因："+leaveOffice.getReason());
			orgDynamic.setOrgId(orgnization.getId());
			orgDynamic.setCreateDate(new Date());
		}
		orgDynamic.setTeacherId(leaveOffice.getTeacherId());
		orgDynamic.setTeacherName(leaveOffice.getName());
		orgDynamic.setUserId(leaveOffice.getTeacherId());
		orgDynamic.setType(5);
		orgDynamic.setUserType(2);
		
		orgDynamicService.save(orgDynamic);
		userUnreadService.setUserUnread(2, leaveOffice.getTeacherId(), 5);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 拒绝原因
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/showRefuseReason" }, method = { RequestMethod.GET })
	public String showRefuseReason(Long id, ModelMap model) {
		LeaveOffice leaveOffice=leaveOfficeService.find(id);
		model.addAttribute("leaveOffice", leaveOffice);
		return "/admin/leave_office/refuseReason";
		
	}
	

	
	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.leaveOfficeService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
