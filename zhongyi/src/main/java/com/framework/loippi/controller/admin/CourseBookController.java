package com.framework.loippi.controller.admin;

import java.text.ParseException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseBook;
import com.framework.loippi.entity.CourseBookRecord;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.User;
import com.framework.loippi.entity.WithdrawView;
import com.framework.loippi.service.CourseBookRecordService;
import com.framework.loippi.service.CourseBookService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 课程预约试听
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCourseBookController")
@RequestMapping({ "/admin/course_book" })
public class CourseBookController extends GenericController {

	@Resource
	private CourseBookService courseBookService;

	@Resource
	private StudentService studentService;

	@Resource
	private UserService userService;

	@Resource
	private CourseService courseService;

	@Resource
	private ParentService parentService;

	@Resource
	private CourseBookRecordService courseBookRecordService;

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
		if (user != null) {
			List<Course> courseList = courseService.findList("orgId", user.getOrgId());
			model.addAttribute("courseList", courseList);
		}
		return "/admin/course_book/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, CourseBook courseBook, RedirectAttributes redirectAttributes) {

		String parentAccount = request.getParameter("parentAccount");
		String childName = request.getParameter("childName");
		String courseId = request.getParameter("courseId");
		String sex = request.getParameter("sex");

		if (!StringUtil.isEmpty(parentAccount)) {
			Parent parent = parentService.find("account", parentAccount);
			if (parent != null) {
				courseBook.setParentId(parent.getId());
				courseBook.setName(parent.getRelName());

				if (!StringUtil.isEmpty(childName)) {
					Map map = new HashMap<>();
					map.put("account", parentAccount);
					map.put("nickname", childName);

					List<Student> studentList = studentService.findList(map);
					if (studentList != null && studentList.size() > 0) {
						Student student = studentList.get(0);
						courseBook.setChildId(student.getId());
					}
				}
			}
		}

		if (!StringUtil.isEmpty(courseId)) {
			Course course = courseService.find(Long.parseLong(courseId));
			if (course != null) {
				courseBook.setCourseName(course.getName());
			}
		}

		if (!StringUtil.isEmpty(sex)) {
			courseBook.setSex(sex);
		}

		User user = userService.getCurrent();
		if (user != null) {
			courseBook.setOrgId(user.getOrgId());
		}
		courseBook.setCreateDate(new Date());

		courseBookService.save(courseBook);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {

		User user = userService.getCurrent();
		if (user != null) {
			List<Course> courseList = courseService.findList("orgId", user.getOrgId());
			model.addAttribute("courseList", courseList);
		}

		CourseBook courseBook = courseBookService.find(id);
		model.addAttribute("courseBook", courseBook);
		return "/admin/course_book/edit";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Pageable pageable, @PathVariable Long id, ModelMap model) {

		Map map = new HashMap<>();
		map.put("bookId", id);
		pageable.setParameter(map);

		Page<CourseBookRecord> courseBookList = this.courseBookRecordService.findByPage(pageable);
		model.addAttribute("page", courseBookList);

		model.addAttribute("courseBookId", id);
		return "/admin/course_book/view";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, CourseBook courseBook, RedirectAttributes redirectAttributes) {

		String courseId = request.getParameter("courseId");

		if (!StringUtil.isEmpty(courseId)) {
			Course course = courseService.find(Long.parseLong(courseId));
			if (course != null) {
				courseBook.setCourseName(course.getName());
			}
		}

		/*
		 * Map map1 = new HashMap<>(); map1.put("account",
		 * courseBook.getPhone()); map1.put("nick_name",
		 * courseBook.getParentAccount()); List<Parent> parentList =
		 * parentService.findList(map1); if(parentList != null) { Parent parent
		 * = parentList.get(0); courseBook.setParentId(parent.getId()); }
		 */

		/*
		 * Map map2 = new HashMap<>(); map2.put("account",
		 * courseBook.getPhone()); map2.put("nickname",
		 * courseBook.getChildName()); List<Student> studentList =
		 * studentService.findList(map2); if(studentList != null) { Student
		 * student = studentList.get(0); courseBook.setChildId(student.getId());
		 * }
		 */

		Course course = courseService.find("name", courseBook.getCourseName());
		if (course != null) {
			courseBook.setCourseId(course.getId());
		}

		courseBookService.update(courseBook);
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
	public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {

		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}

		Map paramter2 = RequestMap(request);
		pageable.setParameter(paramter2);

		model.addAttribute("paramter", paramter);

		Page<CourseBook> pages = this.courseBookService.findByPage(pageable);

		model.addAttribute("page", pages);
		return "/admin/course_book/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.courseBookService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 过滤
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();

		User user = userService.getCurrent();
		if (user != null) {
			paramter.put("orgId", user.getOrgId());
		}

		String filter_parentAccount = request.getParameter("filter_parentAccount");
		String filter_courseName = request.getParameter("filter_courseName");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");

		if (!StringUtil.isEmpty(filter_parentAccount)) {
			paramter.put("filter_parentAccount", StringUtil.filterSQL(filter_parentAccount));
		}

		if (!StringUtil.isEmpty(filter_courseName)) {
			paramter.put("filter_courseName", StringUtil.filterSQL(filter_courseName));
		}

		if (!StringUtil.isEmpty(filter_sDate)) {
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

		if (!StringUtil.isEmpty(filter_eDate)) {
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
	 * 删除课时记录
	 */
	@RequestMapping(value = { "/deleteCourseBook" }, method = { RequestMethod.POST })
	@ResponseBody
	public Message deleteCourseBook(Long id) {
		List<CourseBookRecord> courseBookRecordList = courseBookRecordService.findList("bookId", id);
		if (courseBookRecordList != null) {
			for (CourseBookRecord courseBookRecord : courseBookRecordList) {
				courseBookRecordService.delete(courseBookRecord.getId());
			}
		}

		this.courseBookService.delete(id);

		return SUCCESS_MESSAGE;
	}

	/**
	 * 新增试课跟进记录
	 */
	@RequestMapping(value = { "/addCourseBookRecord/{id}" }, method = { RequestMethod.GET })
	public String addCourseBookRecord(@PathVariable Long id, ModelMap model) {

		model.addAttribute("courseBookId", id);
		return "/admin/course_book/addCourseBookRecord";
	}

	/**
	 * 保存试课跟进记录
	 */
	@RequestMapping(value = "/saveCourseBookRecord", method = RequestMethod.POST)
	public String saveCourseBookRecord(HttpServletRequest request, CourseBookRecord courseBookRecord,
			RedirectAttributes redirectAttributes) {

		String courseBookId = request.getParameter("courseBookId");

		courseBookRecord.setCreateTime(new Date());
		courseBookRecord.setBookId(Long.parseLong(courseBookId));

		courseBookRecordService.save(courseBookRecord);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/course_book/view/" + Long.parseLong(courseBookId) + ".jhtml";
	}

	/**
	 * 编辑试课跟进记录
	 */
	@RequestMapping(value = "/editCourseBookRecord/{id}", method = RequestMethod.GET)
	public String editCourseBookRecord(@PathVariable Long id, ModelMap model) {

		CourseBookRecord courseBookRecord = courseBookRecordService.find(id);
		model.addAttribute("courseBookRecord", courseBookRecord);
		return "/admin/course_book/editCourseBookRecord";
	}

	/**
	 * 更新试课跟进记录
	 */
	@RequestMapping(value = "/updateCourseBookRecord", method = RequestMethod.POST)
	public String updateCourseBookRecord(HttpServletRequest request, CourseBookRecord courseBookRecord,
			RedirectAttributes redirectAttributes) {

		String bookId = request.getParameter("bookId");

		courseBookRecordService.update(courseBookRecord);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		return "redirect:/admin/course_book/view/" + Long.parseLong(bookId) + ".jhtml";

	}

	/**
	 * 删除试课跟进记录
	 */
	@RequestMapping(value = { "/deleteCourseBookRecord" }, method = { RequestMethod.POST })
	@ResponseBody
	public Message deleteCourseBookRecord(Long id) {
		this.courseBookRecordService.delete(id);

		return SUCCESS_MESSAGE;
	}

	/**
	 * 导出试课申请列表
	 */
	@RequestMapping(value = "tCourseBookExport", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView expenseExport(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map paramter = RequestMap(request);
		List<CourseBook> courseBookList = courseBookService.findListByPage(paramter);

		String fileName = "试课申请表格" + ".xls";

		return new ModelAndView(new ExcelView(fileName, fileName,
				new String[] { "createDateString", "parentAccount", "phone", "childName", "sex", "birthdayString", "courseId", "courseName", "remark" },
				new String[] { "申请时间", "账号", "联系方式", "孩子姓名", "孩子性别", "出生日期", "课程ID" , "课程名称" , "报班目的" },
				new Integer[] { 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000 }, null, courseBookList, null), model);

	}
}
