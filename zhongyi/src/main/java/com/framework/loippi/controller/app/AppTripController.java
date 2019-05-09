package com.framework.loippi.controller.app;



import com.framework.loippi.controller.app.model.AppOrgDynamic;
import com.framework.loippi.controller.app.model.AppTrip;
import com.framework.loippi.controller.app.model.AppTripDetail;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.CallRoll;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.TTrip;
import com.framework.loippi.entity.UStudent;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.CallRollService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.service.UStudentService;
import com.framework.loippi.utils.StringUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by longbh on 2016/12/18.
 */

@Controller
@RequestMapping("api/app/trip/*")
public class AppTripController extends BaseController {

	@Resource
    private TTripService tTripService;
	@Resource
    private UStudentService uStudentService;
	@Resource
    private TParentService tParentService;
	@Resource
    private OrgDynamicService orgDynamicService;
	@Resource
    private CallRollService callRollService;
	@Resource
    private CourseStuTimeService courseStuTimeService;
	@Resource
    private OrderService orderService;
	
	
    /**
     * 发布行程
     */
    @ResponseBody
    @RequestMapping(value = "sendTrip", method = RequestMethod.POST)
    public String sendTrip(HttpServletRequest request,String sessionId) {
    	LoginUser loginUser = validateRedisLogiUser(sessionId);
    	//String tripTime=request.getParameter("tripTime");
    	//String alarmTime=request.getParameter("alarmTime");
    	String content=request.getParameter("content");
    	String images=request.getParameter("images");
        TTrip tTrip=new TTrip();
        //tTrip.setAlarmTime(StringUtil.StringDateTimeToDate4(alarmTime));
        //tTrip.setTripTime(StringUtil.StringDateTimeToDate4(tripTime));
        tTrip.setCreateDate(new Date());
        tTrip.setAccount(loginUser.getPhone());
        tTrip.setContent(content);
        tTrip.setName(loginUser.getUserName());
        tTrip.setImages(images);
        tTrip.setUid(loginUser.getId());
        tTripService.save(tTrip);
        return jsonSucess();
    }

    
    
    
    /**
     * 编辑行程
     */
    @ResponseBody
    @RequestMapping(value = "edutTrip", method = RequestMethod.POST)
    public String edutTrip(HttpServletRequest request,Long id,String sessionId) {
    	LoginUser loginUser = validateRedisLogiUser(sessionId);
    	//String tripTime=request.getParameter("tripTime");
    	//String alarmTime=request.getParameter("alarmTime");
    	String content=request.getParameter("content");
    	String images=request.getParameter("images");
        TTrip tTrip=tTripService.find(id);
        //tTrip.setAlarmTime(StringUtil.StringDateTimeToDate4(alarmTime));
        //tTrip.setTripTime(StringUtil.StringDateTimeToDate4(tripTime));
        tTrip.setContent(content);
        if(StringUtil.isEmpty(images)){
        	images="";
        }
        tTrip.setImages(images);
        tTrip.setUid(loginUser.getId());
        tTripService.update(tTrip);
        return jsonSucess();
    }
   
    
    /**
     * 删除行程
     */
    @ResponseBody
    @RequestMapping(value = "deleteTrip", method = RequestMethod.POST)
    public String deleteTrip(HttpServletRequest request,Long id,String sessionId) {
    	LoginUser loginUser = validateRedisLogiUser(sessionId);
        tTripService.delete(id);
        return jsonSucess();
    }

    /**
     * 获取行程列表
     */
    @ResponseBody
    @RequestMapping(value = "getTripList", method = RequestMethod.POST)
    public String getTripList(HttpServletRequest request,Integer pageNumber,String sessionId) {
          Integer pageMix=(pageNumber - 1) * 10;
      	LoginUser loginUser = validateRedisLogiUser(sessionId);
    	  Map<String, Object> pageMap = new HashMap<String, Object>();
          pageMap.put("pageNumber", pageMix);
          pageMap.put("pageSize", 10);
          pageMap.put("uid", loginUser.getId());
    	  List<TTrip> tTrips=tTripService.findList(pageMap);
    	  List<AppTrip> apptTrips=new ArrayList<AppTrip>();
    	  for(TTrip tTrip:tTrips){
    		  AppTrip appTrip=new AppTrip();
    		  //appTrip.setTripTime(StringUtil.date2String4(tTrip.getTripTime()));
    		  appTrip.setImages(tTrip.getImages());
    		  appTrip.setCreateDate(StringUtil.parseDate(tTrip.getCreateDate()));
    		  appTrip.setContent(tTrip.getContent());
    		  //appTrip.setAlarmTime(StringUtil.date2String4(tTrip.getAlarmTime()));
    		  appTrip.setId(tTrip.getId());
    		  apptTrips.add(appTrip);
    	  }
    	  return jsonSucess(apptTrips);
    }
    
    /**
     * 获取行程列表
     */
    @ResponseBody
    @RequestMapping(value = "getTripList1", method = RequestMethod.POST)
    public String getTripList1(HttpServletRequest request,Integer pageNumber,Long destUid) {
          Integer pageMix=(pageNumber - 1) * 10;
    	  Map<String, Object> pageMap = new HashMap<String, Object>();
          pageMap.put("pageNumber", pageMix);
          pageMap.put("pageSize", 10);
          pageMap.put("uid",destUid);
    	  List<TTrip> tTrips=tTripService.findList(pageMap);
    	  List<AppTrip> apptTrips=new ArrayList<AppTrip>();
    	  for(TTrip tTrip:tTrips){
    		  AppTrip appTrip=new AppTrip();
    		  //appTrip.setTripTime(StringUtil.date2String4(tTrip.getTripTime()));
    		  appTrip.setImages(tTrip.getImages());
    		  appTrip.setCreateDate(StringUtil.parseDate(tTrip.getCreateDate()));
    		  appTrip.setContent(tTrip.getContent());
    		  //appTrip.setAlarmTime(StringUtil.date2String4(tTrip.getAlarmTime()));
    		  appTrip.setId(tTrip.getId());
    		  apptTrips.add(appTrip);
    	  }
    	  return jsonSucess(apptTrips);
    }
    
    
    
    /**
     * 行程详情
     */
    @ResponseBody
    @RequestMapping(value = "getTripDetail", method = RequestMethod.POST)
    public String getTripDetail(HttpServletRequest request,Integer pageNumber,String sessionId,Long id) {
    	  LoginUser loginUser = validateRedisLogiUser(sessionId);
    	  TTrip tTrip=tTripService.find(id);
    		  AppTripDetail appTrip=new AppTripDetail();
    		  //appTrip.setTripTime(StringUtil.date2String4(tTrip.getTripTime()));
    		  appTrip.setImages(tTrip.getImages());
    		  appTrip.setCreateDate(StringUtil.date2String4(tTrip.getCreateDate()));
    		  appTrip.setContent(tTrip.getContent());
    		  //appTrip.setAlarmTime(StringUtil.date2String4(tTrip.getAlarmTime()));
    		  appTrip.setId(tTrip.getId());
    		  TParent  tParent=tParentService.find("id", loginUser.getId());
    		  appTrip.setAvatar(tParent.getAvatar());
    		  appTrip.setUserName(tParent.getNickName());
    	  return jsonSucess(appTrip);
    }
    
    /**
     * 机构动态
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ResponseBody
    @RequestMapping(value = "getDynamicList", method = RequestMethod.POST)
    public String getDynamicList(HttpServletRequest request,Integer pageNumber,String sessionId) throws IllegalAccessException, InvocationTargetException {
        
    	Integer pageMix=(pageNumber - 1) * 10;
    	  LoginUser loginUser = validateRedisLogiUser(sessionId);
    	  Map<String, Object> pageMap = new HashMap<String, Object>();
          pageMap.put("pageNumber", pageMix);
          pageMap.put("pageSize", 10);
          pageMap.put("userId", loginUser.getId());
          pageMap.put("userType",1);
          List<OrgDynamic> orgDynamics=orgDynamicService.findOrgDynamic(pageMap);
          List<AppOrgDynamic> appOrgDynamics=new ArrayList<AppOrgDynamic>();
          for(OrgDynamic orgDynamic:orgDynamics){
        	  AppOrgDynamic dto = new AppOrgDynamic();
        	  BeanUtils.copyProperties(dto, orgDynamic);
        	  dto.setCreateDate1(StringUtil.parseDate(orgDynamic.getCreateDate()));
        	  appOrgDynamics.add(dto);
          }
    	  return jsonSucess(appOrgDynamics);
    }
  
    /**
     * 点名确认
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ResponseBody
    @RequestMapping(value = "callRollAffirm", method = RequestMethod.POST)
    public String callRollAffirm(HttpServletRequest request,String sessionId,Long id) {
    	  LoginUser loginUser = validateRedisLogiUser(sessionId);
    	  OrgDynamic orgDynamic=orgDynamicService.find(id);
    	  orgDynamic.setCallrollStatus(1);
    	  orgDynamicService.update(orgDynamic);
    	  
    	  CallRoll callRoll=callRollService.find(orgDynamic.getCallrollId());
    	  callRoll.setStatus(1);
    	  callRollService.update(callRoll);
    	  
    	  Map map=new HashMap();
//    	  map.put("studentId",callRoll.getStuId() );
    	  map.put("id",callRoll.getCourseTimeId() );
    	 List<CourseStuTime>  courseStuTimes=courseStuTimeService.findList(map);
    	 for(CourseStuTime courseStuTime:courseStuTimes){
    		 courseStuTime.setStatus(2);
    		 courseStuTimeService.update(courseStuTime);
    		 
    			Order order=orderService.find(courseStuTime.getOrderId());
				Integer classtimes=order.getClasstimes();
				if(classtimes>0){
					order.setClasstimes(classtimes-1);
					orderService.update(order);
				}
    	 }
    	  return jsonSucess();
    }

}
