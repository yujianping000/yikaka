package com.framework.loippi.controller.admin;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.ParameterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.OffWork;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTimeService;
import com.framework.loippi.service.OffWorkService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import java.util.HashMap;
import java.util.List;

/**
 * Controller - 请假
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminOffWorkController")
@RequestMapping({ "/admin/off_work" })
public class OffWorkController extends GenericController {

	@Resource
	private OffWorkService offWorkService;
	@Resource
	private CourseTimeService courseTimeService;
	@Resource
	private CourseService courseService;
	@Resource
	private UserService userService;
	
	@Resource
	private CourseStuTimeService courseStuTimeService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private StudentService studentService;
	
	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/off_work/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(OffWork offWork, RedirectAttributes redirectAttributes) {
		offWorkService.save(offWork);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		OffWork offWork = offWorkService.find(id);
		model.addAttribute("offWork", offWork);
		return "/admin/off_work/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		OffWork offWork = offWorkService.find(id);
		model.addAttribute("offWork", offWork);
		return "/admin/off_work/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(OffWork offWork, RedirectAttributes redirectAttributes) {
		offWorkService.update(offWork);
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
		User user=userService.getCurrent();
		Long orgId=user.getOrgId();
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        } 
		  if(orgId!=null){
				map.put("orgId",  orgId);
			}
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.offWorkService.findByPage(pageable));
		return "/admin/off_work/list";
	}
	/**
	 *确认请假天数页面
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/showPass" }, method = { RequestMethod.GET })
	public String  showPass(HttpServletRequest request, ModelMap model,Long id) {
		OffWork offWork = offWorkService.find(id);
		model.addAttribute("offWork", offWork);
		return "/admin/off_work/viewPass";
	}
	
	/**
	 *拒绝原因页面
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/refusePage" }, method = { RequestMethod.GET })
	public String  refusePage(HttpServletRequest request, ModelMap model,Long id) {
		OffWork offWork = offWorkService.find(id);
		model.addAttribute("offWork", offWork);
		return "/admin/off_work/refusePage";
	}

	
	/**
	 *查看详情页面
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/viewDetails" }, method = { RequestMethod.GET })
	public String  viewDetails(HttpServletRequest request, ModelMap model,Long id) {
		OffWork offWork = offWorkService.find(id);
		model.addAttribute("offWork", offWork);
		String timeids = offWork.getHour();
		Map map1 = new HashMap();
		map1.put("timeIds", timeids);
		List<String> coursetineTchs = courseTimeService.findCourseTime3(map1);
		model.addAttribute("coursetineTchs", coursetineTchs);
		Course course=courseService.find(offWork.getCourseId());
		model.addAttribute("courseName", course.getName());
		if(!StringUtil.isEmpty(offWork.getType())){
			model.addAttribute("typeName", getLeaveName((Integer.parseInt(offWork.getType()))));
		}
	
		
		return "/admin/off_work/view";
	}
	
	/**
	 * 确认操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmPass" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmPass(Long id,Integer dayNum) {
		OffWork offWork=offWorkService.find(id);
		offWork.setStatus(1);
		offWork.setDayNum(dayNum);
		offWorkService.update(offWork);
		   Student student=studentService.find(offWork.getStuid());
		   if(student!=null){
		   JpushRunnable t1 = new JpushRunnable("你的请假已经通过，请到请假记录查看!", "p"+student.getUid(), "type",
  				Constants.offwork+"_"+offWork.getId() + "");
   		   t1.start();
		   }
		String times=offWork.getHour();
		if(times!=null){
			String[] timess=times.split(",");
			for(String hour:timess){
				 CourseStuTime courseStuTime=courseStuTimeService.find(Long.parseLong(hour));
				 if(courseStuTime!=null){
					 courseStuTime.setStatus(3);
					 courseStuTimeService.update(courseStuTime);
				 }
				
			}
		}
		
		
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 拒绝操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/affirmRefuse" }, method = { RequestMethod.GET })
	public @ResponseBody Message affirmRefuse(Long id,String reason) {
		OffWork offWork=offWorkService.find(id);
		offWork.setStatus(3);
		offWork.setReason(reason);
		offWorkService.update(offWork);
		
		   Student student=studentService.find(offWork.getStuid());
		   if(student!=null){
		   JpushRunnable t1 = new JpushRunnable("你的请假已经拒绝，请到请假记录查看!", "p"+student.getUid(), "type",
  				Constants.offwork+"_"+offWork.getId() + "");
   		   t1.start();
		   }
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.offWorkService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	private String getLeaveName(Integer type){
		 String typeName="";
		  switch(type){
		  case 1:typeName="事假";
		  break;
		  case 2:typeName="病假";
		  break;
		  case 3:typeName="年假";
		  break;
		  case 4:typeName="调休";
		  break;
		  case 5:typeName="婚假";
		  break;
		  case 6:typeName="产假";
		  break;
		  case 7:typeName="陪产假";
		  break;
		  case 8:typeName="丧假";
		  break;
		  case 9:typeName="其他";
		  break;
		  }
		return typeName;
	}
}
