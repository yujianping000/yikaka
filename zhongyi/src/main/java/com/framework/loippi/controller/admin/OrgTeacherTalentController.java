package com.framework.loippi.controller.admin;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTimeComment;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.OrgInvite;
import com.framework.loippi.entity.OrgTeacherview;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.TSalary;
import com.framework.loippi.entity.TcommentScope;
import com.framework.loippi.entity.TcontinueClass;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherComment;
import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.entity.TeacherTalentView;
import com.framework.loippi.entity.TremarkOn;
import com.framework.loippi.entity.User;
import com.framework.loippi.entity.Week;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.CourseTimeCommentService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgInviteService;
import com.framework.loippi.service.OrgTeacherviewService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TSalaryService;
import com.framework.loippi.service.TcommentScopeService;
import com.framework.loippi.service.TcontinueClassService;
import com.framework.loippi.service.TeacherCommentService;
import com.framework.loippi.service.TeacherExperienceService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.TeacherTalentViewService;
import com.framework.loippi.service.TremarkOnService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.XDigestUtils;
import com.framework.loippi.utils.qiniu.FileServiceImpl;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 教师表
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminOrgTeacherTalentController")
@RequestMapping({ "/admin/org_teacher_talent" })
public class OrgTeacherTalentController extends GenericController {

	@Resource
	private UserService userService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private TeacherExperienceService teacherExperenceService;
	
	@Resource
	private CourseCommentService courseCommentService;
	
	@Resource
	private TeacherCommentService teacherCommentService;
	
	@Resource
	private TeacherExperienceService teacherExperienceService;
	
	@Resource
	private OrgnizationService orgnizationService;
	
	@Resource
	private OrgTeacherviewService orgTeacherviewService;

	@Resource
	private TSalaryService tSalaryService;
	
	@Resource
	private TcommentScopeService tcommentScopeService;
	@Resource
	private TcontinueClassService tcontinueClassService;
	
	@Resource
	private TremarkOnService tremarkOnService;
	
	@Resource
	private OrgInviteService orgInviteService;
	
	@Resource
	private TeacherTalentViewService teacherTalentViewService;
	
	@Resource
	private OrgDynamicService orgDynamicService;
	
	@Resource
	private CourseTeaTimeService courseTeaTimeService;
	@Resource
	private UserUnreadService userUnreadService;
	
	@Resource
	private OrgUserService orgUserService;
	
	@Resource
	private CourseTimeCommentService courseTimeCommentService;
	
	

	/**
	 * 查看
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		
		Teacher teacher = teacherService.find(id);
		
		int favStar1 = 0;
		int num1 = 0;
		
		int favStar2 = 0;
		int num2 = 0;
		
		//用户评分
		List<CourseTimeComment> CourseTimeComments = courseTimeCommentService.findList("teacherId",id);
		if (CourseTimeComments != null && CourseTimeComments.size() > 0) {
			for (CourseTimeComment courseTimeComment : CourseTimeComments) {
				if (courseTimeComment != null) {
					favStar1 = favStar1 + courseTimeComment.getFavStar();
					num1 ++;
				}
			}

			if (num1 != 0) {
				favStar1 = favStar1 / num1;
			}
		}
		teacher.setUserEvaluation(favStar1);
		
		//机构评分
		Map map = new HashMap<>();
		map.put("teacherId", id);
		map.put("commentType", 2);
		List<TeacherComment> teacherComments = teacherCommentService.findList(map);
		if (teacherComments != null && teacherComments.size() > 0) {
			for (TeacherComment teacherComment : teacherComments) {
				if (teacherComment != null) {
					if (teacherComment.getStatus() == 1) {
						favStar2 = favStar2 + teacherComment.getFavStar();
						num2 ++;
					}
				}
			}

			if (num2 != 0) {
				favStar2 = favStar2 / num2;
			}
		}
		teacher.setOrgEvaluation(favStar2);
		
		model.addAttribute("teacher", teacher);
		
		List<TeacherExperience> teacherExperenceList = teacherExperenceService.findList("teacherId",id);
		model.addAttribute("teacherExperenceList", teacherExperenceList);
		
		
		Map map1 = new HashMap<>();
		map1.put("teacherId",id);
		map1.put("pageNumber",0);
		map1.put("pageSize",10);
		map1.put("order","create_date DESC");
		
		List<CourseTimeComment> CourseTimeCommentList = courseTimeCommentService.findListByPage(map1);
		if (CourseTimeCommentList != null && CourseTimeCommentList.size() > 0) {
			for (CourseTimeComment courseTimeComment : CourseTimeCommentList) {
				if(courseTimeComment != null && courseTimeComment.getMark() != null) {
					courseTimeComment.setMark(courseTimeComment.getMark().replaceAll("##", "  "));
				}
			}
		}
			
		model.addAttribute("CourseTimeCommentList", CourseTimeCommentList);
		
		Map map2 = new HashMap<>();
		map2.put("teacherId",id);
		map2.put("commentType",2);
		map2.put("pageNumber",0);
		map2.put("pageSize",10);
		map1.put("order","create_date DESC");
		List<TeacherComment> teacherCommentList = teacherCommentService.findListByPage(map2);
		for (TeacherComment teacherComment : teacherCommentList) {
			if(teacherComment != null &&teacherComment.getMark() != null ) {
				teacherComment.setMark(teacherComment.getMark().replaceAll("##", "  "));
			}
		}
		model.addAttribute("teacherCommentList", teacherCommentList);
		
		return "/admin/org_teacher_talent/view";
	}
	

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET )
	public String list(Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map paramter = RequestMap(request);
		pageable.setParameter(paramter);
		
		
		Page<TeacherTalentView> pages = teacherTalentViewService.findByPage(pageable);
		
		List<TeacherTalentView> teacherTalentViewList = pages.getContent();
		
		if(teacherTalentViewList != null) {
			//判断教师是否已经加入该机构
			User user = userService.getCurrent();
			if(user != null) {
				Map map = new HashMap<>();
				map.put("orgId", user.getOrgId());
				map.put("type", 2);
				
				List<OrgUser> orgUserList = orgUserService.findList(map);
				
				for (TeacherTalentView teacherTalentView : teacherTalentViewList) {
					if(orgUserList != null) {
						for (OrgUser orgUser : orgUserList) {
							if(orgUser.getUid() == teacherTalentView.getId()) {
								teacherTalentView.setJoinType(1);
							}
						}
					}
				}
			}
		}
		
		model.addAttribute("page", pages);
		model.addAttribute("paramter", paramter);
		
		return "/admin/org_teacher_talent/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.teacherService.deleteAll(ids);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 用户评价
	 */
	@RequestMapping(value = "/userRate/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public String userRate(@PathVariable Long id, ModelMap model,HttpServletRequest request,Pageable pageable) {
		
	   Map<String, Object> paramter = new HashMap<>();
		paramter.put("teacherId", id);
		pageable.setParameter(paramter);
		
		Page<CourseTimeComment> pages = this.courseTimeCommentService.findByPage(pageable);
		model.addAttribute("page",pages);
		
		List<CourseTimeComment> courseTimeCommentList = pages.getContent();
		for (CourseTimeComment courseTimeComment : courseTimeCommentList) {
			if(courseTimeComment != null && courseTimeComment.getMark() != null) {
				courseTimeComment.setMark(courseTimeComment.getMark().replaceAll("##", "  "));
			}
		}
		return "/admin/org_teacher_talent/userRate";
	}
	
	/**
	 * 机构评价
	 */
	@RequestMapping(value = "/orgnizationRate/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public String orgnizationRate(@PathVariable Long id, ModelMap model,HttpServletRequest request,Pageable pageable) {
		
		Map<String, Object> paramter = new HashMap<>();
		paramter.put("teacherId", id);
		pageable.setParameter(paramter);
		
		Page<TeacherComment> pages = this.teacherCommentService.findByPage(pageable);
		model.addAttribute("page",pages);
		
		List<TeacherComment> teacherCommentList = pages.getContent();
		for (TeacherComment teacherComment : teacherCommentList) {
			if(teacherComment != null) {
				teacherComment.setMark(teacherComment.getMark().replaceAll("##", "  "));
			}
		}
		
		return "/admin/org_teacher_talent/orgnizationRate";
	}
	
	
	/**
	 * 邀请加入
	 */
	@RequestMapping(value="/inviteTeacher",method=RequestMethod.POST)
	public String inviteTeacher(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String teacherId = request .getParameter("teacherId");
		String teacherType = request.getParameter("teacherType");
		String reason = request.getParameter("reason");
		
		User user = userService.getCurrent();
		if(user != null) {
			if(!StringUtil.isEmpty(teacherId) && !StringUtil.isEmpty(teacherType) && !StringUtil.isEmpty(reason) ) {
				OrgInvite orgInvite = new OrgInvite();
				orgInvite.setOrgId(user.getOrgId());
				orgInvite.setTeacherId(Long.parseLong(teacherId));
				orgInvite.setType(Integer.parseInt(teacherType));
				orgInvite.setInvitetype(2);
				orgInvite.setReason(reason);
				
				orgInviteService.save(orgInvite);
				
				OrgDynamic orgDynamic = new OrgDynamic();
				Orgnization orgnization = orgnizationService.find(user.getOrgId());
				
				orgDynamic.setOrgId(orgnization.getId());
				if(orgInvite.getType() == 1) {
					orgDynamic.setPrompt("来自"+orgnization.getName()+"机构的全职教师邀请");
				}
				
				if(orgInvite.getType() == 2) {
					orgDynamic.setPrompt("来自"+orgnization.getName()+"机构的兼职教师邀请");
				}
				orgDynamic.setImgUrl(orgnization.getImage());
				orgDynamic.setAvatar(orgnization.getImage());
				orgDynamic.setTextContent(reason);
				orgDynamic.setCreateDate(new Date());
				orgDynamic.setUserId(Long.parseLong(teacherId));
				orgDynamic.setType(3);
				orgDynamic.setUserType(2);
				orgDynamic.setInviteId(orgInvite.getId());
				orgDynamic.setInviteStatus(2);
				orgDynamic.setStatus(1);
				orgDynamic.setOrgId(orgnization.getId());
				orgDynamic.setInviteReason(orgInvite.getReason());
				orgDynamic.setTeacherId(Long.parseLong(teacherId));
				
				orgDynamicService.save(orgDynamic);
				userUnreadService.setUserUnread(2, Long.parseLong(teacherId), 3);
			}
		}
		return "redirect:list.jhtml";
	}
	
	
	/**
	 * 周课程表
	 */
	@RequestMapping(value="/weekCourse",method=RequestMethod.GET)
	public String weekCourse(Long id,RedirectAttributes redirectAttributes,ModelMap model) {
		
		//存放周课程表
		List<Week> weekCourseList = new ArrayList<>();
		//存放课程时间为上午的记录
		List<CourseTeaTime> morningTimeList = new ArrayList<>();
		//存放课程时间为下午的记录
		List<CourseTeaTime> afternoonTimeList = new ArrayList<>();
		//存放课程时间为晚上的记录
		List<CourseTeaTime> eveningTimeList = new ArrayList<>();
		
		List<CourseTeaTime> courseTeaTimeList = courseTeaTimeService.findList("teacherId", id);
		if(courseTeaTimeList != null) {
			for (CourseTeaTime courseTeaTime : courseTeaTimeList) {
				int time = Integer.parseInt(courseTeaTime.getCourseTime1().replace(":", ""));
				//上午
				if(time > 600 && time <= 1200) {
					morningTimeList.add(courseTeaTime);
				}
				
				//下午
				if(time > 1200 && time <= 1800) {
					afternoonTimeList.add(courseTeaTime);
				}
				
				//晚上
				if(time > 1800 && time <= 2400) {
					eveningTimeList.add(courseTeaTime);
				}
			}
		}
		
		Week week1 = new Week();
		Week week2 = new Week();
		Week week3 = new Week();
		
		//判断上午记录的日期为周几
		for(int i = 0; i < morningTimeList.size(); i ++) {
			CourseTeaTime morningTime = morningTimeList.get(i);
			
			if(StringUtil.getWeekOfDate(morningTime.getCourseTime()) == "一") {
				week1.setMonday(1);
			}
			if(StringUtil.getWeekOfDate(morningTime.getCourseTime()) == "二") {
				week1.setTuesday(1);
			}
			if(StringUtil.getWeekOfDate(morningTime.getCourseTime()) == "三") {
				week1.setWednesday(1);
			}
			if(StringUtil.getWeekOfDate(morningTime.getCourseTime()) == "四") {
				week1.setThursday(1);
			}
			if(StringUtil.getWeekOfDate(morningTime.getCourseTime()) == "五") {
				week1.setFriday(1);
			}
			if(StringUtil.getWeekOfDate(morningTime.getCourseTime()) == "六") {
				week1.setSaturday(1);
			}
			if(StringUtil.getWeekOfDate(morningTime.getCourseTime()) == "日") {
				week1.setSunday(1);
			}
		}
		
		//判断下午记录的日期为周几
				for(int i = 0; i < afternoonTimeList.size(); i ++) {
					CourseTeaTime afternoonTime = afternoonTimeList.get(i);
					
					
					if(StringUtil.getWeekOfDate(afternoonTime.getCourseTime()) == "一") {
						week2.setMonday(1);
					}
					if(StringUtil.getWeekOfDate(afternoonTime.getCourseTime()) == "二") {
						week2.setTuesday(1);
					}
					if(StringUtil.getWeekOfDate(afternoonTime.getCourseTime()) == "三") {
						week2.setWednesday(1);
					}
					if(StringUtil.getWeekOfDate(afternoonTime.getCourseTime()) == "四") {
						week2.setThursday(1);
					}
					if(StringUtil.getWeekOfDate(afternoonTime.getCourseTime()) == "五") {
						week2.setFriday(1);
					}
					if(StringUtil.getWeekOfDate(afternoonTime.getCourseTime()) == "六") {
						week2.setSaturday(1);
					}
					if(StringUtil.getWeekOfDate(afternoonTime.getCourseTime()) == "日") {
						week2.setSunday(1);
					}
				}
		
				//判断晚上记录的日期为周几
				for(int i = 0; i < eveningTimeList.size(); i ++) {
					CourseTeaTime eveningTime = eveningTimeList.get(i);
					
					if(StringUtil.getWeekOfDate(eveningTime.getCourseTime()) == "一") {
						week3.setMonday(1);
					}
					if(StringUtil.getWeekOfDate(eveningTime.getCourseTime()) == "二") {
						week3.setTuesday(1);
					}
					if(StringUtil.getWeekOfDate(eveningTime.getCourseTime()) == "三") {
						week3.setWednesday(1);
					}
					if(StringUtil.getWeekOfDate(eveningTime.getCourseTime()) == "四") {
						week3.setThursday(1);
					}
					if(StringUtil.getWeekOfDate(eveningTime.getCourseTime()) == "五") {
						week3.setFriday(1);
					}
					if(StringUtil.getWeekOfDate(eveningTime.getCourseTime()) == "六") {
						week3.setSaturday(1);
					}
					if(StringUtil.getWeekOfDate(eveningTime.getCourseTime()) == "日") {
						week3.setSunday(1);
					}
				}
				//type ： 1——上午   2——下午  3——晚上
				week1.setType(1);
				week2.setType(2);
				week3.setType(3);
				
				weekCourseList.add(week1);
				weekCourseList.add(week2);
				weekCourseList.add(week3);
				
				model.addAttribute("weekCourseList", weekCourseList);
		
				return "/admin/org_teacher_talent/weekCourse";
	}
	
	
	/**
	 *过滤 
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();
		
		User user = userService.getCurrent();
		if(user != null) {
			paramter.put("org_id", user.getOrgId());
		}
		
		String filter_account = request.getParameter("filter_account");
		String filter_nickname = request.getParameter("filter_nickname");
		//String filter_type = request.getParameter("filter_type");
		String filter_inviteType = request.getParameter("filter_inviteType");
		
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_nickname)) {
			paramter.put("filter_nickname", StringUtil.filterSQL(filter_nickname));
		}
		
		/*if(!StringUtil.isEmpty(filter_type)) {
			paramter.put("filter_type", Integer.parseInt(StringUtil.filterSQL(filter_type)));
		}*/
		
		if(!StringUtil.isEmpty(filter_inviteType)) {
			paramter.put("filter_inviteType", Integer.parseInt(StringUtil.filterSQL(filter_inviteType)));
		}
		
		//教师人才库为兼职教师
		//paramter.put("filter_type", 2);
		
		return paramter;
	}

}
