package com.framework.loippi.controller.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppOrg;
import com.framework.loippi.controller.app.model.AppSign;
import com.framework.loippi.controller.app.model.AppSignLog;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.CourseTeaTime;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.TSign;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TSignService;
import com.framework.loippi.utils.MyMapUtil;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;

@Controller
@RequestMapping("api/app/platteacher/sign/*")
public class AppPlatTeacherSignControlle extends BaseController {
	  @Resource
	   private OrgnizationService orgnizationService;
	  @Resource
	   private TSignService tSignService;
	  @Resource
	   private CourseTeaTimeService courseTeaTimeService;
	  @Resource
	   private OrderService orderService;
	  
	  
	  
	/**
	 * 签到信息
	 */
	@ResponseBody
	@RequestMapping(value = "getSignInfo", method = RequestMethod.POST)
	public String getSignInfo(HttpServletRequest request, String longitude,String latitude) {
		System.out.println("weidu"+latitude+"jingdu"+longitude);
		 String sessionId = request.getParameter("sessionId");
		 LoginUser loginuser = validateRedisLogiUser(sessionId);
         AppSign appsign=new AppSign();
         appsign.setAvatar(loginuser.getAvatar());
         appsign.setCurrentTime(StringUtil.date2StringTime(new Date())+","+"周"+StringUtil.getWeekOfDate(new Date()));
         appsign.setUserName(loginuser.getUserName());
	     Orgnization orgnization=orgnizationService.find(loginuser.getOrgId());
	     appsign.setOrgName(orgnization.getName());
	     appsign.setSignAddr(orgnization.getLocation());
		 float distance = MyMapUtil.calculateLineDistance(Double.parseDouble(longitude),	Double.parseDouble(latitude),orgnization.getLocLng().doubleValue(),orgnization.getLocLat().doubleValue());
		 
		 
//		 Map map=new HashMap();
//		 map.put("markTime", Long.parseLong(StringUtil.date2String3(new Date())));
//		 map.put("teacherId", loginuser.getId());
//		 map.put("orgId",loginuser.getOrgId() );
//		 CourseTeaTime  courseTeaTime=courseTeaTimeService. findisEnable(map);
//		 Boolean noSign=courseTeaTime!=null&&courseTeaTime.getClassStatus()==null;
		
		 Map map=new HashMap();
		 map.put("uid", loginuser.getId());
		 map.put("orgId", loginuser.getOrgId());
		 Long num=tSignService.findIsSign(map);
		 
		BigDecimal  range=orgnization.getDistance();
	
        if(range!=null){
        	Long ranges= range.longValue();
        	 if(distance<=ranges*1000&&num==0){
    			 appsign.setEnableSign(1);
    		 }else{
    			 appsign.setEnableSign(0);
    		 }
        }
	       return ReturnJson.jsonString("OK", appsign, AppConstants.OK);
	}
	
	/**
	 * 签到
	 */
	@ResponseBody
	@RequestMapping(value = "setSignInfo", method = RequestMethod.POST)
	public String setSignInfo(HttpServletRequest request) {
		 String sessionId = request.getParameter("sessionId");
		 LoginUser loginuser = validateRedisLogiUser(sessionId);
		 
		 Map sign=new HashMap();
		 sign.put("uid", loginuser.getId());
		 sign.put("orgId", loginuser.getOrgId());
		 Long num=tSignService.findIsSign(sign);
		 if(num==0){
		     Orgnization orgnization=orgnizationService.find(loginuser.getOrgId());
			 TSign tSign=new TSign();
			 tSign.setLocation(orgnization.getLocation());
			 tSign.setMarkTime(new Date());
			 tSign.setMarkTime1(Long.parseLong(StringUtil.date2String3(new Date())) );
			 tSign.setOrgId(orgnization.getId());
			 tSign.setUid(loginuser.getId());
			 tSignService.save(tSign);
			 
			 Map map=new HashMap();
			 map.put("markTime", tSign.getMarkTime1());
			 map.put("teacherId", loginuser.getId());
			 map.put("orgId",loginuser.getOrgId() );
			List<Long>  courseTimeIds=courseTeaTimeService.findTimeIdLate(map);
			 
			List<Long> courseTimeId1s=courseTeaTimeService.findTimeIdonTime(map);
			 
			for(Long courseTimeId:courseTimeIds){
				 if(courseTimeId!=null){
					 CourseTeaTime courseTeaTime=courseTeaTimeService.find(courseTimeId);
					 courseTeaTime.setClassStatus(3);
					 courseTeaTimeService.update(courseTeaTime);
					 tSign.setHourId(courseTimeId);
				 }
			}
			for(Long courseTimeId1:courseTimeId1s){
				 if(courseTimeId1!=null){
					 CourseTeaTime courseTeaTime=courseTeaTimeService.find(courseTimeId1);
					 courseTeaTime.setClassStatus(1);
					 courseTeaTimeService.update(courseTeaTime);
					 tSign.setHourId(courseTimeId1);
				 }
			}
			 tSignService.update(tSign);
		 }
	     return ReturnJson.jsonString("OK", AppConstants.OK);
	}
	
	/**
	 * 签到记录
	 */
	@ResponseBody
	@RequestMapping(value = "getSignLog", method = RequestMethod.POST)
	public String getSignLog(HttpServletRequest request, String longitude,String latitude,Integer pageNumber) {
		 String sessionId = request.getParameter("sessionId");
		 LoginUser loginuser = validateRedisLogiUser(sessionId);
		  Integer pageMix=(pageNumber - 1) * 10;
		   Map map=new HashMap();
		   map.put("orgId",loginuser.getOrgId() );
		   map.put("uid",loginuser.getId() );
		   map.put("pageNumber", pageMix);
		   map.put("pageSize", 10);
		   map.put("order", "id DESC");
		   List<AppSignLog>  appSigns=new ArrayList<AppSignLog>();
           List<TSign> tSigns=tSignService.findListByPage(map);
           for(TSign tSign:tSigns){
        	   AppSignLog appsign=new AppSignLog();
        	   appsign.setMarkTime(StringUtil.date2StringTime(tSign.getMarkTime())+","+"周"+  StringUtil.getWeekOfDate(tSign.getMarkTime()));
      	       Orgnization orgnization=orgnizationService.find(loginuser.getOrgId());
      	       appsign.setSignAddr(orgnization.getLocation());
      	       appSigns.add(appsign);
           }
	       return ReturnJson.jsonString("OK", appSigns, AppConstants.OK);
	}
}
