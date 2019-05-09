package com.framework.loippi.controller.admin;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.xalan.templates.ElemAttributeSet;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.CourseTimeComment;
import com.framework.loippi.entity.OrgTeacherview;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.entity.TeacherComment;
import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.entity.UserUnread;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseTimeCommentService;
import com.framework.loippi.service.OrgTeacherviewService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherCommentService;
import com.framework.loippi.service.TeacherExperienceService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.support.Message.Type;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.XDigestUtils;
import com.framework.loippi.utils.qiniu.FileServiceImpl;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.redis.JedisStringCache;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 教师表
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminTeacherController")
@RequestMapping({ "/admin/teacher" })
public class TeacherController extends GenericController {

	@Resource
	private TeacherService teacherService;
	
	@Resource
	private TeacherExperienceService teacherExperenceService;
	
	@Resource
	private CourseCommentService courseCommentService;
	
	@Resource
	private TeacherCommentService teacherCommentService;
	
	@Resource
	private TeacherExperienceService teacherExperienceService;

	@Resource 
	private TeacherAuthService teacherAuthService;
	
	@Resource
	private OrgnizationService orgnizationService;
	@Resource
	private UserUnreadService userUnreadService;
	
	@Resource
	private CourseTimeCommentService courseTimeCommentService;
	

    @Autowired
    protected JedisCache jedisCache;
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
		
		List<TeacherExperience> teacherExperienceList = new ArrayList<>();
		model.addAttribute("teacherExperienceList", teacherExperienceList);
		
		return "/admin/teacher/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.GET,RequestMethod.POST})
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		Teacher teacher = new Teacher();
		
		String account = request.getParameter("account");
		String avatar = request.getParameter("avatar");
		String status = request.getParameter("status");
		String teacherType = request.getParameter("teacherType");
		String birthday = request.getParameter("birthday");
		String nickname = request.getParameter("nickname");
		String description = request.getParameter("description");
		String mark1 = request.getParameter("mark1");
		String mark2 = request.getParameter("mark2");
		String mark3 = request.getParameter("mark3");
		String password = request.getParameter("password");
		
		String sex = request.getParameter("sex");
		
		
		//检查教师用户是否存在
		Teacher teacher2 = teacherService.find("account", account);
		if(teacher2 != null){
			addFlashMessage(redirectAttributes, new Message(Type.error, "该账号已存在，请使用其他账号", null));
			return "redirect:list.jhtml";
		}
		
		if(!StringUtil.isEmpty(account)){
			teacher.setAccount(account);
		};
		
		if(!StringUtil.isEmpty(avatar)){
			teacher.setAvatar(avatar);
		};
		
		if(!StringUtil.isEmpty(status)){
			teacher.setStatus(Integer.parseInt(status));
		};
		
		if(!StringUtil.isEmpty(teacherType)){
			teacher.setTeacherType(Integer.parseInt(teacherType));
		};
		
		if(!StringUtil.isEmpty(birthday)){
			teacher.setBirthday(StringUtil.StringDateToDate(birthday));
		};
		
		if(!StringUtil.isEmpty(nickname)){
			teacher.setNickname(nickname);
		};
		
		if(!StringUtil.isEmpty(description)){
			teacher.setDescription(description);
		};
		
		if(!StringUtil.isEmpty(sex)){
			teacher.setSex(sex);
		};
		
		String mark = "";
		
		if(!StringUtil.isEmpty(mark1)){
			mark = mark1 +"##";
			teacher.setMark1(mark1);
		};
		
		if(!StringUtil.isEmpty(mark2)){
			mark = mark + mark2 +"##";
			teacher.setMark2(mark2);
		};
		
		if(!StringUtil.isEmpty(mark3)){
			mark = mark + mark3 +"##";
			teacher.setMark3(mark3);
		};
		
		if(!StringUtil.isEmpty(password)){
			teacher.setPassword(DigestUtils.md5Hex(password));
		};
		
		teacher.setMark(mark);
		teacher.setFavStar(5);
		teacher.setJoinTime(new Date());
		teacher.setCreateDate(new Date());
		
		teacherService.save(teacher);
		
		
		setUnread(teacher);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 教师管理——编辑
	 */
	@RequestMapping(value = "/edit/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public String edit(@PathVariable Long id, ModelMap model) {
		Teacher teacher = teacherService.find(id);
		
		String[] markArray = teacher.getMark().split("##");
		
		if(markArray != null && markArray.length > 0) {
			if(markArray.length >= 1) {
				teacher.setMark1(markArray[0]);
				if(markArray.length >= 2) {
					teacher.setMark2(markArray[1]);
				}
				if(markArray.length >= 3) {
					teacher.setMark3(markArray[2]);
				}
			}
		}
		
		model.addAttribute("teacher", teacher);
		
		List<TeacherExperience> teacherExperienceList = teacherExperenceService.findList("teacherId", id);
		
		model.addAttribute("teacherExperienceList",teacherExperienceList);
		
		String token = FileServiceImpl.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
		return "/admin/teacher/edit";
	}
	
	
	/**
	 * 机构管理——教师——编辑
	 */
	@RequestMapping(value = "/edit/{id}/{orgId}", method={RequestMethod.GET,RequestMethod.POST})
	public String edit2(@PathVariable Long id,@PathVariable Long orgId, ModelMap model) {
		Teacher teacher = teacherService.find(id);
		
		String[] markArray = teacher.getMark().split("##");
		
		if(markArray != null && markArray.length > 0) {
			if(markArray.length >= 1) {
				teacher.setMark1(markArray[0]);
				if(markArray.length >= 2) {
					teacher.setMark2(markArray[1]);
				}
				if(markArray.length >= 3) {
					teacher.setMark3(markArray[2]);
				}
			}
		}
		
		model.addAttribute("teacher", teacher);
		
		List<TeacherExperience> teacherExperienceList = teacherExperenceService.findList("teacherId", id);
		
		model.addAttribute("teacherExperienceList",teacherExperienceList);
		
		String token = FileServiceImpl.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
		//由机构id是否存在判断编辑页面是由机构还是教师模块跳转过来
		model.addAttribute("orgId", orgId);
		
		return "/admin/teacher/edit";
	}
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		Teacher teacher = teacherService.find(id);
		
		String mark = teacher.getMark();
		String[] markString = mark.split("##");
		
		if(markString != null && markString.length >0) {
			if(markString.length >= 1) {
				teacher.setMark1(markString[0]);
				if(markString.length >= 2) {
					teacher.setMark2(markString[1]);
					if(markString.length >= 3) {
						teacher.setMark3(markString[2]);
					}
				}
			}
		}
		
		model.addAttribute("teacher", teacher);
		
		List<TeacherExperience> teacherExperenceList = teacherExperenceService.findList("teacherId",id);
		
		model.addAttribute("teacherExperenceList", teacherExperenceList);
		
		return "/admin/teacher/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Teacher teacher,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String mark = "";
		if(teacher.getMark1() != null && teacher.getMark1() != "") {
			mark = teacher.getMark1() +"##";
		}
		if(teacher.getMark2() != null && teacher.getMark2() != "") {
			mark = mark + teacher.getMark2() +"##";
		}
		if(teacher.getMark3() != null && teacher.getMark3() != "") {
			mark = mark + teacher.getMark3();
		}
		
		teacher.setMark(mark);
		
		teacherService.update(teacher);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		//由机构id判断是在机构模块更新教师还是在教师模块更新教师
		String orgId = request.getParameter("orgId");
		if(!StringUtil.isEmpty(orgId)) {
			return "redirect:/admin/orgnization/teacherlist/"+Long.parseLong(orgId)+".jhtml";
		}
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
		
		Page<Teacher> pages = this.teacherService.findByPage(pageable);
		
		List<Teacher> teacherList = pages.getContent();
		
		
		if(teacherList != null) {
			for (Teacher teacher : teacherList) {
				Integer teacherType1 = null;
				String orgName = "";
				String joinTimeList = "";
				
				//判断已入职教师所属机构
				List<TeacherAuth> teacherAuthList = teacherAuthService.findList("teacherId", teacher.getId());
				if(teacherAuthList != null && teacherAuthList.size() > 0) {
					
					for (TeacherAuth teacherAuth : teacherAuthList) {
						//全职教师
						if(teacherAuth.getType() != null && teacherAuth.getType() == 1) {
							teacherType1 = 1;
							
							Orgnization orgnization = orgnizationService.find("id", teacherAuth.getOrgId());
							if(orgnization != null) {
								orgName =  orgName + orgnization.getName() + ",";
							} 
							joinTimeList = joinTimeList + StringUtil.date2String4(teacherAuth.getUpdateTime()) + ",";
						}
							
						//兼职教师
						if(teacherAuth.getType() != null && teacherAuth.getType() == 2) {
							teacherType1 = 2;
							
							Orgnization orgnization = orgnizationService.find("id", teacherAuth.getOrgId());
							if(orgnization != null) {
								orgName = orgName + orgnization.getName() + ",";
							} 
							joinTimeList = joinTimeList + StringUtil.date2String4(teacherAuth.getUpdateTime()) + ",";
						}
					} 
					
					if(orgName.length() >= 2) {
						orgName = orgName.substring(0, orgName.length()-1);
					} else {
						orgName = "";
					}
					
					if(joinTimeList.length() >= 2) {
						joinTimeList = joinTimeList.substring(0,joinTimeList.length()-1);
					} else {
						joinTimeList = "";
					}
					
					//统计用户评价分数
					int favStar = 0;
					int num = 0;
					List<CourseTimeComment> CourseTimeCommentList = courseTimeCommentService.findList("teacherId",teacher.getId());
					if(CourseTimeCommentList != null && CourseTimeCommentList.size() > 0) {
						for (CourseTimeComment courseTimeComment : CourseTimeCommentList) {
							if(courseTimeComment != null) {
								if(courseTimeComment.getStatus()!=null && courseTimeComment.getStatus() == 1) {
									favStar = favStar  + courseTimeComment.getFavStar();
									num ++;
								}
							}
						}
						if(num != 0) {
							favStar = favStar/num;
						}
						teacher.setFavStar(favStar);
					}  else {
						teacher.setFavStar(5);
					}
					teacher.setUserRate(teacher.getFavStar());
					teacher.setTeacherType(teacherType1);
					teacher.setOrgName(orgName);
					teacher.setJoinTimeList(joinTimeList);
					
					
				} else {//未入职的教师，加入机构名称和时间设为null,用户评价设为5
					teacher.setOrgName(null);
					teacher.setJoinTimeList(null);
					teacher.setFavStar(5);
					teacher.setUserRate(5);
				}
				
				teacherService.update(teacher);
			}
		}
		
		model.addAttribute("page", pages);
		
		return "/admin/teacher/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.teacherService.deleteAll(ids);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 教师管理——用户评价
	 */
	@RequestMapping(value = "/userRate/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public String userRate(@PathVariable Long id, ModelMap model,HttpServletRequest request,Pageable pageable) {
		
	   Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
	   Map map=new HashMap();
	   for (String key : paramter.keySet()) {  
             if(!StringUtil.isEmpty( paramter.get(key).toString())){
            	map.put(key,  paramter.get(key));
             }
       }  
		
	   Map<String, Object> paramter2 = new HashMap<>();
		
		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
				
		if(!StringUtil.isEmpty(filter_account)) {
			paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_content)) {
			paramter2.put("filter_content", StringUtil.filterSQL(filter_content));
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
		
		paramter2.put("teacherId", id);
		paramter2.put("order", "create_date DESC");
		pageable.setParameter(paramter2);
		
		model.addAttribute("paramter", paramter);
		
		Page<CourseTimeComment> pages = courseTimeCommentService.findByPage(pageable);
		model.addAttribute("page",pages);
		
		List<CourseTimeComment> courseTimeCommenttList = pages.getContent();
		for (CourseTimeComment courseTimeComment : courseTimeCommenttList) {
			if(courseTimeComment != null && courseTimeComment.getMark() != null) {
				courseTimeComment.setMark(courseTimeComment.getMark().replaceAll("##", "  "));
			}
		}
		
		Teacher teacher = teacherService.find(id);
		model.addAttribute("teacher", teacher);
		
		return "/admin/teacher/userRate";
	}
	
	
	/**
	 * 机构管理——教师——用户评价
	 */
	@RequestMapping(value = "/userRate/{id}/{orgId}", method={RequestMethod.GET,RequestMethod.POST})
	public String userRate2(@PathVariable Long id, @PathVariable Long orgId, ModelMap model,HttpServletRequest request,Pageable pageable) {
	   
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		
	   Map<String, Object> paramter2 = new HashMap<>();
		
		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
				
		if(!StringUtil.isEmpty(filter_account)) {
			paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_content)) {
			paramter2.put("filter_content", StringUtil.filterSQL(filter_content));
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
		
		paramter2.put("teacherId", id);
		
		//由机构id是否存在判断用户评价页面是由机构还是教师模块跳转过来
		paramter2.put("orgId", orgId);
		paramter2.put("order", "create_date DESC");
		
		pageable.setParameter(paramter2);
		
		model.addAttribute("paramter", paramter);
		
		Page<CourseTimeComment> pages = this.courseTimeCommentService.findByPage(pageable);
		model.addAttribute("page",pages);
		
		List<CourseTimeComment> courseTimeCommentList = pages.getContent();
		for (CourseTimeComment courseTimeComment : courseTimeCommentList) {
			if(courseTimeComment != null && courseTimeComment.getMark() != null) {
				courseTimeComment.setMark(courseTimeComment.getMark().replaceAll("##", "  "));
			}
		}
		
		Teacher teacher = teacherService.find(id);
		model.addAttribute("teacher", teacher);
		
		return "/admin/teacher/userRate";
	}
	
	/**
	 * 教师管理——机构评价
	 */
	@RequestMapping(value = "/orgnizationRate/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public String orgnizationRate(@PathVariable Long id, ModelMap model,HttpServletRequest request,Pageable pageable) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		
		Map<String, Object> paramter2 = new HashMap<>();
		
		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
				
		if(!StringUtil.isEmpty(filter_account)) {
			paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_content)) {
			paramter2.put("filter_content", StringUtil.filterSQL(filter_content));
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
		
		paramter2.put("teacherId", id);
		
		pageable.setParameter(paramter2);
		
		model.addAttribute("paramter", paramter);
		
		Page<TeacherComment> pages = this.teacherCommentService.findByPage(pageable);
		model.addAttribute("page",pages);
		
		List<TeacherComment> teacherCommentList = pages.getContent();
		for (TeacherComment teacherComment : teacherCommentList) {
			if(teacherComment != null && teacherComment.getMark() != null) {
				teacherComment.setMark(teacherComment.getMark().replaceAll("##", "  "));
			}
		}
		
		Teacher teacher = teacherService.find(id);
		model.addAttribute("teacher", teacher);
		
		return "/admin/teacher/orgnizationRate";
	}
	
	/**
	 * 机构管理——教师——机构评价
	 */
	@RequestMapping(value = "/orgnizationRate/{id}/{orgId}", method={RequestMethod.GET,RequestMethod.POST})
	public String orgnizationRate2(@PathVariable Long id, @PathVariable Long orgId, ModelMap model,HttpServletRequest request,Pageable pageable) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        } 
		
		Map<String, Object> paramter2 = new HashMap<>();
		
		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
				
		if(!StringUtil.isEmpty(filter_account)) {
			paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_content)) {
			paramter2.put("filter_content", StringUtil.filterSQL(filter_content));
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
		
		paramter2.put("teacherId", id);
		
		//由机构id是否存在判断用户评价页面是由机构还是教师模块跳转过来
		paramter2.put("orgId", orgId);
		
		pageable.setParameter(paramter2);
		
		model.addAttribute("paramter", paramter);
		
		Page<TeacherComment> pages = this.teacherCommentService.findByPage(pageable);
		model.addAttribute("page",pages);
		
		List<TeacherComment> teacherCommentList = pages.getContent();
		for (TeacherComment teacherComment : teacherCommentList) {
			if(teacherComment != null && teacherComment.getMark() != null) {
				teacherComment.setMark(teacherComment.getMark().replaceAll("##", "  "));
			}
		}
		
		Teacher teacher = teacherService.find(id);
		model.addAttribute("teacher", teacher);
		
		return "/admin/teacher/orgnizationRate";
	}
	
	
	/**
	 *过滤 
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();
		
		String filter_account = request.getParameter("filter_account");
		String filter_nickname = request.getParameter("filter_nickname");
		String filter_orgName = request.getParameter("filter_orgName");
		String filter_teacherType = request.getParameter("filter_teacherType");
		String filter_status = request.getParameter("filter_status");
				
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_nickname)) {
			paramter.put("filter_nickname", StringUtil.filterSQL(filter_nickname));
		}
		
		if(!StringUtil.isEmpty(filter_orgName)) {
			paramter.put("filter_orgName", StringUtil.filterSQL(filter_orgName));
		}
		
		if(!StringUtil.isEmpty(filter_teacherType)) {
			paramter.put("filter_teacherType", Integer.parseInt(StringUtil.filterSQL(filter_teacherType)));
		}

		if(!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}
		
		return paramter;
	}
	
	/**
	 * 启用教师
	 */
	@RequestMapping(value={"/startTeacher"},method={RequestMethod.POST})
	@ResponseBody
	public Message startTeacher(Long id) {
		Teacher teacher = this.teacherService.find(id);
		teacher.setStatus(1);
		teacherService.update(teacher);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 禁用教师
	 */
	@RequestMapping(value={"/forbidTeacher"},method={RequestMethod.POST})
	@ResponseBody
	public Message forbidTeacher(Long id) {
		Teacher teacher = this.teacherService.find(id);
		teacher.setStatus(2);
		teacherService.update(teacher);
	    String user_id="user_teacher_"+id;
			JedisStringCache user_idStringCache=	jedisCache.getJedisStringCache(user_id);
			 if(user_idStringCache.get()!=null){
				 //如果存在则修改其状态为已经在其他手机登陆了
				 JedisStringCache oldUserjedisStringCache=	jedisCache.getJedisStringCache(user_idStringCache.get());
				 if(oldUserjedisStringCache.get()!=null){
					 oldUserjedisStringCache.remove(user_idStringCache.get());
				 }
			 }
		
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 启用用户评价
	 */
	@RequestMapping(value={"/startUserRate"},method={RequestMethod.POST})
	@ResponseBody
	public Message startUserRate(Long id) {
		CourseTimeComment userRate = this.courseTimeCommentService.find(id);
		userRate.setStatus(1);
		courseTimeCommentService.update(userRate);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 禁用用户评价
	 */
	@RequestMapping(value={"/forbidUserRate"},method={RequestMethod.POST})
	@ResponseBody
	public Message forbidUserRate(Long id) {
		CourseTimeComment userRate = this.courseTimeCommentService.find(id);
		userRate.setStatus(2);
		courseTimeCommentService.update(userRate);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 启用机构评价
	 */
	@RequestMapping(value={"/startOrgnizationRate"},method={RequestMethod.POST})
	@ResponseBody
	public Message startOrgnizationRate(Long id) {
		TeacherComment teacherRate = this.teacherCommentService.find(id);
		teacherRate.setStatus(1);
		teacherCommentService.update(teacherRate);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 禁用用户评价
	 */
	@RequestMapping(value={"/forbidOrgnizationRate"},method={RequestMethod.POST})
	@ResponseBody
	public Message forbidOrgnizationRate(Long id) {
		TeacherComment teacherRate = this.teacherCommentService.find(id);
		teacherRate.setStatus(2);
		teacherCommentService.update(teacherRate);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 导出课程订单列表
	 */
	@RequestMapping(value="teacherExport",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView teacherExport(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		Map paramter = RequestMap(request);
		
		List<Teacher> teacherList = teacherService.findList(paramter);
		
		String fileName = "教师列表" + ".xls";
		
 		return new ModelAndView(new ExcelView(fileName, fileName,
 				new String[] {"account","nickname","sex","teacherTypeString","orgName","joinTimeString","favStar","statusString"}, 
 				new String[] {"账号","姓名","性别","类型","所属机构","加入时间","用户评价","状态"},
 				new Integer[] {5000,5000,5000,5000,5000,5000,5000,5000}, 
 				null,teacherList, null), model);
		
	}
	
	
	/**
	 * 教师——编辑——新增教师任教经历
	 */
	@RequestMapping(value = { "/addTeacherExperience/{id}" }, method ={RequestMethod.GET,RequestMethod.POST})
	public String addTeacherExperience(@PathVariable Long id,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(id != null) {
			List<TeacherExperience> teacherExperenceList = teacherExperenceService.findList("teacherId", id);
			if(teacherExperenceList.size() < 5) {
				model.addAttribute("teacherId", id);
				return "/admin/teacher/addTeacherExperience";
			} else {
				addFlashMessage(redirectAttributes, new Message(Type.warn, "任教经历不得超过5条", null));
			}
		}
		return "redirect:/admin/teacher/edit/"+id+".jhtml";
	}
	
	/**
	 * 机构——教师——编辑——新增教师任教经历
	 */
	@RequestMapping(value = { "/addTeacherExperience/{id}/{orgId}" }, method ={RequestMethod.GET,RequestMethod.POST})
	public String addTeacherExperience2(@PathVariable Long id,@PathVariable Long orgId,HttpServletRequest request,ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(id != null) {
			List<TeacherExperience> teacherExperenceList = teacherExperenceService.findList("teacherId", id);
			if(teacherExperenceList.size() < 5) {
				model.addAttribute("teacherId", id);
				model.addAttribute("orgId", orgId);
				return "/admin/teacher/addTeacherExperience";
			} else {
				addFlashMessage(redirectAttributes, new Message(Type.warn, "任教经历不得超过5条", null));
			}
		}
		return "redirect:/admin/teacher/edit/"+id+"/"+orgId+".jhtml";
	}
	
	
	/**
	 * 保存教师任教经历
	 */
	@RequestMapping(value = "/saveTeacherExperience", method ={RequestMethod.GET,RequestMethod.POST})
	public String saveTeacherExperience(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		TeacherExperience teacherExperience = new TeacherExperience();
		
		String teacherId = request.getParameter("teacherId");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String content = request.getParameter("content");
		
		if(!StringUtil.isEmpty(teacherId)){
			teacherExperience.setTeacherId(Long.parseLong(teacherId));
		};
		
		if(!StringUtil.isEmpty(start)){
			teacherExperience.setStart(StringUtil.StringDateToDate(start));
		};
		
		if(!StringUtil.isEmpty(end)){
			teacherExperience.setEnd(StringUtil.StringDateToDate(end));
		};
		
		if(!StringUtil.isEmpty(content)){
			teacherExperience.setContent(content);
		};
		
		teacherExperience.setCreateTime(new Date());
		teacherExperience.setTeacherId(Long.parseLong(teacherId));
		
		teacherExperienceService.save(teacherExperience);
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit/"+teacherExperience.getTeacherId()+".jhtml";
	}
	
	/**
	 * 编辑教师任教经历
	 */
	@RequestMapping(value = "/editTeacherExperience/{id}", method = RequestMethod.GET)
	public String editTeacherExperience(@PathVariable Long id, ModelMap model) {
		TeacherExperience teacherExperience = teacherExperienceService.find(id);
		model.addAttribute("teacherExperience", teacherExperience);
		return "/admin/teacher/editTeacherExperience";
	}
	
	/**
	 * 更新教师任教经历
	 */
	@RequestMapping(value = "/updateTeacherExperience", method = RequestMethod.POST)
	public String updateTeacherExperience(TeacherExperience teacherExperience,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		teacherExperenceService.update(teacherExperience);
		TeacherExperience teacherExperience2 = teacherExperenceService.find(teacherExperience.getId());
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		return "redirect:edit/"+teacherExperience2.getTeacherId()+".jhtml";
	}
	
	/**
	 * 删除教师任教经历
	 */
	@RequestMapping(value = "/deleteTeacherExperience", method={RequestMethod.POST})
	@ResponseBody
	public Message deleteTeacherExperience(Long id) {
		
		
		this.teacherExperienceService.delete(id);
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public String updatePwd(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String teacherId = request .getParameter("teacherId");
		String password = request.getParameter("filter_password");
		
		if(!StringUtil.isEmpty(teacherId)) {
			Teacher teacher  = teacherService.find("id", Integer.parseInt(teacherId));
			if(teacher != null) {
				teacher.setPassword(XDigestUtils.md5Hex(password));
				
				teacherService.update(teacher);
			}
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		}
		
		return "redirect:list.jhtml";
	}

	  public void setUnread(Teacher teacher){
	        UserUnread userUnread=new UserUnread();
	  	    userUnread.setUserId(teacher.getId());
	  	    userUnread.setUserType(2);
	  	    userUnread.setSystemMesStatus(1);
	  	    userUnread.setCourserMesStatus(1);
	  	    userUnread.setDynamicStatus(1);
	  	    userUnread.setSystemMesStatus(1);
	  	    userUnread.setTaskTotal(0);
	  	    userUnread.setClassRemindStatus(1);
	  	    userUnread.setFriendStatus(1);
	  	    userUnreadService.save(userUnread);
	    }
}
