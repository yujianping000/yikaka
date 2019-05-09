package com.framework.loippi.controller.app;



import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppArea;
import com.framework.loippi.controller.app.model.AppCourse;
import com.framework.loippi.controller.app.model.AppCourseClass;
import com.framework.loippi.controller.app.model.AppCourseClassSummary;
import com.framework.loippi.controller.app.model.AppCourseComment;
import com.framework.loippi.controller.app.model.AppCourseTime;
import com.framework.loippi.controller.app.model.AppRollcallStudent;
import com.framework.loippi.controller.app.model.AppStudent1;
import com.framework.loippi.controller.app.model.AppStudent2;
import com.framework.loippi.controller.app.model.AppStudent3;
import com.framework.loippi.controller.app.model.AppStudent4;
import com.framework.loippi.controller.app.model.AppStudent5;
import com.framework.loippi.controller.app.model.AppTeacher;
import com.framework.loippi.controller.app.model.AppTeacherExperience;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.AppOrgnization;
import com.framework.loippi.entity.Area;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.entity.CourseType;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.OrgnizationSuggestion;
import com.framework.loippi.entity.TTrip;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.CourseTypeService;
import com.framework.loippi.service.OrderRemarkService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.OrgnizationSuggestionService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherExperienceService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.utils.HttpSend;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.SQLUtil;
import com.framework.loippi.utils.StringUtil;

import org.apache.log4j.Logger;
import org.bouncycastle.i18n.filter.SQLFilter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 机构
 */

@Controller
@RequestMapping("api/app/teacher/*")
public class AppTeacherController extends BaseController {

	private Logger log = Logger.getLogger(getClass());
	@Resource
    private TTripService tTripService;
    
	@Resource
	private  AreaService areaService;
	
	@Resource
	private  OrgnizationService orgnizationService;
	
	@Resource
	private  CourseTypeService  courseTypeService;
	
	@Resource
    private TParentService tParentService;
	
	@Resource
	private  OrgnizationSuggestionService orgnizationSuggestionService;
	@Resource
	private CourseService courseService;
	
	@Resource
	private CourseCommentService  courseCommentService;
	
	@Resource
	private CourseTimeService courseTimeService;
	
	@Resource
	private TeacherService  teacherService;

	@Resource
	private TeacherAuthService teacherAuthService;

	@Resource
	private TeacherExperienceService teacherExperienceService;
	
	@Resource
	private	OrderRemarkService orderRemarkService;

    /**
     * 获取教师详情
     */
    @ResponseBody
    @RequestMapping(value = "getTeacherInfo", method = RequestMethod.POST)
    public String getTeacherInfo(HttpServletRequest request) {
    	String id=request.getParameter("id");
    	Teacher teacher=teacherService.find(Long.parseLong(id));
    	
    	AppTeacher appTeacher=new AppTeacher();
    	if(teacher!=null){
				BeanUtils.copyProperties(teacher, appTeacher);
				if(teacher.getBirthday()!=null){
				appTeacher.setBirthdayString(StringUtil.date2String(teacher.getBirthday()));
				}
				
				
				//获取机构信息
				Map map=new HashMap();
				map.put("teacherId", teacher.getId());
				map.put("status", 1);
				 List<TeacherAuth>	teacherAuths=teacherAuthService.findList(map);
				 String orgIds="";
				 String orgNames="";
				if(teacherAuths!=null){
					for (TeacherAuth teacherAuth : teacherAuths) {
						Orgnization orgnization  =  orgnizationService.find(teacherAuth.getOrgId());
						if(orgnization!=null){
							orgIds=orgIds+orgnization.getId()+",";
							orgNames=orgNames+orgnization.getName()+",";
						}
					}
				}
				orgIds=StringUtil.delChar(orgIds, ",");
				orgNames=StringUtil.delChar(orgNames, ",");
				appTeacher.setOrgIds(orgIds);
				appTeacher.setOrgNames(orgNames);
				
				//教师经验
				List<AppTeacherExperience> appTeacherExperiences=new ArrayList();
				    map=new HashMap();
					map.put("teacherId", teacher.getId());
					map.put("order", " start");
					 List<TeacherExperience>	teacherExperiences=teacherExperienceService.findListByPage(map);
				    if(teacherExperiences!=null){
				    	for (TeacherExperience teacherExperience : teacherExperiences) {
				    		AppTeacherExperience dto2=new AppTeacherExperience();
	 		 				dto2.setContent(teacherExperience.getContent());
	 		 				if(teacherExperience.getStart()!=null){
	 		 					dto2.setStartString(StringUtil.date2String13(teacherExperience.getStart()));
	 		 				}
	 		 				
	 		 				if(teacherExperience.getEnd()!=null){
	 		 					dto2.setEndString(StringUtil.date2String13(teacherExperience.getEnd()));
	 		 				}
	 		 				appTeacherExperiences.add(dto2);
						}
				    }
				    appTeacher.setAppTeacherExperiences(appTeacherExperiences);
				    
				    
				
    	}
    

        return ReturnJson.jsonString("OK",appTeacher, AppConstants.OK);
    }
    
    
    /**
     * 教师课程列表
     */
    @ResponseBody
    @RequestMapping(value = "getCourses", method = RequestMethod.POST)
    public String getCourses(HttpServletRequest request) {
    	String teacherId=request.getParameter("teacherId");
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");

		Map map=new HashMap();
		
		map.put("teacherId", teacherId);
		map.put("pageNumber", (Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize));
		map.put("pageSize", Integer.parseInt(pageSize));
		map.put("order", " create_date desc");
		 List<AppCourse> appCourses=new ArrayList();
		List<Course> courses=courseService.findListByPage(map);
		if(courses!=null){
			
			for (Course course : courses) {
				AppCourse dto=new AppCourse();
 				BeanUtils.copyProperties(course, dto);
 				dto.setCreateDateString(StringUtil.date2String4(course.getCreateDate()));
 				
 				//教师
 				Teacher teacher=teacherService.find(course.getTeacherId());
 				if(teacher!=null){
 					dto.setTeacherMark(teacher.getMark());
 				dto.setTeacherAage(teacher.getAge());
 				dto.setTeacherAvatar(teacher.getAvatar());
 				dto.setTeacherName(teacher.getNickname());
 				}
 				//课程时间
 				
 				appCourses.add(dto);
			}
			
		}
		  return ReturnJson.jsonString("OK",appCourses, AppConstants.OK);
    }
    
    
    /**
     * 教师课程评论 courseCommentService
     */
    @ResponseBody
    @RequestMapping(value = "getCourseComments", method = RequestMethod.POST)
    public String getCourseComments(HttpServletRequest request) {
    	String teacherId=request.getParameter("teacherId");
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		String courseId=request.getParameter("courseId");
		Map map=new HashMap();
		map.put("courseId", courseId);
		map.put("teacherId", teacherId);
		map.put("pageNumber", (Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize));
		map.put("pageSize", Integer.parseInt(pageSize));
		map.put("order", "  create_date desc");
		 List<AppCourseComment> appCourseComments=new ArrayList();
		List<CourseComment> courseComments=courseCommentService.findListByPage(map);
		if(courseComments!=null){
			
			for (CourseComment courseComment : courseComments) {
				AppCourseComment dto=new AppCourseComment();
 				BeanUtils.copyProperties(courseComment, dto);
 				dto.setCreateDateString(StringUtil.date2String4(courseComment.getCreateDate()));
 				
 				appCourseComments.add(dto);
			}
			
		}
		  return ReturnJson.jsonString("OK",appCourseComments, AppConstants.OK);
    }
    
    
   
   
}
