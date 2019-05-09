package com.framework.loippi.controller.admin;
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

import com.framework.loippi.entity.CourseCommentRemark;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.StudentCommentRemark;
import com.framework.loippi.entity.TeacherCommentRemark;
import com.framework.loippi.service.CourseCommentRemarkService;
import com.framework.loippi.service.StudentCommentRemarkService;
import com.framework.loippi.service.TeacherCommentRemarkService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.support.Message.Type;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.XDigestUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 评论标签
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCommentRemarkController")
@RequestMapping({ "/admin/comment_remark" })
public class CommentRemarkController extends GenericController {

	@Resource
	private CourseCommentRemarkService courseCommentRemarkService;
	
	@Resource
	private TeacherCommentRemarkService teacherCommentRemarkService;
	
	@Resource
	private StudentCommentRemarkService studentCommentRemarkService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/comment_remark/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(CourseCommentRemark courseCommentRemark, RedirectAttributes redirectAttributes) {
		courseCommentRemarkService.save(courseCommentRemark);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		CourseCommentRemark courseCommentRemark = courseCommentRemarkService.find(id);
		model.addAttribute("courseCommentRemark", courseCommentRemark);
		return "/admin/comment_remark/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		CourseCommentRemark courseCommentRemark = courseCommentRemarkService.find(id);
		model.addAttribute("courseCommentRemark", courseCommentRemark);
		return "/admin/comment_remark/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CourseCommentRemark courseCommentRemark, RedirectAttributes redirectAttributes) {
		courseCommentRemarkService.update(courseCommentRemark);
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
	@RequestMapping(value = { "/list" },method={RequestMethod.GET,RequestMethod.POST})
	public String list(HttpServletRequest request,ModelMap model) {
		
		List<CourseCommentRemark> courseCommentRemarkList = this.courseCommentRemarkService.findAll();
		
		List<TeacherCommentRemark> teacherCommentRemarkList = this.teacherCommentRemarkService.findAll();
		
		List<StudentCommentRemark> studentCommentRemarkList = this.studentCommentRemarkService.findAll();
		
		model.addAttribute("courseCommentRemarkList", courseCommentRemarkList);
		model.addAttribute("teacherCommentRemarkList", teacherCommentRemarkList);
		model.addAttribute("studentCommentRemarkList", studentCommentRemarkList);
		
		return "/admin/comment_remark/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.courseCommentRemarkService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 删除课程评价
	 */
	@RequestMapping(value = { "/deleteCourseCommentRemark" }, method={RequestMethod.POST})
	@ResponseBody
	public Message deleteCourseCommentRemark(Long id) {
		this.courseCommentRemarkService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 删除教师评价
	 */
	@RequestMapping(value = { "/deleteTeacherCommentRemark" }, method = { RequestMethod.POST })
	@ResponseBody
	public Message deleteTeacherCommentRemark(Long id) {
		this.teacherCommentRemarkService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 删除学生评价
	 */
	@RequestMapping(value = { "/deleteStudentCommentRemark" }, method = { RequestMethod.POST })
	@ResponseBody
	public Message deleteStudentCommentRemark(Long id) {
		this.studentCommentRemarkService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 添加课程评价标签
	 */
	@RequestMapping(value = { "/addCourseCommentRemark" }, method = { RequestMethod.POST })
	public String addCourseCommentRemark(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String name = request .getParameter("courseCommentRemark_name");
		List<CourseCommentRemark> courseCommentRemarkList = courseCommentRemarkService.findAll();
		
		if(!StringUtil.isEmpty(name)) {
			if(courseCommentRemarkList.size() < 5) {
				CourseCommentRemark courseCommentRemark = new CourseCommentRemark();
				
				courseCommentRemark.setName(name);
				courseCommentRemarkService.save(courseCommentRemark);
				
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			} else {
				addFlashMessage(redirectAttributes, new Message(Type.warn, "标签个数不得超过5个", null));
			}
		}
			return "redirect:list.jhtml";
	}
	
	/**
	 * 添加教师评价标签
	 */
	@RequestMapping(value = { "/addTeacherCommentRemark" }, method = { RequestMethod.POST })
	public String addTeacherCommentRemark(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String name = request .getParameter("teacherCommentRemark_name");
		List<TeacherCommentRemark> teacherCommentRemarkList = teacherCommentRemarkService.findAll();
		
		if(!StringUtil.isEmpty(name)) {
			if(teacherCommentRemarkList.size() < 5) {
				TeacherCommentRemark teacherCommentRemark = new TeacherCommentRemark();
				
				teacherCommentRemark.setName(name);
				teacherCommentRemarkService.save(teacherCommentRemark);
				
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			} else {
				addFlashMessage(redirectAttributes, new Message(Type.warn, "标签个数不得超过5个", null));
			}
		}
			return "redirect:list.jhtml";
	}
	
	/**
	 * 添加学生评价标签
	 */
	@RequestMapping(value = { "/addStudentCommentRemark" }, method = { RequestMethod.POST })
	public String addStudentCommentRemark(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String name = request .getParameter("studentCommentRemark_name");
		List<StudentCommentRemark> studentCommentRemarkList = studentCommentRemarkService.findAll();
		
		if(!StringUtil.isEmpty(name)) {
			if(studentCommentRemarkList.size() < 5) {
				StudentCommentRemark studentCommentRemark = new StudentCommentRemark();
				
				studentCommentRemark.setName(name);
				studentCommentRemarkService.save(studentCommentRemark);
				
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			} else {
				addFlashMessage(redirectAttributes, new Message(Type.warn, "标签个数不得超过5个", null));
			}
		}
			return "redirect:list.jhtml";
	}
	
	/**
	 * 编辑课程评价标签
	 */
	@RequestMapping(value = { "/editCourseCommentRemark" }, method = { RequestMethod.POST })
	public String editCourseCommentRemark(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String id = request.getParameter("c_id");
		String name = request.getParameter("c_name");
		
		CourseCommentRemark courseCommentRemark = courseCommentRemarkService.find("id", id);
		if(courseCommentRemark != null) {
			if(!StringUtil.isEmpty(name)) {
				courseCommentRemark.setName(name);
				courseCommentRemarkService.update(courseCommentRemark);
				
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			}
		}
		
		return "redirect:list.jhtml";
	}
	
	/**
	 * 编辑教师评价标签
	 */
	@RequestMapping(value = { "/editTeacherCommentRemark" }, method = { RequestMethod.POST })
	public String editTeacherCommentRemark(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		
		TeacherCommentRemark teacherCommentRemark = teacherCommentRemarkService.find("id", id);
		if(teacherCommentRemark != null) {
			if(!StringUtil.isEmpty(name)) {
				teacherCommentRemark.setName(name);
				teacherCommentRemarkService.update(teacherCommentRemark);
				
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			}
		}
		
		return "redirect:list.jhtml";
	}
	
	/**
	 * 编辑学生评价标签
	 */
	@RequestMapping(value = { "/editStudentCommentRemark" }, method = { RequestMethod.POST })
	public String editStudentCommentRemark(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		String id = request.getParameter("s_id");
		String name = request.getParameter("s_name");
		
		StudentCommentRemark studentCommentRemark = studentCommentRemarkService.find("id", id);
		if(studentCommentRemark != null) {
			if(!StringUtil.isEmpty(name)) {
				studentCommentRemark.setName(name);
				studentCommentRemarkService.update(studentCommentRemark);
				
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			}
		}
		
		return "redirect:list.jhtml";
	}
	
}
