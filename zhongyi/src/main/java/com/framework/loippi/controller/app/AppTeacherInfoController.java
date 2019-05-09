package com.framework.loippi.controller.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.controller.app.model.AppCourseComment;
import com.framework.loippi.controller.app.model.AppCourseTeacher;
import com.framework.loippi.controller.app.model.AppLeaveOffice;
import com.framework.loippi.controller.app.model.AppOrgnizationn;
import com.framework.loippi.controller.app.model.AppTeacherExperience;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.CourseTimeComment;
import com.framework.loippi.entity.LeaveOffice;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherComment;
import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTimeCommentService;
import com.framework.loippi.service.LeaveOfficeService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherCommentService;
import com.framework.loippi.service.TeacherExperienceService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.utils.StringUtil;

@Controller
@RequestMapping("api/app/teacherInfo/*")
public class AppTeacherInfoController extends BaseController {
	@Resource
	private TeacherService teacherService;
	@Resource
	private OrgnizationService orgnizationService;
	@Resource
	private TeacherExperienceService teacherExperienceService;
	@Resource
	private CourseCommentService courseCommentService;
	@Resource
	private TeacherCommentService teacherCommentService;
	@Resource
	private CourseService courseService;
	@Resource
	private LeaveOfficeService leaveOfficeService;
	@Resource
	private TeacherAuthService teacherAuthService;

	@Resource
	private OrgDynamicService orgDynamicService;
	
	@Resource
	private UserUnreadService userUnreadService;
	
	@Resource
	private CourseTimeCommentService courseTimeCommentService;
	
	@Resource
	private ParentService parentService;
	
	
	/**
	 * 个人中心
	 */
	@ResponseBody
	@RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
	public String getUserInfo(HttpServletRequest request, String sessionId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		Teacher teacher = teacherService.find(loginUser.getId());
	    AppTeacherCenter appUserCenter=new AppTeacherCenter();
	    appUserCenter.setAvatar(teacher.getAvatar());
	    appUserCenter.setNickname(teacher.getNickname());
	    appUserCenter.setSex(teacher.getSex());
	    if(teacher.getBirthday()!=null){
	    appUserCenter.setBirthday( StringUtil.date2String13(teacher.getBirthday()));
	}
	    
	    appUserCenter.setMark(teacher.getMark());
	    appUserCenter.setDescription(teacher.getDescription());
	    appUserCenter.setFavStar(teacher.getFavStar());
	    appUserCenter.setTeacherType(teacher.getTeacherType());
	   
	     if(loginUser.getOrgId()!=0&&loginUser.getOrgId()!=null){
	    		Orgnization orgnization=orgnizationService.find(loginUser.getOrgId());
	    		if(orgnization!=null){
	    			appUserCenter.setOrgName(orgnization.getName());
		    		appUserCenter.setOrgId(orgnization.getId());
	    		}
	    	 
	     }else{
	    	    //认证审核通过的才可以显示机构名
	 	    Map<String,Object> map=new HashMap<String,Object> ();
	         map.put("uid", loginUser.getId());
	     	List<Orgnization> orglist = orgnizationService.findListByPage1(map);

	 	    if(orglist != null && orglist.size() > 0 ) {
	 	    	Orgnization orgnization = orglist.get(0); 
	 	    	if(orgnization!=null){
	 	    		appUserCenter.setOrgName(orgnization.getName());
	 	    		appUserCenter.setOrgId(orgnization.getId());
	 	    	}
	 	    }
	     }
		    
		return jsonSucess(appUserCenter);
	}
	
	/**
	 * 修改个人简介
	 */
	@ResponseBody
	@RequestMapping(value = "editDescription", method = RequestMethod.POST)
	public String editDescription(HttpServletRequest request, String sessionId,String description) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser.getUserType()==2){
			Teacher teacher=teacherService.find(loginUser.getId());
			teacher.setDescription(description);
			teacherService.update(teacher);
		}
		return jsonSucess();
	}
	/**
	 * 添加任教经历
	 */
	@ResponseBody
	@RequestMapping(value = "addexperience", method = RequestMethod.POST)
	public String addexperience(HttpServletRequest request, String sessionId,String content,String starts,String ends,String contents) {
	 	LoginUser loginUser = validateRedisLogiUser(sessionId);
	  List<TeacherExperience> teacherExperiences=teacherExperienceService.findList("teacherId", loginUser.getId());
	  for(TeacherExperience teacherExperience:teacherExperiences){
		   teacherExperienceService.delete(teacherExperience.getId());
	  }
	 	String[] startlen=starts.split(",");
	 	String[] endlen=ends.split(",");
	 	String[] experiencelen=contents.split(",");
	 	 for(int i=0;i<startlen.length;i++){
	 		 TeacherExperience teacherExperience=new TeacherExperience();
			 teacherExperience.setContent(experiencelen[i]);
			 teacherExperience.setCreateTime(new Date());
			 teacherExperience.setStart(StringUtil.StringDateToDate(startlen[i]));
			 teacherExperience.setEnd(StringUtil.StringDateToDate(endlen[i]));
			 teacherExperience.setTeacherId(loginUser.getId());
			 teacherExperienceService.save(teacherExperience);
	 	 }
		return jsonSucess();
	}
	/**
	 * 获取任教经历
	 */
	@ResponseBody
	@RequestMapping(value = "getexperience", method = RequestMethod.POST)
	public String getexperience(HttpServletRequest request, String sessionId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		  List<AppTeacherExperience> appTeacherExperiences=new ArrayList<AppTeacherExperience>();
		  Map map = new HashMap<>();
		  map.put("teacherId", loginUser.getId());
		  map.put("order", "start ASC");
		  
		 List<TeacherExperience>  teacherExperiences= teacherExperienceService.findList(map);
		  for(TeacherExperience teacherExperience:teacherExperiences){
			  AppTeacherExperience appTeacherExperience=new AppTeacherExperience();
			  appTeacherExperience.setContent(teacherExperience.getContent());
			  if(teacherExperience.getEnd()!=null){
				  appTeacherExperience.setEndString( StringUtil.date2String13(teacherExperience.getEnd()));
			  }
			  if(teacherExperience.getStart()!=null){
				  appTeacherExperience.setStartString( StringUtil.date2String13(teacherExperience.getStart()));
			  }

			  appTeacherExperiences.add(appTeacherExperience);
		  }
			return ReturnJson.jsonString("OK",appTeacherExperiences, AppConstants.OK);
	}
	
	
	/**
	 * 获取评论列表
	 */
	@ResponseBody
	@RequestMapping(value = "getComments", method = RequestMethod.POST)
	public String getComments(HttpServletRequest request, String sessionId,Integer commentType,Integer pageNumber) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
	    Integer pageMix=(pageNumber - 1) * 10;
		  Map<String, Object> pageMap = new HashMap<String, Object>();
          pageMap.put("pageNumber", pageMix);
          pageMap.put("pageSize", 10);
          pageMap.put("teacherId", loginUser.getId());
          
		if(commentType==1){
			  //获取用户评价
			  pageMap.put("type", 1);
			  pageMap.put("order", "create_date DESC");
			
		      List<CourseTimeComment> CourseTimeCommentList = courseTimeCommentService.findListByPage(pageMap);
		      List<AppCourseComment> appCourseComments = new ArrayList<AppCourseComment>();
		      
		      if(CourseTimeCommentList != null && CourseTimeCommentList.size() > 0) {
		    	  for (CourseTimeComment courseTimeComment : CourseTimeCommentList) {
		    		  AppCourseComment appCourseComment=new AppCourseComment();
		    		  appCourseComment.setAvatar(courseTimeComment.getAvatar());
		    		  appCourseComment.setContent(courseTimeComment.getContent());
		    		  appCourseComment.setCreateDateString(StringUtil.parseDate(courseTimeComment.getCreateDate()));
		    		  appCourseComment.setFavStar(courseTimeComment.getFavStar());
					  appCourseComment.setMark(courseTimeComment.getMark());
					 
					  Parent parent = parentService.find(courseTimeComment.getUid());
					  if(parent != null) {
						  appCourseComment.setName(parent.getNickName());
					  }
					  
					  appCourseComments.add(appCourseComment);
				}
		      }
		      return ReturnJson.jsonString("OK",appCourseComments, AppConstants.OK);
			
			
			/* pageMap.put("commentType", 1);
			    List<TeacherComment> teacherComments=teacherCommentService.findListByPage(pageMap);
			    List<AppCourseComment> appCourseComments=new ArrayList<AppCourseComment>();
			    for(TeacherComment courseComment:teacherComments){
					AppCourseComment appCourseComment=new AppCourseComment();
					appCourseComment.setAvatar(courseComment.getAvatar());
					appCourseComment.setContent(courseComment.getContent());
					appCourseComment.setCreateDateString(StringUtil.parseDate(courseComment.getCreateDate()));
					appCourseComment.setFavStar(courseComment.getFavStar());
					appCourseComment.setMark(courseComment.getMark());
					appCourseComment.setName(courseComment.getOrgName());
					appCourseComments.add(appCourseComment);
				}
			    return ReturnJson.jsonString("OK",appCourseComments, AppConstants.OK);*/
		}else{
			//获取机构评价
			 pageMap.put("commentType", 2);
			 pageMap.put("order", "create_date DESC");
			 
			    List<AppCourseComment> appCourseComments=new ArrayList<AppCourseComment>();
			    List<TeacherComment> teacherComments=teacherCommentService.findListByPage(pageMap);
			    for(TeacherComment teacherComment:teacherComments){
				AppCourseComment appCourseComment=new AppCourseComment();
				appCourseComment.setAvatar(teacherComment.getAvatar());
				appCourseComment.setContent(teacherComment.getContent());
				appCourseComment.setCreateDateString(StringUtil.parseDate(teacherComment.getCreateDate()));
				appCourseComment.setFavStar(teacherComment.getFavStar());
				appCourseComment.setMark(teacherComment.getMark());
				appCourseComment.setName(teacherComment.getOrgName());
				appCourseComments.add(appCourseComment);
			 }
			  return ReturnJson.jsonString("OK",appCourseComments, AppConstants.OK);
		}
	}
	
	
	/**
	 * 机构详情首页
	 */
	@ResponseBody
	@RequestMapping(value = "getOrgIndex", method = RequestMethod.POST)
	public String getOrgIndex(HttpServletRequest request, String sessionId,Long orgId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
        Orgnization orgnization=orgnizationService.find(orgId);
        AppOrgnizationn dto=new AppOrgnizationn();
	 	BeanUtils.copyProperties(orgnization, dto);
	 	Map map=new HashMap();
	 	map.put("teacherId", loginUser.getId());
		map.put("orgId", orgId);
	 	List<LeaveOffice> leaveOffices=leaveOfficeService.findList(map);
	 	if(leaveOffices.size()!=0){
	 		dto.setIsApplyLeave(1);
	 	}else{
	 		dto.setIsApplyLeave(0);
	 	}
		 return ReturnJson.jsonString("OK",dto, AppConstants.OK);
	} 
	
	/**
	 * 课程列表
	 */
	@ResponseBody
	@RequestMapping(value = "getCourseList", method = RequestMethod.POST)
	public String getCourseList(HttpServletRequest request, String sessionId,Long orgId,Integer pageNumber,Integer pageSize) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		
		if(pageSize==null){
			pageSize=10;
		}
	    Integer pageMix=(pageNumber - 1) * pageSize;
		Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("pageNumber", pageMix);
        pageMap.put("pageSize", 10);
        pageMap.put("orgId", orgId);
        List<Course> courses=courseService.findListByPage(pageMap);
        List<AppCourseTeacher> appCourseTeachers=new ArrayList<AppCourseTeacher>();
        for(Course course:courses){
        	AppCourseTeacher dto=new AppCourseTeacher();
    	 	BeanUtils.copyProperties(course, dto);
    	 	Teacher teacher=teacherService.find(course.getTeacherId());
    	 	dto.setTeacherName(teacher.getNickname());
    	 	dto.setTeacherId(teacher.getId());
    	 	appCourseTeachers.add(dto);
        }
    
		 return ReturnJson.jsonString("OK",appCourseTeachers, AppConstants.OK);
	} 
	
	
	/**
	 * 离职申请
	 */
	@ResponseBody
	@RequestMapping(value = "leaveOffice", method = RequestMethod.POST)
	public String leaveOffice(HttpServletRequest request, String sessionId,String leavetime,String classCondition,String reason,Long orgId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		Teacher teacher=teacherService.find(loginUser.getId());
		System.err.println("============================");
		System.err.println(leavetime);
		
        LeaveOffice leaveOffice=new LeaveOffice();
        leaveOffice.setLeaveTime(StringUtil.StringDateToDate(leavetime));
        leaveOffice.setClassCondition(classCondition);
        leaveOffice.setReason(reason);
        leaveOffice.setTeacherId(teacher.getId());
        leaveOffice.setApplyTime(new Date());
        leaveOffice.setAvatar(loginUser.getAvatar());
        leaveOffice.setName(loginUser.getUserName());
        leaveOffice.setSex(loginUser.getSex());
        leaveOffice.setStatus(3);
        leaveOffice.setOrgId(orgId);
        leaveOffice.setType(teacher.getTeacherType());
        leaveOffice.setAccount(teacher.getAccount());
        leaveOfficeService.save(leaveOffice);
        
        
        Orgnization orgnization = orgnizationService.find(leaveOffice.getOrgId());
		//发送消息
		OrgDynamic orgDynamic = new OrgDynamic();
		orgDynamic.setPrompt("离职申请通知");
		if(orgnization != null) {
			orgDynamic.setTextContent("您的离职申请已经提交至"+orgnization.getName()+"进行审核，请耐心等待");
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
        
	    return ReturnJson.jsonString("OK", AppConstants.OK);
	}
	
	/**
	 * 是否已提交离职
	 */
	@ResponseBody
	@RequestMapping(value = "isSumbitLeaveOffice", method = RequestMethod.POST)
	public String isSumbitLeaveOffice(HttpServletRequest request, String sessionId,Long orgId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		Teacher teacher = teacherService.find(loginUser.getId());
		
		//0表示没有离职记录或状态为拒绝，1表示有离职记录且状态为通过，2表示有离职记录且状态为审核中
		Integer type = 0;
       
		if(teacher != null) {
			Map map1 = new HashMap<>();
			map1.put("teacherId", teacher.getId());
			map1.put("orgId", orgId);
			//查询是否有离职状态为已通过的记录
			map1.put("status", 1);
			List<LeaveOffice> leaveOfficeList1 = leaveOfficeService.findList(map1);
			
			Map map2 = new HashMap<>();
			map2.put("teacherId", teacher.getId());
			map2.put("orgId", orgId);
			//查询是否有离职状态为审核中的记录
			map2.put("status", 3);
			List<LeaveOffice> leaveOfficeList2 = leaveOfficeService.findList(map2);
			
			//有申请离职记录且离职状态为已通过的
			if(leaveOfficeList1 != null && leaveOfficeList1.size() > 0) {
				type = 1;
				return ReturnJson.jsonString("OK",type, AppConstants.OK);
			} 
			
			//有申请离职记录且离职状态为审核中的
			if(leaveOfficeList2 != null && leaveOfficeList2.size() > 0) {
				type = 2;
				return ReturnJson.jsonString("OK",type, AppConstants.OK);
			} 
			
			//没有申请离职记录或离职状态为已拒绝的
			return ReturnJson.jsonString("OK",type, AppConstants.OK);
		}
		
		return ReturnJson.jsonString("账号不存在",AppConstants.ACCOUNT_NOT_EXIST);
	}
	
	/**
	 * 离职列表
	 */
	@ResponseBody
	@RequestMapping(value = "leaveOfficeList", method = RequestMethod.POST)
	public String leaveOfficeList(HttpServletRequest request, String sessionId,Long orgId) {
	   	LoginUser loginUser = validateRedisLogiUser(sessionId);
	     AppLeaveOffice dto = new AppLeaveOffice();
	    Map map=new HashMap();
		map.put("teacherId", loginUser.getId());
    	map.put("orgId", orgId);
		List<LeaveOffice> leaveOffices=leaveOfficeService.findList(map);
		if(leaveOffices.size()!=0){
			LeaveOffice leaveOffice=leaveOffices.get(0);
		 	BeanUtils.copyProperties(leaveOffice, dto);
		 	dto.setApplyTimet(StringUtil.date2String(leaveOffice.getApplyTime()));
		 	if(leaveOffice.getLeaveTime() != null) {
		 		dto.setLeaveTimet(StringUtil.date2String(leaveOffice.getLeaveTime()));
		 	}
		 	if(leaveOffice.getStatus() == 2) {
		 		dto.setBackTime(StringUtil.date2String(leaveOffice.getLeaveTime()));
		 	}
		    return ReturnJson.jsonString("OK",dto ,AppConstants.OK);
		}else{
			  return ReturnJson.jsonString("无数据",dto ,AppConstants.NOT_FOUND);
			
		}
	
	}
	
	
}
