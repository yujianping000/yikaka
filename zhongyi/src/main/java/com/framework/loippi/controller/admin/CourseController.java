package com.framework.loippi.controller.admin;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.controller.app.model.AppWebCourseTime;
import com.framework.loippi.entity.AppTeacherInfo;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseRoom;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.entity.CourseType;
import com.framework.loippi.entity.Holiday;
import com.framework.loippi.entity.Log;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.SystemMes;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.CourseRoomService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.CourseTypeService;
import com.framework.loippi.service.HolidayService;
import com.framework.loippi.service.LogService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.SystemMesService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.JpushRunnable2;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.qiniu.FileServiceImpl;

import net.sf.json.JSONObject;

/**
 * Controller - 课程表
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCourseController")
@RequestMapping({ "/admin/course" })
public class CourseController extends GenericController {
	@Resource
	private UserService userService;
	@Resource
	private CourseService courseService;

	@Resource
	private OrgnizationService orgnizationService;

	@Resource
	private CourseRoomService courseRoomService;

	@Resource
	private TeacherService teacherService;

	@Resource
	private HolidayService holidayService;

	@Resource
	private CourseTeaTimeService courseTeaTimeService;

	@Resource
	private CourseStuTimeService courseStuTimeService;
	@Resource
	private CourseTimeService courseTimeService;

	@Resource
	private OrderService orderService;

	@Resource
	private ParentService parentService;
	@Resource
	private SystemMesService systemMesService;

	@Resource
	private OrgDynamicService orgDynamicService;

	@Resource(name = "logServiceImpl")
	private LogService logService;

	@Resource
	private CourseTypeService courseTypeService;

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
		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		return "/admin/course/add";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/addone" }, method = { RequestMethod.GET ,RequestMethod.POST})
	public String addone(ModelMap model) {
		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		return "/admin/course/addone";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/addlist" }, method =  { RequestMethod.GET ,RequestMethod.POST})
	public String addlist(ModelMap model) {
		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		return "/admin/course/addlist";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/getRoomByOrgId" }, method = { RequestMethod.GET })
	public @ResponseBody List<CourseRoom> getRoomByOrgId(Long id) {
		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", id);
		return courseRooms;
	}

	@RequestMapping(value = { "/getTeachersByOrgId" }, method = { RequestMethod.GET })
	public @ResponseBody List<AppTeacherInfo> getTeachersByOrgId(Long id) {

		Map map = new HashMap();
		map.put("orgId", id);

		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher.getId());
				appTeacherInfo.setName(teacher.getNickname());
				appTeacherInfo.setPhone(teacher.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}

		return appTeacherInfos;
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
	public String save(Course course, RedirectAttributes redirectAttributes) {
		course.setFreez(course.getTotalSum());
		course.setFavCount(0);
		course.setBuySum(0);
		course.setCreateDate(new Date());
		course.setUid(Long.parseLong(StringUtil.orderSn()));
		course.setTerm(1);
		courseService.save(course);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveone", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveone(Course course, RedirectAttributes redirectAttributes) {
		course.setFreez(course.getTotalSum());
		course.setFavCount(5);
		course.setBuySum(0);
		course.setCreateDate(new Date());
		course.setUid(Long.parseLong(StringUtil.orderSn()));
		course.setTerm(1);

		if (course.getSubstitute() != null) {
			Teacher teacher = teacherService.find(course.getSubstitute());
			if (teacher != null) {
				course.setSubstituteName(teacher.getNickname());
				course.setSubstituteType(2);
			}
		}

		Orgnization orgnization = orgnizationService.find(course.getOrgId());

		course.setOrgname(orgnization.getName());
		courseService.save(course);

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map = new HashMap<>();
		map.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/savelist", method = { RequestMethod.POST, RequestMethod.GET })
	public String savelist(Course course, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String start = request.getParameter("start");
		String end = request.getParameter("end");

		String week1 = request.getParameter("week1");
		String week2 = request.getParameter("week2");
		String week3 = request.getParameter("week3");
		String week4 = request.getParameter("week4");
		String week5 = request.getParameter("week5");
		String week6 = request.getParameter("week6");
		String week7 = request.getParameter("week7");
		Map map = new HashMap();
		if (!StringUtil.isEmpty(week1) && "1".equals(week1)) {
			map.put(week1, week1);
		}
		if (!StringUtil.isEmpty(week2) && "2".equals(week2)) {
			map.put(week2, week2);
		}
		if (!StringUtil.isEmpty(week3) && "3".equals(week3)) {
			map.put(week3, week3);
		}
		if (!StringUtil.isEmpty(week4) && "4".equals(week4)) {
			map.put(week4, week4);
		}
		if (!StringUtil.isEmpty(week5) && "5".equals(week5)) {
			map.put(week5, week5);
		}
		if (!StringUtil.isEmpty(week6) && "6".equals(week6)) {
			map.put(week6, week6);
		}
		if (!StringUtil.isEmpty(week7) && "7".equals(week7)) {
			map.put(week7, week7);
		}

		String timeRedio1 = request.getParameter("timeRedio1");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");

		String timeRedio2 = request.getParameter("timeRedio2");
		String time3 = request.getParameter("time3");
		String time4 = request.getParameter("time4");

		String timeRedio3 = request.getParameter("timeRedio3");
		String time5 = request.getParameter("time5");
		String time6 = request.getParameter("time6");

		String holiday = request.getParameter("holiday");
		List<Holiday> holidays = holidayService.findAll();

		Orgnization orgnization = orgnizationService.find(course.getOrgId());
		course.setOrgname(orgnization.getName());
		course.setFreez(course.getTotalSum());
		course.setFavCount(5);
		course.setBuySum(0);
		course.setCreateDate(new Date());
		course.setUid(Long.parseLong(StringUtil.orderSn()));
		course.setTerm(1);

		if (course.getSubstitute() != null) {
			Teacher teacher = teacherService.find(course.getSubstitute());
			if (teacher != null) {
				course.setSubstituteName(teacher.getNickname());
				course.setSubstituteType(2);
			}
		}

		courseService.save(course);

		Map mapholidays = new HashMap();
		if (holidays != null) {
			for (Holiday holiday2 : holidays) {
				mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
			}
		}

		Date startDate = StringUtil.StringDateToDate(start);
		Date endDate = StringUtil.StringDateToDate(end);

		// 时间，周几，节假日判断
		List<Date> dates = StringUtil.getBetweenDates(startDate, endDate);
		// 时间段1
		if (!StringUtil.isEmpty(time1) && !StringUtil.isEmpty(time2) && !StringUtil.isEmpty(timeRedio1)
				&& "1".equals(timeRedio1)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段2
		if (!StringUtil.isEmpty(time3) && !StringUtil.isEmpty(time4) && !StringUtil.isEmpty(timeRedio2)
				&& "1".equals(timeRedio2)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段3
		if (!StringUtil.isEmpty(time5) && !StringUtil.isEmpty(time6) && !StringUtil.isEmpty(timeRedio3)
				&& "1".equals(timeRedio3)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map1 = new HashMap<>();
		map1.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map1);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	private void setCourseTime(Date date, String time1, String time2, Integer term, Long courseId) {
		CourseTime courseTime = new CourseTime();
		courseTime.setCourseId(courseId);
		courseTime.setCourseTime(date);
		courseTime.setCourseTime1(time1);
		courseTime.setCourseTime2(time2);
		courseTime.setCourseTime3(Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		courseTime.setCourseTime4(Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));
		courseTime.setTerm(term);
		courseTime.setCreateTime(new Date());
		courseTimeService.save(courseTime);
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);
		model.addAttribute("course", course);
		return "/admin/course/edit";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editone/{id}", method = RequestMethod.GET)
	public String editone(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);
		model.addAttribute("course", course);

		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
			model.addAttribute("classId", courseRoom.getId());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
			model.addAttribute("teacherId", teacher.getId());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
				model.addAttribute("assistantId", teacher2.getId());

			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
				model.addAttribute("substituteId", teacher2.getId());
			}

		}

		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", course.getOrgId());
		model.addAttribute("courseRooms", courseRooms);

		Map map = new HashMap();
		map.put("orgId", course.getOrgId());

		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher1 : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher1.getId());
				appTeacherInfo.setName(teacher1.getNickname());
				appTeacherInfo.setPhone(teacher1.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}
		model.addAttribute("appTeacherInfos", appTeacherInfos);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		return "/admin/course/editone";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editlist/{id}", method = RequestMethod.GET)
	public String editlist(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);
		model.addAttribute("course", course);

		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
			model.addAttribute("classId", courseRoom.getId());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
			model.addAttribute("teacherId", teacher.getId());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
				model.addAttribute("assistantId", teacher2.getId());

			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
				model.addAttribute("substituteId", teacher2.getId());
			}

		}

		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", course.getOrgId());
		model.addAttribute("courseRooms", courseRooms);

		Map map = new HashMap();
		map.put("orgId", course.getOrgId());

		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher1 : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher1.getId());
				appTeacherInfo.setName(teacher1.getNickname());
				appTeacherInfo.setPhone(teacher1.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}
		model.addAttribute("appTeacherInfos", appTeacherInfos);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		// 上课时间
		Map map2 = new HashMap();
		map2.put("courseId", course.getId());
		map2.put("order", " course_time3 desc");

		List<CourseTime> courseTimes = courseTimeService.findListByPage(map2);
		List<AppWebCourseTime> appWebCourseTimes = new ArrayList();
		if (courseTimes != null) {
			for (CourseTime courseTime : courseTimes) {
				AppWebCourseTime appWebCourseTime = new AppWebCourseTime();
				appWebCourseTime.setCoursedate(StringUtil.date2String(courseTime.getCourseTime()));
				appWebCourseTime.setCoursetime(courseTime.getCourseTime1() + "~" + courseTime.getCourseTime2());
				appWebCourseTime.setCourseweek("星期" + StringUtil.getWeekOfDate(courseTime.getCourseTime()));
				appWebCourseTime.setId(courseTime.getId());
				appWebCourseTimes.add(appWebCourseTime);
			}
		}
		model.addAttribute("appWebCourseTimes", appWebCourseTimes);

		return "/admin/course/editlist";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/viewone/{id}", method = RequestMethod.GET)
	public String viewone(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
			}

		}

		model.addAttribute("course", course);
		return "/admin/course/viewone";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/viewlist/{id}", method = RequestMethod.GET)
	public String viewlist(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
			}

		}

		model.addAttribute("course", course);

		// 上课时间
		Map map = new HashMap();
		map.put("courseId", course.getId());
		map.put("order", " course_time3 desc");

		List<CourseTime> courseTimes = courseTimeService.findListByPage(map);
		List<AppWebCourseTime> appWebCourseTimes = new ArrayList();
		if (courseTimes != null) {
			for (CourseTime courseTime : courseTimes) {
				AppWebCourseTime appWebCourseTime = new AppWebCourseTime();
				appWebCourseTime.setCoursedate(StringUtil.date2String(courseTime.getCourseTime()));
				appWebCourseTime.setCoursetime(courseTime.getCourseTime1() + "~" + courseTime.getCourseTime2());
				appWebCourseTime.setCourseweek("星期" + StringUtil.getWeekOfDate(courseTime.getCourseTime()));
				appWebCourseTimes.add(appWebCourseTime);
			}
		}
		model.addAttribute("appWebCourseTimes", appWebCourseTimes);

		return "/admin/course/viewlist";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public String update(Course course, RedirectAttributes redirectAttributes) {
		courseService.update(course);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updateone", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateone(Course course, RedirectAttributes redirectAttributes) {

		Orgnization orgnization = orgnizationService.find(course.getOrgId());

		course.setOrgname(orgnization.getName());

		if (course.getSubstitute() != null) {
			Teacher teacher = teacherService.find(course.getSubstitute());
			if (teacher != null) {
				course.setSubstituteName(teacher.getNickname());
				course.setSubstituteType(2);
			}
		}

		courseService.update(course);

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map = new HashMap<>();
		map.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updatelist", method = { RequestMethod.POST, RequestMethod.GET })
	public String updatelist(Course course, RedirectAttributes redirectAttributes) {

		Orgnization orgnization = orgnizationService.find(course.getOrgId());

		course.setOrgname(orgnization.getName());

		if (course.getSubstitute() != null) {
			Teacher teacher = teacherService.find(course.getSubstitute());
			if (teacher != null) {
				course.setSubstituteName(teacher.getNickname());
				course.setSubstituteType(2);
			}
		}

		courseService.update(course);

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map = new HashMap<>();
		map.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	@RequestMapping(value = "/addlisttime/{id}", method = RequestMethod.GET)
	public String addlisttime(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);

		model.addAttribute("course", course);

		return "/admin/course/addlisttime";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/savelisttime", method = { RequestMethod.POST, RequestMethod.GET })
	public String savelisttime(Course course, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String start = request.getParameter("start");
		String end = request.getParameter("end");

		String week1 = request.getParameter("week1");
		String week2 = request.getParameter("week2");
		String week3 = request.getParameter("week3");
		String week4 = request.getParameter("week4");
		String week5 = request.getParameter("week5");
		String week6 = request.getParameter("week6");
		String week7 = request.getParameter("week7");
		Map map = new HashMap();
		if (!StringUtil.isEmpty(week1) && "1".equals(week1)) {
			map.put(week1, week1);
		}
		if (!StringUtil.isEmpty(week2) && "2".equals(week2)) {
			map.put(week2, week2);
		}
		if (!StringUtil.isEmpty(week3) && "3".equals(week3)) {
			map.put(week3, week3);
		}
		if (!StringUtil.isEmpty(week4) && "4".equals(week4)) {
			map.put(week4, week4);
		}
		if (!StringUtil.isEmpty(week5) && "5".equals(week5)) {
			map.put(week5, week5);
		}
		if (!StringUtil.isEmpty(week6) && "6".equals(week6)) {
			map.put(week6, week6);
		}
		if (!StringUtil.isEmpty(week7) && "7".equals(week7)) {
			map.put(week7, week7);
		}

		String timeRedio1 = request.getParameter("timeRedio1");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");

		String timeRedio2 = request.getParameter("timeRedio2");
		String time3 = request.getParameter("time3");
		String time4 = request.getParameter("time4");

		String timeRedio3 = request.getParameter("timeRedio3");
		String time5 = request.getParameter("time5");
		String time6 = request.getParameter("time6");

		String holiday = request.getParameter("holiday");
		List<Holiday> holidays = holidayService.findAll();

		Map mapholidays = new HashMap();
		if (holidays != null) {
			for (Holiday holiday2 : holidays) {
				mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
			}
		}

		Date startDate = StringUtil.StringDateToDate(start);
		Date endDate = StringUtil.StringDateToDate(end);

		// 时间，周几，节假日判断
		List<Date> dates = StringUtil.getBetweenDates(startDate, endDate);
		// 时间段1
		if (!StringUtil.isEmpty(time1) && !StringUtil.isEmpty(time2) && !StringUtil.isEmpty(timeRedio1)
				&& "1".equals(timeRedio1)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段2
		if (!StringUtil.isEmpty(time3) && !StringUtil.isEmpty(time4) && !StringUtil.isEmpty(timeRedio2)
				&& "1".equals(timeRedio2)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段3
		if (!StringUtil.isEmpty(time5) && !StringUtil.isEmpty(time6) && !StringUtil.isEmpty(timeRedio3)
				&& "1".equals(timeRedio3)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/course/editlist/" + course.getId() + ".jhtml";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {

		Map<String, Object> paramter2 = ParameterUtils.getParametersMapStartingWith(request, "filter_");

		String filter_id = request.getParameter("filter_id");
		String filter_name = request.getParameter("filter_name");
		String filter_orgname = request.getParameter("filter_orgname");
		String filter_teachername = request.getParameter("filter_teachername");
		String filter_type = request.getParameter("filter_type");
		String filter_status = request.getParameter("filter_status");
		Map paramter = new HashMap();
		if (!StringUtil.isEmpty(filter_id)) {
			paramter.put("id", StringUtil.filterSQL(filter_id));
		}

		if (!StringUtil.isEmpty(filter_name)) {
			paramter.put("filter_name", StringUtil.filterSQL(filter_name));
		}

		if (!StringUtil.isEmpty(filter_orgname)) {
			paramter.put("filter_orgname", StringUtil.filterSQL(filter_orgname));
		}

		if (!StringUtil.isEmpty(filter_teachername)) {
			paramter.put("filter_teachername", StringUtil.filterSQL(filter_teachername));
		}

		if (!StringUtil.isEmpty(filter_type)) {
			paramter.put("type", StringUtil.filterSQL(filter_type));
		}

		if (!StringUtil.isEmpty(filter_status)) {
			paramter.put("status", StringUtil.filterSQL(filter_status));
		}
//		Page<Course> page =  courseService.findByPage(pageable);
//		List<Course> l =  page.getContent();
//		System
		pageable.setParameter(paramter);
		model.addAttribute("paramter", paramter2);
		model.addAttribute("page", this.courseService.findByPage(pageable));
		return "/admin/course/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(HttpServletRequest request, Long[] ids) {
		String username = userService.getCurrentUsername();

		List<Course> courseList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			Course course = courseService.find(ids[i]);

			String operator = username;
			String ip = request.getRemoteAddr();
			Log log = new Log();
			log.setOperation("删除课程");
			log.setOperator(operator);
			log.setContent(JSONObject.fromObject(course).toString());
			log.setParameter(JSONObject.fromObject(course).toString());
			log.setIp(ip);
			log.setCreateDate(new Date());
			logService.save(log);

			// 查询课程所属的机构id
			Course course2 = courseService.findOrgIdByCourseId(ids[i]);
			if (course2 != null) {
				courseList.add(course2);
			}
		}

		this.courseService.deleteAll(ids);

		if (courseList != null) {
			// 查询每个机构下的所有课程类型，在删除课程操作后更新到每个机构的课程类型
			for (Course course2 : courseList) {
				String type = "";
				Map map = new HashMap<>();
				map.put("orgId", course2.getOrgId());
				List<Course> courseList2 = courseService.findCoourseTypeByOrgId(map);

				if (courseList2 != null) {
					for (Course course3 : courseList2) {
						if (course3 != null) {
							String courseType = course3.getCourseType();
							type = type + "##" + courseType;
						}
					}
					Orgnization orgnization = orgnizationService.find(course2.getOrgId());
					orgnization.setType(type);
					orgnizationService.update(orgnization);
				}
			}
		}

		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = { "/up" }, method = { RequestMethod.POST })
	public @ResponseBody Message up(Long[] ids) {
		for (Long id : ids) {
			Course course = courseService.find(id);
			course.setStatus(1);
			courseService.update(course);
		}
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = { "/dowm" }, method = { RequestMethod.POST })
	public @ResponseBody Message dowm(Long[] ids) {
		for (Long id : ids) {
			Course course = courseService.find(id);
			course.setStatus(2);
			courseService.update(course);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/dellist" }, method = { RequestMethod.POST })
	public @ResponseBody Message dellist(HttpServletRequest request, Long[] ids) {
		String username = userService.getCurrentUsername();
		for (int i = 0; i < ids.length; i++) {
			CourseTime courseTime = courseTimeService.find(ids[i]);
			String operator = username;
			String ip = request.getRemoteAddr();
			Log log = new Log();
			log.setOperation("删除课程时间");
			log.setOperator(operator);
			log.setContent(JSONObject.fromObject(courseTime).toString());
			log.setParameter(JSONObject.fromObject(courseTime).toString());
			log.setIp(ip);
			log.setCreateDate(new Date());
			logService.save(log);

		}

		this.courseTimeService.deleteAll(ids);

		return SUCCESS_MESSAGE;
	}

	// 机构

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/addorg" }, method = { RequestMethod.GET })
	public String addorg(ModelMap model) {
		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		User user = userService.getCurrent();
		// 教室
		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", user.getOrgId());
		model.addAttribute("courseRooms", courseRooms);
		Map map = new HashMap();
		map.put("orgId", user.getOrgId());
		// 教师
		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher.getId());
				appTeacherInfo.setName(teacher.getNickname());
				appTeacherInfo.setPhone(teacher.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}
		model.addAttribute("teachers", teachers);
		return "/admin/course/addorg";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/addoneorg" }, method = { RequestMethod.GET })
	public String addoneorg(ModelMap model) {
		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		User user = userService.getCurrent();
		// 教室
		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", user.getOrgId());
		model.addAttribute("courseRooms", courseRooms);
		Map map = new HashMap();
		map.put("orgId", user.getOrgId());
		// 教师
		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher.getId());
				appTeacherInfo.setName(teacher.getNickname());
				appTeacherInfo.setPhone(teacher.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}
		model.addAttribute("teachers", appTeacherInfos);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		return "/admin/course/addoneorg";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/addlistorg" }, method = { RequestMethod.GET })
	public String addlistorg(ModelMap model) {
		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		User user = userService.getCurrent();
		// 教室
		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", user.getOrgId());
		model.addAttribute("courseRooms", courseRooms);
		Map map = new HashMap();
		map.put("orgId", user.getOrgId());
		// 教师
		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher.getId());
				appTeacherInfo.setName(teacher.getNickname());
				appTeacherInfo.setPhone(teacher.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}
		model.addAttribute("teachers", appTeacherInfos);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		return "/admin/course/addlistorg";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveorg", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveorg(Course course, RedirectAttributes redirectAttributes) {

		User user = userService.getCurrent();

		course.setOrgId(user.getOrgId());
		course.setFreez(course.getTotalSum());
		course.setFavCount(5);
		course.setBuySum(0);
		course.setCreateDate(new Date());
		course.setUid(Long.parseLong(StringUtil.orderSn()));
		course.setTerm(1);
		courseService.save(course);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveoneorg", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveoneorg(Course course, RedirectAttributes redirectAttributes) {
		User user = userService.getCurrent();

		course.setOrgId(user.getOrgId());
		course.setFreez(course.getTotalSum());
		course.setFavCount(5);
		course.setBuySum(0);
		course.setCreateDate(new Date());
		course.setUid(Long.parseLong(StringUtil.orderSn()));
		course.setTerm(1);

		Orgnization orgnization = orgnizationService.find(course.getOrgId());

		course.setOrgname(orgnization.getName());
		courseService.save(course);

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map = new HashMap<>();
		map.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/savelistorg", method = { RequestMethod.POST, RequestMethod.GET })
	public String savelistorg(Course course, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User user = userService.getCurrent();

		course.setOrgId(user.getOrgId());
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		String week1 = request.getParameter("week1");
		String week2 = request.getParameter("week2");
		String week3 = request.getParameter("week3");
		String week4 = request.getParameter("week4");
		String week5 = request.getParameter("week5");
		String week6 = request.getParameter("week6");
		String week7 = request.getParameter("week7");
		Map map = new HashMap();
		if (!StringUtil.isEmpty(week1) && "1".equals(week1)) {
			map.put(week1, week1);
		}
		if (!StringUtil.isEmpty(week2) && "2".equals(week2)) {
			map.put(week2, week2);
		}
		if (!StringUtil.isEmpty(week3) && "3".equals(week3)) {
			map.put(week3, week3);
		}
		if (!StringUtil.isEmpty(week4) && "4".equals(week4)) {
			map.put(week4, week4);
		}
		if (!StringUtil.isEmpty(week5) && "5".equals(week5)) {
			map.put(week5, week5);
		}
		if (!StringUtil.isEmpty(week6) && "6".equals(week6)) {
			map.put(week6, week6);
		}
		if (!StringUtil.isEmpty(week7) && "7".equals(week7)) {
			map.put(week7, week7);
		}

		String timeRedio1 = request.getParameter("timeRedio1");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");

		String timeRedio2 = request.getParameter("timeRedio2");
		String time3 = request.getParameter("time3");
		String time4 = request.getParameter("time4");

		String timeRedio3 = request.getParameter("timeRedio3");
		String time5 = request.getParameter("time5");
		String time6 = request.getParameter("time6");

		String holiday = request.getParameter("holiday");
		List<Holiday> holidays = holidayService.findAll();

		Orgnization orgnization = orgnizationService.find(course.getOrgId());
		course.setOrgname(orgnization.getName());
		course.setFreez(course.getTotalSum());
		course.setFavCount(5);
		course.setBuySum(0);
		course.setCreateDate(new Date());
		course.setUid(Long.parseLong(StringUtil.orderSn()));
		course.setTerm(1);
		courseService.save(course);

		Map mapholidays = new HashMap();
		if (holidays != null) {
			for (Holiday holiday2 : holidays) {
				mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
			}
		}

		Date startDate = StringUtil.StringDateToDate(start);
		Date endDate = StringUtil.StringDateToDate(end);

		// 时间，周几，节假日判断
		List<Date> dates = StringUtil.getBetweenDates(startDate, endDate);
		// 时间段1
		if (!StringUtil.isEmpty(time1) && !StringUtil.isEmpty(time2) && !StringUtil.isEmpty(timeRedio1)
				&& "1".equals(timeRedio1)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段2
		if (!StringUtil.isEmpty(time3) && !StringUtil.isEmpty(time4) && !StringUtil.isEmpty(timeRedio2)
				&& "1".equals(timeRedio2)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段3
		if (!StringUtil.isEmpty(time5) && !StringUtil.isEmpty(time6) && !StringUtil.isEmpty(timeRedio3)
				&& "1".equals(timeRedio3)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map1 = new HashMap<>();
		map1.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map1);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	private void setCourseTimeorg(Date date, String time1, String time2, Integer term, Long courseId) {
		CourseTime courseTime = new CourseTime();
		courseTime.setCourseId(courseId);
		courseTime.setCourseTime(date);
		courseTime.setCourseTime1(time1);
		courseTime.setCourseTime2(time2);
		courseTime.setCourseTime3(Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		courseTime.setCourseTime4(Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));
		courseTime.setTerm(term);
		courseTime.setCreateTime(new Date());
		courseTimeService.save(courseTime);
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editorg/{id}", method = RequestMethod.GET)
	public String editorg(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);
		model.addAttribute("course", course);
		return "/admin/course/editorg";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editoneorg/{id}", method = RequestMethod.GET)
	public String editoneorg(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);
		model.addAttribute("course", course);

		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
			model.addAttribute("classId", courseRoom.getId());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
			model.addAttribute("teacherId", teacher.getId());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
				model.addAttribute("assistantId", teacher2.getId());

			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
				model.addAttribute("substituteId", teacher2.getId());
			}

		}

		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", course.getOrgId());
		model.addAttribute("courseRooms", courseRooms);

		Map map = new HashMap();
		map.put("orgId", course.getOrgId());

		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher1 : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher1.getId());
				appTeacherInfo.setName(teacher1.getNickname());
				appTeacherInfo.setPhone(teacher1.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}
		model.addAttribute("appTeacherInfos", appTeacherInfos);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		return "/admin/course/editoneorg";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editlistorg/{id}", method = RequestMethod.GET)
	public String editlistorg(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);
		model.addAttribute("course", course);

		String token = FileServiceImpl.getToken();

		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);

		List<Orgnization> orgs = orgnizationService.findAll();
		model.addAttribute("orgs", orgs);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
			model.addAttribute("classId", courseRoom.getId());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
			model.addAttribute("teacherId", teacher.getId());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
				model.addAttribute("assistantId", teacher2.getId());

			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
				model.addAttribute("substituteId", teacher2.getId());
			}

		}

		List<CourseRoom> courseRooms = courseRoomService.findList("orgId", course.getOrgId());
		model.addAttribute("courseRooms", courseRooms);

		Map map = new HashMap();
		map.put("orgId", course.getOrgId());

		List<Teacher> teachers = teacherService.findTeacherListByOrgId(map);
		List<AppTeacherInfo> appTeacherInfos = new ArrayList();
		if (appTeacherInfos != null) {
			for (Teacher teacher1 : teachers) {

				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher1.getId());
				appTeacherInfo.setName(teacher1.getNickname());
				appTeacherInfo.setPhone(teacher1.getAccount());
				appTeacherInfos.add(appTeacherInfo);
			}
		}
		model.addAttribute("appTeacherInfos", appTeacherInfos);

		List<CourseType> courseTypes = courseTypeService.findAll();
		model.addAttribute("courseTypes", courseTypes);

		// 上课时间
		Map map2 = new HashMap();
		map2.put("courseId", course.getId());
		map2.put("order", " course_time3 desc");

		List<CourseTime> courseTimes = courseTimeService.findListByPage(map2);
		List<AppWebCourseTime> appWebCourseTimes = new ArrayList();
		if (courseTimes != null) {
			for (CourseTime courseTime : courseTimes) {
				AppWebCourseTime appWebCourseTime = new AppWebCourseTime();
				appWebCourseTime.setCoursedate(StringUtil.date2String(courseTime.getCourseTime()));
				appWebCourseTime.setCoursetime(courseTime.getCourseTime1() + "~" + courseTime.getCourseTime2());
				appWebCourseTime.setCourseweek("星期" + StringUtil.getWeekOfDate(courseTime.getCourseTime()));
				appWebCourseTime.setId(courseTime.getId());
				appWebCourseTimes.add(appWebCourseTime);
			}
		}
		model.addAttribute("appWebCourseTimes", appWebCourseTimes);

		return "/admin/course/editlistorg";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/viewoneorg/{id}", method = RequestMethod.GET)
	public String viewoneorg(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
			}

		}

		model.addAttribute("course", course);
		return "/admin/course/viewoneorg";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/viewlistorg/{id}", method = RequestMethod.GET)
	public String viewlistorg(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);

		CourseRoom courseRoom = courseRoomService.find(course.getClassId());
		if (courseRoom != null) {
			model.addAttribute("class", courseRoom.getName());
		}

		Teacher teacher = teacherService.find(course.getTeacherId());
		if (teacher != null) {
			model.addAttribute("teacher", teacher.getNickname());
		}

		if (course.getAssistant() != null) {
			Teacher teacher2 = teacherService.find(course.getAssistant());
			if (teacher2 != null) {
				model.addAttribute("assistant", teacher2.getNickname());
			}

		}

		if (course.getSubstitute() != null) {
			Teacher teacher2 = teacherService.find(course.getSubstitute());
			if (teacher2 != null) {
				model.addAttribute("substitute", teacher2.getNickname());
			}

		}

		model.addAttribute("course", course);

		// 上课时间
		Map map = new HashMap();
		map.put("courseId", course.getId());
		map.put("order", " course_time3 desc");

		List<CourseTime> courseTimes = courseTimeService.findListByPage(map);
		List<AppWebCourseTime> appWebCourseTimes = new ArrayList();
		if (courseTimes != null) {
			for (CourseTime courseTime : courseTimes) {
				AppWebCourseTime appWebCourseTime = new AppWebCourseTime();
				appWebCourseTime.setCoursedate(StringUtil.date2String(courseTime.getCourseTime()));
				appWebCourseTime.setCoursetime(courseTime.getCourseTime1() + "~" + courseTime.getCourseTime2());
				appWebCourseTime.setCourseweek("星期" + StringUtil.getWeekOfDate(courseTime.getCourseTime()));
				appWebCourseTimes.add(appWebCourseTime);
			}
		}
		model.addAttribute("appWebCourseTimes", appWebCourseTimes);

		return "/admin/course/viewlistorg";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updateorg", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateorg(Course course, RedirectAttributes redirectAttributes) {
		courseService.update(course);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updateoneorg", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateoneorg(Course course, RedirectAttributes redirectAttributes) {

		Orgnization orgnization = orgnizationService.find(course.getOrgId());

		course.setOrgname(orgnization.getName());

		courseService.update(course);

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map = new HashMap<>();
		map.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updatelistorg", method = { RequestMethod.POST, RequestMethod.GET })
	public String updatelistorg(Course course, RedirectAttributes redirectAttributes) {

		Orgnization orgnization = orgnizationService.find(course.getOrgId());

		course.setOrgname(orgnization.getName());

		courseService.update(course);

		// 查询该机构下的所有课程类型，并更新到该机构的课程类型
		String type = "";
		Map map = new HashMap<>();
		map.put("orgId", course.getOrgId());
		List<Course> courseList = courseService.findCoourseTypeByOrgId(map);
		if (courseList != null) {
			for (Course course2 : courseList) {
				if (course2 != null) {
					String courseType = course2.getCourseType();
					type = type + "##" + courseType;
				}
			}
		}
		orgnization.setType(type);
		orgnizationService.update(orgnization);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	@RequestMapping(value = "/addlisttimeorg/{id}", method = RequestMethod.GET)
	public String addlisttimeorg(@PathVariable Long id, ModelMap model) {
		Course course = courseService.find(id);

		model.addAttribute("course", course);

		return "/admin/course/addlisttimeorg";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/savelisttimeorg", method = { RequestMethod.POST, RequestMethod.GET })
	public String savelisttimeorg(Course course, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String start = request.getParameter("start");
		String end = request.getParameter("end");

		String week1 = request.getParameter("week1");
		String week2 = request.getParameter("week2");
		String week3 = request.getParameter("week3");
		String week4 = request.getParameter("week4");
		String week5 = request.getParameter("week5");
		String week6 = request.getParameter("week6");
		String week7 = request.getParameter("week7");
		Map map = new HashMap();
		if (!StringUtil.isEmpty(week1) && "1".equals(week1)) {
			map.put(week1, week1);
		}
		if (!StringUtil.isEmpty(week2) && "2".equals(week2)) {
			map.put(week2, week2);
		}
		if (!StringUtil.isEmpty(week3) && "3".equals(week3)) {
			map.put(week3, week3);
		}
		if (!StringUtil.isEmpty(week4) && "4".equals(week4)) {
			map.put(week4, week4);
		}
		if (!StringUtil.isEmpty(week5) && "5".equals(week5)) {
			map.put(week5, week5);
		}
		if (!StringUtil.isEmpty(week6) && "6".equals(week6)) {
			map.put(week6, week6);
		}
		if (!StringUtil.isEmpty(week7) && "7".equals(week7)) {
			map.put(week7, week7);
		}

		String timeRedio1 = request.getParameter("timeRedio1");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");

		String timeRedio2 = request.getParameter("timeRedio2");
		String time3 = request.getParameter("time3");
		String time4 = request.getParameter("time4");

		String timeRedio3 = request.getParameter("timeRedio3");
		String time5 = request.getParameter("time5");
		String time6 = request.getParameter("time6");

		String holiday = request.getParameter("holiday");
		List<Holiday> holidays = holidayService.findAll();

		Map mapholidays = new HashMap();
		if (holidays != null) {
			for (Holiday holiday2 : holidays) {
				mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
			}
		}

		Date startDate = StringUtil.StringDateToDate(start);
		Date endDate = StringUtil.StringDateToDate(end);

		// 时间，周几，节假日判断
		List<Date> dates = StringUtil.getBetweenDates(startDate, endDate);
		// 时间段1
		if (!StringUtil.isEmpty(time1) && !StringUtil.isEmpty(time2) && !StringUtil.isEmpty(timeRedio1)
				&& "1".equals(timeRedio1)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time1, time2, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段2
		if (!StringUtil.isEmpty(time3) && !StringUtil.isEmpty(time4) && !StringUtil.isEmpty(timeRedio2)
				&& "1".equals(timeRedio2)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time3, time4, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		// 时间段3
		if (!StringUtil.isEmpty(time5) && !StringUtil.isEmpty(time6) && !StringUtil.isEmpty(timeRedio3)
				&& "1".equals(timeRedio3)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							this.setCourseTime(date2, time5, time6, course.getTerm(), course.getId());
						}
					}
				}
			}
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/course/editlistorg/" + course.getId() + ".jhtml";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listorg" }, method = { RequestMethod.GET })
	public String listorg(Pageable pageable, HttpServletRequest request, ModelMap model) {
		User user = userService.getCurrent();
		Map<String, Object> paramter2 = ParameterUtils.getParametersMapStartingWith(request, "filter_");

		String filter_id = request.getParameter("filter_id");
		String filter_name = request.getParameter("filter_name");
		String filter_orgname = request.getParameter("filter_orgname");
		String filter_teachername = request.getParameter("filter_teachername");
		String filter_type = request.getParameter("filter_type");
		String filter_status = request.getParameter("filter_status");
		Map paramter = new HashMap();
		if (!StringUtil.isEmpty(filter_id)) {
			paramter.put("id", StringUtil.filterSQL(filter_id));
		}

		if (!StringUtil.isEmpty(filter_name)) {
			paramter.put("filter_name", StringUtil.filterSQL(filter_name));
		}

		if (!StringUtil.isEmpty(filter_orgname)) {
			paramter.put("filter_orgname", StringUtil.filterSQL(filter_orgname));
		}

		if (!StringUtil.isEmpty(filter_teachername)) {
			paramter.put("filter_teachername", StringUtil.filterSQL(filter_teachername));
		}

		if (!StringUtil.isEmpty(filter_type)) {
			paramter.put("type", StringUtil.filterSQL(filter_type));
		}

		if (!StringUtil.isEmpty(filter_status)) {
			paramter.put("status", StringUtil.filterSQL(filter_status));
		}

		paramter.put("orgId", user.getOrgId());
		pageable.setParameter(paramter);
		model.addAttribute("paramter", paramter2);
		model.addAttribute("page", this.courseService.findByPage(pageable));
		return "/admin/course/listorg";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/deleteorg" }, method = { RequestMethod.POST })
	public @ResponseBody Message deleteorg(HttpServletRequest request, Long[] ids) {
		String username = userService.getCurrentUsername();
		for (int i = 0; i < ids.length; i++) {
			Course course = courseService.find(ids[i]);

			String operator = username;
			String ip = request.getRemoteAddr();
			Log log = new Log();
			log.setOperation("删除课程");
			log.setOperator(operator);
			log.setContent(JSONObject.fromObject(course).toString());
			log.setParameter(JSONObject.fromObject(course).toString());
			log.setIp(ip);
			log.setCreateDate(new Date());
			logService.save(log);

		}

		this.courseService.deleteAll(ids);

		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = { "/uporg" }, method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Message uporg(Long[] ids) {
		for (Long id : ids) {
			Course course = courseService.find(id);
			course.setStatus(1);
			courseService.update(course);
		}
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = { "/dowmorg" }, method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Message dowmorg(Long[] ids) {
		for (Long id : ids) {
			Course course = courseService.find(id);
			course.setStatus(2);
			courseService.update(course);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/dellistorg" }, method = { RequestMethod.POST })
	public @ResponseBody Message dellistorg(HttpServletRequest request, Long[] ids) {
		String username = userService.getCurrentUsername();
		for (int i = 0; i < ids.length; i++) {
			CourseTime courseTime = courseTimeService.find(ids[i]);
			String operator = username;
			String ip = request.getRemoteAddr();
			Log log = new Log();
			log.setOperation("删除课程时间");
			log.setOperator(operator);
			log.setContent(JSONObject.fromObject(courseTime).toString());
			log.setParameter(JSONObject.fromObject(courseTime).toString());
			log.setIp(ip);
			log.setCreateDate(new Date());
			logService.save(log);

		}

		this.courseTimeService.deleteAll(ids);

		return SUCCESS_MESSAGE;
	}

	/**
	 * 查看课时
	 */
	@RequestMapping(value = { "/courseTime/{id}/{orgId}" }, method = { RequestMethod.GET })
	public String courseTime(@PathVariable Long id, @PathVariable Long orgId, ModelMap model) {

		Map map = new HashMap<>();
		map.put("courseId", id);
		map.put("orgId", orgId);
		List<CourseTeaTime> courseTeaTimeList = courseTeaTimeService.findList(map);

		List<CourseTeaTime> prepareCourseTimeList = new ArrayList<>();
		List<CourseTeaTime> finishCourseTimeList = new ArrayList<>();

		Long date = Long.parseLong(StringUtil.date2String3(new Date()));

		for (CourseTeaTime courseTeaTime : courseTeaTimeList) {
			// 未开始课时
			if (courseTeaTime.getCourseTime3() >= date) {
				prepareCourseTimeList.add(courseTeaTime);
			}
			// 已结束课时
			if (courseTeaTime.getCourseTime3() < date) {
				finishCourseTimeList.add(courseTeaTime);
			}
		}

		model.addAttribute("prepareCourseTimeList", prepareCourseTimeList);
		model.addAttribute("finishCourseTimeList", finishCourseTimeList);

		return "/admin/course/courseTime";
	}

	/**
	 * 调课
	 */
	@RequestMapping(value = { "/courseChangeTime/{courseId}/{courseTimeId}" }, method = { RequestMethod.GET })
	public String courseChangeTime(@PathVariable Long courseId, @PathVariable Long courseTimeId, ModelMap model) {

		CourseTeaTime courseTeaTime = courseTeaTimeService.find(courseTimeId);

		model.addAttribute("courseTeaTime", courseTeaTime);
		model.addAttribute("courseId", courseId);

		return "/admin/course/course_change_time";
	}

	/**
	 * 调课更新
	 */
	@RequestMapping(value = "/updateCourseTime", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateCourseTime(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String courseId = request.getParameter("courseId");
		String courseTeaTimeId = request.getParameter("courseTeaTimeId");
		String changeTime = request.getParameter("changeTime");
		String changeStart = request.getParameter("changeStart");
		String changeEnd = request.getParameter("changeEnd");
		String reason = request.getParameter("reason");

		CourseTeaTime courseTeaTime = courseTeaTimeService.find("id", Long.parseLong(courseTeaTimeId));
		Course course = courseService.find("id", Long.parseLong(courseId));

		if (courseTeaTime != null) {
			// 调课时将原上课时间保存到另一字段，原字段保存调课后的时间
			courseTeaTime.setOldTime(courseTeaTime.getCourseTime());
			courseTeaTime.setOldTime1(courseTeaTime.getCourseTime1());
			courseTeaTime.setOldTime2(courseTeaTime.getCourseTime2());

			courseTeaTime.setCourseTime(StringUtil.StringDateToDate((StringUtil.filterSQL(changeTime))));
			courseTeaTime.setCourseTime1(StringUtil.filterSQL(changeStart));
			courseTeaTime.setCourseTime2(StringUtil.filterSQL(changeEnd));
			courseTeaTime.setReason(StringUtil.filterSQL(reason));

			Date changeTimeDate = StringUtil.StringDateToDate((StringUtil.filterSQL(changeTime)));
			String str = StringUtil.date2String2(changeTimeDate);
			String str1 = str + changeStart.replace(":", "");
			String str2 = str + changeEnd.replace(":", "");

			courseTeaTime.setCourseTime3(Long.parseLong(str1));
			courseTeaTime.setCourseTime4(Long.parseLong(str2));
			courseTeaTime.setCTime(changeTimeDate);
			courseTeaTime.setType(2);

			courseTeaTimeService.update(courseTeaTime);

			// 更新学生上课时间
			Map map = new HashMap();
			map.put("courseId", Long.parseLong(courseId));
			map.put("courseTime", courseTeaTime.getOldTime());
			map.put("courseTime1", courseTeaTime.getOldTime1());
			map.put("courseTime2", courseTeaTime.getOldTime2());

			List<CourseStuTime> courseStuTimeList = courseStuTimeService.findList(map);
			for (CourseStuTime courseStuTime2 : courseStuTimeList) {
				courseStuTime2.setCourseTime(StringUtil.StringDateToDate((StringUtil.filterSQL(changeTime))));
				courseStuTime2.setCourseTime1(StringUtil.filterSQL(changeStart));
				courseStuTime2.setCourseTime2(StringUtil.filterSQL(changeEnd));
				courseStuTime2.setCourseTime3(Long.parseLong(str1));
				courseStuTime2.setCourseTime4(Long.parseLong(str2));

				courseStuTimeService.update(courseStuTime2);

				// 发送推送消息-家长

				if (courseTeaTime.getOldTime() != null) {

					Order order = orderService.find("id", courseStuTime2.getOrderId());
					Parent parent = parentService.find("id", order.getUid());

					String content = parent.getRelName() + "家长您好，原定于"
							+ StringUtil.date2String13(courseTeaTime.getOldTime()) + courseTeaTime.getOldTime1() + "~"
							+ courseTeaTime.getOldTime2() + "的" + course.getName() + "课程因" + courseTeaTime.getReason()
							+ "的原因调整至" + StringUtil.date2String13(courseStuTime2.getCourseTime())
							+ courseStuTime2.getCourseTime1() + "~" + courseStuTime2.getCourseTime2() + ",特此通知，敬请原谅。";

					SystemMes systemMes1 = new SystemMes();
					systemMes1.setUserId(parent.getId());
					systemMes1.setUserName(parent.getRelName());
					systemMes1.setCreateDate(new Date());
					systemMes1.setContent(content);
					systemMes1.setType(2);
					systemMes1.setTitle("调课通知");

					systemMesService.save(systemMes1);

					OrgDynamic orgDynamic = new OrgDynamic();

					orgDynamic.setTargetId(systemMes1.getId());
					orgDynamic.setOrgId(courseTeaTime.getOrgId());
					Orgnization orgnization = orgnizationService.find(courseTeaTime.getOrgId());
					orgDynamic.setImgUrl(orgnization.getImage());
					orgDynamic.setAvatar(orgnization.getImage());
					orgDynamic.setPrompt("调课通知");
					orgDynamic.setTextContent(content);
					orgDynamic.setCreateDate(new Date());
					orgDynamic.setUserId(courseTeaTime.getTeacherId());
					orgDynamic.setType(5);
					orgDynamic.setUserType(1);
					orgDynamic.setStatus(1);

					orgDynamicService.save(orgDynamic);
					userUnreadService.setUserUnread(1, parent.getId(), 5);

					JpushRunnable t1 = new JpushRunnable(systemMes1.getTitle(), "p" + parent.getId(), "type",
							Constants.system + "_" + systemMes1.getId() + "", this.mapService());
					t1.start();
				}
			}

			// 发送推送消息-教师
			Teacher teacher = teacherService.find("id", courseTeaTime.getTeacherId());

			if (courseTeaTime.getOldTime() != null) {

				String content = teacher.getNickname() + "老师您好，原定于"
						+ StringUtil.date2String13(courseTeaTime.getOldTime()) + courseTeaTime.getOldTime1() + "~"
						+ courseTeaTime.getOldTime2() + "的" + course.getName() + "课程因" + courseTeaTime.getReason()
						+ "的原因调整至" + StringUtil.date2String13(courseTeaTime.getCourseTime())
						+ courseTeaTime.getCourseTime1() + "~" + courseTeaTime.getCourseTime2() + ",特此通知，敬请原谅。";
				//系统消息
				SystemMes systemMes2 = new SystemMes();
				systemMes2.setUserId(teacher.getId());
				systemMes2.setUserName(teacher.getNickname());
				systemMes2.setCreateDate(new Date());
				systemMes2.setContent(content);
				systemMes2.setType(1);
				systemMes2.setTitle("调课通知");

				systemMesService.save(systemMes2);
				//机构动态
				OrgDynamic orgDynamic = new OrgDynamic();

				orgDynamic.setTargetId(systemMes2.getId());
				orgDynamic.setOrgId(courseTeaTime.getOrgId());
				Orgnization orgnization = orgnizationService.find(courseTeaTime.getOrgId());
				orgDynamic.setImgUrl(orgnization.getImage());
				orgDynamic.setAvatar(orgnization.getImage());
				orgDynamic.setPrompt("调课通知");
				orgDynamic.setTextContent(content);
				orgDynamic.setCreateDate(new Date());
				orgDynamic.setUserId(courseTeaTime.getTeacherId());
				orgDynamic.setType(5);
				orgDynamic.setUserType(2);
				orgDynamic.setStatus(1);
				
				

				orgDynamicService.save(orgDynamic);
				userUnreadService.setUserUnread(2, teacher.getId(), 5);

				JpushRunnable2 t2 = new JpushRunnable2(systemMes2.getTitle(), "t" + teacher.getId(), "type",
						Constants.system + "_" + systemMes2.getId() + "", this.mapService());
				t2.start();
			}
		}

		return "redirect:/admin/course/courseTime/" + Long.parseLong(courseId) + ".jhtml";
	}

	private Map mapService() {
		Map map = new HashMap();

		return map;
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delalllist" }, method = { RequestMethod.POST })
	public @ResponseBody Message delalllistorg(HttpServletRequest request) {

		String id = request.getParameter("id");
		String username = userService.getCurrentUsername();
		List<CourseTime> courseTimes = courseTimeService.findList("courseId", id);
		if (courseTimes != null) {
			for (CourseTime courseTime : courseTimes) {

				String operator = username;
				String ip = request.getRemoteAddr();
				Log log = new Log();
				log.setOperation("删除课程时间");
				log.setOperator(operator);
				log.setContent(JSONObject.fromObject(courseTime).toString());
				log.setParameter(JSONObject.fromObject(courseTime).toString());
				log.setIp(ip);
				log.setCreateDate(new Date());
				logService.save(log);

				this.courseTimeService.delete(courseTime.getId());
			}
		}

		return SUCCESS_MESSAGE;
	}

	/**
	 * 发送开课时间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/opencoursetime" }, method = { RequestMethod.POST })
	public @ResponseBody Message opencoursetime(HttpServletRequest request) {
		Long nowtime = Long.parseLong(StringUtil.date2String3(new Date()));
		String id = request.getParameter("id");
		//当前用户
		String username = userService.getCurrentUsername();
		
		Course course = courseService.find(Long.parseLong(id));
		Map map = new HashMap();
		map.put("courseId", id);
		map.put("pageSize3", nowtime);
		// 删除当前时间以后的课程课时，教师课时，学生课时
		List<CourseTime> courseTimes = courseTimeService.findListByPage(map);
		//courseTimeService.de
		courseStuTimeService.deletewhere(map);
		courseTeaTimeService.deletewhere(map);

		// 上课的学生id
		Map map2 = new HashMap();
		map2.put("targetId", id);
		map2.put("term", course.getTerm());
		map2.put("type", 1);
		// List<Order> orders= orderService.findListByPage(map2);
//		System.err.println("================="+id+"======================");
//		System.err.println("===================="+course.getTerm()+"===================");
		// 该课程的所有学生(去除重复项)
		List<Order> orderList1 = orderService.findStuIdByCourseId(map2);
		System.err.println("=======================================");
		System.err.println(orderList1);

		// 若同一学生同一课程有两条以上订单，取出剩余课时最大的那条订单
		List<Order> orderList2 = new ArrayList<>();
		for (Order order : orderList1) {
			Map map3 = new HashMap();
			map3.put("targetId", id);
			map3.put("term", course.getTerm());
			map3.put("type", 1);
			map3.put("stuId", order.getStuId());
			map3.put("order", "classtimes DESC");
			List<Order> orderList3 = orderService.findListByPage(map3);
			System.err.println(orderList3);
			if (orderList3 != null) {
				orderList2.add(orderList3.get(0));
			}

		}

		if (courseTimes != null) {
			for (CourseTime courseTime : courseTimes) {

				// 已上过的课不生成上课时间表
				Long dateLong = Long.parseLong(StringUtil.date2String3(new Date()));
				if (courseTime.getCourseTime3() > dateLong) {

					String operator = username;
					String ip = request.getRemoteAddr();
					Log log = new Log();
					log.setOperation("发送开课课程时间");
					log.setOperator(operator);
					log.setContent(JSONObject.fromObject(courseTime).toString());
					log.setParameter(JSONObject.fromObject(courseTime).toString());
					log.setIp(ip);
					log.setCreateDate(new Date());
					logService.save(log);

					// 重新分配上课时间
					if (orderList2 != null) {
						for (int i = 0; i < orderList2.size(); i++) {
							Order order = orderList2.get(i);
							// 判断是否为中间报名，1为中间报名
							if (order.getRestType() == null) {
								setStuCourseTime(courseTime.getCourseTime(), courseTime.getCourseTime1(),
										courseTime.getCourseTime2(), courseTime.getTerm(), courseTime.getCourseId(),
										order.getId(), order.getStuId(), courseTime.getId());
							} else if (order.getRestType() == 1 && order.getRestclass() > 0) {
								setStuCourseTime(courseTime.getCourseTime(), courseTime.getCourseTime1(),
										courseTime.getCourseTime2(), courseTime.getTerm(), courseTime.getCourseId(),
										order.getId(), order.getStuId(), courseTime.getId());

								// 少上的课时数减一
								order.setRestclass(order.getRestclass() - 1);
								orderService.update(order);
							}

							/*
							 * if(order.getRestType() == 1 &&
							 * order.getRestclass() > 0) {
							 * setStuCourseTime(courseTime.getCourseTime(),
							 * courseTime.getCourseTime1()
							 * ,courseTime.getCourseTime2(),
							 * courseTime.getTerm(),courseTime.getCourseId()
							 * ,order.getId(),order.getStuId(),courseTime.getId(
							 * ));
							 * 
							 * //少上的课时数减一
							 * order.setRestclass(order.getRestclass()-1);
							 * orderService.save(order); }
							 */
						}
					}
				}

				
				setTeaCourseTime(courseTime.getCourseTime(), courseTime.getCourseTime1(), courseTime.getCourseTime2(),
						courseTime.getTerm(), courseTime.getCourseId(), null, course.getTeacherId(), courseTime.getId(),
						course.getOrgId());
			}
		}

		return SUCCESS_MESSAGE;

	}

	private void setStuCourseTime(Date date, String time1, String time2, Integer term, Long courseId, Long orderId,
			Long stuId, Long courseTimeId) {
		System.err.println("1111111111111111111111111111111111111111111");
		CourseStuTime courseStuTime = new CourseStuTime();
		courseStuTime.setOrderId(orderId);
		courseStuTime.setStatus(1);
		courseStuTime.setStudentId(stuId);
		courseStuTime.setCourseId(courseId);
		courseStuTime.setCourseTime(date);
		courseStuTime.setCourseTime1(time1);
		courseStuTime.setCourseTime2(time2);
		courseStuTime.setCourseTime3(Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		courseStuTime.setCourseTime4(Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));
		courseStuTime.setTerm(term);
		courseStuTime.setCreateTime(new Date());
		courseStuTime.setCourseTimeId(courseTimeId);
		courseStuTimeService.save(courseStuTime);
		
		Course course = courseService.find(courseId);
		
		Order order = orderService.find(orderId);
		
		
		//您的课程XXX已经开课了，上课时间为2016年09月09日12:00~13:00
		
         if(courseStuTime!=null){
        	 
        	 String content="您的课程"+course.getName()+"已经开课了，上课时间为"+StringUtil.date2String13(courseStuTime.getCourseTime())
				+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2();
        	 OrgDynamic  orgDynamic=new OrgDynamic();
        	 
        	 SystemMes systemMes1 = new SystemMes();
			systemMes1.setUserId(order.getUid());
			Parent parent = parentService.find(order.getUid());
			systemMes1.setUserName(parent.getRelName());
			systemMes1.setCreateDate(new Date());
			systemMes1.setContent(content);
			systemMes1.setType(2);
			systemMes1.setTitle("开课通知");

			systemMesService.save(systemMes1);

   		  orgDynamic.setTextContent(content);
   		  orgDynamic.setPrompt("开课通知");
   		  orgDynamic.setTargetId(orderId);
   		  orgDynamic.setStarts(1);
   		  orgDynamic.setUserType(1);
   		  orgDynamic.setType(5);
   		  orgDynamic.setUserId(order.getUid());
   		  orgDynamicService.save(orgDynamic);
        	 
   		userUnreadService.setUserUnread(1, parent.getId(), 5);
        	 
		JpushRunnable t1 = new JpushRunnable(systemMes1.getTitle(), "p" + parent.getId(), "type",
				Constants.system + "_" + systemMes1.getId() + "", this.mapService());
		t1.start();
         }
		
		
		
		
	}

	private void setTeaCourseTime(Date date, String time1, String time2, Integer term, Long courseId, Long orderId,
			Long teaId, Long courseTimeId, Long orgId) {
		CourseTeaTime courseTeaTime = new CourseTeaTime();
		courseTeaTime.setCourseTimeId(courseTimeId);
		courseTeaTime.setStatus(1);
		courseTeaTime.setCourseId(courseId);
		courseTeaTime.setCourseTime(date);
		courseTeaTime.setCTime(date);
		courseTeaTime.setCourseTime1(time1);
		courseTeaTime.setCourseTime2(time2);
		courseTeaTime.setCourseTime3(Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		courseTeaTime.setCourseTime4(Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));
		courseTeaTime.setTerm(term);
		courseTeaTime.setTeacherId(teaId);
		courseTeaTime.setCourseTimeId(courseTimeId);
		courseTeaTime.setCreateTime(new Date());
		courseTeaTime.setOrgId(orgId);
		// 上课签到时间，设置30分钟
		Date date2 = StringUtil.StringDateToDate4(StringUtil.date2String2(date) + time1.replace(":", ""));
		// 对 calendar 设置为 date 所定的日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		calendar.add(Calendar.MINUTE, -15);
		courseTeaTime.setCourseTimeLess(Long.parseLong(StringUtil.date2String3(calendar.getTime())));
		courseTeaTimeService.save(courseTeaTime);
		System.err.println("**********************************///////////////////////////////////////////////******************************");
		Course course = courseService.find(courseId);
		
		//Order order = orderService.find(orderId);
		Teacher teacher = teacherService.find(courseTeaTime.getTeacherId());
		
		//您的课程XXX已经开课了，上课时间为2016年09月09日12:00~13:00
		
         if(courseTeaTime!=null){
        	 
        	 String content="您的课程"+course.getName()+"已经开课了，上课时间为"+StringUtil.date2String13(courseTeaTime.getCourseTime())
				+" "+courseTeaTime.getCourseTime1()+"~"+courseTeaTime.getCourseTime2();
        	 OrgDynamic  orgDynamic=new OrgDynamic();
        	 
        	 SystemMes systemMes1 = new SystemMes();
			systemMes1.setUserId(teacher.getId());
			//Parent parent = parentService.find(order.getUid());
			
			systemMes1.setUserName(teacher.getNickname());
			systemMes1.setCreateDate(new Date());
			systemMes1.setContent(content);
			systemMes1.setType(1);
			systemMes1.setTitle("开课通知");

			systemMesService.save(systemMes1);

   		  orgDynamic.setTextContent(content);
   		  orgDynamic.setPrompt("开课通知");
   		  orgDynamic.setTargetId(orderId);
   		  orgDynamic.setStarts(1);
   		  orgDynamic.setUserType(2);
   		  orgDynamic.setType(5);
   		  orgDynamic.setUserId(teacher.getId());
   		  orgDynamicService.save(orgDynamic);
        	 
   		userUnreadService.setUserUnread(2, teacher.getId(), 5);
        	 
		JpushRunnable t1 = new JpushRunnable(systemMes1.getTitle(), "p" + teacher.getId(), "type",
				Constants.system + "_" + systemMes1.getId() + "", this.mapService());
		t1.start();
         }
	}
	/**
	 * 开课
	 * 
	 * @param ids
	 * @return
	 */
	/*@RequestMapping(value = { "/open" }, method = { RequestMethod.POST })
	public @ResponseBody Message open(Long[] ids) {
		if(ids!=null){
			for (int i = 0; i < ids.length; i++) {
				Order order=new Order();
				order.setId(ids[i]);
				order.setOpenclass(2);
				orderService.update(order);
				order=orderService.find(ids[i]);
				//您的课程XXX已经开课了，上课时间为2016年09月09日12:00~13:00
				Map map=new HashMap();
				map.put("orderId", ids[i]);
				map.put("order", "course_time3");
				List<CourseStuTime>	courseStuTimes=courseStuTimeService.findListByPage(map);
                 if(courseStuTimes!=null&&courseStuTimes.size()>0){
                	 CourseStuTime courseStuTime=courseStuTimes.get(0);
                	 String content="您的课程"+order.getTargetName()+"已经开课了，上课时间为"+StringUtil.date2String13(courseStuTime.getCourseTime())
						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2();
                	 OrgDynamic  orgDynamic=new OrgDynamic();
    
           		  orgDynamic.setTextContent(content);
           		  orgDynamic.setPrompt("开课通知");
           		  orgDynamic.setTargetId(ids[i]);
           		  orgDynamic.setStarts(1);
           		  orgDynamic.setUserType(1);
           		 	orgDynamic.setType(5);
           		  orgDynamic.setUserId(order.getUid());
           		  orgDynamicService.save(orgDynamic);
                	 
           		userUnreadService.setUserUnread(1, order.getUid(), 5);
                	 
				JpushRunnable t1 = new JpushRunnable(content,"p"+ order.getUid()
				, "type", Constants.system+"_"+order.getId() + "",
						this.mapService1());
				t1.start();
                 }
			}
			
			
		}
		return SUCCESS_MESSAGE;
	}
	
	private Map mapService1() {
		Map map = new HashMap();

		return map;

	}*/

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping(value = "/checkTime", method = { RequestMethod.POST, RequestMethod.GET })
	public String checkTime(Course course, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String start = request.getParameter("start");
		String end = request.getParameter("end");

		String week1 = request.getParameter("week1");
		String week2 = request.getParameter("week2");
		String week3 = request.getParameter("week3");
		String week4 = request.getParameter("week4");
		String week5 = request.getParameter("week5");
		String week6 = request.getParameter("week6");
		String week7 = request.getParameter("week7");
		Map map = new HashMap();
		if (!StringUtil.isEmpty(week1) && "1".equals(week1)) {
			map.put(week1, week1);
		}
		if (!StringUtil.isEmpty(week2) && "2".equals(week2)) {
			map.put(week2, week2);
		}
		if (!StringUtil.isEmpty(week3) && "3".equals(week3)) {
			map.put(week3, week3);
		}
		if (!StringUtil.isEmpty(week4) && "4".equals(week4)) {
			map.put(week4, week4);
		}
		if (!StringUtil.isEmpty(week5) && "5".equals(week5)) {
			map.put(week5, week5);
		}
		if (!StringUtil.isEmpty(week6) && "6".equals(week6)) {
			map.put(week6, week6);
		}
		if (!StringUtil.isEmpty(week7) && "7".equals(week7)) {
			map.put(week7, week7);
		}

		String timeRedio1 = request.getParameter("timeRedio1");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");

		String timeRedio2 = request.getParameter("timeRedio2");
		String time3 = request.getParameter("time3");
		String time4 = request.getParameter("time4");

		String timeRedio3 = request.getParameter("timeRedio3");
		String time5 = request.getParameter("time5");
		String time6 = request.getParameter("time6");

		String holiday = request.getParameter("holiday");
		List<Holiday> holidays = holidayService.findAll();

		Map mapholidays = new HashMap();
		if (holidays != null) {
			for (Holiday holiday2 : holidays) {
				mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
			}
		}

		Date startDate = StringUtil.StringDateToDate(start);
		Date endDate = StringUtil.StringDateToDate(end);

		// 时间，周几，节假日判断
		List<Date> dates = StringUtil.getBetweenDates(startDate, endDate);
		// 时间段1
		if (!StringUtil.isEmpty(time1) && !StringUtil.isEmpty(time2) && !StringUtil.isEmpty(timeRedio1)
				&& "1".equals(timeRedio1)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}

		// 时间段2
		if (!StringUtil.isEmpty(time3) && !StringUtil.isEmpty(time4) && !StringUtil.isEmpty(timeRedio2)
				&& "1".equals(timeRedio2)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}

		// 时间段3
		if (!StringUtil.isEmpty(time5) && !StringUtil.isEmpty(time6) && !StringUtil.isEmpty(timeRedio3)
				&& "1".equals(timeRedio3)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}
		Map returnMap = new HashMap();
		returnMap.put("message","ok");
		returnMap.put("status",0);
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}
	
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping(value = "/checkTime2", method = { RequestMethod.POST, RequestMethod.GET })
	public String checkTime2( HttpServletRequest request, RedirectAttributes redirectAttributes) {
	
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		String week1 = request.getParameter("week1");
		String week2 = request.getParameter("week2");
		String week3 = request.getParameter("week3");
		String week4 = request.getParameter("week4");
		String week5 = request.getParameter("week5");
		String week6 = request.getParameter("week6");
		String week7 = request.getParameter("week7");
		String id = request.getParameter("id");
		Long idl=Long.parseLong(id);
		Course course=courseService.find(idl);
		Map map = new HashMap();
		if (!StringUtil.isEmpty(week1) && "1".equals(week1)) {
			map.put(week1, week1);
		}
		if (!StringUtil.isEmpty(week2) && "2".equals(week2)) {
			map.put(week2, week2);
		}
		if (!StringUtil.isEmpty(week3) && "3".equals(week3)) {
			map.put(week3, week3);
		}
		if (!StringUtil.isEmpty(week4) && "4".equals(week4)) {
			map.put(week4, week4);
		}
		if (!StringUtil.isEmpty(week5) && "5".equals(week5)) {
			map.put(week5, week5);
		}
		if (!StringUtil.isEmpty(week6) && "6".equals(week6)) {
			map.put(week6, week6);
		}
		if (!StringUtil.isEmpty(week7) && "7".equals(week7)) {
			map.put(week7, week7);
		}

		String timeRedio1 = request.getParameter("timeRedio1");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");

		String timeRedio2 = request.getParameter("timeRedio2");
		String time3 = request.getParameter("time3");
		String time4 = request.getParameter("time4");

		String timeRedio3 = request.getParameter("timeRedio3");
		String time5 = request.getParameter("time5");
		String time6 = request.getParameter("time6");

		String holiday = request.getParameter("holiday");
		List<Holiday> holidays = holidayService.findAll();

		Map mapholidays = new HashMap();
		if (holidays != null) {
			for (Holiday holiday2 : holidays) {
				mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
			}
		}

		Date startDate = StringUtil.StringDateToDate(start);
		Date endDate = StringUtil.StringDateToDate(end);

		// 时间，周几，节假日判断
		List<Date> dates = StringUtil.getBetweenDates(startDate, endDate);
		// 时间段1
		if (!StringUtil.isEmpty(time1) && !StringUtil.isEmpty(time2) && !StringUtil.isEmpty(timeRedio1)
				&& "1".equals(timeRedio1)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}

		// 时间段2
		if (!StringUtil.isEmpty(time3) && !StringUtil.isEmpty(time4) && !StringUtil.isEmpty(timeRedio2)
				&& "1".equals(timeRedio2)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}

		// 时间段3
		if (!StringUtil.isEmpty(time5) && !StringUtil.isEmpty(time6) && !StringUtil.isEmpty(timeRedio3)
				&& "1".equals(timeRedio3)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}
		Map returnMap = new HashMap();
		returnMap.put("message","ok");
		returnMap.put("status",0);
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping(value = "/checkTime3", method = { RequestMethod.POST, RequestMethod.GET })
	public String checkTime3( HttpServletRequest request, RedirectAttributes redirectAttributes) {
	
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		String week1 = request.getParameter("week1");
		String week2 = request.getParameter("week2");
		String week3 = request.getParameter("week3");
		String week4 = request.getParameter("week4");
		String week5 = request.getParameter("week5");
		String week6 = request.getParameter("week6");
		String week7 = request.getParameter("week7");
		String orderId = request.getParameter("orderId");
		Long orderIdl=Long.parseLong(orderId);
		
	Order order=	orderService.find(orderIdl);
		Course course=courseService.find(order.getCourseId());
		Map map = new HashMap();
		if (!StringUtil.isEmpty(week1) && "1".equals(week1)) {
			map.put(week1, week1);
		}
		if (!StringUtil.isEmpty(week2) && "2".equals(week2)) {
			map.put(week2, week2);
		}
		if (!StringUtil.isEmpty(week3) && "3".equals(week3)) {
			map.put(week3, week3);
		}
		if (!StringUtil.isEmpty(week4) && "4".equals(week4)) {
			map.put(week4, week4);
		}
		if (!StringUtil.isEmpty(week5) && "5".equals(week5)) {
			map.put(week5, week5);
		}
		if (!StringUtil.isEmpty(week6) && "6".equals(week6)) {
			map.put(week6, week6);
		}
		if (!StringUtil.isEmpty(week7) && "7".equals(week7)) {
			map.put(week7, week7);
		}

		String timeRedio1 = request.getParameter("timeRedio1");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");

		String timeRedio2 = request.getParameter("timeRedio2");
		String time3 = request.getParameter("time3");
		String time4 = request.getParameter("time4");

		String timeRedio3 = request.getParameter("timeRedio3");
		String time5 = request.getParameter("time5");
		String time6 = request.getParameter("time6");

		String holiday = request.getParameter("holiday");
		List<Holiday> holidays = holidayService.findAll();

		Map mapholidays = new HashMap();
		if (holidays != null) {
			for (Holiday holiday2 : holidays) {
				mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
			}
		}

		Date startDate = StringUtil.StringDateToDate(start);
		Date endDate = StringUtil.StringDateToDate(end);

		// 时间，周几，节假日判断
		List<Date> dates = StringUtil.getBetweenDates(startDate, endDate);
		// 时间段1
		if (!StringUtil.isEmpty(time1) && !StringUtil.isEmpty(time2) && !StringUtil.isEmpty(timeRedio1)
				&& "1".equals(timeRedio1)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}

		// 时间段2
		if (!StringUtil.isEmpty(time3) && !StringUtil.isEmpty(time4) && !StringUtil.isEmpty(timeRedio2)
				&& "1".equals(timeRedio2)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}

		// 时间段3
		if (!StringUtil.isEmpty(time5) && !StringUtil.isEmpty(time6) && !StringUtil.isEmpty(timeRedio3)
				&& "1".equals(timeRedio3)) {
			for (Date date2 : dates) {

				// 判断date2是周几。这周几是否被勾选了
				if (map.containsKey(StringUtil.getWeekOfDate2(date2))) {
					// 判断date2是否是节假日
					if ("1".equals(holiday)) {
						// 不用判断节假日
						String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
						if (s != null) {
							return s;
						}
						s = checkClassRoomTime(date2, time1, time2, course.getClassId());
						if (s != null) {
							return s;
						}
					} else {
						// 判断节假日
						if (!map.containsKey(StringUtil.date2String(date2))) {
							String s = this.checkCourseTime(date2, time1, time2, course.getTeacherId());
							if (s != null) {
								return s;
							}
							s = checkClassRoomTime(date2, time1, time2, course.getClassId());
							if (s != null) {
								return s;
							}
						}
					}
				}
			}
		}
		Map returnMap = new HashMap();
		returnMap.put("message","ok");
		returnMap.put("status",0);
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}

	

	private String checkCourseTime(Date date, String time1, String time2, Long teacherId) {


		Map	params2 = new HashMap();
		params2.put("teacherId", teacherId);
		params2.put("teachertime1", Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		List<CourseTeaTime> courseTeaTimes = courseTeaTimeService.findList(params2);
		if (courseTeaTimes != null && courseTeaTimes.size() > 0) {
			System.err.println("courseTimes1:"+ReturnJson.jsonString("数据",courseTeaTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 1);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}

		params2 = new HashMap();
		params2.put("teacherId", teacherId);
		params2.put("teachertime1", Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));
		courseTeaTimes = courseTeaTimeService.findList(params2);
		if (courseTeaTimes != null && courseTeaTimes.size() > 0) {
			System.err.println("courseTimes2:"+ReturnJson.jsonString("数据",courseTeaTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 1);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}
		
		
		params2 = new HashMap();
		params2.put("teacherId", teacherId);
		params2.put("teachertime2", Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		List<CourseTime>  courseTimes = courseTimeService.findByParams2(params2);
		if (courseTimes != null && courseTimes.size() > 0) {
			System.err.println("courseTimes3:"+ReturnJson.jsonString("数据",courseTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 1);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}


		
		params2 = new HashMap();
		params2.put("teacherId", teacherId);
		params2.put("teachertime2", Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));
		courseTimes = courseTimeService.findByParams2(params2);
		if (courseTimes != null && courseTimes.size() > 0) {
			System.err.println("courseTimes4:"+ReturnJson.jsonString("数据",courseTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 1);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}


		

		return null;
	}

	private String checkClassRoomTime(Date date, String time1, String time2, Long cloassId) {

		Map params = new HashMap();
		params.put("classId", cloassId);
		params.put("teachertime2", Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		List<CourseTeaTime>	courseTeaTimes = courseTeaTimeService.findByParams2(params);
		if (courseTeaTimes != null && courseTeaTimes.size() > 0) {
		
			System.err.println("l1:"+ReturnJson.jsonString("数据",courseTeaTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 3);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}

		params = new HashMap();
		params.put("classId", cloassId);
		params.put("teachertime2", Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));

		courseTeaTimes = courseTeaTimeService.findByParams2(params);
		if (courseTeaTimes != null && courseTeaTimes.size() > 0) {
		
			System.err.println("l2:"+ReturnJson.jsonString("数据",courseTeaTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 3);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}
		
		
		Map	params2 = new HashMap();
		params2.put("classId", cloassId);
		params2.put("teachertime2", Long.parseLong(StringUtil.date2String2(date) + time1.replace(":", "")));
		List<CourseTime>  courseTimes = courseTimeService.findByParams3(params2);
		if (courseTimes != null && courseTimes.size() > 0) {
			System.err.println("courseTimes3:"+ReturnJson.jsonString("数据",courseTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 3);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}


		
		params2 = new HashMap();
		params2.put("classId", cloassId);
		params2.put("teachertime2", Long.parseLong(StringUtil.date2String2(date) + time2.replace(":", "")));
		courseTimes = courseTimeService.findByParams3(params2);
		if (courseTimes != null && courseTimes.size() > 0) {
			System.err.println("courseTimes4:"+ReturnJson.jsonString("数据",courseTimes, AppConstants.OK));
			Map returnMap = new HashMap();
			returnMap.put("message",
					StringUtil.date2String(date) + " " + time1 + "至" + StringUtil.date2String(date) + " " + time2);
			returnMap.put("status", 3);
			JSONObject json = JSONObject.fromObject(returnMap);
			return json.toString();
		}

		
		
		

		return null;
	}

}
