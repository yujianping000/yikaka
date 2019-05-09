package com.framework.loippi.controller.admin;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.entity.Holiday;
import com.framework.loippi.entity.Log;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.service.CourseRoomService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.HolidayService;
import com.framework.loippi.service.LogService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.support.Message.Type;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 学生上课时间安排
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCourseStuTimeController")
@RequestMapping({ "/admin/course_stu_time" })
public class CourseStuTimeController extends GenericController {

	@Resource
	private CourseStuTimeService courseStuTimeService;
	@Resource
	private OrderService orderService;
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
	private CourseTimeService courseTimeService;
	@Resource(name = "logServiceImpl")
	private LogService logService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(HttpServletRequest request,ModelMap model) {
		
		String orderId=request.getParameter("id");
		model.addAttribute("orderId", orderId);
		
		return "/admin/course_stu_time/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.GET,RequestMethod.POST})
	public String save(HttpServletRequest request,  RedirectAttributes redirectAttributes) {

		String orderId=	request.getParameter("orderId");
		String courseId=	request.getParameter("courseId");
		String start=	request.getParameter("start");
		String end=	request.getParameter("end");
		
		Order	order=orderService.find(Long.parseLong(orderId));
		if(order==null){
			addFlashMessage(redirectAttributes, new Message(Type.error, "订单不存在", null));

			return "redirect:/admin/cource_stu_view/listorg/"+courseId+".jhtml";
		}
		
		Course course=	courseService.find(order.getTargetId());
		
		if(course==null){
			addFlashMessage(redirectAttributes, new Message(Type.error, "课程不存在", null));

			return "redirect:/admin/cource_stu_view/listorg/"+courseId+".jhtml";
		}
		
	String week1=	request.getParameter("week1");
	String week2=	request.getParameter("week2");
	String week3=	request.getParameter("week3");
	String week4=	request.getParameter("week4");
	String week5=	request.getParameter("week5");
	String week6=	request.getParameter("week6");
	String week7=	request.getParameter("week7");
	Map map=new HashMap();
	if(!StringUtil.isEmpty(week1)&&"1".equals(week1)){
		map.put(week1, week1);
	}
	if(!StringUtil.isEmpty(week2)&&"2".equals(week2)){
		map.put(week2, week2);
	}
	if(!StringUtil.isEmpty(week3)&&"3".equals(week3)){
		map.put(week3, week3);
	}
	if(!StringUtil.isEmpty(week4)&&"4".equals(week4)){
		map.put(week4, week4);
	}
	if(!StringUtil.isEmpty(week5)&&"5".equals(week5)){
		map.put(week5, week5);
	}
	if(!StringUtil.isEmpty(week6)&&"6".equals(week6)){
		map.put(week6, week6);
	}
	if(!StringUtil.isEmpty(week7)&&"7".equals(week7)){
		map.put(week7, week7);
	}
	
	String timeRedio1=	request.getParameter("timeRedio1");
	String time1=	request.getParameter("time1");
	String time2=	request.getParameter("time2");	
	
	String timeRedio2=	request.getParameter("timeRedio2");
	String time3=	request.getParameter("time3");
	String time4=	request.getParameter("time4");	
	
	String timeRedio3=	request.getParameter("timeRedio3");
	String time5=	request.getParameter("time5");
	String time6=	request.getParameter("time6");	
	
	String holiday=	request.getParameter("holiday");
	List<Holiday> holidays=holidayService.findAll();

	Map mapholidays=new HashMap();
	if(holidays!=null){
		for (Holiday holiday2 : holidays) {
			mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
		}
	}
	
	Date startDate=StringUtil.StringDateToDate(start) ;
	Date endDate=StringUtil.StringDateToDate(end) ;
	
	//时间，周几，节假日判断
 	List<Date>  dates=StringUtil.getBetweenDates(startDate,endDate);
	//时间段1
	if(!StringUtil.isEmpty(time1)&&!StringUtil.isEmpty(time2)
			&&!StringUtil.isEmpty(timeRedio1)&&"1".equals(timeRedio1)){
	 	for (Date date2 : dates) {
	 		
	 		//判断date2是周几。这周几是否被勾选了
			if(map.containsKey(StringUtil.getWeekOfDate2(date2))){
				//判断date2是否是节假日
				if("1".equals(holiday)){
					//不用判断节假日
						this.setCourseTime(date2,time1,time2,order.getTerm(), 
								order.getTargetId(),order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
				}else{
					//判断节假日
					if(!map.containsKey(StringUtil.date2String(date2))){
						this.setCourseTime(date2,time1,time2,order.getTerm(), 
								order.getTargetId(),order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
					}
				}
			}
		}
	}
	
	
	//时间段2
	if(!StringUtil.isEmpty(time3)&&!StringUtil.isEmpty(time4)
			&&!StringUtil.isEmpty(timeRedio2)&&"1".equals(timeRedio2)){
	 	for (Date date2 : dates) {
	 		
	 		//判断date2是周几。这周几是否被勾选了
			if(map.containsKey(StringUtil.getWeekOfDate2(date2))){
				//判断date2是否是节假日
				if("1".equals(holiday)){
					//不用判断节假日
						this.setCourseTime(date2,time3,time4,order.getTerm(), 
								order.getTargetId(),order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
				}else{
					//判断节假日
					if(!map.containsKey(StringUtil.date2String(date2))){
						this.setCourseTime(date2,time3,time4,order.getTerm(), 
								order.getTargetId(),order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
					}
				}
			}
		}
	}
	
	//时间段3
	if(!StringUtil.isEmpty(time5)&&!StringUtil.isEmpty(time6)
			&&!StringUtil.isEmpty(timeRedio3)&&"1".equals(timeRedio3)){
	 	for (Date date2 : dates) {
	 		
	 		//判断date2是周几。这周几是否被勾选了
			if(map.containsKey(StringUtil.getWeekOfDate2(date2))){
				//判断date2是否是节假日
				if("1".equals(holiday)){
					//不用判断节假日
						this.setCourseTime(date2,time5,time6,order.getTerm(), 
								order.getTargetId(),order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
				}else{
					//判断节假日
					if(!map.containsKey(StringUtil.date2String(date2))){
						this.setCourseTime(date2,time5,time6,  order.getTerm(),
								order.getTargetId(),order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
					}
				}
			}
		}
	}
	
		
		
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		return "redirect:/admin/cource_stu_view/list/"+order.getTargetId()+".jhtml";
	}
	
	private void setCourseTime(Date date,String time1,String time2, 
			Integer term,Long courseId,Long orderId,Long stuId,Long orgId,Long teaId){
		CourseStuTime	courseStuTime=new CourseStuTime();
		courseStuTime.setOrderId(orderId);
		courseStuTime.setStatus(1);
		courseStuTime.setStudentId(stuId);
		courseStuTime.setCourseId(courseId);
		courseStuTime.setCourseTime(date);
		courseStuTime.setCourseTime1(time1);
		courseStuTime.setCourseTime2(time2);
		courseStuTime.setCourseTime3(Long.parseLong(StringUtil.date2String2(date)+time1.replace(":", "")));
		courseStuTime.setCourseTime4(Long.parseLong(StringUtil.date2String2(date)+time2.replace(":", "")));
		courseStuTime.setTerm(term);
		courseStuTime.setCreateTime(new Date());
		courseStuTimeService.save(courseStuTime);
		
		setTeaCourseTime(courseStuTime.getCourseTime(),courseStuTime.getCourseTime1()
				,courseStuTime.getCourseTime2(), courseStuTime.getTerm(),courseStuTime.getCourseId()
				,null,teaId,courseStuTime.getId(),orgId);
		
		
	}
	

	private void setTeaCourseTime(Date date,String time1,String time2, Integer term
			,Long courseId,Long orderId,Long teaId,Long courseTimeId,Long orgId){
		CourseTeaTime	courseTeaTime=new CourseTeaTime();
		courseTeaTime.setCourseTimeId(courseTimeId);
		courseTeaTime.setStatus(1);
		courseTeaTime.setCourseId(courseId);
		courseTeaTime.setCourseTime(date);
		courseTeaTime.setCourseTime1(time1);
		courseTeaTime.setCourseTime2(time2);
		courseTeaTime.setCourseTime3(Long.parseLong(StringUtil.date2String2(date)+time1.replace(":", "")));
		courseTeaTime.setCourseTime4(Long.parseLong(StringUtil.date2String2(date)+time2.replace(":", "")));
		courseTeaTime.setTerm(term);
		courseTeaTime.setTeacherId(teaId);
		courseTeaTime.setCourseTimeId(courseTimeId);
		courseTeaTime.setCreateTime(new Date());
		courseTeaTime.setOrgId(orgId);
		courseTeaTime.setCTime(date);
		//上课签到时间，设置30分钟
		Date date2 =StringUtil.StringDateToDate4(StringUtil.date2String2(date)+time1.replace(":", ""));
	      // 对 calendar 设置为 date 所定的日期  
		  Calendar calendar = Calendar.getInstance();  
	      calendar.setTime(date2);  
	      calendar.add(Calendar.MINUTE, -15);
		courseTeaTime.setCourseTimeLess(Long.parseLong(StringUtil.date2String3(calendar.getTime())));
		courseTeaTimeService.save(courseTeaTime);
	}



	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		CourseStuTime courseStuTime = courseStuTimeService.find(id);
		model.addAttribute("courseStuTime", courseStuTime);
		return "/admin/course_stu_time/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		CourseStuTime courseStuTime = courseStuTimeService.find(id);
		model.addAttribute("courseStuTime", courseStuTime);
		return "/admin/course_stu_time/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CourseStuTime courseStuTime, RedirectAttributes redirectAttributes) {
		courseStuTimeService.update(courseStuTime);
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
	@RequestMapping(value = { "/list/{id}" }, method = { RequestMethod.GET })
	public String list(@PathVariable Long id,Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		  

		  pageable.setOrderProperty("course_time3");
		  pageable.setPageSize(300);
		  map.put("orderId", id);  
		pageable.setParameter(map);
		model.addAttribute("id", id);
		
		
		
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.courseStuTimeService.findByPage(pageable));
		return "/admin/course_stu_time/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/list/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids,HttpServletRequest request) {
		String username = userService.getCurrentUsername();

		if(ids!=null){
			for (int i = 0; i < ids.length; i++) {
				
				
				
				CourseStuTime courseStuTime= courseStuTimeService.find("id", ids[i]);
				Long nowtime=Long.parseLong(StringUtil.date2String3(new Date()));
				if(courseStuTime.getCourseTime3()<nowtime){
					return new Message(Type.error, "过期时间禁止删除", null);
				}
				String operator = username;
				String ip = request.getRemoteAddr();
				Log log = new Log();
				log.setOperation("删除学生课程时间");
				log.setOperator(operator);
				log.setContent(JSONObject.fromObject(courseStuTime).toString());
				log.setParameter(JSONObject.fromObject(courseStuTime).toString());
				log.setIp(ip);
				log.setCreateDate(new Date());
				logService.save(log);
				
				Course	course=courseService.find(courseStuTime.getCourseId());
				
				if(course.getType()==1){
					Map map=new HashMap();
					map.put("courseId",courseStuTime.getCourseId());
					map.put("courseTimeId",courseStuTime.getId());
					List<CourseTeaTime> courseTeaTimes= courseTeaTimeService.findList(map);
					if(courseTeaTimes!=null&&courseTeaTimes.size()>0){
						log = new Log();
						log.setOperation("删除教师课程时间");
						log.setOperator(operator);
						log.setContent(JSONObject.fromObject(courseTeaTimes.get(0)).toString());
						log.setParameter(JSONObject.fromObject(courseTeaTimes.get(0)).toString());
						log.setIp(ip);
						log.setCreateDate(new Date());
						logService.save(log);
						courseTeaTimeService.delete(courseTeaTimes.get(0).getId());
					}
				}
				this.courseStuTimeService.deleteAll(ids[i]);
			}
			
			
	
		}
		
		return SUCCESS_MESSAGE;
	}
	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/listorg/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message deleteorg(Long[] ids,HttpServletRequest request) {
		String username = userService.getCurrentUsername();

		if(ids!=null){
			for (int i = 0; i < ids.length; i++) {
				
				
				CourseStuTime courseStuTime= courseStuTimeService.find("id", ids[i]);
				Long nowtime=Long.parseLong(StringUtil.date2String3(new Date()));
				if(courseStuTime.getCourseTime3()<nowtime){
					new Message(Type.error, "过期时间禁止删除", null);
				}
				String operator = username;
				String ip = request.getRemoteAddr();
				Log log = new Log();
				log.setOperation("删除学生课程时间");
				log.setOperator(operator);
				log.setContent(JSONObject.fromObject(courseStuTime).toString());
				log.setParameter(JSONObject.fromObject(courseStuTime).toString());
				log.setIp(ip);
				log.setCreateDate(new Date());
				logService.save(log);
				
				Course	course=courseService.find(courseStuTime.getCourseId());
				
				if(course.getType()==1){
					Map map=new HashMap();
					map.put("courseId",courseStuTime.getCourseId());
					map.put("courseTimeId",courseStuTime.getId());
					List<CourseTeaTime> courseTeaTimes= courseTeaTimeService.findList(map);
					if(courseTeaTimes!=null&&courseTeaTimes.size()>0){
						log = new Log();
						log.setOperation("删除教师课程时间");
						log.setOperator(operator);
						log.setContent(JSONObject.fromObject(courseTeaTimes.get(0)).toString());
						log.setParameter(JSONObject.fromObject(courseTeaTimes.get(0)).toString());
						log.setIp(ip);
						log.setCreateDate(new Date());
						logService.save(log);
						courseTeaTimeService.delete(courseTeaTimes.get(0).getId());
					}
				}
				this.courseStuTimeService.deleteAll(ids[i]);
			}
			
			
	
		}
		
		return SUCCESS_MESSAGE;
	}
	

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/addorg" }, method = { RequestMethod.GET })
	public String addorg(HttpServletRequest request,ModelMap model) {
		
		String orderId=request.getParameter("id");
		model.addAttribute("orderId", orderId);
		
		return "/admin/course_stu_time/addorg";
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveorg",  method = { RequestMethod.GET, RequestMethod.POST})
	public String saveorg(HttpServletRequest request,  RedirectAttributes redirectAttributes) {

		String orderId=	request.getParameter("orderId");
		
		String start=	request.getParameter("start");
		String end=	request.getParameter("end");
		
		Order	order=orderService.find(Long.parseLong(orderId));
		Course course=	courseService.find(order.getTargetId());
	String week1=	request.getParameter("week1");
	String week2=	request.getParameter("week2");
	String week3=	request.getParameter("week3");
	String week4=	request.getParameter("week4");
	String week5=	request.getParameter("week5");
	String week6=	request.getParameter("week6");
	String week7=	request.getParameter("week7");
	Map map=new HashMap();
	if(!StringUtil.isEmpty(week1)&&"1".equals(week1)){
		map.put(week1, week1);
	}
	if(!StringUtil.isEmpty(week2)&&"2".equals(week2)){
		map.put(week2, week2);
	}
	if(!StringUtil.isEmpty(week3)&&"3".equals(week3)){
		map.put(week3, week3);
	}
	if(!StringUtil.isEmpty(week4)&&"4".equals(week4)){
		map.put(week4, week4);
	}
	if(!StringUtil.isEmpty(week5)&&"5".equals(week5)){
		map.put(week5, week5);
	}
	if(!StringUtil.isEmpty(week6)&&"6".equals(week6)){
		map.put(week6, week6);
	}
	if(!StringUtil.isEmpty(week7)&&"7".equals(week7)){
		map.put(week7, week7);
	}
	
	String timeRedio1=	request.getParameter("timeRedio1");
	String time1=	request.getParameter("time1");
	String time2=	request.getParameter("time2");	
	
	String timeRedio2=	request.getParameter("timeRedio2");
	String time3=	request.getParameter("time3");
	String time4=	request.getParameter("time4");	
	
	String timeRedio3=	request.getParameter("timeRedio3");
	String time5=	request.getParameter("time5");
	String time6=	request.getParameter("time6");	
	
	String holiday=	request.getParameter("holiday");
	List<Holiday> holidays=holidayService.findAll();

	Map mapholidays=new HashMap();
	if(holidays!=null){
		for (Holiday holiday2 : holidays) {
			mapholidays.put(holiday2.getHoliday(), holiday2.getHoliday());
		}
	}
	
	Date startDate=StringUtil.StringDateToDate(start) ;
	Date endDate=StringUtil.StringDateToDate(end) ;
	
	//时间，周几，节假日判断
 	List<Date>  dates=StringUtil.getBetweenDates(startDate,endDate);
	//时间段1
	if(!StringUtil.isEmpty(time1)&&!StringUtil.isEmpty(time2)
			&&!StringUtil.isEmpty(timeRedio1)&&"1".equals(timeRedio1)){
	 	for (Date date2 : dates) {
	 		
	 		//判断date2是周几。这周几是否被勾选了
			if(map.containsKey(StringUtil.getWeekOfDate2(date2))){
				//判断date2是否是节假日
				if("1".equals(holiday)){
					//不用判断节假日
						this.setCourseTime(date2,time1,time2,order.getTerm(), 
								order.getTargetId(),order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
				}else{
					//判断节假日
					if(!map.containsKey(StringUtil.date2String(date2))){
						this.setCourseTime(date2,time1,time2,order.getTerm(),  order.getTargetId()
								,order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
					}
				}
			}
		}
	}
	
	
	//时间段2
	if(!StringUtil.isEmpty(time3)&&!StringUtil.isEmpty(time4)
			&&!StringUtil.isEmpty(timeRedio2)&&"1".equals(timeRedio2)){
	 	for (Date date2 : dates) {
	 		
	 		//判断date2是周几。这周几是否被勾选了
			if(map.containsKey(StringUtil.getWeekOfDate2(date2))){
				//判断date2是否是节假日
				if("1".equals(holiday)){
					//不用判断节假日
						this.setCourseTime(date2,time3,time4,order.getTerm(),  order.getTargetId()
								,order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
				}else{
					//判断节假日
					if(!map.containsKey(StringUtil.date2String(date2))){
						this.setCourseTime(date2,time3,time4,order.getTerm(),  order.getTargetId()
								,order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
					}
				}
			}
		}
	}
	
	//时间段3
	if(!StringUtil.isEmpty(time5)&&!StringUtil.isEmpty(time6)
			&&!StringUtil.isEmpty(timeRedio3)&&"1".equals(timeRedio3)){
	 	for (Date date2 : dates) {
	 		
	 		//判断date2是周几。这周几是否被勾选了
			if(map.containsKey(StringUtil.getWeekOfDate2(date2))){
				//判断date2是否是节假日
				if("1".equals(holiday)){
					//不用判断节假日
						this.setCourseTime(date2,time5,time6,order.getTerm(),  order.getTargetId()
								,order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
				}else{
					//判断节假日
					if(!map.containsKey(StringUtil.date2String(date2))){
						this.setCourseTime(date2,time5,time6,  order.getTerm(),  order.getTargetId()
								,order.getId(),order.getStuId(),order.getOrgId(),course.getTeacherId());
					}
				}
			}
		}
	}
	
		
		
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		return "redirect:/admin/cource_stu_view/listorg/"+order.getTargetId()+".jhtml";
	}
	



	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listorg/{id}" }, method = { RequestMethod.GET })
	public String listorg(@PathVariable Long id,Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		  
		   pageable.setOrderProperty("course_time3");
		  pageable.setPageSize(300);
		  map.put("orderId", id);  
		  pageable.setParameter(map);
		  model.addAttribute("id", id);
		
		
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.courseStuTimeService.findByPage(pageable));
		return "/admin/course_stu_time/listorg";
	}
	
	/**
	 * 考勤
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/lisa/{id}" }, method = { RequestMethod.GET })
	public String lisa(@PathVariable Long id,Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		  
		  
		  Map<String, Object> paramter2 = new HashMap<>();
		  String filter_status = request.getParameter("filter_status");
		  
		  if(!StringUtil.isEmpty(filter_status)) {
              paramter2.put("status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
			}
		  
		  pageable.setOrderProperty("course_time3");
		  pageable.setPageSize(300);
		  map.put("orderId", id);  
		  pageable.setParameter(map);
		  model.addAttribute("id", id);
		
		
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.courseStuTimeService.findByPage(pageable));
		return "/admin/course_stu_time/lisa";
	}

}
