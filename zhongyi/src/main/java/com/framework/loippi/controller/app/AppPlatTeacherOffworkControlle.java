package com.framework.loippi.controller.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppCourseTch;
import com.framework.loippi.controller.app.model.AppCourseTime;
import com.framework.loippi.controller.app.model.AppCourseTimeTch;
import com.framework.loippi.controller.app.model.AppOffWork;
import com.framework.loippi.controller.app.model.AppSign;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.entity.OffWork;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.TSign;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.OffWorkService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TSignService;
import com.framework.loippi.utils.MyMapUtil;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;

@Controller
@RequestMapping("api/app/platteacher/offwork/*")
public class AppPlatTeacherOffworkControlle extends BaseController {
	@Resource
	private OrgnizationService orgnizationService;
	@Resource
	private OffWorkService offWorkService;
	@Resource
	private CourseService courseService;
	@Resource
	private CourseTimeService courseTimeService;
	@Resource
	private StudentService studentService;
	@Resource
	private CourseTeaTimeService courseTeaTimeService;
	
	/**
	 * 请假获取课程
	 */
	@ResponseBody
	@RequestMapping(value = "getCourseList", method = RequestMethod.POST)
	public String getCourseList(HttpServletRequest request, Integer pageNumber) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		Integer pageMix = (pageNumber - 1) * 10;
		Map map = new HashMap();
		map.put("teacherId", loginuser.getId());
		map.put("orgId", loginuser.getOrgId());
		map.put("pageNumber", pageMix);
		map.put("pageSize", 10);
		List<Course> courses = courseService.findList(map);
		List<AppCourseTch> appCourseTchs = new ArrayList<AppCourseTch>();
		for (Course course : courses) {
			AppCourseTch courseTch = new AppCourseTch();
			courseTch.setCourseid(course.getId());
			courseTch.setImage(course.getImage());
			courseTch.setName(course.getName());
			courseTch.setOrgId(course.getOrgId());
			Orgnization org = orgnizationService.find(course.getOrgId());
			courseTch.setOrgname(org.getName());
			courseTch.setPrice(course.getPrice());
			courseTch.setType(course.getType());
			appCourseTchs.add(courseTch);
		}
		return ReturnJson.jsonString("OK", appCourseTchs, AppConstants.OK);

	}

	/**
	 * 请假获取课时
	 */
	@ResponseBody
	@RequestMapping(value = "getCourseTime", method = RequestMethod.POST)
	public String getCourseTime(HttpServletRequest request, Integer pageNumber, Long courseId,Integer pageSize) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		if(pageSize==null){
			pageSize=20;
		}
		
		Integer pageMix = (pageNumber - 1) * pageSize;
		Map map = new HashMap();
		map.put("courseId", courseId);
		map.put("pageNumber", pageMix);
		map.put("pageSize", pageSize);
		List<AppCourseTimeTch> coursetineTchs = courseTimeService.findCourseTimeById(map);
		return ReturnJson.jsonString("OK", coursetineTchs, AppConstants.OK);

	}

	/**
	 * 请假
	 */
	@ResponseBody
	@RequestMapping(value = "addOffwork", method = RequestMethod.POST)
	public String addOffwork(HttpServletRequest request, OffWork offWork, String startTime, String endTime) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		offWork.setStart(StringUtil.StringDateTimeToDate4(startTime));
		offWork.setEnd(StringUtil.StringDateTimeToDate4(endTime));
		offWork.setCreateTime(new Date());
		offWork.setStatus(2);
		offWork.setUserType(1);
		offWork.setOrgId(loginuser.getOrgId());
		if(loginuser.getType()!=1){
			offWork.setAccount(loginuser.getUserName());
		}else{
			offWork.setAccount(loginuser.getPhone());	
		}
		offWork.setNickName(loginuser.getUserName());
		offWork.setUid(loginuser.getId());
		offWorkService.save(offWork);
		
		String hourss=offWork.getHour();
		String[] hours=hourss.split(",");
		for(String hour:hours){
			 CourseTeaTime courseTeaTime=courseTeaTimeService.find(Long.parseLong(hour));
			 courseTeaTime.setClassStatus(4);
			 courseTeaTimeService.update(courseTeaTime);
		}
		return ReturnJson.jsonString("OK", AppConstants.OK);
	}

	/**
	 * 请假记录详情
	 */
	@ResponseBody
	@RequestMapping(value = "getOffworks", method = RequestMethod.POST)
	public String getSignInfo(HttpServletRequest request, Integer pageNumber) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		Integer pageMix = (pageNumber - 1) * 10;
		Map map = new HashMap();
		map.put("orgId", loginuser.getOrgId());
		map.put("uid", loginuser.getId());
		map.put("userType", 1);
		map.put("pageNumber", pageMix);
		map.put("pageSize", 10);
		List<OffWork> offWorks = offWorkService.findListByPage(map);
		List<AppOffWork> appOffWorks = new ArrayList<AppOffWork>();

		for (OffWork offWork : offWorks) {
			AppOffWork appOffWork = new AppOffWork();
			appOffWork.setAvatar(loginuser.getAvatar());
			appOffWork.setUserName(loginuser.getUserName());
			appOffWork.setCourseId(offWork.getCourseId());
			appOffWork.setDayNum(offWork.getDayNum());
			appOffWork.setDescription(offWork.getDescription());
			appOffWork.setEnd(StringUtil.date2String4(offWork.getEnd()));
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
			List<String> coursetineTchs = courseTimeService.findCourseTime1(map1);
			appOffWork.setCourseTime(coursetineTchs);
			appOffWorks.add(appOffWork);
		}

		return ReturnJson.jsonString("OK", appOffWorks, AppConstants.OK);
	}
	
}
