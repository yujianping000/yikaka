package com.framework.loippi.controller.app;



import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppActivitySignup;
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
import com.framework.loippi.entity.CallRoll;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.CourseSummary;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.entity.CourseTimeComment;
import com.framework.loippi.entity.CourseType;
import com.framework.loippi.entity.OffWork;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.OrgnizationSuggestion;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.StudentCommentRemark;
import com.framework.loippi.entity.TTrip;
import com.framework.loippi.entity.TeaStuCoursetime;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.service.CallRollService;
import com.framework.loippi.service.CourseCommentRemarkService;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseSummaryService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.CourseTimeCommentService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.CourseTypeService;
import com.framework.loippi.service.OffWorkService;
import com.framework.loippi.service.OrderRemarkService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.OrgnizationSuggestionService;
import com.framework.loippi.service.StudentCommentRemarkService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.service.TeaStuCoursetimeService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherExperienceService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.utils.HttpSend;
import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.SQLUtil;
import com.framework.loippi.utils.StringUtil;

import lombok.Data;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 教师端课程
 */

@Controller
@RequestMapping("api/app/platteacher/course/*")
public class AppPlatTeacherCourseController extends BaseController {

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
	@Resource
	private	OrderService orderService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private	CallRollService callRollService;
    
	@Resource
	private	OrgDynamicService  orgDynamicService;
	
	@Resource
	private StudentCommentRemarkService studentCommentRemarkService;
	
	@Resource
	private OffWorkService  offWorkService;
	
	@Resource
	private CourseStuTimeService courseStuTimeService;
	
	
	@Resource
	private CourseTeaTimeService courseTeaTimeService;
	
	
	@Resource
	private CourseSummaryService courseSummaryService;
	@Resource
	private TeaStuCoursetimeService teaStuCoursetimeService;
	@Resource
	private CourseTimeCommentService courseTimeCommentService;
	@Resource
	private UserUnreadService userUnreadService;
	
	
	
    /**
     * 教师课程列表
     */
    @ResponseBody
    @RequestMapping(value = "getCourses", method = RequestMethod.POST)
    public String getCourses(HttpServletRequest request) {
    	
 
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		String sessionId=request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser == null) {
			ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.USER_SESS_EXPIRED);
		} 
		
		Map map=new HashMap();
		map.put("orgId", loginUser.getOrgId());
		map.put("teacherId", loginUser.getId());
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
     * 课程详情
     */
    @ResponseBody
    @RequestMapping(value = "getCourseById", method = RequestMethod.POST)
    public String getCourseById(HttpServletRequest request) {
    	String id=request.getParameter("id");
		
		AppCourse dto=new AppCourse();
		Course course=courseService.find(Long.parseLong(id));
		if(course!=null){
			
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
 				
 				Map parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("order", " course_time3");
 				 List<AppCourseTime> appCourseTimes=new ArrayList();		
 				List<CourseTime> courseTimes=courseTimeService.findListByPage(parameter);
 				if(courseTimes!=null){
 					for (CourseTime courseTime : courseTimes) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseTime, dto2);
 		 				//dto2.setCourseTimeString(StringUtil.date2String4(courseTime.getCourseTime()));
 		 				appCourseTimes.add(dto2);
					}
 				}
 				
 				dto.setAppCourseTimes(appCourseTimes);
 	
			
		}
		  return ReturnJson.jsonString("OK",dto, AppConstants.OK);
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
		String sessionId=request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser == null) {
			ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.USER_SESS_EXPIRED);
		} 
		map.put("courseId", courseId);
		map.put("teacherId", loginUser.getId());
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
    
    
    /**
     * 查看学生-全部
     */
    @ResponseBody
    @RequestMapping(value = "getCourseAllStudent", method = RequestMethod.POST)
    public String getCourseAll(@RequestParam(value = "pageNumber") Integer pageNumber,
    		@RequestParam(value = "pageSize")Integer pageSize
    		,@RequestParam(value = "courseId")Long courseId,HttpServletRequest request) {



		Map map=new HashMap();
        map.put("order", "created_time desc");
        map.put("pageNumber", (pageNumber-1)*pageSize);
        map.put("pageSize", pageSize);
        map.put("type",1);
        map.put("targetId",courseId);
        
        
 
        	List list=new ArrayList();
        	list.add(2);
        	list.add(3);
        	list.add(8);
            map.put("orderStatus", list);
		List<Order> orders = orderService.findListByPage(map);
		List  listAppStudent1s=new ArrayList();
		if(listAppStudent1s!=null){
			for (Order order : orders) {
				AppStudent1 appStudent1=new AppStudent1();
				appStudent1.setAvatar(order.getStuAvatar());
				appStudent1.setId(order.getStuId());
				appStudent1.setName(order.getStuName());
				appStudent1.setPeriod(order.getClasstimes());
				listAppStudent1s.add(appStudent1);
			}
		}
		
		  return ReturnJson.jsonString("OK",listAppStudent1s, AppConstants.OK);
    }
    
    /**
     * 查看学生-续班
     */
    @ResponseBody
    @RequestMapping(value = "getCourseContinue", method = RequestMethod.POST)
    public String getCourseContinue(@RequestParam(value = "pageNumber") Integer pageNumber,
    		@RequestParam(value = "pageSize")Integer pageSize
    		,@RequestParam(value = "courseId")Long courseId,HttpServletRequest request) {
    	
    	
    	Map map=new HashMap();
        map.put("order", "created_time desc");
        map.put("pageNumber", (pageNumber-1)*pageSize);
        map.put("pageSize", pageSize);
        map.put("type",1);
        map.put("targetId",courseId);
        map.put("continueclass",1);
        
 
        	List list=new ArrayList();
        	list.add(2);
        	list.add(3);
        	list.add(8);
            map.put("orderStatus", list);
		List<Order> orders = orderService.findListByPage(map);
		List  listAppStudent1s=new ArrayList();
		if(listAppStudent1s!=null){
			for (Order order : orders) {
				AppStudent2 appStudent1=new AppStudent2();
				appStudent1.setAvatar(order.getStuAvatar());
				appStudent1.setId(order.getStuId());
				appStudent1.setName(order.getStuName());
				appStudent1.setTime(StringUtil.date2String(order.getCreatedTime()));
				listAppStudent1s.add(appStudent1);
			}
		}
		
    	
    	
	
		  return ReturnJson.jsonString("OK",listAppStudent1s, AppConstants.OK);
    }
    
    
    /**
     * 查看学生-请假
     */
    @ResponseBody
    @RequestMapping(value = "getCourseLeave", method = RequestMethod.POST)
    public String getCourseLeave(@RequestParam(value = "pageNumber") Integer pageNumber,
    		@RequestParam(value = "pageSize")Integer pageSize
    		,@RequestParam(value = "courseId")Long courseId,HttpServletRequest request) {

		Map map=new HashMap();
		map.put("courseId", courseId);
		map.put("userType", 2);
        map.put("order", "created_time desc");
        map.put("pageNumber", (pageNumber-1)*pageSize);
        map.put("pageSize", pageSize);
		List  list=new ArrayList();
		 List<OffWork>  offWorks=offWorkService.findListByPage(map);
		 if(offWorks!=null){
			 for (OffWork offWork : offWorks) {
				 AppStudent3 appStudent1=new AppStudent3();
			
					appStudent1.setId(offWork.getId());
					appStudent1.setName(offWork.getStuname());
					appStudent1.setTime(StringUtil.date2String4(offWork.getStart()));
					appStudent1.setStatus(offWork.getStatus()+"");//待确认
					appStudent1.setReason(offWork.getReason());
					appStudent1.setAvatar(offWork.getStuavatar());
					list.add(appStudent1);
			}
		 }
	
	
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    
    /**
     * 查看学生-旷课
     */
    @ResponseBody
    @RequestMapping(value = "getCourseAbsent", method = RequestMethod.POST)
    public String getCourseAbsent(@RequestParam(value = "pageNumber") Integer pageNumber,
    		@RequestParam(value = "pageSize")Integer pageSize
    		,@RequestParam(value = "courseId")Long courseId,HttpServletRequest request) {

    	Map map=new HashMap();
		map.put("courseId", courseId);
		map.put("status", 5);
        map.put("order", "create_time desc");
        map.put("pageNumber", (pageNumber-1)*pageSize);
        map.put("pageSize", pageSize);
		List  list=new ArrayList();
		 List<CourseStuTime>  courseStuTimes=courseStuTimeService.findListByPage(map);
    	
		 if(courseStuTimes!=null){
			 for (CourseStuTime courseStuTime : courseStuTimes) {
					AppStudent1 appStudent1=new AppStudent1();
				    Long orderId=	courseStuTime.getOrderId();
			          Order	order	=orderService.find(orderId);
			          if(order!=null){
			        		appStudent1.setAvatar(order.getStuAvatar());
							appStudent1.setId(courseStuTime.getId());
							appStudent1.setName(order.getStuName());
							appStudent1.setPeriod(order.getClasstimes());
							list.add(appStudent1);
			          }
			}
		 }
	
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    
    /**
     * 查看课表-未开始
     */
    @ResponseBody
    @RequestMapping(value = "getCourseClass1", method = RequestMethod.POST)
    public String getCourseClass1(@RequestParam(value = "pageNumber") Integer pageNumber,
    		@RequestParam(value = "pageSize")Integer pageSize
    		,@RequestParam(value = "courseId")Long courseId,HttpServletRequest request) {

    	String type=request.getParameter("type");

		Map map=new HashMap();
		List  list=new ArrayList();
		//未开始
			Map parameter=new HashMap();
			parameter.put("courseId",courseId);
			parameter.put("order", "course_time3");
			parameter.put("pageNumber", (pageNumber-1)*pageSize);
			parameter.put("pageSize", pageSize);
			parameter.put("pageSize3",Long.parseLong(StringUtil.date2String3(new Date())));

			List<CourseTeaTime> courseTeaTimes=courseTeaTimeService.findListByPage(parameter);
			if(courseTeaTimes!=null){
				for (CourseTeaTime courseTeaTime : courseTeaTimes) {
					AppCourseClass apCourseClass=new AppCourseClass();
					apCourseClass.setId(courseTeaTime.getId());
					apCourseClass.setTime(StringUtil.date2String13(courseTeaTime.getCourseTime())
							+" "+courseTeaTime.getCourseTime1()+"~"+courseTeaTime.getCourseTime2());
					list.add(apCourseClass);

			}
			}
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    /**
     * 查看课表-带总结
     */
    @ResponseBody
    @RequestMapping(value = "getCourseClass2", method = RequestMethod.POST)
    public String getCourseClass2(HttpServletRequest request) {
    	String courseId=request.getParameter("courseId");
    	String type=request.getParameter("type");

		Map map=new HashMap();
		List  list=new ArrayList();
		//待总结
			Map parameter=new HashMap();
			parameter.put("courseId",courseId);
			parameter.put("order", "course_time3");
			parameter.put("nosummary",1);
			parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(new Date())));
			
			
			String pageNumbers=request.getParameter("pageNumber");
			String pageSizes=request.getParameter("pageSize");
			if(!StringUtil.isEmpty(pageNumbers)&&!StringUtil.isEmpty(pageSizes)){
				Integer pageNumber=Integer.parseInt(pageNumbers);
				Integer pageSize=Integer.parseInt(pageSizes);
				parameter.put("pageNumber", (pageNumber-1)*pageSize);
				parameter.put("pageSize", pageSize);
			}
			
			List<CourseTeaTime> courseTeaTimes=courseTeaTimeService.findListByPage(parameter);
			if(courseTeaTimes!=null){
				for (CourseTeaTime courseTeaTime : courseTeaTimes) {
					AppCourseClass apCourseClass=new AppCourseClass();
					apCourseClass.setId(courseTeaTime.getId());
					apCourseClass.setTime(StringUtil.date2String13(courseTeaTime.getCourseTime())
							+" "+courseTeaTime.getCourseTime1()+"~"+courseTeaTime.getCourseTime2());
					list.add(apCourseClass);

			}
			}
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    
    /**
     * 查看课表-待评价
     */
    @ResponseBody
    @RequestMapping(value = "getCourseClass3", method = RequestMethod.POST)
    public String getCourseClass3(HttpServletRequest request) {
    	String courseId=request.getParameter("courseId");
    	String type=request.getParameter("type");

		Map map=new HashMap();
		List  list=new ArrayList();
		//待评价
			Map parameter=new HashMap();
			parameter.put("courseId",courseId);
			parameter.put("order", "course_time3");
			parameter.put("nocomment",1);
			parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(new Date())));
			
			String pageNumbers=request.getParameter("pageNumber");
			String pageSizes=request.getParameter("pageSize");
			if(!StringUtil.isEmpty(pageNumbers)&&!StringUtil.isEmpty(pageSizes)){
				Integer pageNumber=Integer.parseInt(pageNumbers);
				Integer pageSize=Integer.parseInt(pageSizes);
				parameter.put("pageNumber", (pageNumber-1)*pageSize);
				parameter.put("pageSize", pageSize);
			}

			List<CourseTeaTime> courseTeaTimes=courseTeaTimeService.findListByPage(parameter);
			if(courseTeaTimes!=null){
				for (CourseTeaTime courseTeaTime : courseTeaTimes) {
					AppCourseClass apCourseClass=new AppCourseClass();
					apCourseClass.setId(courseTeaTime.getId());
					apCourseClass.setTime(StringUtil.date2String13(courseTeaTime.getCourseTime())
							+" "+courseTeaTime.getCourseTime1()+"~"+courseTeaTime.getCourseTime2());
					list.add(apCourseClass);

			}
			}
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    /**
     * 查看课表-已结束
     */
    @ResponseBody
    @RequestMapping(value = "getCourseClass4", method = RequestMethod.POST)
    public String getCourseClass4(HttpServletRequest request) {
    	String courseId=request.getParameter("courseId");
    	String type=request.getParameter("type");

		Map map=new HashMap();
		List  list=new ArrayList();
		//已结束
			Map parameter=new HashMap();
			parameter.put("courseId",courseId);
			parameter.put("order", "course_time3");
			parameter.put("comment",1);
			parameter.put("summary",1);
			parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(new Date())));
			
			String pageNumbers=request.getParameter("pageNumber");
			String pageSizes=request.getParameter("pageSize");
			if(!StringUtil.isEmpty(pageNumbers)&&!StringUtil.isEmpty(pageSizes)){
				Integer pageNumber=Integer.parseInt(pageNumbers);
				Integer pageSize=Integer.parseInt(pageSizes);
				parameter.put("pageNumber", (pageNumber-1)*pageSize);
				parameter.put("pageSize", pageSize);
			}
			List<CourseTeaTime> courseTeaTimes=courseTeaTimeService.findListByPage(parameter);
			if(courseTeaTimes!=null){
				for (CourseTeaTime courseTeaTime : courseTeaTimes) {
					AppCourseClass apCourseClass=new AppCourseClass();
					apCourseClass.setId(courseTeaTime.getId());
					apCourseClass.setTime(StringUtil.date2String13(courseTeaTime.getCourseTime())
							+" "+courseTeaTime.getCourseTime1()+"~"+courseTeaTime.getCourseTime2());
					list.add(apCourseClass);

			}
			}
	
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    /**
     * 查看课表-设置课程总结
     */
    @ResponseBody
    @RequestMapping(value = "setCourseClassSummary", method = RequestMethod.POST)
    public String setCourseClassSummary(HttpServletRequest request) {
    	String id=request.getParameter("id");
    	
    	CourseTeaTime courseTeaTime=courseTeaTimeService.find(Long.parseLong(id));
    	String content=request.getParameter("content");
    	String image=request.getParameter("image");
		String sessionId = request.getParameter("sessionId");

		LoginUser loginuser = validateRedisLogiUser(sessionId);
    	Map map=new HashMap();
    	map.put("courseId", courseTeaTime.getCourseId());
    	map.put("courseTime3", courseTeaTime.getCourseTime3());
    	List<CourseStuTime>  courseStuTimes=	courseStuTimeService.findListByPage(map);
    	if(courseStuTimes!=null){
    		for (CourseStuTime courseStuTime : courseStuTimes) {
    			 Order  order	=orderService.find(courseStuTime.getOrderId());
    			 if(order!=null&&(order.getStatus()==2||order.getStatus()==3||order.getStatus()==8)){
    				 OrgDynamic orgDynamic=new OrgDynamic();
    				 orgDynamic.setAvatar(loginuser.getAvatar());
    				 orgDynamic.setCourseId(courseTeaTime.getCourseId());
    				 orgDynamic.setCourseName(order.getCourseName());
    				 orgDynamic.setCreateDate(new Date());
    				 orgDynamic.setImgUrl(image);
    				 orgDynamic.setTextContent(content);
    				 orgDynamic.setTeacherId(loginuser.getId());
    				 orgDynamic.setTeacherName(loginuser.getUserName());
    				 orgDynamic.setTargetId(courseStuTime.getId());
    				 orgDynamic.setUserId(order.getUid());
    				 orgDynamic.setStuId(order.getStuId());
    				 orgDynamic.setStatus(1);
    				 orgDynamic.setType(1);
    				 orgDynamic.setUserType(1);
    				 orgDynamicService.save(orgDynamic);
    			 }
			}
    	}
    	
    	
    	courseTeaTime.setSummary(1);
    	courseTeaTimeService.update(courseTeaTime);
    	
    	
    	CourseSummary courseSummary=new CourseSummary();
    	courseSummary.setContent(content);
    	courseSummary.setCreateDate(new Date());
    	courseSummary.setImage(image);
    	courseSummary.setTeaId(loginuser.getId());
    	courseSummary.setTeaName(loginuser.getUserName());
    	courseSummary.setCourseTeaTimeId(Long.parseLong(id));
    	courseSummary.setAvatar(loginuser.getAvatar());
    	courseSummaryService.save(courseSummary);
    	
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
   
    /**
     * 查看课表-课程查看总结
     */
    @ResponseBody
    @RequestMapping(value = "getCourseClassSummary", method = RequestMethod.POST)
    public String getCourseClassSummary(HttpServletRequest request) {
    	String id=request.getParameter("id");
    	
    	CourseSummary	courseSummary=courseSummaryService.find("courseTeaTimeId", Long.parseLong(id));
    
    	AppCourseClassSummary appCourseClassSummary=new AppCourseClassSummary();
    	
    	if(courseSummary!=null){
    	appCourseClassSummary.setId(courseSummary.getId());
    	appCourseClassSummary.setContent(courseSummary.getContent());
    	appCourseClassSummary.setAvatar(courseSummary.getImage());
    	appCourseClassSummary.setImage(courseSummary.getImage());
    	appCourseClassSummary.setName(courseSummary.getTeaName());
    	appCourseClassSummary.setTimeString(StringUtil.date2String4(courseSummary.getCreateDate()));
    	appCourseClassSummary.setAvatar(courseSummary.getAvatar());
    	}
		  return ReturnJson.jsonString("OK", appCourseClassSummary,AppConstants.OK);
    }
   
    
    
    /**
     * 查看课表-课程未评价
     */
    @ResponseBody
    @RequestMapping(value = "getCourseClassNoComment", method = RequestMethod.POST)
    public String setCourseClassNoComment(HttpServletRequest request) {
    	String id=request.getParameter("id");
    	String courseId=request.getParameter("courseId");
    	CourseTeaTime courseTeaTime=courseTeaTimeService.find(Long.parseLong(id));
		List  list=new ArrayList();

		Map parameter=new HashMap();
		parameter.put("courseId", courseTeaTime.getCourseId());
		parameter.put("courseTime3", courseTeaTime.getCourseTime3());
		parameter.put("nocomment", 1);
		
		
		 List<TeaStuCoursetime> teaStuCoursetimes=	teaStuCoursetimeService.findListByPage(parameter);
		if(teaStuCoursetimes!=null){
			for (TeaStuCoursetime teaStuCoursetime : teaStuCoursetimes) {
				AppStudent4 appStudent1=new AppStudent4();
				appStudent1.setAvatar(teaStuCoursetime.getAvatar());
				appStudent1.setId(teaStuCoursetime.getId());
				appStudent1.setName(teaStuCoursetime.getNickname());
				list.add(appStudent1);
			}
		}
		
	
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    /**
     * 查看课表-课程已评价
     */
    @ResponseBody
    @RequestMapping(value = "getCourseClassHadComment", method = RequestMethod.POST)
    public String getCourseClassComment(HttpServletRequest request) {
    	String id=request.getParameter("id");
    	String courseId=request.getParameter("courseId");
    	CourseTeaTime courseTeaTime=courseTeaTimeService.find(Long.parseLong(id));
    	List  list=new ArrayList();
    	
    	Map parameter=new HashMap();
		parameter.put("courseId", courseTeaTime.getCourseId());
		parameter.put("courseTime3", courseTeaTime.getCourseTime3());
		 List<CourseTimeComment> 	courseTimeComments=
				 courseTimeCommentService.findListByPage(parameter);
        if(courseTimeComments!=null){
        	for (CourseTimeComment courseTimeComment : courseTimeComments) {
        		AppStudent4 appStudent1=new AppStudent4();
        		appStudent1.setAvatar(courseTimeComment.getAvatar());
        		appStudent1.setId(courseTimeComment.getId());
        		appStudent1.setName(courseTimeComment.getName());
        		appStudent1.setContent(courseTimeComment.getContent());
        		appStudent1.setStar(courseTimeComment.getFavStar()+"");
        		appStudent1.setMark(courseTimeComment.getMark());
        		appStudent1.setNomark(courseTimeComment.getNomark());
        		list.add(appStudent1);
			}
        }
		
		
	
	
		  return ReturnJson.jsonString("OK",list, AppConstants.OK);
    }
    
    
    /**
     * 查看课表-课程评价标签
     */
    @ResponseBody
    @RequestMapping(value = "getCommentMark", method = RequestMethod.POST)
    public String getCommentMark(HttpServletRequest request) {
		 return  ReturnJson.jsonString("OK",studentCommentRemarkService.findAll(),AppConstants.OK);

    }
    
    
    /**
     * 查看课表-对课程的课时进行评价
     */
    @ResponseBody
    @RequestMapping(value = "setCourseClassComment", method = RequestMethod.POST)
    public String setCourseClassComment(HttpServletRequest request) {

    	String id=request.getParameter("id");
    	System.err.println("id====================="+id);
    	String studentId=request.getParameter("studentId");
    	System.err.println("studentId====================="+studentId);
    	String content=request.getParameter("content");
    	String star=request.getParameter("star");
    	String mark=request.getParameter("mark");
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
    	CourseTeaTime courseTeaTime=courseTeaTimeService.find(Long.parseLong(id));
    	List<StudentCommentRemark>  studentCommentRemarks=studentCommentRemarkService.findAll();
		if(!StringUtil.isEmpty(studentId)){
			String[] studentIds= studentId.split(",");
			for (int i = 0; i < studentIds.length; i++) {
				Map parameter=new HashMap();
				parameter.put("id", studentIds[i]);
				parameter.put("courseId", courseTeaTime.getCourseId());
				parameter.put("courseTime3", courseTeaTime.getCourseTime3());
				List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
				if(courseStuTimes!=null){
					for (CourseStuTime courseStuTime : courseStuTimes) {
						
						if(courseStuTime.getComment()==null||courseStuTime.getComment()!=1){
						courseStuTime.setComment(1);
						courseStuTimeService.update(courseStuTime);
						CourseTimeComment	courseTimeComment=new CourseTimeComment();
						Student student=studentService.find(courseStuTime.getStudentId());
						if(student!=null){
							
							//课程时间评论
							courseTimeComment.setAvatar(student.getAvatar());
							courseTimeComment.setContent(content);
							courseTimeComment.setCourseId(courseStuTime.getCourseId());
							courseTimeComment.setCourseTime(courseStuTime.getCourseTime());
							courseTimeComment.setCourseTime1(courseStuTime.getCourseTime1());
							courseTimeComment.setCourseTime2(courseStuTime.getCourseTime2());
							courseTimeComment.setCourseTime3(courseStuTime.getCourseTime3());
							courseTimeComment.setCreateDate(new Date());
							courseTimeComment.setFavStar(Integer.parseInt(star));
							courseTimeComment.setMark(mark);
							courseTimeComment.setName(student.getNickname());
							courseTimeComment.setStudentId(student.getId());
							courseTimeComment.setTeacherAvatar(loginuser.getAvatar());
							courseTimeComment.setTeacherId(loginuser.getId());
							courseTimeComment.setTeacherName(loginuser.getUserName());
							courseTimeComment.setTimeId(courseStuTime.getId());
							courseTimeComment.setUid(student.getUid());
							courseTimeComment.setOrgId(courseStuTime.getOrderId());
							courseTimeComment.setNomark(this.nomark(studentCommentRemarks, mark));
							courseTimeComment.setType(1);
							courseTimeCommentService.save(courseTimeComment);
							
							//动态
							Course course=courseService.find(courseStuTime.getCourseId());
							if(course!=null){
							 OrgDynamic orgDynamic=new OrgDynamic();
		    				 orgDynamic.setAvatar(loginuser.getAvatar());
		    				 orgDynamic.setCourseId(courseTeaTime.getCourseId());
		    				 orgDynamic.setCourseName(course.getName());
		    				 orgDynamic.setCreateDate(new Date());
		    				 orgDynamic.setMark(mark);
		    				 orgDynamic.setTextContent(content);
		    				 orgDynamic.setTeacherId(loginuser.getId());
		    				 orgDynamic.setTeacherName(loginuser.getUserName());
		    				 orgDynamic.setTargetId(courseStuTime.getId());
		    				 orgDynamic.setUserId(student.getUid());
		    				 orgDynamic.setStuId(student.getId());
		    				 orgDynamic.setStatus(1);
		    				 orgDynamic.setType(1);
		    				 orgDynamic.setUserType(1);
		    				 orgDynamic.setStarts(Integer.parseInt(star));
		    				 orgDynamicService.save(orgDynamic);
							}
							
						}
						}
					
						
					}
				}
			}
		}
		
		//update 教师课程时间是否 已经评价
		Map parameter=new HashMap();
		parameter.put("nocomment", 1);
		parameter.put("courseId", courseTeaTime.getCourseId());
		parameter.put("courseTime3", courseTeaTime.getCourseTime3());
		List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
		if(courseStuTimes==null||courseStuTimes.size()==0){
			courseTeaTime.setComment(1);
			courseTeaTimeService.update(courseTeaTime);
		}
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
    
    private String nomark(	List<StudentCommentRemark>  studentCommentRemarks,String mark){
    	String nomark="";
    	if(!StringUtil.isEmpty(mark)){
    		String [] marks=mark.split("##");
    
    			for (StudentCommentRemark studentCommentRemark : studentCommentRemarks) {
    				boolean b=false;
    				for (int i = 0; i < marks.length; i++) {
    				    if(marks[i].equals(studentCommentRemark.getName())){
    				    	b=true;
    				    }
    				}
    				if(!b){
    					nomark=nomark+"##"+studentCommentRemark.getName();
    				}
    			}
			
    	}
    	
    	nomark=StringUtil.delChar(nomark, "#");
    	nomark=StringUtil.delChar(nomark, "#");
    	
    	return nomark;
    }
    
    
    /**
     * 上课点名-上课点名学生列表
     */
    @ResponseBody
    @RequestMapping(value = "getRollcall", method = RequestMethod.POST)
    public String getRollcall(HttpServletRequest request) {
    	String courseId=request.getParameter("courseId");
System.err.println("courseId============"+courseId);
    	log.error("courseId==============="+courseId);
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
    	List<AppStudent5>  all=new ArrayList();//全部学生
        List<AppStudent5>  arrived=new ArrayList();//到达学生
        List<AppStudent5>  pendingConfirm=new ArrayList();//待确定
        List<AppStudent5>  noarrived=new ArrayList();//未到
        List<AppStudent5>  leave=new ArrayList();//请假的
		
		Map parameter=new HashMap();
		parameter.put("courseId",courseId);
		parameter.put("teacherId", loginuser.getId());
		//parameter.put("inrollcalltime",Long.parseLong(StringUtil.date2String3(new Date())));
	
	  //教师提前十分钟点名	
	  Calendar nowTime = Calendar.getInstance();
	  nowTime.add(Calendar.MINUTE, 10);
	  parameter.put("aheadInrollcalltime",Long.parseLong(StringUtil.date2String3(nowTime.getTime())));
	  System.err.println("aheadInrollcalltime============"+Long.parseLong(StringUtil.date2String3(nowTime.getTime())));

		List<CourseTeaTime> courseTeaTimes=courseTeaTimeService.findListByPage(parameter);
    	if(courseTeaTimes!=null&&courseTeaTimes.size()>0){
    		CourseTeaTime courseTeaTime=	courseTeaTimes.get(0);
    		
    		//已经按了点名
    		if(courseTeaTime.getCallroll()!=null&&courseTeaTime.getCallroll()==1){
    		    parameter=new HashMap();
    			parameter.put("courseId", courseTeaTime.getCourseId());
    			parameter.put("courseTime3", courseTeaTime.getCourseTime3());
    			List<TeaStuCoursetime> teaStuCoursetimes=	teaStuCoursetimeService.findListByPage(parameter);
    			if(teaStuCoursetimes!=null){
    				for (TeaStuCoursetime teaStuCoursetime : teaStuCoursetimes) {
						
    					//到达学生
    					if(2==teaStuCoursetime.getStatus()){
    						AppStudent5 appStudent1=new AppStudent5();
    						appStudent1.setAvatar(teaStuCoursetime.getAvatar());
    						appStudent1.setId(teaStuCoursetime.getStudentId());
    						appStudent1.setName(teaStuCoursetime.getNickname());
    						arrived.add(appStudent1);
    					}
    					//待确定
    					else if(1==teaStuCoursetime.getStatus()){
    						AppStudent5 appStudent1=new AppStudent5();
    						appStudent1.setAvatar(teaStuCoursetime.getAvatar());
    						appStudent1.setId(teaStuCoursetime.getStudentId());
    						appStudent1.setName(teaStuCoursetime.getNickname());
    						pendingConfirm.add(appStudent1);
    					}
    					//未到
    					else if(5==teaStuCoursetime.getStatus()){
    						AppStudent5 appStudent1=new AppStudent5();
    						appStudent1.setAvatar(teaStuCoursetime.getAvatar());
    						appStudent1.setId(teaStuCoursetime.getStudentId());
    						appStudent1.setName(teaStuCoursetime.getNickname());
    						noarrived.add(appStudent1);
    					}
    					//请假的
    					else if(3==teaStuCoursetime.getStatus()){
    						AppStudent5 appStudent1=new AppStudent5();
    						appStudent1.setAvatar(teaStuCoursetime.getAvatar());
    						appStudent1.setId(teaStuCoursetime.getStudentId());
    						appStudent1.setName(teaStuCoursetime.getNickname());
    						leave.add(appStudent1);
    					}
    					
					}
    			}
    			
    			
    			
    		}else{
    			 parameter=new HashMap();
     			parameter.put("courseId", courseTeaTime.getCourseId());
     			parameter.put("courseTime3", courseTeaTime.getCourseTime3());
     			List<TeaStuCoursetime> teaStuCoursetimes=	teaStuCoursetimeService.findListByPage(parameter);
     			if(teaStuCoursetimes!=null){
     				for (TeaStuCoursetime teaStuCoursetime : teaStuCoursetimes) {
 						
     					//请假
     					if(3==teaStuCoursetime.getStatus()){
     						AppStudent5 appStudent1=new AppStudent5();
     						appStudent1.setAvatar(teaStuCoursetime.getAvatar());
     						appStudent1.setId(teaStuCoursetime.getStudentId());
     						appStudent1.setName(teaStuCoursetime.getNickname());
     						leave.add(appStudent1);
     					}else{
     						AppStudent5 appStudent1=new AppStudent5();
     						appStudent1.setAvatar(teaStuCoursetime.getAvatar());
     						appStudent1.setId(teaStuCoursetime.getStudentId());
     						appStudent1.setName(teaStuCoursetime.getNickname());
     						all.add(appStudent1);
     					}
     					
 					}
     			}
    		}
    		
    	}
    	
    	AppRollcallStudent appRollcallStudent=new AppRollcallStudent();
    	
    	//查询教师上课时间
		appRollcallStudent.setAll(all);
		appRollcallStudent.setArrived(arrived);
		appRollcallStudent.setLeave(leave);
		appRollcallStudent.setNoarrived(noarrived);
		appRollcallStudent.setPendingConfirm(pendingConfirm);
    	
    	
		  return ReturnJson.jsonString("OK",appRollcallStudent, AppConstants.OK);
    }
    
    /**
     * 上课点名
     */
    @ResponseBody
    @RequestMapping(value = "rollcall", method = RequestMethod.POST)
    public String rollcall(HttpServletRequest request) {

    	String courseId=request.getParameter("courseId");
		String sessionId=request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		

		//检查是否在点名时间，不在的话不能触发点名时间
		
		Course	course=	courseService.find(Long.parseLong(courseId));
		Teacher teacher=new Teacher();
		if(course!=null){
			teacher=	teacherService.find(course.getTeacherId());
		}else{
			  return ReturnJson.jsonString("OK", AppConstants.PARAMETER__NOT_COMPLETE);
		}
			

		 
		 
			Map parameter=new HashMap();
			parameter.put("courseId",courseId);
			parameter.put("teacherId", loginUser.getId());
			//parameter.put("inrollcalltime",Long.parseLong(StringUtil.date2String3(new Date())));
			
			//教师提前十分钟点名	
			Calendar nowTime = Calendar.getInstance();
			nowTime.add(Calendar.MINUTE, 10);
			parameter.put("aheadInrollcalltime",Long.parseLong(StringUtil.date2String3(nowTime.getTime())));
			System.err.println("aheadInrollcalltime============"+Long.parseLong(StringUtil.date2String3(nowTime.getTime())));

			List<CourseTeaTime> courseTeaTimes=courseTeaTimeService.findListByPage(parameter);
	    	if(courseTeaTimes!=null&&courseTeaTimes.size()>0){
	    		CourseTeaTime courseTeaTime=	courseTeaTimes.get(0);
			
    		    parameter=new HashMap();
    			parameter.put("courseId", courseTeaTime.getCourseId());
    			parameter.put("courseTime3", courseTeaTime.getCourseTime3());
    			List<TeaStuCoursetime> teaStuCoursetimes=	teaStuCoursetimeService.findListByPage(parameter);
		      if(teaStuCoursetimes!=null&&teaStuCoursetimes.size()>0){
		         for (TeaStuCoursetime teaStuCoursetime : teaStuCoursetimes) {
		        	 //生成点名数据
		              CallRoll   callRoll=new  CallRoll();
				      callRoll.setCourseId(Long.parseLong(courseId));
				      callRoll.setCourseTimeId(teaStuCoursetime.getId());
				      callRoll.setCreateDate(new Date());
				      callRoll.setStatus(2);
				      callRoll.setParentId(teaStuCoursetime.getUid());
				      callRoll.setAvatar(teaStuCoursetime.getAvatar());
				      callRoll.setStuId(teaStuCoursetime.getStudentId());
				      callRoll.setStuName(teaStuCoursetime.getNickname());
				      callRoll.setTeacherId(loginUser.getId());
				      callRollService.save(callRoll);
				      
				      //生成点名动态信息
				      
				     // 点名提示-吉他弹唱零基础速成班
				      
				      OrgDynamic orgDynamic=new OrgDynamic();
				      orgDynamic.setPrompt("点名提示-"+course.getName());
				      orgDynamic.setAvatar(teacher.getAvatar());
				      orgDynamic.setCallrollId(callRoll.getId());
				      orgDynamic.setCallrollStatus(2);
				      orgDynamic.setCourseId(Long.parseLong(courseId));
				      orgDynamic.setCourseName(course.getName());
				      orgDynamic.setCreateDate(new Date());
				      orgDynamic.setImgUrl(null);
				      orgDynamic.setStuId(teaStuCoursetime.getStudentId());
				      orgDynamic.setTeacherId(teacher.getId());
				      orgDynamic.setTeacherName(teacher.getNickname());
				      orgDynamic.setType(3);
				      orgDynamic.setUserId(teaStuCoursetime.getUid());
				      orgDynamic.setUserType(1);
				      orgDynamicService.save(orgDynamic);
				      
				      userUnreadService.setUserUnread(1, teaStuCoursetime.getUid(), 3);
				  	// 极光推送信息
						JpushRunnable t1 = new JpushRunnable("点名提示-"+course.getName(),"p"+ teaStuCoursetime.getUid(), "type", "callRoll_"+callRoll.getId() + "",
								this.mapService());
						t1.start();
			     }
		      }
		      
		      courseTeaTime.setCallroll(1);
		      courseTeaTimeService.update(courseTeaTime);
			  return ReturnJson.jsonString("OK", AppConstants.OK);
		} else{
			  return ReturnJson.jsonString("OK", AppConstants.inrollcalltime);
		}
	
    }
    /**
     * 发送机构意见箱
     */
    @ResponseBody
    @RequestMapping(value = "sendSuggestion", method = RequestMethod.POST)
    public String sendSuggestion(HttpServletRequest request) {
    	String orgId = request.getParameter("orgId");
		String sessionId = request.getParameter("sessionId");
		String content = request.getParameter("content");
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		OrgnizationSuggestion	orgnizationSuggestion=new OrgnizationSuggestion();
		orgnizationSuggestion.setContent(content);
		orgnizationSuggestion.setCreateDate(new Date());
		orgnizationSuggestion.setName(user.getUserName());
		orgnizationSuggestion.setPhone(user.getPhone());
		orgnizationSuggestion.setParentId(user.getId());
		orgnizationSuggestion.setOrgId(Long.parseLong(orgId));
		orgnizationSuggestionService.save(orgnizationSuggestion);

		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
    

	private Map mapService() {
		Map map = new HashMap();

		return map;

	}
}
