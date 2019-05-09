package com.framework.loippi.controller.admin;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.entity.Acl;
import com.framework.loippi.entity.AppTeacherInfo;
import com.framework.loippi.entity.Area;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.SystemMes;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TimeView;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.AclService;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.service.CaptchaService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.SystemMesService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.TimeViewService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.shiro.Principal;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Message.Type;
import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.JpushRunnable2;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.qiniu.FileServiceImpl;
import com.loippi.core.gen.IFactory;
import com.loippi.core.gen.ITable;
import com.loippi.core.gen.mysql.impl.MysqlFactory;




/**
 * Controller - 共用
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class CommonController extends GenericController implements ServletContextAware  {

	@Value("${system.name}")
	private String systemName;
	@Value("${system.version}")
	private String systemVersion;
	@Value("${system.description}")
	private String systemDescription;
	
	
	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;
	
	@Resource
	private AclService aclService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private UserService userService;
	
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	
	@Resource
	private OrgnizationService orgnizationService;
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private TimeViewService timeViewService;
	
	@Resource
	private CourseTeaTimeService courseTeaTimeService;
	
	@Resource
	private CourseStuTimeService courseStuTimeService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private SystemMesService systemMesService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrgDynamicService orgDynamicService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private UserUnreadService userUnreadService;
	
	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	
	
	
	/**
	 * 主页
	 */
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request ,ModelMap model) {
		model.addAttribute("systemName", systemName);
		model.addAttribute("systemVersion", systemVersion);
		model.addAttribute("systemDescription", systemDescription);
		model.addAttribute("javaVersion", System.getProperty("java.version"));
		model.addAttribute("javaHome", System.getProperty("java.home"));
		model.addAttribute("osName", System.getProperty("os.name"));
		model.addAttribute("osArch", System.getProperty("os.arch"));
		model.addAttribute("serverInfo", servletContext.getServerInfo());
		model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null && principal.getId() != null) {
				model.addAttribute("principal", principal);
			}
		}
		
		
		
		List<Acl> acls = aclService.findRoots();
		
		for (int i = 0; i < acls.size(); i++) {
			if("edit".equals(acls.get(i).getUrl())){
				acls.remove(i);
			}
		}
		
		for (Acl acl : acls) {
			List<Acl> acls2 =	aclService.findChildrens(acl.getId());
			List<Acl> acls3 =new ArrayList();
			for (int i = 0; i < acls2.size(); i++) {
				if(!"edit".equals(acls2.get(i).getUrl())){
					acls3.add(acls2.get(i));
				}
			}
			acl.getChildren().addAll(acls3);
		}
		model.addAttribute("acls", acls);
		
		User user = userService.getCurrent();
		
		model.addAttribute("user",user);
		
		if(user != null && user.getTheme() != null &&  user.getTheme() == 1) {
			Orgnization orgnization = orgnizationService.find(user.getOrgId());
			model.addAttribute("orgnization",orgnization);
		}
		
		if(user != null && user.getTheme() != null &&  user.getTheme() == 2){
			return "/admin/common/main_classic";
		}
		return "/admin/common/main";
	}
	
	
	/**
	 * 主题设置
	 */
	
	@RequestMapping(value = "/theme", method = RequestMethod.POST)
	public @ResponseBody Message theme(HttpServletRequest request,Integer theme ,ModelMap model) {
		User user = userService.getCurrent();
		user.setTheme(theme);
		userService.update(user);
		return SUCCESS_MESSAGE;
	}
	
	
	

	/**
	 * 首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		User user = userService.getCurrent();
		if(user != null) {
			//今天课时
			Map map1 = new HashMap<>();
			map1.put("orgId", user.getOrgId());
			map1.put("cTime",StringUtil.date2String(new Date()));
			List<TimeView> todaycourseTimeList = timeViewService.findList(map1);
			
			model.addAttribute("todaycourseTimeList", todaycourseTimeList);
			
			//明天课时
			Map map2 = new HashMap<>();
			Date date1 = new Date();
			Calendar calendar1 = new GregorianCalendar();
			calendar1.setTime(date1);
			calendar1.add(calendar1.DATE,1);
			
			map2.put("orgId", user.getOrgId());
			map2.put("cTime", StringUtil.date2String(calendar1.getTime()));
			
			List<TimeView> tomorrowcourseTimeList = timeViewService.findList(map2);
			model.addAttribute("tomorrowcourseTimeList", tomorrowcourseTimeList);
			
			//后天课时
			Map map3 = new HashMap<>();
			Date date2 = new Date();
			Calendar calendar2 = new GregorianCalendar();
			calendar2.setTime(date2);
			calendar2.add(calendar2.DATE,2);
			
			map3.put("orgId", user.getOrgId());
			map3.put("cTime", StringUtil.date2String(calendar2.getTime()));
			
			List<TimeView> aftertomorrowcourseTimeList = timeViewService.findList(map3);
			model.addAttribute("aftertomorrowcourseTimeList", aftertomorrowcourseTimeList);
		}
		
		if(user != null) {
			Orgnization orgnization = orgnizationService.find(user.getOrgId());
			model.addAttribute("orgnization",orgnization);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("systemName", systemName);
		model.addAttribute("systemVersion", systemVersion);
		model.addAttribute("systemDescription", systemDescription);
		model.addAttribute("javaVersion", System.getProperty("java.version"));
		model.addAttribute("javaHome", System.getProperty("java.home"));
		model.addAttribute("osName", System.getProperty("os.name"));
		model.addAttribute("osArch", System.getProperty("os.arch"));
		model.addAttribute("serverInfo", servletContext.getServerInfo());
		model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
		
		return "/admin/common/index";
	}
	
	



	/**
	 * 注销
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SecurityUtils.getSubject().logout();
		return "redirect:/admin/login.jhtml?jssionid="+session.getId();
	}
	
	
	
	/**
	 * 数据字典
	 */
	@RequestMapping(value = "/dic", method = RequestMethod.GET)
	public String dic(ModelMap model) {
		try {
			IFactory factory = MysqlFactory.getInstance(url, username, password);
			List<ITable> tables = factory.getTables();
			model.addAttribute("tables", tables);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/admin/common/dic";
	}
	
	
	/**
	 * 地区
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody Map<Long, String> area(Long parentId) {
		List<Area> areas = new ArrayList<Area>();
		if (parentId != null) {
			areas = areaService.findChildrens(parentId);
		} else {
			areas = areaService.findRoots();
		}
		Map<Long, String> options = new HashMap<Long, String>();
		for (Area area : areas) {
			options.put(area.getId(), area.getName());
		}
		return options;
	}

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(captchaId)) {
			captchaId = request.getSession().getId();
		}
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			BufferedImage bufferedImage = captchaService.buildImage(captchaId);
			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(servletOutputStream);
		}
	}

	/**
	 * 错误提示
	 */
	@RequestMapping("/error")
	public String error() {
		return ERROR_VIEW;
	}

	/**
	 * 权限错误
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request,
			HttpServletResponse response) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null
				&& requestType.equalsIgnoreCase("XMLHttpRequest")) {
			response.addHeader("loginStatus", "unauthorized");
			try {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return "/admin/common/unauthorized";
	}
	
	/**
	 * 资源不存在
	 */
	@RequestMapping("/resource_not_found")
	public String resourceNotFound() {
		return "/admin/common/resource_not_found";
	}
	
	
	/**
	 * 跳转机构信息管理页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/orgManager" ,method = {RequestMethod.GET,RequestMethod.POST} )
	public String orgManager(ModelMap model,HttpServletRequest request) {
		
		User user = userService.getCurrent();
		if(user != null) {
			Orgnization orgnization = orgnizationService.find(user.getOrgId());
			model.addAttribute("orgnization",orgnization);
			
			//省
	        Map<String, Object> params = new HashMap<>();
	        params.put("parentId", 1);
	        model.addAttribute("provinces", this.areaService.findList(params));

	        //市
	        if (orgnization.getProvinceId() != null && !"".equals(orgnization.getProvinceId())) {
	            params = new HashMap<>();
	            params.put("parentId",orgnization.getProvinceId());

	            List<Area> areas = this.areaService.findList(params);

	            model.addAttribute("citys", areas);
	        }

	        //区
	        if (orgnization.getCityId() != null && !"".equals(orgnization.getCityId())&&orgnization.getAreaId() != null && !"".equals(orgnization.getAreaId())) {
	            params = new HashMap<>();
	            params.put("parentId",orgnization.getCityId());

	            List<Area> areas = this.areaService.findList(params);

	            model.addAttribute("areas", areas);
	        }
		}
		
		String token1 = FileServiceImpl.getToken();
		model.addAttribute("token1", token1);
		model.addAttribute("PREFIX1", FileServiceImpl.QINIU_PREFIX);
		
		String token2 = FileServiceImpl.getToken();
		model.addAttribute("token2", token2);
		model.addAttribute("PREFIX2", FileServiceImpl.QINIU_PREFIX);
		
		return "/admin/common/orgManager";
	}
	
	/**
	 * 跳转机构二维码页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/orgQrcode" ,method = {RequestMethod.GET,RequestMethod.POST} )
	public String orgQrcode(ModelMap model,HttpServletRequest request) {
		User user = userService.getCurrent();
		if(user != null) {
			Orgnization orgnization = orgnizationService.find(user.getOrgId());
			model.addAttribute("orgnization",orgnization);
		}
		return "/admin/common/orgQrcode";
	}
	
	/**
	 * 机构二维码下载
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/downloadOrgQrcode" ,method = {RequestMethod.GET,RequestMethod.POST} )
	public void downloadOrgQrcode(ModelMap model,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception {
		
		String orgImage = request.getParameter("orgImage");
		
		if (StringUtils.isNotEmpty(orgImage)) {
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				response.setHeader("Content-Disposition", "attachment;filename=" + new String("机构二维码.jpg".getBytes("UTF-8"), "ISO8859-1"));
			} else{
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("机构二维码.jpg", "UTF-8"));
			}
		} else {
			response.setHeader("Content-disposition", "attachment");
		}
		   
		response.setContentType("image/jpg");
		
		URL url = new URL(orgImage);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		DataInputStream in = new DataInputStream(connection.getInputStream());
		DataOutputStream out = new DataOutputStream(response.getOutputStream());
        byte[] buffer = new byte[4096];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        out.flush();
        in.close();
        
        connection.disconnect();
	}
	
	/**
	 * 城市联动——城市
	 */
	@ResponseBody
	@RequestMapping(value = "/city", method = RequestMethod.POST)
	public String city(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String parentId = request.getParameter("parentId");
		if(StringUtil.isEmpty(parentId)){
			   return ReturnJson.jsonString("OK", "","1");
		}
	   return ReturnJson.jsonString("OK", areaService.findList("parentId", Long.parseLong(parentId)),"2");
	}
	
	/**
	 * 城市联动——区县
	 */
	@ResponseBody
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	public String area(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String parentId = request.getParameter("parentId");
		if(StringUtil.isEmpty(parentId)){
			   return ReturnJson.jsonString("OK", "","1");
		}
	   return ReturnJson.jsonString("OK", areaService.findList("parentId", Long.parseLong(parentId)),"2");
	}
	
	/**
	 * 更新机构信息
	 */
	/*@RequestMapping(value = "/updateOrgManager", method =RequestMethod.GET)
	public String updateOrgManager(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String id = request.getParameter("id");
		String backgroundName = request.getParameter("backgroundName");
		String backgroundLogo = request.getParameter("backgroundLogo");
		String distance = request.getParameter("distance");
		String name = request.getParameter("name");
		String telPhone = request.getParameter("telPhone");
		String image = request.getParameter("image");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String areaId = request.getParameter("areaId");
		String location = request.getParameter("location");
		String locLng = request.getParameter("locLng");
		String locLat = request.getParameter("locLat");
		String description = request.getParameter("description");
		
		if(!StringUtil.isEmpty(id)) {
			Orgnization orgnization = orgnizationService.find(Long.parseLong(id));
			
			if(!StringUtil.isEmpty(backgroundName)){
				orgnization.setBackgroundName(backgroundName);
			};
			
			if(!StringUtil.isEmpty(backgroundLogo)){
				orgnization.setBackgroundLogo(backgroundLogo);
			};
			
			if(!StringUtil.isEmpty(distance)){
				orgnization.setDistance(new BigDecimal(distance));
			};
			
			if(!StringUtil.isEmpty(name)){
				orgnization.setName(name);
			};
			
			if(!StringUtil.isEmpty(telPhone)){
				orgnization.setTelPhone(telPhone);
			};
			
			if(!StringUtil.isEmpty(image)){
				orgnization.setImage(image);
			};
			
			if(!StringUtil.isEmpty(provinceId)&&!provinceId.equals(orgnization.getProvinceId())){
				orgnization.setProvinceId(Long.parseLong(provinceId));
				Area area1 = areaService.find(Long.parseLong(provinceId));
				orgnization.setProvince(area1.getName());
				
			};
			
			if(!StringUtil.isEmpty(cityId)&&!cityId.equals(orgnization.getCityId())){
				orgnization.setCityId(Long.parseLong(cityId));
				Area area2 = areaService.find(Long.parseLong(cityId));
				orgnization.setCity(area2.getName());
			};
			
			if(!StringUtil.isEmpty(areaId)&&!areaId.equals(orgnization.getAreaId())){
				orgnization.setAreaId(Long.parseLong(areaId));
				Area area3 = areaService.find(Long.parseLong(areaId));
				orgnization.setArea(area3.getName());
			};
			
			if(!StringUtil.isEmpty(location)){
				orgnization.setLocation(location);
			};
			
			if(!StringUtil.isEmpty(locLng)){
				orgnization.setLocLng(new BigDecimal(locLng));
			};
			
			if(!StringUtil.isEmpty(locLat)){
				orgnization.setLocLat(new BigDecimal(locLat));
			};
			
			if(!StringUtil.isEmpty(description)){
				orgnization.setDescription(description);
			};
			
			orgnizationService.update(orgnization);
			
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		}
		
		return "redirect:/admin/common/index.jhtml";
		//return "/admin/login";
		//return "redirect:/admin/login.jhtml";
	}*/
	
	/**
	 * 更新机构信息
	 */
	@RequestMapping(value = "/updateOrgManager", method =RequestMethod.POST)
	@ResponseBody
	public Message updateOrgManager(Long id,String backgroundName,String backgroundLogo,Double distance,String name,
			String telPhone,String image,Long provinceId,Long cityId,Long areaId,
			String location,Double locLng,Double locLat,String description) {
		
		
		if(id != null) {
			Orgnization orgnization = orgnizationService.find(id);
			
			if(!StringUtil.isEmpty(backgroundName)){
				orgnization.setBackgroundName(backgroundName);
			};
			
			if(!StringUtil.isEmpty(backgroundLogo)){
				orgnization.setBackgroundLogo(backgroundLogo);
			};
			
			if(distance !=null){
				orgnization.setDistance(new BigDecimal(distance));
			};
			
			if(!StringUtil.isEmpty(name)){
				orgnization.setName(name);
			};
			
			if(!StringUtil.isEmpty(telPhone)){
				orgnization.setTelPhone(telPhone);
			};
			
			if(!StringUtil.isEmpty(image)){
				orgnization.setImage(image);
			};
			
			if(provinceId != null &&!provinceId.equals(orgnization.getProvinceId())){
				orgnization.setProvinceId(provinceId);
				Area area1 = areaService.find(provinceId);
				orgnization.setProvince(area1.getName());
				
			};
			
			if(cityId != null &&!cityId.equals(orgnization.getCityId())){
				orgnization.setCityId(cityId);
				Area area2 = areaService.find(cityId);
				orgnization.setCity(area2.getName());
			};
			
			if(areaId != null &&!areaId.equals(orgnization.getAreaId())){
				orgnization.setAreaId(areaId);
				Area area3 = areaService.find(areaId);
				orgnization.setArea(area3.getName());
			};
			
			if(!StringUtil.isEmpty(location)){
				orgnization.setLocation(location);
			};
			
			if(locLng != null){
				orgnization.setLocLng(new BigDecimal(locLng));
			};
			
			if(locLat != null){
				orgnization.setLocLat(new BigDecimal(locLat));
			};
			
			if(!StringUtil.isEmpty(description)){
				orgnization.setDescription(description);
			};
			
			orgnizationService.update(orgnization);
			
		}
		
		return SUCCESS_MESSAGE;
		//return "/admin/login";
		//return "redirect:/admin/login.jhtml";
	}
	
	/**
	 * 更新后台名称和logo
	 */
/*	@RequestMapping(value = "/updateOrgManager", method =RequestMethod.POST)
	public String updateBackGround(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		return "redirect:index.jhtml";
		
	}*/
	
	/**
	 * 代课
	 */
	@RequestMapping(value = { "/substitute/{courseId}/{courseTimeId}" }, method = { RequestMethod.GET })
	public String  substitute(@PathVariable Long courseId,@PathVariable Long courseTimeId,ModelMap model) {
		
		Course course = courseService.find(courseId);
		if(course != null) {
			
			Teacher	teacher1 = teacherService.find(course.getTeacherId());
			if(teacher1 != null){
				model.addAttribute("teacher", teacher1.getNickname());
				model.addAttribute("teacherId", teacher1.getId());
			}
			
			if(course.getSubstitute() != null) {
				Teacher	teacher2 = teacherService.find(course.getSubstitute());
				if(teacher2 != null){
					model.addAttribute("substitute", teacher2.getNickname());
					model.addAttribute("substituteId", teacher2.getId());
				}
				
			}
			
			Map map = new HashMap();
			User user = userService.getCurrent();
			if(user != null) {
				map.put("orgId", user.getOrgId());
			}
			
			List<Teacher> teacherList = teacherService.findTeacherListByOrgId(map);
			List<AppTeacherInfo> appTeacherInfoList = new ArrayList();
			
			for (Teacher teacher3 : teacherList) {
				AppTeacherInfo appTeacherInfo = new AppTeacherInfo();
				appTeacherInfo.setId(teacher3.getId());
				appTeacherInfo.setName(teacher3.getNickname());
				appTeacherInfo.setPhone(teacher3.getAccount());
				appTeacherInfoList.add(appTeacherInfo);
			}
			model.addAttribute("appTeacherInfoList", appTeacherInfoList);
		}
		
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseTimeId", courseTimeId);
		
		return "/admin/common/substitute";
	}
	
	/**
	 * 代课更新
	 */
	@RequestMapping(value = "/updateSubstitute", method = RequestMethod.POST)
	public String updateSubstitute(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String courseId = request.getParameter("courseId");
		String courseTimeId = request.getParameter("courseTimeId");
		String teacherId = request.getParameter("teacherId");
		String substituteId = request.getParameter("substituteId");
		
		Course course = courseService.find("id",Long.parseLong(courseId));
		course.setTeacherId(Long.parseLong(teacherId));
		course.setSubstitute(Long.parseLong(substituteId));
		
		Teacher teacher = teacherService.find("id", Long.parseLong(substituteId));
		if(teacher != null) {
			course.setSubstituteName(teacher.getNickname());
		}
		course.setSubstituteType(2);
		
		courseService.update(course);
		
		return "redirect:index.jhtml";
	}
	
	/**
	 * 调课
	 */
	@RequestMapping(value = { "/courseChangeTime/{courseId}/{courseTimeId}" }, method = { RequestMethod.GET })
	public String  courseChangeTime(@PathVariable Long courseId,@PathVariable Long courseTimeId, ModelMap model) {
		
		CourseTeaTime courseTeaTime = courseTeaTimeService.find(courseTimeId);
		
		model.addAttribute("courseTeaTime", courseTeaTime);
		model.addAttribute("courseId", courseId);
		
		return "/admin/common/course_change_time";
	}
	
	/**
	 * 调课更新
	 */
	@RequestMapping(value = "/updateCourseTime", method = RequestMethod.POST)
	public String updateCourseTime(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String courseId = request.getParameter("courseId");
		String courseTeaTimeId = request.getParameter("courseTeaTimeId");
		String changeTime = request.getParameter("changeTime");
		String changeStart = request.getParameter("changeStart");
		String changeEnd = request.getParameter("changeEnd");
		String reason = request.getParameter("reason");
		
		CourseTeaTime courseTeaTime = courseTeaTimeService.find("id",Long.parseLong(courseTeaTimeId));
		Course course = courseService.find("id", Long.parseLong(courseId));
		
		if(courseTeaTime != null) {
			
			Map map1 = new HashMap<>();
			map1.put("teacherId", courseTeaTime.getTeacherId());
			map1.put("courseTime", StringUtil.StringDateToDate((StringUtil.filterSQL(changeTime))));
			
			int stratTime = Integer.parseInt(changeStart.replace(":", ""));
			int endTime = Integer.parseInt(changeEnd.replace(":", ""));
			
			List<CourseTeaTime> CourseTeaTimeList = courseTeaTimeService.findList(map1);
			if(CourseTeaTimeList != null && CourseTeaTimeList.size() > 0) {
				for (CourseTeaTime courseTeaTime2 : CourseTeaTimeList) {
					int time1 = Integer.parseInt(courseTeaTime2.getCourseTime1().replace(":", ""));
					int time2 = Integer.parseInt(courseTeaTime2.getCourseTime2().replace(":", ""));
					
					
					//判断教师上课时间是否有冲突
					if(stratTime >= time1 && stratTime < time2 && endTime > time1 && endTime <= time2) {
						addFlashMessage(redirectAttributes, new Message(Type.warn, "该教师当天上课时间段有冲突，请重新选择时间段", null));
						return "redirect:/admin/common/courseChangeTime/"+Long.parseLong(courseId)+"/"+Long.parseLong(courseTeaTimeId)+".jhtml";
					}
					
					//判断教师上课时间是否有冲突
					if(stratTime < time1 && endTime > time2) {
						addFlashMessage(redirectAttributes, new Message(Type.warn, "该教师当天上课时间段有冲突，请重新选择时间段", null));
						return "redirect:/admin/common/courseChangeTime/"+Long.parseLong(courseId)+"/"+Long.parseLong(courseTeaTimeId)+".jhtml";
					}
				}
			}
			
			
			//调课时将原上课时间保存到另一字段，原字段保存调课后的时间
			courseTeaTime.setOldTime(courseTeaTime.getCourseTime());
			courseTeaTime.setOldTime1(courseTeaTime.getCourseTime1());
			courseTeaTime.setOldTime2(courseTeaTime.getCourseTime2());
			
			courseTeaTime.setCourseTime(StringUtil.StringDateToDate((StringUtil.filterSQL(changeTime))));
			courseTeaTime.setCourseTime1(StringUtil.filterSQL(changeStart));
			courseTeaTime.setCourseTime2(StringUtil.filterSQL(changeEnd));
			courseTeaTime.setReason(StringUtil.filterSQL(reason));
			
			Date changeTimeDate = StringUtil.StringDateToDate((StringUtil.filterSQL(changeTime)));
			String str = StringUtil.date2String2(changeTimeDate);
			String str1 = str + changeStart.replace(":", "");
			String str2 = str + changeEnd.replace(":", "");
			
			courseTeaTime.setCourseTime3(Long.parseLong(str1));
			courseTeaTime.setCourseTime4(Long.parseLong(str2));
			courseTeaTime.setCTime(changeTimeDate);
			courseTeaTime.setType(2);
			
			courseTeaTimeService.update(courseTeaTime);
			
			//更新学生上课时间
			Map map = new HashMap();
			map.put("courseId", Long.parseLong(courseId));
			map.put("courseTime", courseTeaTime.getOldTime());
			map.put("courseTime1", courseTeaTime.getOldTime1());
			map.put("courseTime2", courseTeaTime.getOldTime2());
			
			List<CourseStuTime> courseStuTimeList = courseStuTimeService.findList(map);
			for (CourseStuTime courseStuTime2 : courseStuTimeList) {
				courseStuTime2.setCourseTime(StringUtil.StringDateToDate((StringUtil.filterSQL(changeTime))));
				courseStuTime2.setCourseTime1(StringUtil.filterSQL(changeStart));
				courseStuTime2.setCourseTime2(StringUtil.filterSQL(changeEnd));
				courseStuTime2.setCourseTime3(Long.parseLong(str1));
				courseStuTime2.setCourseTime4(Long.parseLong(str2));
				
				courseStuTimeService.update(courseStuTime2);
				
				//发送推送消息-家长
				if(courseTeaTime.getOldTime() != null) {
					Student student = studentService.find("id",courseStuTime2.getStudentId());
					Parent parent = parentService.find("id", student.getUid());
					
					String content = parent.getRelName() +"家长您好，原定于"+ StringUtil.date2String13(courseTeaTime.getOldTime())
					+ courseTeaTime.getOldTime1() + "~"+ courseTeaTime.getOldTime2() + "的" + course.getName() + "课程因" 
					+ courseTeaTime.getReason() + "的原因调整至" + StringUtil.date2String13(courseStuTime2.getCourseTime())
					+ courseStuTime2.getCourseTime1() +"~"+ courseStuTime2.getCourseTime2() + ",特此通知，敬请原谅。";
					
					SystemMes systemMes1 = new SystemMes();
					systemMes1.setUserId(parent.getId());
					systemMes1.setUserName(parent.getRelName());
					systemMes1.setCreateDate(new Date());
					systemMes1.setContent(content);
					systemMes1.setType(2);
					systemMes1.setTitle("调课通知");
					
					systemMesService.save(systemMes1);
					
					OrgDynamic orgDynamic = new OrgDynamic();
					
					orgDynamic.setTargetId(systemMes1.getId());
					orgDynamic.setOrgId(courseTeaTime.getOrgId());
					Orgnization orgnization = orgnizationService.find(courseTeaTime.getOrgId());
					orgDynamic.setImgUrl(orgnization.getImage());
					orgDynamic.setAvatar(orgnization.getImage());
					orgDynamic.setPrompt("调课通知");
					orgDynamic.setTextContent(content);
					orgDynamic.setCreateDate(new Date());
					orgDynamic.setUserId(courseTeaTime.getTeacherId());
					orgDynamic.setType(5);
					orgDynamic.setUserType(1);
					orgDynamic.setStatus(1);
					
					orgDynamicService.save(orgDynamic);
					userUnreadService.setUserUnread(1, student.getUid(), 5);
					
					JpushRunnable t1 = new JpushRunnable(systemMes1.getTitle(), "p"+parent.getId(), "type",
							Constants.system+"_"+systemMes1.getId() + "");
					t1.start();
				}
			}
			
			
			//发送推送消息-教师
			Teacher teacher = teacherService.find("id", courseTeaTime.getTeacherId());
			
			
			if(courseTeaTime.getOldTime() != null) {
				
				String content = teacher.getNickname() +"老师您好，原定于"+ StringUtil.date2String13(courseTeaTime.getOldTime())
				+ courseTeaTime.getOldTime1() + "~"+ courseTeaTime.getOldTime2() + "的" + course.getName() + "课程因" 
				+ courseTeaTime.getReason() + "的原因调整至" + StringUtil.date2String13(courseTeaTime.getCourseTime())
				+ courseTeaTime.getCourseTime1() +"~"+ courseTeaTime.getCourseTime2() + ",特此通知，敬请原谅。";
				
				SystemMes systemMes2 = new SystemMes();
				systemMes2.setUserId(teacher.getId());
				systemMes2.setUserName(teacher.getNickname());
				systemMes2.setCreateDate(new Date());
				systemMes2.setContent(content);
				systemMes2.setType(1);
				systemMes2.setTitle("调课通知");
				
				systemMesService.save(systemMes2);
				
				OrgDynamic orgDynamic = new OrgDynamic();
				
				orgDynamic.setTargetId(systemMes2.getId());
				orgDynamic.setOrgId(courseTeaTime.getOrgId());
				Orgnization orgnization = orgnizationService.find(courseTeaTime.getOrgId());
				orgDynamic.setImgUrl(orgnization.getImage());
				orgDynamic.setAvatar(orgnization.getImage());
				orgDynamic.setPrompt("调课通知");
				orgDynamic.setTextContent(content);
				orgDynamic.setCreateDate(new Date());
				orgDynamic.setUserId(courseTeaTime.getTeacherId());
				orgDynamic.setType(5);
				orgDynamic.setUserType(2);
				orgDynamic.setStatus(1);
				
				orgDynamicService.save(orgDynamic);
				userUnreadService.setUserUnread(2, teacher.getId(), 5);
				
				JpushRunnable2 t2 = new JpushRunnable2(systemMes2.getTitle(), "t"+teacher.getId(), "type",
						Constants.system+"_"+orgDynamic.getId()+ "");
				t2.start();

			}
	}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:index.jhtml";
	}
	
	
}