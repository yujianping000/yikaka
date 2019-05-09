package com.framework.loippi.controller.app;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.controller.app.model.AppActivity;
import com.framework.loippi.controller.app.model.AppActivity2;
import com.framework.loippi.controller.app.model.AppActivityScore;
import com.framework.loippi.controller.app.model.AppActivitySignup;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.Activity;
import com.framework.loippi.entity.ActivityJoin;
import com.framework.loippi.entity.ActivityUser;
import com.framework.loippi.entity.Order;
import com.framework.loippi.service.ActivityJoinService;
import com.framework.loippi.service.ActivityService;
import com.framework.loippi.service.ActivityUserService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.utils.StringUtil;

/**
 * 活动
 */
@Controller
@RequestMapping("api/app/activity/*")
public class AppActivityController extends BaseController {
	
	
	@Resource
	private ActivityService activityService;
	
	
	@Resource
	private ActivityUserService activityUserService;
	
	@Resource
	private ActivityJoinService activityJoinService;
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 显示活动列表
	 */
	@ResponseBody
	@RequestMapping(value="activityList",method=RequestMethod.POST)
	public String activityList(HttpServletRequest request,HttpServletResponse response) {
		
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		
		
		List<AppActivity2> appActivityList = new ArrayList<>(); 
		
		Map map=new HashMap();
		map.put("pageNumber", (Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize));
		map.put("pageSize", Integer.parseInt(pageSize));
		map.put("order", "start_time desc");
		
		List<Activity> activityList = activityService.findListByPage(map);
		if(activityList!=null) {
			for (Activity activity : activityList) {
				AppActivity2 appActivity2 = new AppActivity2();
				
				BeanUtils.copyProperties(activity, appActivity2);
				if(activity.getStartTime()!=null){
				appActivity2.setStartTime(StringUtil.date2String(activity.getStartTime()));
				}
				
				if(activity.getEndTime()!=null){
				appActivity2.setEndTime(StringUtil.date2String(activity.getEndTime()));
				}
				if(appActivity2.getStatus()!=null&&appActivity2.getStatus()!=0)
				{
				appActivityList.add(appActivity2);
				}
			}
			
		} 
		return ReturnJson.jsonString("OK",appActivityList, AppConstants.OK);
	}
	
	
	/**
	 * 活动详情
	 */
	@ResponseBody
	@RequestMapping(value="getActivityById",method=RequestMethod.POST)
	public String getActivityById(HttpServletRequest request,HttpServletResponse response) {
		
		String id=request.getParameter("id");
		
		AppActivity appActivity = new AppActivity();
		Activity activity = activityService.find(Long.parseLong(id));
		if(activity!=null) {
				BeanUtils.copyProperties(activity, appActivity);
				appActivity.setFree(activity.getFee());
				appActivity.setStartTime(StringUtil.date2String(activity.getStartTime()));
				appActivity.setEndTime(StringUtil.date2String(activity.getEndTime()));
		} 
		return ReturnJson.jsonString("OK",appActivity, AppConstants.OK);
	}
	
	/**
	 * 显示我的活动列表
	 */
	@ResponseBody
	@RequestMapping(value="myActivityList",method=RequestMethod.POST)
	public String myActivityList(HttpServletRequest request,HttpServletResponse response) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		String pageNumber=request.getParameter("pageNumber");
		String pageSize=request.getParameter("pageSize");
		
		List<AppActivity2> appActivityList = new ArrayList<>(); 
		
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		
		Map map=new HashMap();
		map.put("pageNumber", (Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize));
		map.put("pageSize", Integer.parseInt(pageSize));
		map.put("orderuid", loginuser.getId());
		map.put("order", " T_ORDER.created_time desc");
		
		List<Activity> activityList = activityService.findMyListByPage(map);
		if(activityList!=null) {
			for (Activity activity : activityList) {
				AppActivity2 appActivity2 = new AppActivity2();
				BeanUtils.copyProperties(activity, appActivity2);
				appActivity2.setStartTime(StringUtil.date2String(activity.getStartTime()));
				appActivity2.setEndTime(StringUtil.date2String(activity.getEndTime()));
				appActivityList.add(appActivity2);
			}
			
		} 
		return ReturnJson.jsonString("OK",appActivityList, AppConstants.OK);
	}
	
	/**
	 * 我的活动报名记录
	 */
	@ResponseBody
	@RequestMapping(value="getMyActivitySignup",method=RequestMethod.POST)
	public String getMyActivitySignup(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		String sessionId = request.getParameter("sessionId");
		List<AppActivitySignup> activitySignupList = new ArrayList<>(); 
		
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		/** 订单状态 1 待付款 2已付款 3 待评价 4已取消 5客户端删除  8 已完成 */
		List<Integer> listStatus=new ArrayList();
		listStatus.add(2);
		listStatus.add(3);
		listStatus.add(8);
		Map map=new HashMap();

		map.put("targetId", id);
		map.put("type", 2);
		map.put("uid", user.getId());
		map.put("orderStatus",listStatus);
		map.put("order", " T_ORDER.created_time desc");
		
		List<Order> orderList = orderService.findListByPage(map);
		if(orderList!=null) {
			for (Order order : orderList) {
				AppActivitySignup appActivitySignup = new AppActivitySignup();
				BeanUtils.copyProperties(order, appActivitySignup);
				appActivitySignup.setSignupTime(StringUtil.date2String4(order.getCreatedTime()));
				
				System.err.println("==========================================");
 				System.err.println(appActivitySignup.getStuSex());
				
				if(order.getStuSex().equals("男") || order.getStuSex().equals("男孩")) {
					appActivitySignup.setStuSex("男孩");
 				}
 				if(order.getStuSex().equals("女") || order.getStuSex().equals("女孩")) {
 					appActivitySignup.setStuSex("女孩");
 				}
				activitySignupList.add(appActivitySignup);
			}
			
		} 
		return ReturnJson.jsonString("OK",activitySignupList, AppConstants.OK);
	}
	
	/**
	 * 查询成绩
	 */
	@ResponseBody
	@RequestMapping(value="searchScore",method=RequestMethod.POST)
	public String searchScore(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		String number=request.getParameter("number");
		String name=request.getParameter("name");
		String sessionId = request.getParameter("sessionId");

		LoginUser loginuser = validateRedisLogiUser(sessionId);
		if(loginuser==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		/** 订单状态 1 待付款 2已付款 3 待评价 4已取消 5客户端删除  8 已完成 */
		Map map=new HashMap();
		map.put("targetId", id);
		map.put("type", 2);
		map.put("activityNumber",number);
		map.put("stuName",name);
		AppActivityScore appActivityScore=new AppActivityScore();
		appActivityScore.setName(name);
		appActivityScore.setNumber(number);
		List<Order> orderList = orderService.findListByPage(map);
		if(orderList!=null&&orderList.size()>0) {
			Order order=orderList.get(0);
			appActivityScore.setName(name);
			appActivityScore.setNumber(number);
			appActivityScore.setScore(order.getActivityPrize());
		} 
		return ReturnJson.jsonString("OK",appActivityScore, AppConstants.OK);
	}
	
	

}
