package com.framework.loippi.job;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.TSalaryLogService;


@Component("UpdateSalaryJob")
@Lazy(false)
public class UpdateSalaryJob{
 	
	@Resource
	  private	OrderService orderService;
	@Resource
	  private	TSalaryLogService tSalaryLogService;
	
	
	private Logger log = Logger.getLogger(getClass());
	
	@Scheduled(cron = "0 10 1 1 * ?")  //每月1号的01：10触发
		public void build() {
		tSalaryLogService.payroll();
		  log.info("凌晨1点执行，更新本月工资成功!");
	  }
	
} 
