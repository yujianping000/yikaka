package com.framework.loippi.controller.admin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.TSalary;
import com.framework.loippi.entity.TattendaanceCount;
import com.framework.loippi.entity.TcommentScope;
import com.framework.loippi.entity.TcontinueClass;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherComment;
import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.entity.ThourExpense;
import com.framework.loippi.entity.TleaveExplain;
import com.framework.loippi.entity.TleaveManage;
import com.framework.loippi.entity.TremarkOn;
import com.framework.loippi.entity.User;
import com.framework.loippi.entity.UserUnread;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseTimeCommentService;
import com.framework.loippi.service.HourViewService;
import com.framework.loippi.service.OrgTeacherviewService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TSalaryService;
import com.framework.loippi.service.TattendaanceCountService;
import com.framework.loippi.service.TcommentScopeService;
import com.framework.loippi.service.TcontinueClassService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherCommentService;
import com.framework.loippi.service.TeacherExperienceService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.ThourExpenseService;
import com.framework.loippi.service.TleaveExplainService;
import com.framework.loippi.service.TleaveManageService;
import com.framework.loippi.service.TremarkOnService;
import com.framework.loippi.service.UserService;
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
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 教师表
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminOrgTeacherController")
@RequestMapping({ "/admin/org_teacher" })
public class OrgTeacherController extends GenericController {

	@Resource
	private UserService userService;

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
	private OrgnizationService orgnizationService;

	@Resource
	private OrgTeacherviewService orgTeacherviewService;

	@Resource
	private TSalaryService tSalaryService;

	@Resource
	private TcommentScopeService tcommentScopeService;
	@Resource
	private TcontinueClassService tcontinueClassService;

	@Resource
	private TremarkOnService tremarkOnService;
	@Resource
	private TleaveManageService tleaveManageService;
	@Resource
	private TleaveExplainService tleaveExplainService;
	@Resource
	private TattendaanceCountService tattendaanceCountService;
	@Resource
	private ThourExpenseService thourExpenseService;
	@Resource
	private OrgUserService orgUserService;
	@Resource
	private HourViewService hourViewService;
	@Resource
	private UserUnreadService userUnreadService;
	
	@Resource
	private CourseTimeCommentService courseTimeCommentService;
	
	
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

		return "/admin/org_teacher/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes, TleaveExplain tleaveExplain,
			TattendaanceCount tattendaanceCount, ThourExpense thourExpense, TSalary tSalary,Integer optionsRadiosinline ) {

		User user = userService.getCurrent();
		Teacher teacher = new Teacher();
		String account = request.getParameter("account");
		String avatar = request.getParameter("avatar");
		String status = request.getParameter("status");
		String sex = request.getParameter("sex");
		String teacherType = request.getParameter("teacherType");
		String birthday = request.getParameter("birthday");
		String nickname = request.getParameter("nickname");
		String description = request.getParameter("description");
		String mark1 = request.getParameter("mark1");
		String mark2 = request.getParameter("mark2");
		String mark3 = request.getParameter("mark3");
		String password = request.getParameter("password");
		String deducta = request.getParameter("deducta");
		String deductb = request.getParameter("deductb");

		// 检查教师用户是否存在
		Teacher teacher2 = teacherService.find("account", account);
		if (teacher2 != null) {
			addFlashMessage(redirectAttributes, new Message(Type.error, "该账号已存在，请使用其他账号", null));
			return "redirect:list.jhtml";
		}

		if (!StringUtil.isEmpty(account)) {
			teacher.setAccount(account);
		}
		;

		if (!StringUtil.isEmpty(avatar)) {
			teacher.setAvatar(avatar);
		}
		;
		
		if (!StringUtil.isEmpty(sex)) {
			teacher.setSex(sex);
		}
		;

		if (!StringUtil.isEmpty(status)) {
			teacher.setStatus(Integer.parseInt(status));
		}
		;

		if (!StringUtil.isEmpty(teacherType)) {
			teacher.setTeacherType(Integer.parseInt(teacherType));
		}
		;

		if (!StringUtil.isEmpty(birthday)) {
			teacher.setBirthday(StringUtil.StringDateToDate(birthday));
		}
		;

		if (!StringUtil.isEmpty(nickname)) {
			teacher.setNickname(nickname);
		}
		;

		if (!StringUtil.isEmpty(description)) {
			teacher.setDescription(description);
		}
		;

		String mark = "";

		if (!StringUtil.isEmpty(mark1)) {
			mark = mark1 + "##";
			teacher.setMark1(mark1);
		}
		;

		if (!StringUtil.isEmpty(mark2)) {
			mark = mark + mark2 + "##";
			teacher.setMark2(mark2);
		}
		;

		if (!StringUtil.isEmpty(mark3)) {
			mark = mark + mark3 + "##";
			teacher.setMark3(mark3);
		}
		;

		if (!StringUtil.isEmpty(password)) {
			teacher.setPassword(DigestUtils.md5Hex(password));
		}
		;

		teacher.setMark(mark);
		teacher.setCreateDate(new Date());
		teacher.setFavStar(5);

		teacherService.save(teacher);
		
		setUnread(teacher);

		// 机构教师总数加一
		Orgnization orgnization = orgnizationService.find("id", user.getOrgId());
		orgnization.setTeacherCount(orgnization.getTeacherCount() + 1);
		orgnizationService.update(orgnization);

		// 机构用户表数据加一
		OrgUser orgUser = new OrgUser();
		orgUser.setCreateTime(new Date());
		orgUser.setOrgId(user.getOrgId());
		orgUser.setType(2);
		orgUser.setUid(teacher.getId());
		orgUserService.save(orgUser);

		tSalary.setUid(teacher.getId());
		tSalary.setOrgId(user.getOrgId());
		tSalaryService.save(tSalary);

		tleaveExplain.setUid(teacher.getId());
		tleaveExplain.setOrgId(user.getOrgId());
		tleaveExplainService.save(tleaveExplain);

		tattendaanceCount.setOrgId(user.getOrgId());
		if (!StringUtil.isEmpty(deducta)) {
			tattendaanceCount.setDeduct1(new BigDecimal(deducta));
		}
		if (!StringUtil.isEmpty(deductb)) {
			tattendaanceCount.setDeduct2(new BigDecimal(deductb));
		}
		tattendaanceCount.setUid(teacher.getId());
		tattendaanceCountService.save(tattendaanceCount);

		thourExpense.setUid(teacher.getId());
		thourExpense.setOrgId(user.getOrgId());
		
		if(optionsRadiosinline==1){
			thourExpense.setOneClass(new BigDecimal(0) );
			thourExpense.setMoneyRate(new BigDecimal(0));
		}else if(optionsRadiosinline==2){
			thourExpense.setAward(new BigDecimal(0) );
			thourExpense.setDeduct(new BigDecimal(0));
			thourExpense.setHour(0);
			thourExpense.setMoneyRate(new BigDecimal(0));
		}else{
			thourExpense.setAward(new BigDecimal(0) );
			thourExpense.setDeduct(new BigDecimal(0));
			thourExpense.setHour(0);
			thourExpense.setOneClass(new BigDecimal(0) );
		}
		thourExpenseService.save(thourExpense);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit/" + teacher.getId() + ".jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		Teacher teacher = teacherService.find(id);

		String[] markArray = teacher.getMark().split("##");

		if (markArray != null && markArray.length > 0) {
			if (markArray.length >= 1) {
				teacher.setMark1(markArray[0]);
				if (markArray.length >= 2) {
					teacher.setMark2(markArray[1]);
				}
				if (markArray.length >= 3) {
					teacher.setMark3(markArray[2]);
				}
			}
		}

		model.addAttribute("teacher", teacher);

		List<TeacherExperience> teacherExperienceList = teacherExperenceService.findList("teacherId", id);

		model.addAttribute("teacherExperienceList", teacherExperienceList);

		String token = FileServiceImpl.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		List<TcommentScope> tcommentScopes = tcommentScopeService.findList("uid", id);
		model.addAttribute("tcommentScopes", tcommentScopes);
		List<TcontinueClass> tcontinueClasss = tcontinueClassService.findList("uid", id);
		model.addAttribute("tcontinueClasss", tcontinueClasss);
		List<TremarkOn> tremarkOns = tremarkOnService.findList("uid", id);
		model.addAttribute("tremarkOns", tremarkOns);

		TleaveExplain tleaveExplain = tleaveExplainService.find("uid", id);
		model.addAttribute("tleaveExplain", tleaveExplain);

		TattendaanceCount tattendaanceCount = tattendaanceCountService.find("uid", id);
		model.addAttribute("tattendaanceCount", tattendaanceCount);

		ThourExpense thourExpense = thourExpenseService.find("uid", id);
		model.addAttribute("thourExpense", thourExpense);

		TSalary tSalary = tSalaryService.find("uid", id);
		model.addAttribute("tSalary", tSalary);
		return "/admin/org_teacher/edit";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		Teacher teacher = teacherService.find(id);
		model.addAttribute("teacher", teacher);
		
		

		String mark = teacher.getMark();
		String[] markString = mark.split("##");

		if (markString != null && markString.length > 0) {
			if (markString.length >= 1) {
				teacher.setMark1(markString[0]);
				if (markString.length >= 2) {
					teacher.setMark2(markString[1]);
					if (markString.length >= 3) {
						teacher.setMark3(markString[2]);
					}
				}
			}

		}

		List<TeacherExperience> teacherExperenceList = teacherExperenceService.findList("teacherId", id);
		model.addAttribute("teacherExperenceList", teacherExperenceList);

		return "/admin/org_teacher/view";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Teacher teacher, RedirectAttributes redirectAttributes, TSalary tSalary,
			TleaveExplain tleaveExplain, TattendaanceCount tattendaanceCount, ThourExpense thourExpense,
			HttpServletRequest request, Long tSalaryId, Long tleaveExplainId, Long thourExpenseId,
			Long tattendaanceCountId, Long uid,Integer optionsRadiosinline) {
		User user = userService.getCurrent();
		teacher.setId(uid);
		String deducta = request.getParameter("deducta");
		String deductb = request.getParameter("deductb");
		String mark = "";
		if (teacher.getMark1() != null && teacher.getMark1() != "") {
			mark = teacher.getMark1() + "##";
		}
		if (teacher.getMark2() != null && teacher.getMark2() != "") {
			mark = mark + teacher.getMark2() + "##";
		}
		if (teacher.getMark3() != null && teacher.getMark3() != "") {
			mark = mark + teacher.getMark3();
		}

		teacher.setMark(mark);
		teacherService.update(teacher);

		if (tSalaryId != null) {
			tSalary.setUid(uid);
			tSalary.setId(tSalaryId);
			tSalary.setOrgId(user.getOrgId());
			tSalaryService.update(tSalary);
		} else {
			tSalary.setUid(uid);
			tSalary.setOrgId(user.getOrgId());
			tSalaryService.save(tSalary);
		}

		if (tleaveExplainId != null) {
			tleaveExplain.setUid(uid);
			tleaveExplain.setId(tleaveExplainId);
			tleaveExplain.setOrgId(user.getOrgId());
			tleaveExplainService.update(tleaveExplain);
		} else {
			tleaveExplain.setUid(uid);
			tleaveExplain.setOrgId(user.getOrgId());
			tleaveExplainService.save(tleaveExplain);
		}

		if (!StringUtil.isEmpty(deducta)) {
			tattendaanceCount.setDeduct1(new BigDecimal(deducta));
		}
		if (!StringUtil.isEmpty(deductb)) {
			tattendaanceCount.setDeduct2(new BigDecimal(deductb));
		}

		if (tattendaanceCountId != null) {
			tattendaanceCount.setUid(uid);
			tattendaanceCount.setOrgId(user.getOrgId());
			tattendaanceCount.setId(tattendaanceCountId);
			tattendaanceCountService.update(tattendaanceCount);
		}else{
			tattendaanceCount.setUid(uid);
			tattendaanceCount.setOrgId(user.getOrgId());
			tattendaanceCountService.save(tattendaanceCount);
		}

		

		
		if (thourExpenseId != null) {
			thourExpense.setUid(uid);
			thourExpense.setId(thourExpenseId);
			thourExpense.setOrgId(user.getOrgId());
			if(optionsRadiosinline==1){
				thourExpense.setOneClass(new BigDecimal(0) );
				thourExpense.setMoneyRate(new BigDecimal(0));
			}else if(optionsRadiosinline==2){
				thourExpense.setAward(new BigDecimal(0));
				thourExpense.setDeduct(new BigDecimal(0));
				thourExpense.setHour(0);
				thourExpense.setMoneyRate(new BigDecimal(0));
			}else{
				thourExpense.setAward(new BigDecimal(0) );
				thourExpense.setDeduct(new BigDecimal(0));
				thourExpense.setHour(0);
				thourExpense.setOneClass(new BigDecimal(0) );
			}
			thourExpenseService.update(thourExpense);
		} else {
			thourExpense.setUid(uid);
			thourExpense.setOrgId(user.getOrgId());
			if(optionsRadiosinline==1){
				thourExpense.setOneClass(new BigDecimal(0) );
				thourExpense.setMoneyRate(new BigDecimal(0));
			}else if(optionsRadiosinline==2){
				thourExpense.setAward(new BigDecimal(0) );
				thourExpense.setDeduct(new BigDecimal(0));
				thourExpense.setHour(0);
				thourExpense.setMoneyRate(new BigDecimal(0));
			}else{
				thourExpense.setAward(new BigDecimal(0) );
				thourExpense.setDeduct(new BigDecimal(0));
				thourExpense.setHour(0);
				thourExpense.setOneClass(new BigDecimal(0) );
			}
			thourExpenseService.save(thourExpense);
		}

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

		Map<String, Object> paramter = new HashMap<>();

		User user = userService.getCurrent();
		if (user != null) {
			Orgnization orgnization = orgnizationService.find(user.getOrgId());
			model.addAttribute("orgnization", orgnization);
		}

		String filter_account = request.getParameter("filter_account");
		String filter_nickname = request.getParameter("filter_nickname");
		String filter_teacherType = request.getParameter("filter_teacherType");
		String filter_status = request.getParameter("filter_status");

		if (user.getOrgId() != null) {
			paramter.put("orgId", user.getOrgId());
		}

		if (!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}

		if (!StringUtil.isEmpty(filter_nickname)) {
			paramter.put("filter_nickname", StringUtil.filterSQL(filter_nickname));
		}

		if (!StringUtil.isEmpty(filter_teacherType)) {
			paramter.put("filter_teacherType", Integer.parseInt(StringUtil.filterSQL(filter_teacherType)));
		}

		if (!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}

		pageable.setParameter(paramter);

		model.addAttribute("paramter", paramter);

		Page<OrgTeacherview> pages = this.orgTeacherviewService.findByPage(pageable);

		List<OrgTeacherview> OrgTeacherviewList = pages.getContent();

		if(OrgTeacherviewList != null) {
			for (OrgTeacherview orgTeacherview : OrgTeacherviewList) {
				//统计用户评价分数
				int favStar = 0;
				int num = 0;
				
				List<CourseTimeComment> CourseTimeCommentList = courseTimeCommentService.findList("teacherId",orgTeacherview.getTeacherId());
				if(CourseTimeCommentList != null && CourseTimeCommentList.size() > 0) {
					for (CourseTimeComment courseTimeComment : CourseTimeCommentList) {
						if(courseTimeComment != null) {
							if(courseTimeComment.getStatus()!= null && courseTimeComment.getStatus() == 1) {
								favStar = favStar  + courseTimeComment.getFavStar();
								num ++;
							}
						}
					}
					if(num != 0) {
						favStar = favStar/num;
					}
					
					orgTeacherview.setUserRate(favStar);
				} else {
					orgTeacherview.setUserRate(5);
				}
			}
		}

		model.addAttribute("page", pages);

		return "/admin/org_teacher/list";
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
	 * 用户评价
	 */
	@RequestMapping(value = "/userRate/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String userRate(@PathVariable Long id, ModelMap model, HttpServletRequest request, Pageable pageable) {

		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}

		Map<String, Object> paramter2 = new HashMap<>();

		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");

		if (!StringUtil.isEmpty(filter_account)) {
			paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
		}

		if (!StringUtil.isEmpty(filter_content)) {
			paramter2.put("filter_content", StringUtil.filterSQL(filter_content));
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

			paramter2.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
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

			paramter2.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		paramter2.put("teacherId", id);
		paramter2.put("order", "create_date DESC");

		pageable.setParameter(paramter2);
		model.addAttribute("paramter", paramter);

		 Page<CourseTimeComment> pages = this.courseTimeCommentService.findByPage(pageable);
		model.addAttribute("page", pages);

		List<CourseTimeComment> courseTimeCommentList = pages.getContent();
		for (CourseTimeComment courseTimeComment : courseTimeCommentList) {
			if (courseTimeComment != null && courseTimeComment.getMark() != null) {
				courseTimeComment.setMark(courseTimeComment.getMark().replaceAll("##", "  "));
			}
		}

		Teacher teacher = teacherService.find(id);
		model.addAttribute("teacher", teacher);

		return "/admin/org_teacher/userRate";
	}

	/**
	 * 机构评价
	 */
	@RequestMapping(value = "/orgnizationRate/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String orgnizationRate(@PathVariable Long id, ModelMap model, HttpServletRequest request,
			Pageable pageable) {

		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}

		Map<String, Object> paramter2 = new HashMap<>();

		String filter_account = request.getParameter("filter_account");
		String filter_content = request.getParameter("filter_content");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");

		if (!StringUtil.isEmpty(filter_account)) {
			paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
		}

		if (!StringUtil.isEmpty(filter_content)) {
			paramter2.put("filter_content", StringUtil.filterSQL(filter_content));
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

			paramter2.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
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

			paramter2.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		paramter2.put("teacherId", id);

		pageable.setParameter(paramter2);
		model.addAttribute("paramter", paramter);

		Page<TeacherComment> pages = this.teacherCommentService.findByPage(pageable);
		model.addAttribute("page", pages);

		List<TeacherComment> teacherCommentList = pages.getContent();
		for (TeacherComment teacherComment : teacherCommentList) {
			if (teacherComment != null && teacherComment.getMark() != null) {
				teacherComment.setMark(teacherComment.getMark().replaceAll("##", "  "));
			}
		}

		Teacher teacher = teacherService.find(id);
		model.addAttribute("teacher", teacher);

		return "/admin/org_teacher/orgnizationRate";
	}

	/**
	 * 启用教师
	 */
	@RequestMapping(value = { "/startOrgTeacher" }, method = { RequestMethod.POST })
	@ResponseBody
	public Message startOrgTeacher(Long id) {
		Teacher teacher = this.teacherService.find(id);
		teacher.setStatus(1);
		teacherService.update(teacher);

		return SUCCESS_MESSAGE;

	}

	/**
	 * 禁用教师
	 */
	@RequestMapping(value = { "/forbidOrgTeacher" }, method = { RequestMethod.POST })
	@ResponseBody
	public Message forbidOrgTeacher(Long id) {
		Teacher teacher = this.teacherService.find(id);
		teacher.setStatus(2);
		teacherService.update(teacher);

		return SUCCESS_MESSAGE;

	}

	/**
	 * 启用用户评价
	 */
	@RequestMapping(value = { "/startUserRate" }, method = { RequestMethod.POST })
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
	@RequestMapping(value = { "/forbidUserRate" }, method = { RequestMethod.POST })
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
	@RequestMapping(value = { "/startOrgnizationRate" }, method = { RequestMethod.POST })
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
	@RequestMapping(value = { "/forbidOrgnizationRate" }, method = { RequestMethod.POST })
	@ResponseBody
	public Message forbidOrgnizationRate(Long id) {
		TeacherComment teacherRate = this.teacherCommentService.find(id);
		teacherRate.setStatus(2);
		teacherCommentService.update(teacherRate);

		return SUCCESS_MESSAGE;

	}

	/**
	 * 新增教师任教经历
	 */
	@RequestMapping(value = { "/addTeacherExperience/{id}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String addTeacherExperience(@PathVariable Long id, ModelMap model) {
		model.addAttribute("teacherId", id);
		return "/admin/org_teacher/addTeacherExperience";
	}

	/**
	 * 保存教师任教经历
	 */
	@RequestMapping(value = "/saveTeacherExperience", method = { RequestMethod.GET, RequestMethod.POST })
	public String saveTeacherExperience(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		TeacherExperience teacherExperience = new TeacherExperience();

		String teacherId = request.getParameter("teacherId");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String content = request.getParameter("content");

		if (!StringUtil.isEmpty(teacherId)) {
			teacherExperience.setTeacherId(Long.parseLong(teacherId));
		}
		;

		if (!StringUtil.isEmpty(start)) {
			teacherExperience.setStart(StringUtil.StringDateToDate(start));
		}
		;

		if (!StringUtil.isEmpty(end)) {
			teacherExperience.setEnd(StringUtil.StringDateToDate(end));
		}
		;

		if (!StringUtil.isEmpty(content)) {
			teacherExperience.setContent(content);
		}
		;

		teacherExperience.setCreateTime(new Date());
		teacherExperience.setTeacherId(Long.parseLong(teacherId));

		teacherExperienceService.save(teacherExperience);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit/" + teacherExperience.getTeacherId() + ".jhtml";
	}

	/**
	 * 编辑教师任教经历
	 */
	@RequestMapping(value = "/editTeacherExperience/{id}", method = RequestMethod.GET)
	public String editTeacherExperience(@PathVariable Long id, ModelMap model) {
		TeacherExperience teacherExperience = teacherExperienceService.find(id);
		model.addAttribute("teacherExperience", teacherExperience);
		return "/admin/org_teacher/editTeacherExperience";
	}

	/**
	 * 更新教师任教经历
	 */
	@RequestMapping(value = "/updateTeacherExperience", method = RequestMethod.POST)
	public String updateTeacherExperience(TeacherExperience teacherExperience, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		teacherExperenceService.update(teacherExperience);
		TeacherExperience teacherExperience2 = teacherExperenceService.find(teacherExperience.getId());

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		return "redirect:edit/" + teacherExperience2.getTeacherId() + ".jhtml";
	}

	/**
	 * 删除教师任教经历
	 */
	@RequestMapping(value = "/deleteTeacherExperience", method = { RequestMethod.POST })
	@ResponseBody
	public Message deleteTeacherExperience(Long id) {
		this.teacherExperienceService.delete(id);

		return SUCCESS_MESSAGE;
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public String updatePwd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String teacherId = request.getParameter("teacherId");
		String password = request.getParameter("filter_password");

		if (!StringUtil.isEmpty(teacherId)) {
			Teacher teacher = teacherService.find("id", Integer.parseInt(teacherId));
			if (teacher != null) {
				teacher.setPassword(XDigestUtils.md5Hex(password));

				teacherService.update(teacher);
			}
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		}

		return "redirect:list.jhtml";
	}

	/**
	 * 新镇数值
	 */
	@RequestMapping(value = "/addvalue", method = { RequestMethod.GET })
	@ResponseBody
	public Message addvalue(Integer performanceType, BigDecimal account, BigDecimal max, BigDecimal mix, Long uid,
			Long id, Integer editType) {
		if (editType == 1) {
			if (performanceType == 1) {
				TcommentScope tcommentScope = new TcommentScope();
				tcommentScope.setAccount(account);
				tcommentScope.setMax(max);
				tcommentScope.setMix(mix);
				tcommentScope.setUid(uid);
				tcommentScopeService.save(tcommentScope);
			} else if (performanceType == 2) {
				TcontinueClass tcontinueClass = new TcontinueClass();
				tcontinueClass.setAccount(account);
				tcontinueClass.setMax(max);
				tcontinueClass.setMix(mix);
				tcontinueClass.setUid(uid);
				tcontinueClassService.save(tcontinueClass);
			} else {
				TremarkOn tremarkOn = new TremarkOn();
				tremarkOn.setAccount(account);
				tremarkOn.setMax(max);
				tremarkOn.setMix(mix);
				tremarkOn.setUid(uid);
				tremarkOnService.save(tremarkOn);
			}
		} else {
			if (performanceType == 1) {
				TcommentScope tcommentScope = tcommentScopeService.find(id);
				tcommentScope.setAccount(account);
				tcommentScope.setMax(max);
				tcommentScope.setMix(mix);
				tcommentScope.setUid(uid);
				tcommentScopeService.update(tcommentScope);
			} else if (performanceType == 2) {
				TcontinueClass tcontinueClass = tcontinueClassService.find(id);
				tcontinueClass.setAccount(account);
				tcontinueClass.setMax(max);
				tcontinueClass.setMix(mix);
				tcontinueClass.setUid(uid);
				tcontinueClassService.update(tcontinueClass);
			} else {
				TremarkOn tremarkOn = tremarkOnService.find(id);
				tremarkOn.setAccount(account);
				tremarkOn.setMax(max);
				tremarkOn.setMix(mix);
				tremarkOn.setUid(uid);
				tremarkOnService.update(tremarkOn);
			}
		}

		return SUCCESS_MESSAGE;
	}

	/**
	 * 删除数值
	 */
	@RequestMapping(value = "/deleteValue", method = { RequestMethod.GET })
	@ResponseBody
	public Message deleteValue(Integer type, Long id) {
		if (type == 1) {
			tcommentScopeService.delete(id);
		} else if (type == 2) {
			tcontinueClassService.delete(id);
		} else {
			tremarkOnService.delete(id);
		}

		return SUCCESS_MESSAGE;
	}

	/**
	 * 授课信息
	 */
	@RequestMapping(value = "/hourview", method = RequestMethod.GET)
	public String hourview(Pageable pageable, HttpServletRequest request, ModelMap model, Long id) {
		User user = userService.getCurrent();
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map = new HashMap();
		for (String key : paramter.keySet()) {
			if (!StringUtil.isEmpty(paramter.get(key).toString())) {
				map.put(key, paramter.get(key));
			}
		}
		map.put("orgId", user.getOrgId());
		map.put("teacherId", id);
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.hourViewService.findByPage(pageable));
		return "/admin/hour_view/list";
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
