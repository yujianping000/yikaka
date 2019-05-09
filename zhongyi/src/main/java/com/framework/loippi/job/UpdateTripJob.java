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


@Component("UpdateTripJob")
@Lazy(false)
public class UpdateTripJob{
	@Resource
	  private	TTripService tTripService;

	
	
	@Scheduled(cron = "*/60 * * * * ?")  //每60秒执行
		public void build() {
		//行程定时推送
	 List<TTrip> tTrips=tTripService.findListTrip();
	 for(TTrip tTrip:tTrips){
		   JpushRunnable t1 = new JpushRunnable(tTrip.getContent(), "p"+tTrip.getUid(), "type",
  					Constants.trip+"_"+tTrip.getId() + "");
   		   t1.start();
	 }
		System.out.println("更新成功!");
	}
} 
