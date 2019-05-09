package com.framework.loippi.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.entity.CallRoll;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.TTrip;
import com.framework.loippi.service.CallRollService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.StringUtil;


@Component("updateStatusController")
@Lazy(false)
public class updateStatusController{
 	@Resource
    private	CourseTeaTimeService courseTeaTimeService;
	@Resource
	  private	CallRollService callRollService;
	@Resource
	  private	CourseStuTimeService courseStuTimeService;
	@Resource
	  private	StudentService studentService;
	@Resource
	  private	OrgDynamicService orgDynamicService;
	@Resource
	  private	TTripService tTripService;
	@Resource
	  private	OrderService orderService;
	
	
	@Scheduled(cron = "*/60 * * * * ?")  //每60秒执行
		public void build() {
	    Long markTime= Long.parseLong(StringUtil.date2String3(new Date()));
	    //更新教师签到
		Map map=new HashMap();
		map.put("markTime", markTime);
		courseTeaTimeService.updateClassStatus(map);
		
	//更新点名
		List<CallRoll> callRolls=callRollService.selectStatus();
		for(CallRoll callRoll:callRolls){
			callRoll.setStatus(3);
			callRollService.update(callRoll);
			CourseStuTime courseStuTime= courseStuTimeService.find(callRoll.getCourseTimeId());
			if(courseStuTime!=null){
				courseStuTime.setStatus(5);
				courseStuTimeService.update(courseStuTime);
				Order order=orderService.find(courseStuTime.getOrderId());
				Integer classtimes=order.getClasstimes();
				if(classtimes>0){
					order.setClasstimes(classtimes-1);
					orderService.update(order);
				}
			}
		}

	}
} 

