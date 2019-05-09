package com.framework.loippi.controller.app;



import com.framework.loippi.controller.admin.StudentController;
import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppActivity2;
import com.framework.loippi.controller.app.model.AppArea;
import com.framework.loippi.controller.app.model.AppCourse;
import com.framework.loippi.controller.app.model.AppCourseComment;
import com.framework.loippi.controller.app.model.AppCourseTime;
import com.framework.loippi.controller.app.model.AppCourseTimeComment;
import com.framework.loippi.controller.app.model.AppCourseTimeTch;
import com.framework.loippi.controller.app.model.AppMyCourse;
import com.framework.loippi.controller.app.model.AppMyCourseDetail;
import com.framework.loippi.controller.app.model.AppOffWork;
import com.framework.loippi.controller.app.model.AppStudent;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.Activity;
import com.framework.loippi.entity.AppOrgnization;
import com.framework.loippi.entity.Area;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseBook;
import com.framework.loippi.entity.CourseChange;
import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.CourseMakeup;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.entity.CourseTimeComment;
import com.framework.loippi.entity.CourseType;
import com.framework.loippi.entity.OffWork;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.OrgnizationScanlog;
import com.framework.loippi.entity.OrgnizationSuggestion;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.TTrip;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.service.CourseBookService;
import com.framework.loippi.service.CourseChangeService;
import com.framework.loippi.service.CourseCommentRemarkService;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseMakeupService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTimeCommentService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.CourseTypeService;
import com.framework.loippi.service.OffWorkService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgnizationScanlogService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.OrgnizationSuggestionService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.service.TeacherCommentRemarkService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.utils.HttpSend;
import com.framework.loippi.utils.MyMapUtil;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.SQLUtil;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.qiniu.FileServiceImpl4Qiniu;

import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LoggingEventPatternConverter;
import org.bouncycastle.i18n.filter.SQLFilter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
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
import javax.servlet.http.HttpServletResponse;

/**
 * 机构
 */

@Controller
@RequestMapping("api/app/org/*")
public class AppOrgController extends BaseController {

	private Logger log = Logger.getLogger(getClass());
    @Autowired
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
	private StudentService studentService;
	
	@Resource
	private CourseBookService courseBookService;

	@Resource
	private OrderService orderService;
	@Resource
	private CourseChangeService courseChangeService;
	
	@Resource
	private CourseCommentRemarkService courseCommentRemarkService;
	@Resource
	private CourseTimeCommentService courseTimeCommentService;
	
	@Resource
	private CourseMakeupService courseMakeupService;
	
	@Resource
	private CourseStuTimeService courseStuTimeService;
	
	@Resource
	private OffWorkService offWorkService;
	
	@Resource
	private OrgnizationScanlogService orgnizationScanlogService;
	@Resource
	private TeacherCommentRemarkService teacherCommentRemarkService;
	
	@Resource
	private ParentService parentService;
	
    /**
     * 获取城市-字母列表
     */
    @ResponseBody
    @RequestMapping(value = "getCitys", method = RequestMethod.POST)
    public String getCitys(HttpServletRequest request) {
    	
    	List<Area> areas=areaService.findList("type", "2");
    	
    	List list=orgnizationService.getGroup(areas);

        return ReturnJson.jsonString("OK", list, AppConstants.OK);
    }
    
    /**
     * 获取城市的区县
     */
    @ResponseBody
    @RequestMapping(value = "getAreas", method = RequestMethod.POST)
    public String getAreas(HttpServletRequest request,String name) {
    	
    	List<Area> areas=areaService.findList("name",name);
		 List<AppArea> appAreas=new ArrayList();
		 if(areas!=null&&areas.size()>0){
			 Area area2= areas.get(0);
			 areas=areaService.findList("parentId",area2.getId());
			 if(areas!=null){
			 for (Area area : areas) {
				AppArea dto=new AppArea();
				BeanUtils.copyProperties(area, dto);
				appAreas.add(dto);
		     }
			 }
		 }
        return ReturnJson.jsonString("OK", appAreas, AppConstants.OK);
    }
    
    /**
     * 获取课程类型
     */
    @ResponseBody
    @RequestMapping(value = "getTypes", method = RequestMethod.POST)
    public String getTypes(HttpServletRequest request,String name) {
    	
    	List<CourseType> courseTypes=courseTypeService.findAll();
		
        return ReturnJson.jsonString("OK", courseTypes, AppConstants.OK);
    }
    
    /**
     * 扫码机构
     */
    @ResponseBody
    @RequestMapping(value = "scanOrgnization", method = RequestMethod.POST)
    public String scanOrgnization(HttpServletRequest request,String name) {
    	
    	String uid	=request.getParameter("uid");
    	Orgnization orgnization=orgnizationService.find("uuid", uid);
        AppOrgnization dto=new AppOrgnization();
		 if(orgnization!=null){
				BeanUtils.copyProperties(orgnization, dto);
				
				String sessionId=request.getParameter("sessionId");
				LoginUser loginUser = validateRedisLogiUser(sessionId);
				if(loginUser != null) {
					
					Map map=new HashMap();
					map.put("userId", loginUser.getId());
					map.put("orgId", dto.getId());
					List<OrgnizationScanlog> orgnizationScanlogs=	orgnizationScanlogService.findList(map);
					if(orgnizationScanlogs!=null&&orgnizationScanlogs.size()>0){
						OrgnizationScanlog orgnizationScanlog=	orgnizationScanlogs.get(0);
						orgnizationScanlog.setUpdateDate(new Date());
						orgnizationScanlogService.update(orgnizationScanlog);
					}else{
						OrgnizationScanlog orgnizationScanlog=	new OrgnizationScanlog();
						orgnizationScanlog.setOrgId(dto.getId());
						orgnizationScanlog.setUserId(loginUser.getId());
						orgnizationScanlog.setCreateDate(new Date());
						orgnizationScanlog.setUpdateDate(new Date());
						orgnizationScanlogService.save(orgnizationScanlog);
					}
				} 
				
				
				return ReturnJson.jsonString("OK", dto, AppConstants.OK);
		 }else{
			 return ReturnJson.jsonString("没有找到机构信息", dto, AppConstants.NOT_FOUND);
		 }
		  
    }
    

    /**
     * 搜索机构
     */
    @ResponseBody
    @RequestMapping(value = "searchOrg", method = RequestMethod.POST)
    public String searchOrg(HttpServletRequest request) {
    	String sessionId = request.getParameter("sessionId");

		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
    	
    	String city	=request.getParameter("city");
    	String area	=request.getParameter("area");
    	String lng	=request.getParameter("lng");
    	String lat	=request.getParameter("lat");
    //	String keyword	=request.getParameter("keyword");
    	String type	=request.getParameter("type");
    	// 综合排序 1  离我最近 2  人气最高 3 评价最高4
    	String sort	=request.getParameter("sort");
    	
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
    	
    	
    	//根据坐标获取城市名称
    	String location=lng+","+lat;
		String urlcode = "http://restapi.amap.com/v3/geocode/regeo?output=xml&location=" + location
				+ "&key=1e2d63b21185e95b4335024f9c7c2fc5&radius=100&extensions=all";
		System.err.println("urlcode=" + urlcode);
		String retgeocode = HttpSend.getSend(urlcode);
		System.err.println("retgeocode=" + retgeocode);
		String cityName="";
		Document dom2;
		try {
			dom2 = DocumentHelper.parseText(retgeocode);
		
		Element root2 = dom2.getRootElement();
		String citycode = root2.element("regeocode").element("addressComponent").elementText("city");
	
		String provincecode = root2.element("regeocode").element("addressComponent").elementText("province");
		
		//处理直辖市的问题

		if(StringUtil.isEmpty(citycode)){
			cityName=provincecode;
		}else{
			cityName=citycode;
		}
		//去掉市字
		cityName=cityName.replace("市","");
		
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<AppOrgnization> appOrgnizations=new ArrayList();
    	//app只传坐标过来，返回定位城市，机构数据
    	if(StringUtil.isEmpty(city)&&StringUtil.isEmpty(city)){
    		
    		
    		Map map=new HashMap();
    		map.put("lat", lat);
    		map.put("lng", lng);
    		map.put("city", cityName);
    		map.put("sort", 1);
    		map.put("userId", user.getId());
    		map.put("pageNumber", (Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize));
    		map.put("pageSize", Integer.parseInt(pageSize));
    		List<Orgnization> orgnizations=orgnizationService.findListByPage(map);
    	
    		 if(orgnizations!=null&&orgnizations.size()>0){
    			 for (Orgnization orgnization : orgnizations) {
    				 AppOrgnization dto=new AppOrgnization();
    				BeanUtils.copyProperties(orgnization, dto);
    				appOrgnizations.add(dto);
    				System.err.println("经度"+lng);
    				System.err.println("纬度"+lat);
    				System.err.println("距离0"+dto.getDistance());
        	    	 float distance = MyMapUtil.calculateLineDistance(Double.parseDouble(lng),Double.parseDouble(lat),orgnization.getLocLng().doubleValue(),orgnization.getLocLat().doubleValue());
        	    	 System.err.println("距离1"+distance);
    			 }
    			
    		 }
    	
    		   	
    	    	
        return ReturnJson.jsonString("OK", appOrgnizations, AppConstants.OK);
    	}else{
    		
    	
    		
    		Map map=new HashMap();
    		map.put("lat", lat);
    		map.put("lng", lng);
    		
    		if(!StringUtil.isEmpty(type)){
    			map.put("type", type);
    		}
    		
    		if(!StringUtil.isEmpty(sort)){
    			map.put("sort", sort);
    		}else{
    			map.put("sort", 1);
    		}
    		
    		if(StringUtil.isEmpty(city)){
    			map.put("city", cityName);
    		}else{
    			map.put("city", city);
    		}
    		
    	
    		
    		if(!StringUtil.isEmpty(area)){
    			map.put("area", area);
    		}
    		map.put("userId", user.getId());
    		map.put("pageNumber", (Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize));
    		map.put("pageSize", Integer.parseInt(pageSize));
    		
    		List<Orgnization> orgnizations=orgnizationService.findListByPage(map);
    	
    		 if(orgnizations!=null&&orgnizations.size()>0){
    			 for (Orgnization orgnization : orgnizations) {
    				 AppOrgnization dto=new AppOrgnization();
    				BeanUtils.copyProperties(orgnization, dto);
    				appOrgnizations.add(dto);
    				System.err.println("经度:"+lng);
    				System.err.println("纬度:"+lat);
    				System.err.println("距离0:"+dto.getDistance());
        	    	 float distance = MyMapUtil.calculateLineDistance(Double.parseDouble(lng),Double.parseDouble(lat),orgnization.getLocLng().doubleValue(),orgnization.getLocLat().doubleValue());
        	    	 System.err.println("距离1:"+distance);
    		     }
    			
    		 }
    	    return ReturnJson.jsonString(cityName, appOrgnizations, AppConstants.OK);
    	}
    }
    
    
    
    /**
     * 搜索课程
     */
    @ResponseBody
    @RequestMapping(value = "searchCourse", method = RequestMethod.POST)
    public String searchCourse(HttpServletRequest request) {

    	String keyword	=request.getParameter("keyword");
    	String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");

		Map map=new HashMap();
		map.put("status", 1);
		map.put("namelike", StringUtil.filterSQL(keyword));
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

 				appCourses.add(dto);
			}
			
		}
		  return ReturnJson.jsonString("OK",appCourses, AppConstants.OK);
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
    

    /**
     * 机构课程列表
     */
    @ResponseBody
    @RequestMapping(value = "getCourses", method = RequestMethod.POST)
    public String getCourses(HttpServletRequest request) {
    	String orgId=request.getParameter("orgId");
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");

		Map map=new HashMap();
		map.put("status", 1);
		map.put("orgId", orgId);
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
 				
 				Map parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("order", " course_time3");
 				parameter.put("notstudent", "1");
 				parameter.put("pageSize3",Long.parseLong(StringUtil.date2String3(new Date())) );
 				parameter.put("pageNumber",0);
 				parameter.put("pageSize",3);
 				List<AppCourseTime> appCourseTimes=new ArrayList();		
 				List<CourseTime> courseTimes=courseTimeService.findListByPage(parameter);
 				if(courseTimes!=null){
 					for (CourseTime courseTime : courseTimes) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseTime.getCourseTime()));
 		 				dto2.setTime(courseTime.getCourseTime1()+"~"+courseTime.getCourseTime2());
 		 				appCourseTimes.add(dto2);
					}
 				}
 				dto.setAppCourseTimes(appCourseTimes);	
		}
		  return ReturnJson.jsonString("OK",dto, AppConstants.OK);
    }
    
    
    /**
     * 课程上课时间
     */
    @ResponseBody
    @RequestMapping(value = "getCourseTime", method = RequestMethod.POST)
    public String getCourseTime(HttpServletRequest request) {
    	String id=request.getParameter("id");
    
    	
		AppCourse dto=new AppCourse();
		Course course=courseService.find(Long.parseLong(id));
		
		Map map=new HashMap();
		
			List<AppCourseTime> appCourseTimes=new ArrayList();	
			List<AppCourseTime> appCourseTimes2=new ArrayList();		
		map.put("courseTime", appCourseTimes);
		map.put("courseTime2", appCourseTimes2);
		if(course!=null){
			
Map parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("order", " course_time3");
 				parameter.put("notstudent", "1");
 				parameter.put("pageSize3",Long.parseLong(StringUtil.date2String3(new Date())));
 		
 				List<CourseTime> courseTimes=courseTimeService.findListByPage(parameter);
 				if(courseTimes!=null){
 					for (CourseTime courseTime : courseTimes) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseTime.getCourseTime()));
 		 				dto2.setTime(courseTime.getCourseTime1()+"~"+courseTime.getCourseTime2());
 		 				appCourseTimes.add(dto2);
					}
 				}
 				map.put("courseTime", appCourseTimes);
 				
 				 parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("order", " course_time3 desc");
 				parameter.put("notstudent", "1");
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(new Date())) );
 			
 				List<CourseTime> courseTimes2=courseTimeService.findListByPage(parameter);
 				if(courseTimes2!=null){
 					for (CourseTime courseTime : courseTimes2) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseTime.getCourseTime()));
 		 				dto2.setTime(courseTime.getCourseTime1()+"~"+courseTime.getCourseTime2());
 		 				appCourseTimes2.add(dto2);
					}
 				}
 				map.put("courseTime2", appCourseTimes2);



		}
		
		
		
		
		
		  return ReturnJson.jsonString("OK",map, AppConstants.OK);
    }
    
    /**
     * 机构课程评论 courseCommentService
     */
    @ResponseBody
    @RequestMapping(value = "getCourseComments", method = RequestMethod.POST)
    public String getCourseComments(HttpServletRequest request) {
    	String courseId=request.getParameter("courseId");
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");

		Map map=new HashMap();
		
		map.put("courseId", courseId);
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
     * 新增学生
     */
    @ResponseBody
    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    public String addStudent(HttpServletRequest request) {
    	String sessionId = request.getParameter("sessionId");
		String avatar = request.getParameter("avatar");
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");
		String birtchday = request.getParameter("birtchday");
		String hobby = request.getParameter("hobby");
		
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		Student	student=new Student();
		student.setAvatar(avatar);
		student.setBirtchday(StringUtil.StringDateToDate(birtchday));
		student.setCreateTime(new Date());
		student.setHobby(hobby);
		student.setNickname(nickname);
		
		if(sex.equals("男孩") || sex.equals("男")) {
			student.setSex("男");
		}
		
		if(sex.equals("女孩") || sex.equals("女")) {
			student.setSex("女");
		}
		
		student.setUid(user.getId());
		studentService.save(student);
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
    
    /**
     * 修改学生
     */
    @ResponseBody
    @RequestMapping(value = "updateStudent", method = RequestMethod.POST)
    public String updateStudent(HttpServletRequest request) {
    	String sessionId = request.getParameter("sessionId");
    	String id = request.getParameter("id");
		String avatar = request.getParameter("avatar");
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");
		String birtchday = request.getParameter("birtchday");
		String hobby = request.getParameter("hobby");
		
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		Student	student=studentService.find(Long.parseLong(id));
        
		if(student!=null){
		student.setAvatar(avatar);
		student.setBirtchday(StringUtil.StringDateToDate(birtchday));
		student.setCreateTime(new Date());
		student.setHobby(hobby);
		student.setNickname(nickname);
		
		if(sex.equals("男孩") || sex.equals("男")) {
			student.setSex("男");
		}
		
		if(sex.equals("女孩") || sex.equals("女")) {
			student.setSex("女");
		}
		
		student.setUid(user.getId());
		studentService.update(student);
		}
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
    
    /**
     * 删除学生
     */
    @ResponseBody
    @RequestMapping(value = "delStudent", method = RequestMethod.POST)
    public String delStudent(HttpServletRequest request) {
    	String sessionId = request.getParameter("sessionId");
    	String id = request.getParameter("id");
		
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
	
		studentService.delete(Long.parseLong(id));

		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
 
    /**
     * 学生列表
     */
    @ResponseBody
    @RequestMapping(value = "getStudents", method = RequestMethod.POST)
    public String getStudents(HttpServletRequest request) {
    	String sessionId = request.getParameter("sessionId");
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
          Map map=new HashMap();
		
		map.put("uid", user.getId());
		map.put("pageNumber", 0);
		map.put("pageSize", 50);
		 List<AppStudent> appStudents=new ArrayList();
		List<Student> students=studentService.findListByPage(map);
		if(appStudents!=null){
			
			for (Student student : students) {
				AppStudent dto=new AppStudent();
 				BeanUtils.copyProperties(student, dto);
 				dto.setBirtchdayString(StringUtil.date2String(student.getBirtchday()));
 				
 				if(student.getSex().equals("男孩") || student.getSex().equals("男") ) {
 					dto.setSex("男孩");
 				}
 				
 				if(student.getSex().equals("女孩") || student.getSex().equals("女") ) {
 					dto.setSex("女孩");
 				}
 				
 				appStudents.add(dto);
			}
			
		}
		  return ReturnJson.jsonString("OK",appStudents, AppConstants.OK);
    }
    
    
    
    
    /**
     * 课程预约试听
     */
    @ResponseBody
    @RequestMapping(value = "bookCourse", method = RequestMethod.POST)
    public String bookCourse(HttpServletRequest request) {
    	String sessionId = request.getParameter("sessionId");
    	
    	String courseId = request.getParameter("courseId");
    	String childId = request.getParameter("childId");
    	String childName = request.getParameter("childName");
    	String name = request.getParameter("name");
    	String phone = request.getParameter("phone");
    	String remark = request.getParameter("remark");
    	String courseName = request.getParameter("courseName");
//    	String sessionId = request.getParameter("sessionId");
//    	String sessionId = request.getParameter("sessionId");
    	
    	CourseBook courseBook=new CourseBook();
    	
    	Course	course=courseService.find(Long.parseLong(courseId));
    	Student student = studentService.find(Long.parseLong(childId));
    	
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		courseBook.setChildId(Long.parseLong(childId));
		courseBook.setChildName(childName);
		courseBook.setCourseId(Long.parseLong(courseId));
		courseBook.setPhone(phone);
		courseBook.setRemark(remark);
		courseBook.setParentId(user.getId());
		courseBook.setName(name);
		courseBook.setCourseName(courseName);
		courseBook.setParentAccount(user.getUserName());
		courseBook.setCreateDate(new Date());
		courseBook.setOrgId(course.getOrgId());
		courseBook.setBirthday(student.getBirtchday());
		if(student.getSex().equals("男") || student.getSex().equals("男孩")) {
			courseBook.setSex("男");
		}
		if(student.getSex().equals("女") || student.getSex().equals("女孩")) {
			courseBook.setSex("女");
		}
		courseBookService.save(courseBook);
		
		
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
    
    
    
    /**
	 * 显示我的课程列表
	 */
	@ResponseBody
	@RequestMapping(value="myCourseList",method=RequestMethod.POST)
	public String myActivityList(HttpServletRequest request,HttpServletResponse response) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		
		
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		
		Map map=new HashMap();
		map.put("pageNumber", (Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize));
		map.put("pageSize", Integer.parseInt(pageSize));
		map.put("orderuid", loginuser.getId());
		map.put("order", " T_ORDER.created_time desc");
		 List<AppMyCourse> appCourses=new ArrayList();
		List<Course> courses = courseService.findMyListByPage(map);
		if(courses!=null) {

			for (Course course : courses) {
				AppMyCourse dto=new AppMyCourse();
				dto.setClasstimes(course.getTotalSum());
				dto.setId(course.getId());
				dto.setImage(course.getImage());
				dto.setName(course.getName());
				dto.setOrgId(course.getOrgId());
				dto.setOrgname(course.getOrgname());
				dto.setStudentId(course.getUid());
				dto.setType(course.getType());
				//教师
 				Teacher teacher=teacherService.find(course.getTeacherId());
 				if(teacher!=null){
 					dto.setTeacherId(teacher.getId());
 				dto.setTeacherAvatar(teacher.getAvatar());
 				dto.setTeacherName(teacher.getNickname());
 			
 				}
 				dto.setOrderId(course.getOrderId());
 				appCourses.add(dto);
			}

			
		} 
		return ReturnJson.jsonString("OK",appCourses, AppConstants.OK);
	}
	
	
	
	 /**
     * 我的课程详情
     */
    @ResponseBody
    @RequestMapping(value = "getMyCourseById", method = RequestMethod.POST)
    public String getMyCourseById(HttpServletRequest request) {
    	String id=request.getParameter("id");
    	String orderId=request.getParameter("orderId");
    	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");
    	
    	LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
    	
		Order	order=orderService.find(Long.parseLong(orderId));
    	
		AppMyCourseDetail dto=new AppMyCourseDetail();
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
 				
 				parameter.put("order", " course_time3");
 				parameter.put("pageSize3",Long.parseLong(StringUtil.date2String3(new Date())) );
 				
 				parameter.put("orderId", orderId);
 				parameter.put("pageNumber",0);
 				parameter.put("pageSize",3);
 				List<AppCourseTime> appCourseTimes=new ArrayList();		
 				List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes.add(dto2);
					}
 				}
 				dto.setStudentId(Long.parseLong(studentId));
 				dto.setAppCourseTimes(appCourseTimes);	
 	            if(order!=null){
 	            	dto.setClasstimes(order.getClasstimes());
 	            }
			
		}
		  return ReturnJson.jsonString("OK",dto, AppConstants.OK);
    }
 
    
    /**
     * 课程上课时间
     */
    @ResponseBody
    @RequestMapping(value = "getMyCourseTime", method = RequestMethod.POST)
    public String getMyCourseTime(HttpServletRequest request) {
    	String id=request.getParameter("id");
       	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");
    	String orderId=request.getParameter("orderId");
    	LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
    	
    	
    	
		AppCourse dto=new AppCourse();
		Course course=courseService.find(Long.parseLong(id));
		
		Map map=new HashMap();
		
			List<AppCourseTime> appCourseTimes=new ArrayList();	
			List<AppCourseTime> appCourseTimes2=new ArrayList();		
		map.put("courseTime", appCourseTimes);
		map.put("courseTime2", appCourseTimes2);
		if(course!=null){
			
 				Map parameter=new HashMap();
 				parameter.put("orderId", orderId);
 				parameter.put("order", " course_time3");
 				parameter.put("pageSize3",Long.parseLong(StringUtil.date2String3(new Date())) );

 				List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes.add(dto2);
					}
 				}
 				map.put("courseTime", appCourseTimes);
 				
 				parameter=new HashMap();
 				parameter.put("orderId", orderId);
 				parameter.put("order", " course_time3 desc");
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(new Date())) );
 			
 				List<CourseStuTime> courseStuTimes2=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes2!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes2) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes2.add(dto2);
					}
 				}
 				map.put("courseTime2", appCourseTimes2);


		}
		
		
		
		
		
		  return ReturnJson.jsonString("OK",map, AppConstants.OK);
    }
    
    /**
     * 调班申请
     */
    @ResponseBody
    @RequestMapping(value = "changeClassroom", method = RequestMethod.POST)
    public String changeClassroom(HttpServletRequest request) {
    	String id=request.getParameter("id");
       	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");
      	String name=request.getParameter("name");
      	String phone=request.getParameter("phone");
      	String content=request.getParameter("content");
    	LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		Student student=	studentService.find(Long.parseLong(studentId));
		Course	course=courseService.find(Long.parseLong(id));
		CourseChange courseChange=new CourseChange();
		courseChange.setAccount(user.getUserName());
		courseChange.setName(name);
		courseChange.setBirtchday(student.getBirtchday());
		courseChange.setContent(content);
		courseChange.setCourseId(Long.parseLong(id));
		courseChange.setCourseName(course.getName());
		courseChange.setCreateTime(new Date());
		courseChange.setOrgId(course.getOrgId());
		courseChange.setParentId(user.getId());
		courseChange.setPhone(phone);
		if(student.getSex().equals("男") || student.getSex().equals("男孩")) {
			courseChange.setSex("男");
		}
		if(student.getSex().equals("女") || student.getSex().equals("女孩")) {
			courseChange.setSex("女");
		}
		
		courseChange.setStudentId(Long.parseLong(studentId));
		courseChange.setStudentName(student.getNickname());
		courseChangeService.save(courseChange);
		
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
    
    
    /**
     * 课时管理
     */
    @ResponseBody
    @RequestMapping(value = "getMyManageCourseTime", method = RequestMethod.POST)
    public String getMyManageCourseTime(HttpServletRequest request) {
    	String id=request.getParameter("id");
       //	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");
    	String orderId=request.getParameter("orderId");
    	
    	System.err.println("==============================");
			System.err.println("课程id:"+id);
			System.err.println("订单id:"+orderId);
			//System.err.println("学生id:"+studentId);
    	
    	LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
    	
    	
    	
		AppCourse dto=new AppCourse();
		Course course=courseService.find(Long.parseLong(id));
		
		Map map=new HashMap();
		
			List<AppCourseTime> appCourseTimes1=new ArrayList();	
			List<AppCourseTime> appCourseTimes2=new ArrayList();
			List<AppCourseTime> appCourseTimes3=new ArrayList();	
			List<AppCourseTime> appCourseTimes4=new ArrayList();	
			List<AppCourseTime> appCourseTimes5=new ArrayList();	

		map.put("courseTime1", appCourseTimes1);
		map.put("courseTime2", appCourseTimes2);
		map.put("courseTime3", appCourseTimes3);
		map.put("courseTime4", appCourseTimes4);
		map.put("courseTime5", appCourseTimes5);

		if(course!=null){
			//未开始
 				Map parameter=new HashMap();
 				parameter.put("courseId",course.getId());
 				parameter.put("orderId", orderId);
 				//parameter.put("studentId",Long.parseLong(studentId));
 				parameter.put("order", "course_time3");
 				
 				parameter.put("pageSize3",Long.parseLong(StringUtil.date2String3(new Date())));
 		
 				List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes1.add(dto2);
					}
 				}
 				map.put("courseTime1", appCourseTimes1);
 				
 				//未评价
 				parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("orderId", orderId);
 				//parameter.put("studentId",Long.parseLong(studentId));
 				parameter.put("nostucomment", "1");
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(new Date())) );
 				parameter.put("order", " course_time3 desc");
 				List<CourseStuTime> courseStuTimes2=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes2!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes2) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes2.add(dto2);
					}
 				}
 				map.put("courseTime2", appCourseTimes2);
 				
 				
 				
 				//已结束,已评价
 				parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("order", " course_time3 desc");
 				parameter.put("stucomment", "1");
 				parameter.put("orderId", orderId);
 				//parameter.put("studentId",Long.parseLong(studentId));
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(new Date())) );
 			
 				List<CourseStuTime> courseStuTimes3=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes3!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes3) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes3.add(dto2);
					}
 				}
 				map.put("courseTime3", appCourseTimes3);
 				
 				//请假
 				parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("order", " course_time3 desc");
 				parameter.put("status", 3);
 				parameter.put("orderId", orderId);
 				//parameter.put("studentId",Long.parseLong(studentId));
 			
 				List<CourseStuTime> courseStuTimes4=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes4!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes4) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes4.add(dto2);
					}
 				}
 				map.put("courseTime4", appCourseTimes4);

 				//旷课
 				  long time = 30*60*1000;//30分钟
 				  Date now = new Date();
 				  Date beforeDate = new Date(now .getTime() - time);//30分钟前的时间
 				
 				
 				parameter=new HashMap();
 				parameter.put("courseId", course.getId());
 				parameter.put("order", " course_time3 desc");
 				parameter.put("status", 5);
 				
 				parameter.put("orderId", orderId);
 				//parameter.put("studentId",Long.parseLong(studentId));
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(beforeDate)) );
 			
 				List<CourseStuTime> courseStuTimes5=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes5!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes5) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String111(courseStuTime.getCourseTime()));
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes5.add(dto2);
					}
 				}
 

 				map.put("courseTime5", appCourseTimes5);

		}
		
		  return ReturnJson.jsonString("OK",map, AppConstants.OK);
    }
    
    

    /**
     * 评论课程时间标签
     */
    @ResponseBody
    @RequestMapping(value = "getTimeCommentRemark", method = RequestMethod.POST)
    public String getTimeCommentRemark(HttpServletRequest request) {
    
		
		  return ReturnJson.jsonString("OK",teacherCommentRemarkService.findAll(), AppConstants.OK);
    }
    

    /**
     * 评论课程时间
     */
    @ResponseBody
    @RequestMapping(value = "setTimeComment", method = RequestMethod.POST)
    public String setTimeComment(HttpServletRequest request) {
    	String id=request.getParameter("id");
       	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");
    	String content = request.getParameter("content");
    	String favStar = request.getParameter("favStar");
    	String mark = request.getParameter("mark");
    	
    	LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		CourseStuTime courseTime=courseStuTimeService.find(Long.parseLong(id));
		courseTime.setStucomment(1);
		courseStuTimeService.update(courseTime);
		
		Course course=courseService.find(courseTime.getCourseId());
    	CourseTimeComment courseTimeComment=new CourseTimeComment();
    	courseTimeComment.setAvatar(user.getAvatar());
    	courseTimeComment.setContent(content);
    	courseTimeComment.setCourseId(courseTime.getCourseId());
    	courseTimeComment.setCourseTime(courseTime.getCourseTime());
    	courseTimeComment.setCourseTime1(courseTime.getCourseTime1());
    	courseTimeComment.setCourseTime2(courseTime.getCourseTime2());
    	courseTimeComment.setCourseTime3(courseTime.getCourseTime3());
    	courseTimeComment.setCreateDate(new Date());
    	courseTimeComment.setFavStar(Integer.parseInt(favStar));
    	courseTimeComment.setMark(mark);
    	courseTimeComment.setName(user.getUserName());
    	courseTimeComment.setOrgId(course.getOrgId());
    	courseTimeComment.setStudentId(Long.parseLong(studentId));
    	courseTimeComment.setTeacherId(course.getTeacherId());
    	courseTimeComment.setTimeId(Long.parseLong(id));
    	courseTimeComment.setUid(user.getId());
    	courseTimeComment.setType(1);
    	courseTimeComment.setStatus(1);
    	Parent parent = parentService.find(user.getId());
    	if(parent != null) {
    		courseTimeComment.setAccount(parent.getAccount());
    	}
    	//教师
			Teacher teacher=teacherService.find(course.getTeacherId());
			if(teacher!=null){
			courseTimeComment.setTeacherName(teacher.getNickname());
	    	courseTimeComment.setTeacherAvatar(teacher.getAvatar());
			}
    
    	courseTimeCommentService.save(courseTimeComment);
    	
    
		
		  return ReturnJson.jsonString("OK",AppConstants.OK);
    }
    

    /**
     * 查看课程时间的评论
     */
    @ResponseBody
    @RequestMapping(value = "getTimeComment", method = RequestMethod.POST)
    public String getTimeComment(HttpServletRequest request) {
    	String id=request.getParameter("id");
       	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");

    	
    	LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		CourseStuTime courseTime=courseStuTimeService.find(Long.parseLong(id));
		Course course=courseService.find(courseTime.getCourseId());
		
		Map map=new HashMap();
		map.put("timeId", id);
		map.put("studentId", Long.parseLong(studentId));
    	List<CourseTimeComment> courseTimeComments=courseTimeCommentService.findList(map);
    	
    	AppCourseTimeComment appCourseTimeComment=new AppCourseTimeComment();
       if(courseTimeComments!=null&&courseTimeComments.size()>0){
    	   CourseTimeComment courseTimeComment= courseTimeComments.get(0);
			BeanUtils.copyProperties(courseTimeComment, appCourseTimeComment);
       }
		  return ReturnJson.jsonString("OK",appCourseTimeComment,AppConstants.OK);
    }
    

    /**
     * 补课申请
     */
    @ResponseBody
    @RequestMapping(value = "makeupClass", method = RequestMethod.POST)
    public String makeupClass(HttpServletRequest request) {
    	String id=request.getParameter("id");
       	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");
      	String name=request.getParameter("name");
      	String phone=request.getParameter("phone");
      	String content=request.getParameter("content");
    	LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		Student student=	studentService.find(Long.parseLong(studentId));
		Course	course=courseService.find(Long.parseLong(id));
		CourseMakeup courseMakeup=new CourseMakeup();
		courseMakeup.setAccount(user.getUserName());
		courseMakeup.setName(name);
		courseMakeup.setBirtchday(student.getBirtchday());
		courseMakeup.setContent(content);
		courseMakeup.setCourseId(Long.parseLong(id));
		courseMakeup.setCourseName(course.getName());
		courseMakeup.setCreateTime(new Date());
		courseMakeup.setOrgId(course.getOrgId());
		courseMakeup.setParentId(user.getId());
		courseMakeup.setPhone(phone);
		if(student.getSex().equals("男") || student.getSex().equals("男孩")) {
			courseMakeup.setSex("男");
		}
		if(student.getSex().equals("女") || student.getSex().equals("女孩")) {
			courseMakeup.setSex("女");
		}
		courseMakeup.setStudentId(Long.parseLong(studentId));
		courseMakeup.setStudentName(student.getNickname());
		courseMakeupService.save(courseMakeup);
		
		
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }
    

    
    
    
    public static void main(String[] args) throws DocumentException {
		String urlcode = "http://restapi.amap.com/v3/geocode/regeo?output=xml&location=113.3897890000,23.1307990000&key=1e2d63b21185e95b4335024f9c7c2fc5&radius=100&extensions=all";
		
		String retgeocode = HttpSend.getSend(urlcode);
	System.err.println(retgeocode);
	Document dom2 = DocumentHelper.parseText(retgeocode);
	Element root2 = dom2.getRootElement();
	String city = root2.element("regeocode").element("addressComponent").elementText("city");
	String province = root2.element("regeocode").element("addressComponent").elementText("province");
	System.err.println(city);
	System.err.println(province);
	}

   
    
	/** 上传图片权限参数 */
	@ResponseBody
	@RequestMapping(value = "qiniuToken",method = RequestMethod.POST)
	public String qiniuToken(HttpServletRequest request, HttpServletResponse response) {
		
        return 		ReturnJson.jsonString(FileServiceImpl4Qiniu.QINIU_PREFIX,FileServiceImpl4Qiniu.getToken(), AppConstants.OK);
	}
	
    /**
     * 请假申请的时间
     */
    @ResponseBody
    @RequestMapping(value = "getLeaveTime", method = RequestMethod.POST)
    public String getLeaveTime(HttpServletRequest request,Integer pageNumber,Integer pageSize) {
    	String orderId=request.getParameter("orderId");
    	String studentId=request.getParameter("studentId");
    	String sessionId = request.getParameter("sessionId");
    	LoginUser user = validateRedisLogiUser(sessionId);
    	Integer pageMix = (pageNumber - 1) * pageSize;
		System.err.println("orderId===="+orderId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		List<AppCourseTime> appCourseTimes=new ArrayList();	
			Map parameter=new HashMap();
			parameter.put("orderId", orderId);
			parameter.put("pageNumber", pageMix);
			parameter.put("pageSize", pageSize);
			List<AppCourseTimeTch> coursetineTchs = courseTimeService.findCourseTimeById2(parameter);
			return ReturnJson.jsonString("OK", coursetineTchs, AppConstants.OK);
    }
    
    /**
     * 请假申请
     */
    @ResponseBody
    @RequestMapping(value = "sendLeave", method = RequestMethod.POST)
    public String sendLeave(HttpServletRequest request,Long orderId,String leavetime,String description,String timeIds ) {
    	String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		CourseStuTime courseStuTime=courseStuTimeService.find("orderId", orderId);
    	OffWork offWork=new OffWork();
       	String studentId=request.getParameter("studentId");
       	
        Student  student=  	studentService.find(Long.valueOf(studentId));
     Order order=   orderService.find(orderId);
        if(student==null||order==null){
  		  return ReturnJson.jsonString("参数不完整", AppConstants.PARAMETER__NOT_COMPLETE);
        	
        }
        offWork.setStuavatar(student.getAvatar());
        offWork.setOrgId(order.getOrgId());
        offWork.setStuid(student.getId());
        offWork.setStuname(student.getNickname());
		offWork.setCourseId(courseStuTime.getCourseId());
		offWork.setStart((StringUtil.StringDateTimeToDate4(leavetime)));
		offWork.setCreateTime(new Date());
		offWork.setStatus(2);
		offWork.setUserType(2);
		if(loginuser.getType()!=1){
			offWork.setAccount(loginuser.getUserName());
		}else{
			offWork.setAccount(loginuser.getPhone());	
		}
		offWork.setNickName(loginuser.getUserName());
		offWork.setUid(Long.valueOf(studentId));
		offWork.setDescription(description);
		offWork.setReason(description);
		timeIds=StringUtil.delChar(timeIds, ",");
		offWork.setHour(timeIds);
		String courseStuTimeString="";
		if(!StringUtil.isEmpty(timeIds)){
			String[] timeids=	timeIds.split(",");
			for (int i = 0; i < timeids.length; i++) {
				 CourseStuTime  courseStuTime1=	courseStuTimeService.find(Long.parseLong(timeids[i]));
				 courseStuTimeString=courseStuTimeString+","+StringUtil.date2String(courseStuTime1.getCourseTime())+" "+courseStuTime1.getCourseTime1();
			}
		}
		courseStuTimeString=StringUtil.delChar(courseStuTimeString, ",");
		offWork.setHourtime(courseStuTimeString);
		offWorkService.save(offWork);
		  return ReturnJson.jsonString("OK", AppConstants.OK);
    }

	/**
	 * 请假记录详情
	 */
	@ResponseBody
	@RequestMapping(value = "getOffworksUser", method = RequestMethod.POST)
	public String getOffworksUser(HttpServletRequest request, Integer pageNumber,Long studentId) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		Integer pageMix = (pageNumber - 1) * 10;
		Map map = new HashMap();
		map.put("uid",studentId );
		map.put("userType", 2);
		map.put("pageNumber", pageMix);
		map.put("pageSize", 10);
		List<OffWork> offWorks = offWorkService.findListByPage(map);
		List<AppOffWork> appOffWorks = new ArrayList<AppOffWork>();
		for (OffWork offWork : offWorks) {
			AppOffWork appOffWork = new AppOffWork();
			Student Student=studentService.find(studentId);
			appOffWork.setAvatar(Student.getAvatar());
			appOffWork.setUserName(Student.getNickname());
			appOffWork.setCourseId(offWork.getCourseId());
			appOffWork.setDayNum(offWork.getDayNum());
			appOffWork.setDescription(offWork.getDescription());
			appOffWork.setStart((StringUtil.date2String4(offWork.getStart())));
			appOffWork.setImage(offWork.getImage());
			appOffWork.setReason(offWork.getReason());
			appOffWork.setStatus(offWork.getStatus());
			appOffWork.setType(offWork.getType());
			appOffWork.setCreateDate(StringUtil.date2String(offWork.getCreateTime()));
			Course course = courseService.find(offWork.getCourseId());
			appOffWork.setCourseName(course.getName());
			String timeids = offWork.getHour();
			Map map1 = new HashMap();
			map1.put("timeIds", timeids);
			List<String> coursetineTchs = courseTimeService.findCourseTime3(map1);
			appOffWork.setCourseTime(coursetineTchs);
			appOffWorks.add(appOffWork);
		}

		return ReturnJson.jsonString("OK", appOffWorks, AppConstants.OK);
	}
	

}
