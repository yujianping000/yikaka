package com.framework.loippi.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framework.loippi.api.utils.ApiUtils;
import com.framework.loippi.api.utils.Xerror;
import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.service.CacheService;
import com.framework.loippi.support.APIPrincipal;
import com.framework.loippi.utils.SettingUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.redis.JedisStringCache;

import net.sf.json.JSONObject;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JedisCache jedisCache;
	//不需要登录的接口控制
	private Set<Pattern> freeset = new HashSet<>();
	//session参数名字
	private String sessionName = "sessionId";

	{
		freeset.add(Pattern.compile("/api/app/auth"));
		freeset.add(Pattern.compile("api/app/ad"));
		freeset.add(Pattern.compile("api/app/order/alipay"));
		freeset.add(Pattern.compile("api/app/order/notify_wechat"));
		freeset.add(Pattern.compile("api/app/order/notify"));
		freeset.add(Pattern.compile("api/app/org"));
		freeset.add(Pattern.compile("api/app/teacher/"));
		freeset.add(Pattern.compile("api/app/order"));
		freeset.add(Pattern.compile("api/app/user/"));
		freeset.add(Pattern.compile("api/app/user/"));
		freeset.add(Pattern.compile("api/app/activity/"));
		freeset.add(Pattern.compile("api/app/platteacher/user/"));
		freeset.add(Pattern.compile("admin/article/common/"));
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String ip = request.getRemoteAddr();
	  	System.err.println("通过过滤器-----------");
		// 先检查是否为不需要签名的界面
		for (Pattern pattern : freeset) {
			if (pattern.matcher(uri).find()) {
				System.err.println("通过-----------");
				return true;
			}
		}

		String sessionId = request.getParameter("sessionId");
	
		if(sessionId == null){
			responseJsonMessage(response, ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.USER_SESS_EXPIRED));
			return false;
		}
		LoginUser user = validateRedisLogiUser(sessionId);
		
		if(user == null){
			responseJsonMessage(response, ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.USER_SESS_EXPIRED));
			return false;
		}else if(user.getStatus().equals(2)){
			responseJsonMessage(response, ReturnJson.jsonString("用户已被锁定，请联系管理人员",user, AppConstants.LOCK_USER));
			return false;
		}
		request.setAttribute("session", user);
		request.setAttribute("ip", ip);
		return true;
	}

	/**
	 * 获取登录的用户
	 * @param sessionId
	 * @return
	 */
	public LoginUser validateRedisLogiUser(String sessionId) {
		if (StringUtil.isEmpty(sessionId)) {
			return null;
		}
		// 检查是否已经登录
		JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(sessionId);
		if (jedisStringCache.get() == null) {
			return null;
		}
		JSONObject jsonObject = new JSONObject().fromObject(jedisStringCache.get());// 将json字符串转换为json对象

		LoginUser loginUser = (LoginUser) JSONObject.toBean(jsonObject, LoginUser.class);// 将建json对象转换为Person对象
		return loginUser;
	}

	private void responseJsonMessage(HttpServletResponse response, String msg) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
