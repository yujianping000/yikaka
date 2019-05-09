package com.framework.loippi.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
import com.framework.loippi.entity.TaskLevel;
import com.framework.loippi.entity.TaskUser;
import com.framework.loippi.service.CallRollService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.service.TaskLevelService;
import com.framework.loippi.service.TaskUserService;
import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.StringUtil;


@Component("UpdateUserTaskStatusJob")
@Lazy(false)
public class UpdateUserTaskStatusJob{
 	
	@Resource
	private TaskUserService taskUserService;
	private Logger log = Logger.getLogger(getClass());
	
	@Scheduled(cron = "0 0 23 * * ?")   //23点执行，成长任务设置为未执行
		public void build() {
		TaskUser taskUser=new TaskUser();
		taskUser.setStatus(0);
		taskUserService.update(taskUser);
		
	    
	  log.info("凌晨23点执行，成长任务设置为未执行!");
	}
} 

