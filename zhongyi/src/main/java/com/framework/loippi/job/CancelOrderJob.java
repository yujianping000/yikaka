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
import com.framework.loippi.service.CallRollService;
import com.framework.loippi.service.CourseStuTimeService;
import com.framework.loippi.service.CourseTeaTimeService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.StringUtil;


@Component("CancelOrderJob")
@Lazy(false)
public class CancelOrderJob{
 	
	@Resource
	  private	OrderService orderService;
	private Logger log = Logger.getLogger(getClass());
	
	@Scheduled(cron = "0 0 3 * * ?")  //凌晨3点执行，取消未支付的订单
		public void build() {
	    Long markTime= Long.parseLong(StringUtil.date2String3(new Date()));

	    Map map=new HashMap();
	    map.put("status", 1);
	    map.put("type", 1);
	  List<Order>  orders=orderService.findList(map);
	  if(orders!=null){
		  for (Order order : orders) {
			  Order order2=new Order();
			  order2.setId(order.getId());
			  order2.setStatus(4);
			  orderService.update(order2);
		}
	  }
	  log.info("凌晨3点执行，取消未支付的订单，更新成功!");
	}
} 

