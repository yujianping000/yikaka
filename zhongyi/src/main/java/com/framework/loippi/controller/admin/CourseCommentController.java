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

import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.User;
import com.framework.loippi.mybatis.paginator.domain.Order.Direction;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;

/**
 * Controller - 课程评论
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCourseCommentController")
@RequestMapping({ "/admin/course_comment" })
public class CourseCommentController extends GenericController {

	@Resource
	private CourseCommentService courseCommentService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/course_comment/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(CourseComment courseComment, RedirectAttributes redirectAttributes) {
		courseCommentService.save(courseComment);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		CourseComment courseComment = courseCommentService.find(id);
		model.addAttribute("courseComment", courseComment);
		return "/admin/course_comment/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		CourseComment courseComment = courseCommentService.find(id);
		model.addAttribute("courseComment", courseComment);
		return "/admin/course_comment/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CourseComment courseComment, RedirectAttributes redirectAttributes) {
		courseCommentService.update(courseComment);
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
		

		Map<String, Object> paramter2 = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		  
		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		
		Map paramter=new HashMap();
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_content)) {
			paramter.put("filter_content", StringUtil.filterSQL(filter_content));
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
		
	  
		paramter.put("courseId", id);
	pageable.setParameter(paramter);
	model.addAttribute("paramter", paramter2);

		  pageable.setOrderDirection(Direction.DESC);
		  pageable.setOrderProperty("create_date");

		model.addAttribute("id", id);

		model.addAttribute("page", this.courseCommentService.findByPage(pageable));
		return "/admin/course_comment/list";
	}
	
	
	/**
	 * 列表查询 学员评价
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listorgcc/{id}" }, method = { RequestMethod.GET })
	public String listorgcc(@PathVariable Long id,Pageable pageable,HttpServletRequest request, ModelMap model) {
		

		Map<String, Object> paramter2 = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		  
		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		
		Map paramter=new HashMap();
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_content)) {
			paramter.put("filter_content", StringUtil.filterSQL(filter_content));
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
		
	  
		paramter.put("courseId", id);
	pageable.setParameter(paramter);
	model.addAttribute("paramter", paramter2);

		  pageable.setOrderDirection(Direction.DESC);
		  pageable.setOrderProperty("create_date");

		model.addAttribute("id", id);

		model.addAttribute("page", this.courseCommentService.findByPage(pageable));
		return "/admin/course_comment/listorgcc";
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
		

		Map<String, Object> paramter2 = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		  
		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		
		Map paramter=new HashMap();
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_content)) {
			paramter.put("filter_content", StringUtil.filterSQL(filter_content));
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
		
	  
		paramter.put("courseId", id);
	pageable.setParameter(paramter);
	model.addAttribute("paramter", paramter2);

		  pageable.setOrderDirection(Direction.DESC);
		  pageable.setOrderProperty("create_date");

		model.addAttribute("id", id);

		model.addAttribute("page", this.courseCommentService.findByPage(pageable));
		return "/admin/course_comment/listorg";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.courseCommentService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 启用
	 */
	@RequestMapping(value = "/use", method = RequestMethod.POST)
	public @ResponseBody Message use(Long[] ids) {
		for (Long id : ids) {
		CourseComment courseComment=	courseCommentService.find(id);
		courseComment.setStatus(1);
		courseCommentService.update(courseComment);
		
		courseCommentService.updateCourseCommentCount(courseComment.getCourseId());
		}
		
		return SUCCESS_MESSAGE;
	}
	
	
	/**
	 * 禁用
	 */
	@RequestMapping(value = "/deluse", method = RequestMethod.POST)
	public @ResponseBody Message deluse(Long[] ids) {
		for (Long id : ids) {
			
			CourseComment courseComment=	courseCommentService.find(id);
			courseComment.setStatus(2);
			courseCommentService.update(courseComment);
			courseCommentService.updateCourseCommentCount(courseComment.getCourseId());
		}
		
		return SUCCESS_MESSAGE;
	}
	
	
	
	//机构
}
