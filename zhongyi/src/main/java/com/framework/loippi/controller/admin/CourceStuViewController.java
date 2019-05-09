package com.framework.loippi.controller.admin;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import com.framework.loippi.utils.ParameterUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;
import com.framework.loippi.controller.app.model.AppCourseTime;
import com.framework.loippi.entity.CourceStuView;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.User;
import com.framework.loippi.mybatis.paginator.domain.Order.Direction;
import com.framework.loippi.service.CourceStuViewService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 课程查询学员
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminCourceStuViewController")
@RequestMapping({ "/admin/cource_stu_view" })
public class CourceStuViewController extends GenericController {
	@Resource
	private UserService userService;
	@Resource
	private CourceStuViewService courceStuViewService;
	@Resource
	private CourseTimeService courseTimeService;
	@Resource
	private CourseStuTimeService courseStuTimeService;
	@Resource
	private CourseService courseService;
	
	@Resource
	private OrderService orderService;
	@Resource
	private OrgDynamicService orgDynamicService;
	@Resource
	private UserUnreadService userUnreadService;
	@Resource
	private ParentService parentService;
	@Resource
	private StudentService studentService;
	@Resource
	private OrgUserService orgUserService;
	
	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/cource_stu_view/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(CourceStuView courceStuView, RedirectAttributes redirectAttributes) {
		courceStuViewService.save(courceStuView);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		CourceStuView courceStuView = courceStuViewService.find(id);
		model.addAttribute("courceStuView", courceStuView);
		return "/admin/cource_stu_view/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		CourceStuView courceStuView = courceStuViewService.find(id);
		model.addAttribute("courceStuView", courceStuView);
		List<AppCourseTime> appCourseTimes1=new ArrayList();	
		List<AppCourseTime> appCourseTimes2=new ArrayList();
		List<AppCourseTime> appCourseTimes3=new ArrayList();	
		List<AppCourseTime> appCourseTimes4=new ArrayList();	
		List<AppCourseTime> appCourseTimes5=new ArrayList();	

		
	
		String nowtime=StringUtil.date2String3(new Date());
		//未开始时间
		Map parameter=new HashMap();
		parameter.put("orderId", courceStuView.getId());
			parameter.put("order", " course_time3");
			parameter.put("courseId", courceStuView.getTargetId());
			parameter.put("pageSize3",Long.parseLong(nowtime));
	
			List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
			if(courseStuTimes!=null){
				for (CourseStuTime courseStuTime : courseStuTimes) {
					AppCourseTime dto2=new AppCourseTime();
	 				BeanUtils.copyProperties(courseStuTime, dto2);
	 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
	 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
	 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
	 				appCourseTimes1.add(dto2);
			}
			}
			model.addAttribute("courseTime1", appCourseTimes1);
				//待评价
				parameter=new HashMap();
 				parameter.put("courseId",courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());
 				parameter.put("status", 2);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("nocomment", "1");
 				parameter.put("pageSize4",Long.parseLong(nowtime) );
 				parameter.put("order", " course_time3 desc");
 				List<CourseStuTime> courseStuTimes2=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes2!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes2) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes2.add(dto2);
					}
 				}
 				model.addAttribute("courseTime2", appCourseTimes2);
 				
 				
 				//已结束,已评价
 				parameter=new HashMap();
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("order", " course_time3 desc");
 				 parameter.put("hadcomment", "1");
 				parameter.put("status", 4);
  				parameter.put("courseId", courceStuView.getTargetId());
  				 parameter.put("timestudentId",courceStuView.getStuId());		
 				parameter.put("pageSize4",Long.parseLong(nowtime));
 			
 				List<CourseStuTime> courseStuTimes3=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes3!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes3) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes3.add(dto2);
					}
 				}
 				model.addAttribute("courseTime3", appCourseTimes3);
 				
 				//请假
 				parameter=new HashMap();
 				parameter.put("order", " course_time3 desc");
 				parameter.put("status", 3);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("pageSize4",Long.parseLong(nowtime));
  				parameter.put("courseId", courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());		
 				
 				List<CourseStuTime> courseStuTimes4=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes4!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes4) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes4.add(dto2);
					}
 				}
 				model.addAttribute("courseTime4", appCourseTimes4);

 				//旷课
 				  long time = 30*60*1000;//30分钟
 				  Date now = new Date();
 				  Date beforeDate = new Date(now .getTime() - time);//30分钟前的时间
 				
 				
 				parameter=new HashMap();
 				parameter.put("order", " course_time3 desc");
 				parameter.put("status", 1);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(beforeDate)) );
 			
  				parameter.put("courseId", courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());
 				
 				
 				List<CourseStuTime> courseStuTimes5=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes5!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes5) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes5.add(dto2);
					}
 				}
 

 				model.addAttribute("courseTime5", appCourseTimes5);

		
		
		
		return "/admin/cource_stu_view/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CourceStuView courceStuView, RedirectAttributes redirectAttributes) {
		courceStuViewService.update(courceStuView);
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
		  
		  Map<String, Object> paramter2 = new HashMap<>();
			
			String filter_account = request.getParameter("filter_account");
			String filter_stuName = request.getParameter("filter_stuName");
			String filter_sDate = request.getParameter("filter_sDate");
			String filter_eDate = request.getParameter("filter_eDate");
			
			if(!StringUtil.isEmpty(filter_account)) {
				paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
			}
			
			if(!StringUtil.isEmpty(filter_stuName)) {
				paramter2.put("filter_stuName", StringUtil.filterSQL(filter_stuName));
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
				
				paramter2.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
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
				
				paramter2.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
			}
  
			pageable.setOrderDirection(Direction.DESC);
			pageable.setOrderProperty("created_time");
			paramter2.put("targetId", id);
		pageable.setParameter(paramter2);
		model.addAttribute("id", id);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.courceStuViewService.findByPage(pageable));
		return "/admin/cource_stu_view/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.courceStuViewService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/vieworg/{id}", method = RequestMethod.GET)
	public String vieworg(@PathVariable Long id, ModelMap model) {
		CourceStuView courceStuView = courceStuViewService.find(id);
		model.addAttribute("courceStuView", courceStuView);
		List<AppCourseTime> appCourseTimes1=new ArrayList();	
		List<AppCourseTime> appCourseTimes2=new ArrayList();
		List<AppCourseTime> appCourseTimes3=new ArrayList();	
		List<AppCourseTime> appCourseTimes4=new ArrayList();	
		List<AppCourseTime> appCourseTimes5=new ArrayList();	

		
	
		String nowtime=StringUtil.date2String3(new Date());
		//未开始时间
		Map parameter=new HashMap();
		parameter.put("orderId", courceStuView.getId());
			parameter.put("order", " course_time3");
			parameter.put("courseId", courceStuView.getTargetId());
			parameter.put("pageSize3",Long.parseLong(nowtime));
	
			List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
			if(courseStuTimes!=null){
				for (CourseStuTime courseStuTime : courseStuTimes) {
					AppCourseTime dto2=new AppCourseTime();
	 				BeanUtils.copyProperties(courseStuTime, dto2);
	 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
	 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
	 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
	 				appCourseTimes1.add(dto2);
			}
			}
			model.addAttribute("courseTime1", appCourseTimes1);
				//待评价
				parameter=new HashMap();
 				parameter.put("courseId",courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());
 				parameter.put("status", 2);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("nocomment", "1");
 				parameter.put("pageSize4",Long.parseLong(nowtime) );
 				parameter.put("order", " course_time3 desc");
 				List<CourseStuTime> courseStuTimes2=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes2!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes2) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes2.add(dto2);
					}
 				}
 				model.addAttribute("courseTime2", appCourseTimes2);
 				
 				
 				//已结束,已评价
 				parameter=new HashMap();
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("order", " course_time3 desc");
 				 parameter.put("hadcomment", "1");
 				parameter.put("status", 4);
  				parameter.put("courseId", courceStuView.getTargetId());
  				 parameter.put("timestudentId",courceStuView.getStuId());		
 				parameter.put("pageSize4",Long.parseLong(nowtime));
 			
 				List<CourseStuTime> courseStuTimes3=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes3!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes3) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes3.add(dto2);
					}
 				}
 				model.addAttribute("courseTime3", appCourseTimes3);
 				
 				//请假
 				parameter=new HashMap();
 				parameter.put("order", " course_time3 desc");
 				parameter.put("status", 3);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("pageSize4",Long.parseLong(nowtime));
  				parameter.put("courseId", courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());		
 				
 				List<CourseStuTime> courseStuTimes4=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes4!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes4) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes4.add(dto2);
					}
 				}
 				model.addAttribute("courseTime4", appCourseTimes4);

 				//旷课
 				  long time = 30*60*1000;//30分钟
 				  Date now = new Date();
 				  Date beforeDate = new Date(now .getTime() - time);//30分钟前的时间
 				
 				
 				parameter=new HashMap();
 				parameter.put("order", " course_time desc");
 				parameter.put("status", 1);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(beforeDate)) );
 			
  				parameter.put("courseId", courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());
 				
 				
 				List<CourseStuTime> courseStuTimes5=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes5!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes5) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes5.add(dto2);
					}
 				}
 

 				model.addAttribute("courseTime5", appCourseTimes5);

		
		
		
		return "/admin/cource_stu_view/vieworg";
	}
	
	

	/**
	 * 学员板块详情
	 */
	@RequestMapping(value = "/vieworgss/{id}", method = RequestMethod.GET)
	public String vieworgss(@PathVariable Long id, ModelMap model) {
		CourceStuView courceStuView = courceStuViewService.find(id);
		model.addAttribute("courceStuView", courceStuView);
		List<AppCourseTime> appCourseTimes1=new ArrayList();	
		List<AppCourseTime> appCourseTimes2=new ArrayList();
		List<AppCourseTime> appCourseTimes3=new ArrayList();	
		List<AppCourseTime> appCourseTimes4=new ArrayList();	
		List<AppCourseTime> appCourseTimes5=new ArrayList();	

		
	
		String nowtime=StringUtil.date2String3(new Date());
		//未开始时间
		Map parameter=new HashMap();
		parameter.put("orderId", courceStuView.getId());
			parameter.put("order", " course_time3");
			parameter.put("courseId", courceStuView.getTargetId());
			parameter.put("pageSize3",Long.parseLong(nowtime));
	
			List<CourseStuTime> courseStuTimes=courseStuTimeService.findListByPage(parameter);
			if(courseStuTimes!=null){
				for (CourseStuTime courseStuTime : courseStuTimes) {
					AppCourseTime dto2=new AppCourseTime();
	 				BeanUtils.copyProperties(courseStuTime, dto2);
	 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
	 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
	 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
	 				appCourseTimes1.add(dto2);
			}
			}
			model.addAttribute("courseTime1", appCourseTimes1);
				//待评价
				parameter=new HashMap();
 				parameter.put("courseId",courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());
 				parameter.put("status", 2);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("nocomment", "1");
 				parameter.put("pageSize4",Long.parseLong(nowtime) );
 				parameter.put("order", " course_time3 desc");
 				List<CourseStuTime> courseStuTimes2=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes2!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes2) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes2.add(dto2);
					}
 				}
 				model.addAttribute("courseTime2", appCourseTimes2);
 				
 				
 				//已结束,已评价
 				parameter=new HashMap();
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("order", " course_time3 desc");
 				 parameter.put("hadcomment", "1");
 				parameter.put("status", 4);
  				parameter.put("courseId", courceStuView.getTargetId());
  				 parameter.put("timestudentId",courceStuView.getStuId());		
 				parameter.put("pageSize4",Long.parseLong(nowtime));
 			
 				List<CourseStuTime> courseStuTimes3=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes3!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes3) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes3.add(dto2);
					}
 				}
 				model.addAttribute("courseTime3", appCourseTimes3);
 				
 				//请假
 				parameter=new HashMap();
 				parameter.put("order", " course_time3 desc");
 				parameter.put("status", 3);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("pageSize4",Long.parseLong(nowtime));
  				parameter.put("courseId", courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());		
 				
 				List<CourseStuTime> courseStuTimes4=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes4!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes4) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes4.add(dto2);
					}
 				}
 				model.addAttribute("courseTime4", appCourseTimes4);

 				//旷课
 				  long time = 30*60*1000;//30分钟
 				  Date now = new Date();
 				  Date beforeDate = new Date(now .getTime() - time);//30分钟前的时间
 				
 				
 				parameter=new HashMap();
 				parameter.put("order", " course_time desc");
 				parameter.put("status", 1);
				parameter.put("orderId", courceStuView.getId());
 				parameter.put("pageSize4",Long.parseLong(StringUtil.date2String3(beforeDate)) );
 			
  				parameter.put("courseId", courceStuView.getTargetId());
 				parameter.put("timestudentId",courceStuView.getStuId());
 				
 				
 				List<CourseStuTime> courseStuTimes5=courseStuTimeService.findListByPage(parameter);
 				if(courseStuTimes5!=null){
 					for (CourseStuTime courseStuTime : courseStuTimes5) {
 						AppCourseTime dto2=new AppCourseTime();
 		 				BeanUtils.copyProperties(courseStuTime, dto2);
 		 				dto2.setCourseTimeString(StringUtil.date2String13(courseStuTime.getCourseTime())
 		 						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				dto2.setTime(courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2());
 		 				appCourseTimes5.add(dto2);
					}
 				}
 

 				model.addAttribute("courseTime5", appCourseTimes5);

		
		
		
		return "/admin/cource_stu_view/vieworgss";
	}
	
	
	
	
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updateorg", method = RequestMethod.POST)
	public String updateorg(CourceStuView courceStuView, RedirectAttributes redirectAttributes) {
		courceStuViewService.update(courceStuView);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	
	
	/**
	 * 学员板块
	 * @param id
	 * @param pageable
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listorgss/{id}" }, method = { RequestMethod.GET })
	
  public String listorgss(@PathVariable Long id,Pageable pageable,HttpServletRequest request, ModelMap model){
		//User user = userService.getCurrent();
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		  
		  Map<String, Object> paramter2 = new HashMap<>();
			
			String filter_account = request.getParameter("filter_account");
			String filter_nickName = request.getParameter("filter_nickName");
			String filter_stuName = request.getParameter("filter_stuName");
			/*String filter_sDate = request.getParameter("filter_sDate");
			String filter_eDate = request.getParameter("filter_eDate");*/
			String filter_openclass = request.getParameter("filter_openclass");
			
			
			if(!StringUtil.isEmpty(filter_account)) {
				paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
			}
			
			if(!StringUtil.isEmpty(filter_nickName)) {
				paramter2.put("filter_nickName", StringUtil.filterSQL(filter_nickName));
			}
			
			if(!StringUtil.isEmpty(filter_stuName)) {
				paramter2.put("filter_stuName", StringUtil.filterSQL(filter_stuName));
			}
			
			
			
			if(!StringUtil.isEmpty(filter_openclass)) {
                
				
				paramter2.put("openclass", Integer.parseInt(StringUtil.filterSQL(filter_openclass)));
			}
			
			pageable.setOrderDirection(Direction.DESC);
			pageable.setOrderProperty("created_time");
			paramter2.put("targetId", id);
		pageable.setParameter(paramter2);
		model.addAttribute("id", id);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.courceStuViewService.findByPage(pageable));
		return "/admin/cource_stu_view/listorgss";
		
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
		  
		  Map<String, Object> paramter2 = new HashMap<>();
		  String filter_status = request.getParameter("filter_status");
			String filter_account = request.getParameter("filter_account");
			String filter_nickName = request.getParameter("filter_nickName");
			String filter_stuName = request.getParameter("filter_stuName");
			
			String filter_openclass = request.getParameter("filter_openclass");
			
			
			if(!StringUtil.isEmpty(filter_account)) {
				paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
			}
			
			if(!StringUtil.isEmpty(filter_nickName)) {
				paramter2.put("filter_nickName", StringUtil.filterSQL(filter_nickName));
			}
			
			if(!StringUtil.isEmpty(filter_stuName)) {
				paramter2.put("filter_stuName", StringUtil.filterSQL(filter_stuName));
			}
			
			
			if(!StringUtil.isEmpty(filter_openclass)) {
                
				
				paramter2.put("openclass", Integer.parseInt(StringUtil.filterSQL(filter_openclass)));
			}
			  if(!StringUtil.isEmpty(filter_status)) {
	              
					
					paramter2.put("status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
				}
			pageable.setOrderDirection(Direction.DESC);
			pageable.setOrderProperty("created_time");
			paramter2.put("targetId", id);
		pageable.setParameter(paramter2);
		model.addAttribute("id", id);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.courceStuViewService.findByPage(pageable));
		
		return "/admin/cource_stu_view/listorg";
	}
	
	/**
	 * 
	 *学员批量导入
	 * 
	 */
	@RequestMapping(value = {"/piliang/{id}"}, method = { RequestMethod.POST })
	public String piliang(@PathVariable Long id , MultipartFile batchFileMessage){
		
		InputStream fis =  null;
		Workbook workbook = null;
		try {
			//获得文件流
			fis =  batchFileMessage.getInputStream();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//判断文件类型 创建工作簿对象
			if(batchFileMessage.getOriginalFilename().endsWith(".xls")){
				//2003版本的excel，用.xls结尾
				workbook = new HSSFWorkbook(fis);
			}else{
				//2007版本的excel，用.xlsx结尾
				workbook = new XSSFWorkbook(fis);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获得工作表
		Sheet sheet = workbook.getSheetAt(0);
		//遍历获得每行数据
		for(int i = 2 ; i<= sheet.getLastRowNum() ; i++) {
			//获得当前机构信息
			User user = userService.getCurrent();
			
			//CourceStuView courceStuView = new CourceStuView();
			//创建订单对象
			Order order = new Order();
			//创建家长对象
			Parent parent = new Parent();
			//创建学生对象
			Student student = new Student();
			
			OrgUser  orgUser = new OrgUser();
					
			org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
			
			if(row == null){
				break;
			}
			
			org.apache.poi.ss.usermodel.Cell cell = row.getCell(0);
			if(cell != null){
				cell.setCellType(cell.CELL_TYPE_STRING);
				if(cell.getStringCellValue() != "" && cell.getStringCellValue() != null){
					//courceStuView.setAccount(row.getCell(0).getStringCellValue());
					parent.setAccount(row.getCell(0).getStringCellValue());
					student.setAccount(row.getCell(0).getStringCellValue());
				}
			}
			
			org.apache.poi.ss.usermodel.Cell cell1 = row.getCell(1);
			if(cell1 != null){
				cell1.setCellType(cell.CELL_TYPE_STRING);
				if(cell1.getStringCellValue() != "" && cell1.getStringCellValue() != null){
					//courceStuView.setNickName(row.getCell(1).getStringCellValue());
					parent.setNickName(row.getCell(1).getStringCellValue());
					student.setPnickname(row.getCell(1).getStringCellValue());
				}
			}
			
			
			org.apache.poi.ss.usermodel.Cell cell2 = row.getCell(2);
			if(cell2 != null){
				cell2.setCellType(cell.CELL_TYPE_STRING);
				if(cell2.getStringCellValue() != "" && cell2.getStringCellValue() != null){
					//courceStuView.setPhone(row.getCell(2).getStringCellValue());
					order.setPhone(row.getCell(2).getStringCellValue());
				}
			}
			
			org.apache.poi.ss.usermodel.Cell cell3 = row.getCell(3);
			if(cell3 != null){
				cell3.setCellType(cell.CELL_TYPE_STRING);
				if(cell3.getStringCellValue() != "" && cell3.getStringCellValue() != null){
					//courceStuView.setStuName(row.getCell(3).getStringCellValue());
					order.setStuName(row.getCell(3).getStringCellValue());
					student.setNickname(row.getCell(3).getStringCellValue());
				}
			}
			
			org.apache.poi.ss.usermodel.Cell cell4 = row.getCell(4);
			if(cell4 != null){
				cell4.setCellType(cell.CELL_TYPE_STRING);
				if(cell4.getStringCellValue() != "" && cell4.getStringCellValue() != null){
					//courceStuView.setStuSex(row.getCell(4).getStringCellValue());
					order.setStuSex(row.getCell(4).getStringCellValue());
					student.setSex(row.getCell(4).getStringCellValue());
				}
			}
			
			org.apache.poi.ss.usermodel.Cell cell5 = row.getCell(5);
			if(cell5 != null){
				cell5.setCellType(cell.CELL_TYPE_STRING);
				if(cell5.getStringCellValue() != "" && cell5.getStringCellValue() != null){
					//courceStuView.setStuAvatar(row.getCell(5).getStringCellValue());
					order.setStuAvatar(row.getCell(5).getStringCellValue());
					student.setAvatar(row.getCell(5).getStringCellValue());
				}
			}
			
			//org.apache.poi.ss.usermodel.Cell cell6 = row.getCell(6);
			//cell6.setCellType(cell.CELL_TYPE_STRING);
			if(row.getCell(6).getDateCellValue() != null){
				/*try {
					
					row.getCell(6).getDateCellValue();
					
					
					System.out.println(row.getCell(6).getStringCellValue());
					String[] split = row.getCell(6).getStringCellValue().split("/");
					StringBuffer sb = new StringBuffer();
					for(int s = 0; i < split.length; s++){
					 sb. append(split[i]);
					}
					String s1 = sb.toString();
					
					courceStuView.setStuBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(s1));
				} catch (ParseException e) {
					e.printStackTrace();
				}*/
				//courceStuView.setStuBirthday(row.getCell(6).getDateCellValue());
				order.setStuBirthday(row.getCell(6).getDateCellValue());
				student.setBirtchday(row.getCell(6).getDateCellValue());
			}
//			org.apache.poi.ss.usermodel.Cell cell7 = row.getCell(7);
//			cell7.setCellType(cell.CELL_TYPE_STRING);
			if(row.getCell(7).getDateCellValue() != null){
				/*try {
					String[] split = row.getCell(7).getStringCellValue().split("/");
					StringBuffer sb = new StringBuffer();
					for(int s = 0; i < split.length; s++){
					 sb. append(split[i]);
					}
					String s1 = sb.toString();

					courceStuView.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd").parse(s1));
				} catch (ParseException e) {
					e.printStackTrace();
				}*/
				//courceStuView.setCreatedTime(row.getCell(7).getDateCellValue());
				order.setCreatedTime(row.getCell(7).getDateCellValue());
				student.setCreateTime(row.getCell(7).getDateCellValue());
			}
			org.apache.poi.ss.usermodel.Cell cell8 = row.getCell(8);
			if(cell8 != null){
				cell8.setCellType(cell.CELL_TYPE_STRING);
				if(cell8.getStringCellValue() != "" && cell8.getStringCellValue() != null){
					//courceStuView.setClasstimes(Integer.valueOf(row.getCell(8).getStringCellValue()));
					order.setClasstimes(Integer.valueOf(row.getCell(8).getStringCellValue()));
				}
			}
			
			org.apache.poi.ss.usermodel.Cell cell9 = row.getCell(9);
			if(cell9 != null){
				cell9.setCellType(cell.CELL_TYPE_STRING);
				if(cell9.getStringCellValue() != "" && cell9.getStringCellValue() != null){
					//courceStuView.setTerm(Integer.valueOf(row.getCell(9).getStringCellValue()));
					order.setTerm(Integer.valueOf(row.getCell(9).getStringCellValue()));
				}
			}
			
			org.apache.poi.ss.usermodel.Cell cell10 = row.getCell(10);
			if(cell10 != null){
				cell10.setCellType(cell.CELL_TYPE_STRING);
				//if(cell10.getStringCellValue() != null){
					//courceStuView.setOpenclass(Integer.valueOf(row.getCell(10).getStringCellValue()));
				order.setOpenclass(Integer.valueOf(row.getCell(10).getStringCellValue()));
				//}
			}
			

			String uuid = com.framework.loippi.utils.UUIDGenerator.getUUID();
			
			parent.setUuid(uuid);
			
			parent.setPassword(DigestUtils.md5Hex("123456"));	
			parentService.save(parent);
			
			Long uid = parentService.getById(parent.getAccount());
			//System.out.println(row.getCell(0).getStringCellValue());
			
			student.setUid(uid);
			studentService.save(student);
			Long stuId = studentService.getById(parent.getAccount());
			
			Course course = courseService.find(id);
			order.setBn(StringUtil.orderSn());
			order.setCourseName(course.getName());
			order.setStatus(2);
			order.setUuid(uuid);
			order.setStuId(stuId);
			order.setUid(uid);
			order.setType(1);
			order.setTargetId(id);
			order.setCourseId(id);
			order.setOrgId(user.getOrgId());
			//order.setRemark("0");
			orderService.save(order);
			//添加机构用户
			
			
			orgUser.setOrgId(user.getOrgId());			
			orgUser.setType(1);
			orgUser.setUid(uid);
			orgUser.setCreateTime(new Date());
			orgUserService.save(orgUser);
			
			
			//courceStuViewService.save(courceStuView);
			//更新视图
			/*courceStuView.setTargetId(id);
			courceStuView.setUid(uid);
			courceStuView.setStuId(stuId);
			courceStuView.setId(orderService.getById(order.getUuid()));
			courceStuViewService.update(courceStuView);*/
			
			
			
		}
		try {
			fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:listorg.jhtml";
		//return "/admin/cource_stu_view/list";
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 开课
	 * 
	 * @param ids
	 * @return
	 */
//	@RequestMapping(value = { "/open" }, method = { RequestMethod.POST })
//	public @ResponseBody Message open(Long[] ids) {
//		if(ids!=null){
//			for (int i = 0; i < ids.length; i++) {
//				Order order=new Order();
//				order.setId(ids[i]);
//				order.setOpenclass(2);
//				orderService.update(order);
//				order=orderService.find(ids[i]);
//				//您的课程XXX已经开课了，上课时间为2016年09月09日12:00~13:00
//				Map map=new HashMap();
//				map.put("orderId", ids[i]);
//				map.put("order", "course_time3");
//				List<CourseStuTime>	courseStuTimes=courseStuTimeService.findListByPage(map);
//                 if(courseStuTimes!=null&&courseStuTimes.size()>0){
//                	 CourseStuTime courseStuTime=courseStuTimes.get(0);
//                	 String content="您的课程"+order.getTargetName()+"已经开课了，上课时间为"+StringUtil.date2String13(courseStuTime.getCourseTime())
//						+" "+courseStuTime.getCourseTime1()+"~"+courseStuTime.getCourseTime2();
//                	 OrgDynamic  orgDynamic=new OrgDynamic();
//    
//           		  orgDynamic.setTextContent(content);
//           		  orgDynamic.setPrompt("开课通知");
//           		  orgDynamic.setTargetId(ids[i]);
//           		  orgDynamic.setStarts(1);
//           		  orgDynamic.setUserType(1);
//           		 orgDynamic.setType(5);
//           		  orgDynamic.setUserId(order.getUid());
//           		  orgDynamicService.save(orgDynamic);
//                	 
//           		userUnreadService.setUserUnread(1, order.getUid(), 5);
//                	 
//				JpushRunnable t1 = new JpushRunnable(content,"p"+ order.getUid()
//				, "type", Constants.system+"_"+order.getId() + "",
//						this.mapService());
//				t1.start();
//                 }
//			}
//			
//			
//		}
//		return SUCCESS_MESSAGE;
//	}
//	
//	private Map mapService() {
//		Map map = new HashMap();
//
//		return map;
//
//	}
//	
}
