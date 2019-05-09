package com.framework.loippi.controller.app;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.SystemAppversion;
import com.framework.loippi.entity.SystemFeedback;

import com.framework.loippi.entity.Telphone;

import com.framework.loippi.entity.Teacher;

import com.framework.loippi.service.SystemAppversionService;
import com.framework.loippi.service.SystemFeedbackService;

import com.framework.loippi.service.TelphoneService;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.service.TeacherService;



//用户信息  教师端
@Controller
@RequestMapping("api/app/platteacher/user/*")
public class AppPlatTeacherUserController extends BaseController {
	
	@Autowired
	private SystemFeedbackService systemFeedbackService; 
	@Resource
	private SystemAppversionService systemAppversionService;
	

	@Resource
	private TelphoneService	telphoneService;

	@Resource
	private TeacherService teacherService;

	/**
	 * 意见反馈
	 */
	@ResponseBody
	@RequestMapping(value="feedback",method=RequestMethod.POST)
	public String feedback(HttpServletRequest request,HttpServletResponse response) {
		
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);

		if(loginUser == null) {
			return 	ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.USER_SESS_EXPIRED);
		} else if (loginUser.getStatus() == 2) {
			return ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.USER_ACCOUNT_LOGINED);
		}
		if(StringUtil.isEmpty(type)){
			type="2";
		}
		SystemFeedback feedback = new SystemFeedback();
		feedback.setType(Integer.valueOf(type));
		feedback.setContent(content);
		feedback.setMobile(loginUser.getPhone());
		
		Teacher teacher = teacherService.find(loginUser.getId());
		if(teacher != null) {
			feedback.setNickname(teacher.getNickname());
		}
		
		feedback.setCreateDate(new Date());
	
		systemFeedbackService.save(feedback);
		
		return ReturnJson.jsonString("OK", AppConstants.OK);
	}
	
	
	/**
	 * 联系客服
	 */
	@ResponseBody
	@RequestMapping(value="phone",method=RequestMethod.POST)
	public String phone(HttpServletRequest request,HttpServletResponse response) {
		Telphone telphone=telphoneService.find(Long.valueOf("2"));
		return ReturnJson.jsonString("OK",telphone.getPhone(), AppConstants.OK);

	}
	
	
	/**
	 * 关于我们
	 */
	@ResponseBody
	@RequestMapping(value="aboutus",method=RequestMethod.POST)
	public String aboutus(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/admin/article/aboutus.jhtml", AppConstants.OK);
	}
	
	/**
	 * 服务协议
	 */
	@ResponseBody
	@RequestMapping(value="services",method=RequestMethod.POST)
	public String services(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/admin/article/serviceProtocol.jhtml", AppConstants.OK);
	}
	
	/**
	 * 分享
	 */
	@ResponseBody
	@RequestMapping(value="download",method=RequestMethod.POST)
	public String share(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/download.html", AppConstants.OK);
	}
	
	
	// app版本
	@ResponseBody
	@RequestMapping(value = "appversion", method = RequestMethod.POST)
	public String appversion(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if ("android".equals(type)) {
			SystemAppversion systemAppversion = systemAppversionService.find(1l);
			return ReturnJson.jsonString("OK", systemAppversion, AppConstants.OK);

		} else {
			SystemAppversion systemAppversion = systemAppversionService.find(2l);
			return ReturnJson.jsonString("OK", systemAppversion, AppConstants.OK);
		}
	}
	
}
