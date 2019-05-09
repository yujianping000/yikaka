package com.framework.loippi.controller.admin;
import java.text.ParseException;
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
import com.framework.loippi.entity.CourseBookRecord;
import com.framework.loippi.entity.CourseChange;
import com.framework.loippi.entity.CourseChangeRecord;
import com.framework.loippi.entity.CourseMakeupRecord;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.CourseChangeRecordService;
import com.framework.loippi.service.CourseChangeService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 调班申请
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCourseChangeController")
@RequestMapping({ "/admin/course_change" })
public class CourseChangeController extends GenericController {

	@Resource
	private CourseChangeService courseChangeService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private CourseChangeRecordService courseChangeRecordService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		
		User user = userService.getCurrent();
		if(user != null) {
			List<Course> courseList = courseService.findList("orgId",user.getOrgId());
			model.addAttribute("courseList", courseList);
		}
		
		return "/admin/course_change/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,CourseChange courseChange, RedirectAttributes redirectAttributes) {
		
		String account = request.getParameter("account");
		String studentName = request.getParameter("studentName");
		String courseId = request.getParameter("courseId");
		String sex = request.getParameter("sex");
		
		if(!StringUtil.isEmpty(account)){
			Parent parent = parentService.find("account",account);
			if(parent != null){
				courseChange.setParentId(parent.getId());
				courseChange.setName(parent.getRelName());
				
				if(!StringUtil.isEmpty(studentName)) {
					Map map = new HashMap<>();
					map.put("account", account);
					map.put("nickname", studentName);
					
					List<Student> studentList = studentService.findList(map);
					if(studentList!=null&&studentList.size()>0){
					Student student = studentList.get(0);
					courseChange.setStudentId(student.getId());
					courseChange.setStudentName(student.getNickname());
					}
				}
			}
		}
		
		if(!StringUtil.isEmpty(courseId)){
			Course course = courseService.find(Long.parseLong(courseId));
			if(course != null){
				courseChange.setCourseName(course.getName());
			}
		}
		
		if(!StringUtil.isEmpty(sex)){
			courseChange.setSex(sex);
		}
		
		User user = userService.getCurrent();
		if(user != null) {
			courseChange.setOrgId(user.getOrgId());
		}
		courseChange.setCreateTime(new Date());
		
		courseChangeService.save(courseChange);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		
		User user = userService.getCurrent();
		if(user != null) {
			List<Course> courseList = courseService.findList("orgId",user.getOrgId());
			model.addAttribute("courseList", courseList);
		}
		
		CourseChange courseChange = courseChangeService.find(id);
		model.addAttribute("courseChange", courseChange);
		return "/admin/course_change/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Pageable pageable,@PathVariable Long id, ModelMap model) {
		Map map = new HashMap<>();
		map.put("changeId", id);
		pageable.setParameter(map);
		
		Page<CourseChangeRecord> courseChangeList = this.courseChangeRecordService.findByPage(pageable);
		model.addAttribute("page", courseChangeList);
		
		model.addAttribute("courseChangeId", id);
		
		return "/admin/course_change/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,CourseChange courseChange, RedirectAttributes redirectAttributes) {
		
		String courseId = request.getParameter("courseId");
		
		if(!StringUtil.isEmpty(courseId)){
			Course course = courseService.find(Long.parseLong(courseId));
			if(course != null){
				courseChange.setCourseName(course.getName());
			}
		}
		
		Course course = courseService.find("name", courseChange.getCourseName());
		if(course != null) {
			courseChange.setCourseId(course.getId());
		}
		
		courseChangeService.update(courseChange);
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
		
	  Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		
		Map paramter2 = RequestMap(request);
		pageable.setParameter(paramter2);
		
		model.addAttribute("paramter", paramter);
		
		model.addAttribute("page", this.courseChangeService.findByPage(pageable));
		return "/admin/course_change/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.courseChangeService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 *过滤 
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();
		
		User user = userService.getCurrent();
		if(user != null) {
			paramter.put("orgId", user.getOrgId());
		}
		
		String filter_account = request.getParameter("filter_account");
		String filter_courseName = request.getParameter("filter_courseName");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_courseName)) {
			paramter.put("filter_courseName", StringUtil.filterSQL(filter_courseName));
		}
		
		if(!StringUtil.isEmpty(filter_sDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_sDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_sDate = (new SimpleDateFormat("yyyyMMdd")).format(date);
			
			paramter.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
		}
		
		if(!StringUtil.isEmpty(filter_eDate)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(filter_eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filter_eDate = (new SimpleDateFormat("yyyyMMdd")).format(date);
			
			paramter.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		return paramter;
	}
	
	/**
	 * 删除调班记录
	 */
	@RequestMapping(value = { "/deleteCourseChange" }, method={RequestMethod.POST})
	@ResponseBody
	public Message deleteCourseChange(Long id) {
		
		List<CourseChangeRecord> CourseChangeRecordList = courseChangeRecordService.findList("change_id",id);
		if(CourseChangeRecordList != null) {
			for (CourseChangeRecord courseChangeRecord : CourseChangeRecordList) {
				courseChangeRecordService.delete(courseChangeRecord.getId());
			}
		}
		this.courseChangeService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 新增调班跟进记录
	 */
	@RequestMapping(value = { "/addCourseChangeRecord/{id}" }, method = { RequestMethod.GET })
	public String addCourseChangeRecord(@PathVariable Long id,ModelMap model) {
		
		model.addAttribute("changeId",id);
		return "/admin/course_change/addCourseChangeRecord";
	}
	
	/**
	 * 保存调班跟进记录
	 */
	@RequestMapping(value = "/saveCourseChangeRecord", method = RequestMethod.POST)
	public String saveCourseChangeRecord(HttpServletRequest request,CourseChangeRecord courseChangeRecord, RedirectAttributes redirectAttributes) {
		
		String changeId = request.getParameter("changeId");
		
		courseChangeRecord.setCreateTime(new Date());
		courseChangeRecord.setChangeId(Long.parseLong(changeId));
		
		courseChangeRecordService.save(courseChangeRecord);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/course_change/view/"+Long.parseLong(changeId)+".jhtml";
	}
	
	/**
	 * 编辑调班跟进记录
	 */
	@RequestMapping(value = "/editCourseChangeRecord/{id}", method = RequestMethod.GET)
	public String editCourseChangeRecord(@PathVariable Long id, ModelMap model) {
		
		CourseChangeRecord courseChangeRecord = courseChangeRecordService.find(id);
		model.addAttribute("courseChangeRecord", courseChangeRecord);
		return "/admin/course_change/editCourseChangeRecord";
	}
	
	/**
	 * 更新调班跟进记录
	 */
	@RequestMapping(value = "/updateCourseChangeRecord", method = RequestMethod.POST)
	public String updateCourseChangeRecord(HttpServletRequest request,CourseChangeRecord courseChangeRecord, RedirectAttributes redirectAttributes) {
		
		String changeId = request.getParameter("changeId");
		
		courseChangeRecordService.update(courseChangeRecord);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		return "redirect:/admin/course_change/view/"+Long.parseLong(changeId)+".jhtml";
	}
	
	
	/**
	 * 删除调班跟进记录
	 */
	@RequestMapping(value = { "/deleteCourseChangeRecord" }, method={RequestMethod.POST})
	@ResponseBody
	public Message deleteCourseChangeRecord(Long id) {
		this.courseChangeRecordService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
}
