package com.framework.loippi.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.controller.app.model.CourseTime;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.Student;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TSalaryLogService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.utils.JpushRunnable;


@Component("UpdateWarnJob")
@Lazy(false)
public class UpdateWarnJob{

	
	@Resource
	  private	UserUnreadService userUnreadService;
	@Resource
	  private	CourseStuTimeService courseStuTimeService;
	@Resource
	  private	StudentService studentService;
	@Resource
	  private	CourseTeaTimeService courseTeaTimeService;
	@Resource
	  private	OrgDynamicService orgDynamicService;
	
	
	
	
	private Logger log = Logger.getLogger(getClass());
	
	@Scheduled(cron = "*/60 * * * * ?")  //每60秒执行
		public void build() {
		//上课两小时前提醒学生端。
		List<CourseTime> courseTimes= courseStuTimeService.findListCourse();
		for(CourseTime courseTime:courseTimes){
			OrgDynamic  orgDynamic=new OrgDynamic();
			Student student=studentService.find(courseTime.getStuId());
			orgDynamic.setUserId(student.getUid());
			orgDynamic.setStuId(courseTime.getStuId());
			orgDynamic.setCourseName(courseTime.getName());
			orgDynamic.setUserType(1);
			orgDynamic.setType(4);
			orgDynamic.setPrompt(courseTime.getText());
			orgDynamic.setCreateDate(new Date());
			orgDynamic.setTextContent("课程名称:"+courseTime.getName()+";上课时间:"+courseTime.getCourseTime());
			orgDynamicService.save(orgDynamic);
			 userUnreadService.setUserUnread(1, orgDynamic.getUserId(), 4);
			   JpushRunnable t1 = new JpushRunnable(orgDynamic.getPrompt(), "p"+orgDynamic.getUserId(), "type",
   					Constants.course+"_"+orgDynamic.getId() + "");
    		   t1.start();
		}
		
		//上课两小时前提醒教师端。
		List<CourseTime> courseTimeteas= courseTeaTimeService.findListCoursetea();
		for(CourseTime courseTime:courseTimeteas){
			OrgDynamic  orgDynamic=new OrgDynamic();
			orgDynamic.setTeacherId(courseTime.getStuId());
			orgDynamic.setUserId(courseTime.getStuId());
			orgDynamic.setCourseName(courseTime.getName());
			orgDynamic.setUserType(2);
			orgDynamic.setPrompt(courseTime.getText());
			orgDynamic.setType(4);
			orgDynamic.setCreateDate(new Date());
			orgDynamic.setTextContent("课程名称:"+courseTime.getName()+";上课时间:"+courseTime.getCourseTime());
			orgDynamicService.save(orgDynamic);
			 userUnreadService.setUserUnread(2, orgDynamic.getUserId(), 4);
			   JpushRunnable t1 = new JpushRunnable(orgDynamic.getPrompt(), "t"+orgDynamic.getUserId(), "type",
   					Constants.course+"_"+orgDynamic.getId() + "");
    		   t1.start();
		}
	  }
	
} 
