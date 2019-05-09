package com.framework.loippi.controller.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.framework.loippi.entity.Article;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.SystemAppversion;
import com.framework.loippi.entity.SystemFeedback;
import com.framework.loippi.entity.TBanner;
import com.framework.loippi.entity.Telphone;
import com.framework.loippi.service.ArticleService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.SystemAppversionService;
import com.framework.loippi.service.SystemFeedbackService;
import com.framework.loippi.service.TBannerService;
import com.framework.loippi.service.TelphoneService;
import com.framework.loippi.utils.StringUtil;


//用户信息
@Controller
@RequestMapping("api/app/user/*")
public class AppUserController extends BaseController {
	
	@Autowired
	private SystemFeedbackService systemFeedbackService; 
	@Resource
	private SystemAppversionService systemAppversionService;
	@Resource
	private TBannerService tBannerService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private TelphoneService telphoneService;
	

	@Resource
	private ParentService parentService;
	

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
			return	ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.USER_SESS_EXPIRED);
		} else if (loginUser.getStatus() == 2) {
			return ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.USER_ACCOUNT_LOGINED);
		}
		
		if(StringUtil.isEmpty(type)){
			type="1";
		}
		
		SystemFeedback feedback = new SystemFeedback();
		feedback.setType(Integer.valueOf(type));
		feedback.setContent(content);
		feedback.setMobile(loginUser.getPhone());
		
		Parent parent = parentService.find(loginUser.getId());
		if(parent != null) {
			feedback.setNickname(parent.getNickName());
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

		Telphone telphone=telphoneService.find(Long.valueOf("1"));
		return ReturnJson.jsonString("OK",telphone.getPhone(), AppConstants.OK);

	}
	
	
	/**
	 * 关于我们
	 */
	@ResponseBody
	@RequestMapping(value="aboutus",method=RequestMethod.POST)
	public String aboutus(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/admin/article/common/aboutus.jhtml", AppConstants.OK);
	}
	
	/**
	 * 服务协议
	 */
	@ResponseBody
	@RequestMapping(value="services",method=RequestMethod.POST)
	public String services(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/admin/article/common/serviceProtocol.jhtml", AppConstants.OK);
	}
	
	/**
	 * 分享
	 */
	@ResponseBody
	@RequestMapping(value="download",method=RequestMethod.POST)
	public String share(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://www.ykk81.com", AppConstants.OK);
	}
	
	
	/**
	 *支付协议
	 */
	@ResponseBody
	@RequestMapping(value="payProtocol",method=RequestMethod.POST)
	public String payProtocol(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/admin/article/common/payProtocol.jhtml", AppConstants.OK);
	}
	
	/**
	 * 入职协议
	 */
	@ResponseBody
	@RequestMapping(value="inductionProtocol",method=RequestMethod.POST)
	public String inductionProtocol(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/admin/article/common/inductionProtocol.jhtml", AppConstants.OK);
	}
	
	/**
	 * 等级说明
	 */
	@ResponseBody
	@RequestMapping(value="levelProtocol",method=RequestMethod.POST)
	public String levelProtocol(HttpServletRequest request,HttpServletResponse response) {
		
	
		
		return ReturnJson.jsonString("OK","http://119.29.0.81:7098/zhongyi/admin/article/common/levelProtocol.jhtml", AppConstants.OK);
	}
	
	
	
	/**
	 * 协议，关于我们，等级说明
	 */
	@ResponseBody
	@RequestMapping(value="protocol",method=RequestMethod.POST)
	public String protocol(HttpServletRequest request,HttpServletResponse response) {
		
		//1 关于我们   2服务协议  3入职协议  4支付协议 5等级说明
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		Article article=articleService.find(Long.parseLong(id));
		return ReturnJson.jsonString("OK",article.getContent(), AppConstants.OK);
	}
	
	
	// app版本
	@ResponseBody
	@RequestMapping(value = "appversion", method = RequestMethod.POST)
	public String appversion(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if ("android".equals(type)) {
			SystemAppversion systemAppversion = systemAppversionService.find(3l);
			return ReturnJson.jsonString("OK", systemAppversion, AppConstants.OK);

		} else {
			SystemAppversion systemAppversion = systemAppversionService.find(4l);
			return ReturnJson.jsonString("OK", systemAppversion, AppConstants.OK);
		}
	}
	
	
	
	// 获取滚动图
		@ResponseBody
		@RequestMapping(value = "getBannerList", method = RequestMethod.POST)
		public String getBannerList(HttpServletRequest request, HttpServletResponse response) {
			String type = request.getParameter("type");
			Map map=new HashMap();
			map.put("order", "sort");
            List<TBanner> tBanners=tBannerService.findListByPage(map);
    		return ReturnJson.jsonString("OK",tBanners, AppConstants.OK);
		}
	
}
