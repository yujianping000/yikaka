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

import com.framework.loippi.dao.CourseMakeupRecordDao;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseBookRecord;
import com.framework.loippi.entity.CourseChangeRecord;
import com.framework.loippi.entity.CourseMakeup;
import com.framework.loippi.entity.CourseMakeupRecord;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.CourseMakeupRecordService;
import com.framework.loippi.service.CourseMakeupService;
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
 * Controller - 补课申请
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCourseMakeupController")
@RequestMapping({ "/admin/course_makeup" })
public class CourseMakeupController extends GenericController {

	@Resource
	private CourseMakeupService courseMakeupService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private CourseMakeupRecordService courseMakeupRecordService;

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
		
		return "/admin/course_makeup/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,CourseMakeup courseMakeup, RedirectAttributes redirectAttributes) {
		
		String account = request.getParameter("account");
		String studentName = request.getParameter("studentName");
		String courseId = request.getParameter("courseId");
		String sex = request.getParameter("sex");
		
		if(!StringUtil.isEmpty(account)){
			Parent parent = parentService.find("account",account);
			if(parent != null){
				courseMakeup.setParentId(parent.getId());
				courseMakeup.setName(parent.getRelName());
				
				if(!StringUtil.isEmpty(studentName)) {
					Map map = new HashMap<>();
					map.put("account", account);
					map.put("nickname", studentName);
					
					List<Student> studentList = studentService.findList(map);
					if(studentList!=null&&studentList.size()>0){
					Student student = studentList.get(0);
					courseMakeup.setStudentId(student.getId());
					courseMakeup.setStudentName(student.getNickname());
					}
				}
			}
		}
		
		if(!StringUtil.isEmpty(courseId)){
			Course course = courseService.find(Long.parseLong(courseId));
			if(course != null){
				courseMakeup.setCourseName(course.getName());
			}
		}
		
		if(!StringUtil.isEmpty(sex)){
				courseMakeup.setSex(sex);
		}
		
		User user = userService.getCurrent();
		if(user != null) {
			courseMakeup.setOrgId(user.getOrgId());
		}
		courseMakeup.setCreateTime(new Date());
		
		courseMakeupService.save(courseMakeup);
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
		
		CourseMakeup courseMakeup = courseMakeupService.find(id);
		model.addAttribute("courseMakeup", courseMakeup);
		return "/admin/course_makeup/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Pageable pageable,@PathVariable Long id, ModelMap model) {
		Map map = new HashMap<>();
		map.put("makeupId", id);
		pageable.setParameter(map);
		
		Page<CourseMakeupRecord> courseMakeList = this.courseMakeupRecordService.findByPage(pageable);
		model.addAttribute("page", courseMakeList);
		
		model.addAttribute("courseMakeupId", id);
		
		return "/admin/course_makeup/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,CourseMakeup courseMakeup, RedirectAttributes redirectAttributes) {
		
		String courseId = request.getParameter("courseId");
		
		if(!StringUtil.isEmpty(courseId)){
			Course course = courseService.find(Long.parseLong(courseId));
			if(course != null){
				courseMakeup.setCourseName(course.getName());
			}
		}
		
		Course course = courseService.find("name", courseMakeup.getCourseName());
		if(course != null) {
			courseMakeup.setCourseId(course.getId());
		}
		
		courseMakeupService.update(courseMakeup);
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
		model.addAttribute("page", this.courseMakeupService.findByPage(pageable));
		return "/admin/course_makeup/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.courseMakeupService.deleteAll(ids);
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
	 * 删除补课记录
	 */
	@RequestMapping(value = { "/deleteCourseMakeup" }, method={RequestMethod.POST})
	@ResponseBody
	public Message deleteCourseMakeup(Long id) {
		
		List<CourseMakeupRecord> CourseMakeupRecordList = courseMakeupRecordService.findList("makeupId", id);
		if(CourseMakeupRecordList != null) {
			for (CourseMakeupRecord courseMakeupRecord : CourseMakeupRecordList) {
				courseMakeupRecordService.delete(courseMakeupRecord.getId());
			}
		}
		
		this.courseMakeupService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 新增补课跟进记录
	 */
	@RequestMapping(value = { "/addCourseMakeupRecord/{id}" }, method = { RequestMethod.GET })
	public String addCourseMakeupRecord(@PathVariable Long id,ModelMap model) {
		
		model.addAttribute("makeupId", id);
		return "/admin/course_makeup/addCourseMakeupRecord";
	}
	
	/**
	 * 保存补课跟进记录
	 */
	@RequestMapping(value = "/saveCourseMakeupRecord", method = RequestMethod.POST)
	public String saveCourseMakeupRecord(HttpServletRequest request,CourseMakeupRecord courseMakeupRecord, RedirectAttributes redirectAttributes) {
		
		String makeupId = request.getParameter("makeupId");
		
		courseMakeupRecord.setCreateTime(new Date());
		courseMakeupRecord.setMakeupId(Long.parseLong(makeupId));
		
		courseMakeupRecordService.save(courseMakeupRecord);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/course_makeup/view/"+Long.parseLong(makeupId)+".jhtml";
	}
	
	/**
	 * 编辑补课跟进记录
	 */
	@RequestMapping(value = "/editCourseMakeupRecord/{id}", method = RequestMethod.GET)
	public String editCourseMakeupRecord(@PathVariable Long id, ModelMap model) {
		CourseMakeupRecord courseMakeupRecord = courseMakeupRecordService.find(id);
		model.addAttribute("courseMakeupRecord", courseMakeupRecord);
		return "/admin/course_makeup/editCourseMakeupRecord";
	}
	
	/**
	 * 更新补课跟进记录
	 */
	@RequestMapping(value = "/updateCourseMakeupRecord", method = RequestMethod.POST)
	public String updateCourseMakeupRecord(HttpServletRequest request,CourseMakeupRecord courseMakeupRecord, RedirectAttributes redirectAttributes) {
		
		String makeupId = request.getParameter("makeupId");
		
		courseMakeupRecordService.update(courseMakeupRecord);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		return "redirect:/admin/course_makeup/view/"+Long.parseLong(makeupId)+".jhtml";
		
	}
	
	/**
	 * 删除补课跟进记录
	 */
	@RequestMapping(value = { "/deleteCourseMakeupRecord" }, method={RequestMethod.POST})
	@ResponseBody
	public Message deleteCourseMakeupRecord(Long id) {
		this.courseMakeupRecordService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
}
