package com.framework.loippi.controller.admin;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.RandomGUIDUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.entity.Area;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.Log;
import com.framework.loippi.entity.OrgTeacherview;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.LogService;
import com.framework.loippi.service.OrgStudentviewService;
import com.framework.loippi.service.OrgTeacherviewService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.support.Message.Type;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.XDigestUtils;
import com.framework.loippi.utils.qiniu.FileService4Qiniu;
import com.framework.loippi.utils.qiniu.FileServiceImpl;
import com.framework.loippi.utils.qiniu.FileServiceImpl4Qiniu;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.redis.JedisStringCache;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ibm.icu.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 机构
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminOrgnizationController")
@RequestMapping({ "/admin/orgnization" })
public class OrgnizationController extends GenericController {

	@Resource
	private OrgnizationService orgnizationService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private ParentService parentSrvice;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private OrgUserService orgUserService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private OrgStudentviewService orgStudentviewService;
	
	@Resource
	private OrgTeacherviewService orgTeacherviewService;
	
	@Resource
	private CourseCommentService courseCommentService;

	@Resource(name = "logServiceImpl")
	private LogService logService;
	
	@Autowired
    protected JedisCache jedisCache;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add" ,method = {RequestMethod.GET,RequestMethod.POST} )
	public String add(ModelMap model,HttpServletRequest request) {
		
		String token = FileServiceImpl.getToken();
		
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
		model.addAttribute("provinces", areaService.findList("treePath", ","));
		
		
		return "/admin/orgnization/add";
	}
	
	/**
	 * 城市联动——城市
	 */
	@ResponseBody
	@RequestMapping(value = "/city", method = RequestMethod.POST)
	public String city(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String id = request.getParameter("id");
		if(id != null){
			List<Area> areas = areaService.findList("parentId",Long.parseLong(id));
			return ReturnJson.jsonString("OK", areas,"2");
		}
	   return ReturnJson.jsonString("OK", "","1");
	}
	
	/**
	 * 城市联动——区县
	 */
	@ResponseBody
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	public String area(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String id = request.getParameter("id");
		if(id != null){
			List<Area> areas = areaService.findList("parentId",Long.parseLong(id));
			return ReturnJson.jsonString("OK", areas,"2");
		}
		return ReturnJson.jsonString("OK", "","1");
	}
	
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.GET,RequestMethod.POST})
	public String save(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Orgnization orgnization = new Orgnization();
		
		String status = request.getParameter("status");
		String name = request.getParameter("name");
		String telPhone = request.getParameter("telPhone");
		String serviceName = request.getParameter("serviceName");
		String servicePhone = request.getParameter("servicePhone");
		String image = request.getParameter("image");
		String fee = request.getParameter("fee");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String areaId = request.getParameter("areaId");
		String location = request.getParameter("location");
		String locLng = request.getParameter("locLng");
		String locLat = request.getParameter("locLat");
		String description = request.getParameter("description");
		
		RandomGUIDUtil myGUID = new RandomGUIDUtil();
		String uuid = myGUID.valueAfterMD5;
		
		String account = request.getParameter("account");
		String password = request.getParameter("password1");
		orgnization.setAccount(account);
		//检查用户是否存在
		User user=userService.find("username", account);
		if(user!=null){
			addFlashMessage(redirectAttributes, new Message(Type.error, "该账号已存在，请使用其他账号", null));
			return "redirect:list.jhtml";
		}
		
		if(!StringUtil.isEmpty(status)){
			orgnization.setStatus(Integer.parseInt(status));
		};
		
		if(!StringUtil.isEmpty(name)){
			orgnization.setName(name);
		};
		
		if(!StringUtil.isEmpty(telPhone)){
			orgnization.setTelPhone(telPhone);
		};
		
		
		if(!StringUtil.isEmpty(serviceName)){
			orgnization.setServiceName(serviceName);
		};
		
		if(!StringUtil.isEmpty(servicePhone)){
			orgnization.setServicePhone(servicePhone);
		};
		
		if(!StringUtil.isEmpty(image)){
			orgnization.setImage(image);
		};
		
		if(!StringUtil.isEmpty(fee)){
			orgnization.setFee(new BigDecimal(fee));
		};
		
		if(!StringUtil.isEmpty(provinceId)){
			orgnization.setProvinceId(Long.parseLong(provinceId));
			Area area = areaService.find(Long.parseLong(provinceId));
			orgnization.setProvince(area.getName());
		};
		
		if(!StringUtil.isEmpty(cityId)){
			orgnization.setCityId(Long.parseLong(cityId));
			Area area = areaService.find(Long.parseLong(cityId));
			orgnization.setCity(area.getName());
		};
		
		if(!StringUtil.isEmpty(areaId)){
			orgnization.setAreaId(Long.parseLong(areaId));
			Area area = areaService.find(Long.parseLong(areaId));
			orgnization.setArea(area.getName());
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
		
		orgnization.setCreateDate(new Date());
		orgnization.setFavStar(5);
		orgnization.setTeacherCount(0);
		orgnization.setStudentCount(0);
		orgnization.setUuid(uuid);
		
		//由uuid生成二维码图片并保存
		//二维码内容所使用编码
		 HashMap hints = new HashMap(); 
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		 
		try {
		    //生成QRcode
		    BitMatrix matrix = new MultiFormatWriter().encode(orgnization.getUuid(), BarcodeFormat.QR_CODE, 250, 250 , hints);
		    
		    File outputFile = new File("机构二维码.png");
		    
		    MatrixToImageWriter.writeToFile(matrix, "PNG", outputFile);
		    
		    FileServiceImpl4Qiniu fileServiceImpl4Qiniu = new FileServiceImpl4Qiniu();
		    String orgImage = fileServiceImpl4Qiniu.upload(outputFile, false);
		    
		    orgnization.setOrgImage(orgImage);
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
	     orgnizationService.save(orgnization);
	     
	     
	     System.err.println(orgnization.getId());
	     
	      user=new User();
	      user.setNickname(account);
	      user.setUsername(account);
		user.setPassword(DigestUtils.md5Hex(password));
		user.setIsLocked(false);
		user.setLoginFailureCount(0);
		user.setIsEnabled(Boolean.TRUE);
		user.setLockedDate(null);
		user.setLoginDate(null);
		user.setLoginIp(null);
		user.setCreateDate(new Date());
		user.setType(2);
		user.setRoleId(3l);
		user.setOrgId(orgnization.getId());
		userService.save(user);
	     
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		Orgnization orgnization = orgnizationService.find(id);
		model.addAttribute("orgnization", orgnization);
		
		String token = FileServiceImpl.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
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
		
		return "/admin/orgnization/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		Orgnization orgnization = orgnizationService.find(id);
		model.addAttribute("orgnization", orgnization);
		return "/admin/orgnization/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Orgnization orgnization, RedirectAttributes redirectAttributes) {
		
		Map map = new HashMap<>();
		Orgnization orgnization2 = orgnizationService.find(orgnization.getId());
		
		map.put("username", orgnization2.getAccount());
		map.put("orgId", orgnization.getId());
		List<User> userList = userService.findList(map);
		
		if(userList != null) {
			User user = userList.get(0);
			user.setNickname(orgnization.getAccount());
			user.setPassword(orgnization.getPassword());
			user.setUsername(orgnization.getAccount());
			if(orgnization.getStatus() == 1) {
				user.setIsEnabled(Boolean.TRUE);
			}
			if(orgnization.getStatus() == 2) {
				user.setIsEnabled(Boolean.FALSE);
			}
			user.setUpdateDate(new Date());
			
			userService.update(user);
		}
		
		
		if(orgnization.getProvinceId() != null){
		Area area1 = areaService.find(orgnization.getProvinceId());
		orgnization.setProvince(area1.getName());
		}
		
		if(orgnization.getCityId() != null){
		Area area2 = areaService.find(orgnization.getCityId());
		orgnization.setCity(area2.getName());
		}
		
		if(orgnization.getAreaId() != null){
		Area area3 = areaService.find(orgnization.getAreaId());
		orgnization.setArea(area3.getName());
		}
		
		orgnizationService.update(orgnization);
		
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
		
		Page<Orgnization> pages = this.orgnizationService.findByPage(pageable);
		List<Orgnization> orgnizationServiceList = pages.getContent();
		
		//统计用户评价分数
		int favStar = 0;
		int num = 0;
		if(orgnizationServiceList != null) {
			for (Orgnization orgnization : orgnizationServiceList) {
				//判断该机构是否有人评价且评价状态为启用,有则求平均值作为评价分数,没有评价分数为0
				List<CourseComment> courseCommentList = courseCommentService.findList("orgId",orgnization.getId());
				if(courseCommentList != null && courseCommentList.size() > 0) {
					for (CourseComment courseComment : courseCommentList) {
						if(courseComment.getStatus()!=null && courseComment.getStatus() == 1) {
							favStar = favStar  + courseComment.getFavStar();
							num ++;
						}
					}
					
					if(num != 0) {
						favStar = favStar/num;
					}
					
					orgnization.setFavStar(favStar);
					orgnization.setUserRate(favStar);
					
				} else {
					orgnization.setUserRate(0);
				}
				
				orgnizationService.update(orgnization);
			}
		}
		
		
		model.addAttribute("page",pages);
		
		model.addAttribute("provinces", areaService.findList("parentId", 1));
		
		return "/admin/orgnization/list";
	}
	
	/**
	 * 学员列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/studentlist/{id}" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String studentlist(Pageable pageable,@PathVariable Long id,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = new HashMap<>();
		
		Long orgId = id;
		String filter_account = request.getParameter("filter_account");
		String filter_nickname = request.getParameter("filter_nickname");
		
		if(orgId != null) {
			paramter.put("orgId",orgId );
		}
		
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_nickname)) {
			paramter.put("filter_nickname", StringUtil.filterSQL(filter_nickname));
		}
		
		pageable.setParameter(paramter);
		model.addAttribute("page", this.orgStudentviewService.findByPage(pageable));
		
		Orgnization orgnization = orgnizationService.find(id);
		model.addAttribute("orgnization", orgnization);
		
		return "/admin/orgnization/studentlist";
	}
	
	/**
	 * 教师列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/teacherlist/{id}" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String teacherlist(Pageable pageable,@PathVariable Long id,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = new HashMap<>();
		
		Long orgId = id;
		String filter_account = request.getParameter("filter_account");
		String filter_nickname = request.getParameter("filter_nickname");
		String filter_teacherType = request.getParameter("filter_teacherType");
		String filter_status = request.getParameter("filter_status");
		
		if(orgId != null) {
			paramter.put("orgId",orgId );
		}
		
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_nickname)) {
			paramter.put("filter_nickname", StringUtil.filterSQL(filter_nickname));
		}
		
		if(!StringUtil.isEmpty(filter_teacherType)) {
			paramter.put("filter_teacherType", Integer.parseInt(StringUtil.filterSQL(filter_teacherType)));
		}
		
		if(!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}
		
		pageable.setParameter(paramter);
		Page<OrgTeacherview> pages = this.orgTeacherviewService.findByPage(pageable);
		List<OrgTeacherview> OrgTeacherviewList = pages.getContent();
		
		if(OrgTeacherviewList != null) {
			for (OrgTeacherview orgTeacherview : OrgTeacherviewList) {
				//统计用户评价分数
				int favStar = 0;
				int num = 0;
				
				List<CourseComment> courseCommentList = courseCommentService.findList("teacherId", orgTeacherview.getTeacherId());
				if(courseCommentList != null && courseCommentList.size() > 0) {
					for (CourseComment courseComment : courseCommentList) {
						if(courseComment != null) {
							if(courseComment.getStatus()!=null && courseComment.getStatus() == 1) {
								favStar = favStar  + courseComment.getFavStar();
								num ++;
							}
						}
					}
					if(num != 0) {
						favStar = favStar/num;
					}
					
					orgTeacherview.setUserRate(favStar);
				} else {
					orgTeacherview.setUserRate(0);
				}
			}
		}
		
		model.addAttribute("page", pages);
		
		Orgnization orgnization = orgnizationService.find(id);
		model.addAttribute("orgnization", orgnization);
		return "/admin/orgnization/teacherlist";
	}


	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(HttpServletRequest request,Long[] ids) {

		
		String username = userService.getCurrentUsername();
		for (int i = 0; i < ids.length; i++) {
			Orgnization	orgnization=	orgnizationService.find(ids[i]);
		

			String operator = username;
			String ip = request.getRemoteAddr();
			Log log = new Log();
			log.setOperation("删除机构"+orgnization.getName());
			log.setOperator(operator);
			log.setContent(JSONObject.fromObject(orgnization).toString());
			log.setParameter(JSONObject.fromObject(orgnization).toString());
			log.setIp(ip);
			log.setCreateDate(new Date());
			logService.save(log);
			
			
			
			
			List<User>	users=userService.findList("orgId", ids[i]);
		if(users!=null){
			for (User user : users) {
				 log = new Log();
				log.setOperation("删除"+orgnization.getName()+"机构的人");
				log.setOperator(operator);
				log.setContent(JSONObject.fromObject(user).toString());
				log.setParameter(JSONObject.fromObject(user).toString());
				log.setIp(ip);
				log.setCreateDate(new Date());
				logService.save(log);
				userService.delete(user.getId());
				
			}
		}
			
		}
		
		this.orgnizationService.deleteAll(ids);
		
		
		return SUCCESS_MESSAGE;
	}
	
	/**
	 *过滤 
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();
		
		String filter_id = request.getParameter("filter_id");
		String filter_account = request.getParameter("filter_account");
		String filter_name = request.getParameter("filter_name");
		String filter_provinceId = request.getParameter("filter_provinceId");
		String filter_cityId = request.getParameter("filter_cityId");
		String filter_areaId = request.getParameter("filter_areaId");
		String filter_status = request.getParameter("filter_status");
		
		if(!StringUtil.isEmpty(filter_id)) {
			paramter.put("filter_id", StringUtil.filterSQL(filter_id));
		}
		
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_name)) {
			paramter.put("filter_name", StringUtil.filterSQL(filter_name));
		}
		
		if(!StringUtil.isEmpty(filter_provinceId)) {
			paramter.put("filter_provinceId", Long.parseLong(StringUtil.filterSQL(filter_provinceId)));
		}
		
		if(!StringUtil.isEmpty(filter_cityId)) {
			paramter.put("filter_cityId", Long.parseLong(StringUtil.filterSQL(filter_cityId)));
		}
		
		if(!StringUtil.isEmpty(filter_areaId)) {
			paramter.put("filter_areaId", Long.parseLong(StringUtil.filterSQL(filter_areaId)));
		}

		if(!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
		}

		return paramter;
	}
	
	/**
	 * 启用机构
	 */
	@RequestMapping(value={"/startusing"},method={RequestMethod.POST})
	@ResponseBody
	public Message startusing(Long id) {
		Orgnization orgnization = this.orgnizationService.find(id);
		if(orgnization != null) {
			orgnization.setStatus(1);
		}
		orgnizationService.update(orgnization);
		
		User user = userService.find("orgId",id);
		if(user != null) {
			user.setIsEnabled(true);
		}
		userService.update(user);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 禁用机构
	 */
	@RequestMapping(value={"/forbidusing"},method={RequestMethod.POST})
	@ResponseBody
	public Message forbidusing(Long id) {
		Orgnization orgnization = this.orgnizationService.find(id);
		if(orgnization != null) {
			orgnization.setStatus(2);
		}
		orgnizationService.update(orgnization);
		
		User user = userService.find("orgId",id);
		if(user != null) {
			user.setIsEnabled(false);
		}
		userService.update(user);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 启用教师
	 */
	@RequestMapping(value={"/startTeacher"},method={RequestMethod.POST})
	@ResponseBody
	public Message startTeacher(Long id) {
		Teacher teacher = this.teacherService.find(id);
		if(teacher != null) {
			teacher.setStatus(1);
		}
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
		
		if(teacher != null) {
			teacher.setStatus(2);
		}
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
	 * 修改密码
	 */
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public String updatePwd(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String orgId = request .getParameter("orgId");
		String account = request .getParameter("account1");
		String password = request.getParameter("filter_password");
		
		if(!StringUtil.isEmpty(orgId) && !StringUtil.isEmpty(account) ) {
			Map map = new HashMap<>();
			map.put("orgId", Long.parseLong(orgId));
			map.put("username", account);
			
			List<User> userList = userService.findList(map);
			if(userList != null && userList.size() > 0) {
				User user = userList.get(0);
				user.setPassword(XDigestUtils.md5Hex(password));
				user.setUpdateDate(new Date());
				
				userService.update(user);
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			}
		}
		
		return "redirect:edit/"+Long.parseLong(orgId)+".jhtml";
	}
	
}
