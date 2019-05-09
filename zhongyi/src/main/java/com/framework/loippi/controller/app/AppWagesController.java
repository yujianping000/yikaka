package com.framework.loippi.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppTSalaryLog;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.TSalaryLog;
import com.framework.loippi.entity.TWages;
import com.framework.loippi.entity.WagesList;
import com.framework.loippi.service.TSalaryLogService;
import com.framework.loippi.service.TWagesService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.WagesListService;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;

@Controller
@RequestMapping("api/app/platteacher/wages/*")
public class AppWagesController extends BaseController {
	@Resource
    private TWagesService tWagesService;
	@Resource
    private WagesListService wagesListService;
	@Resource
    private TSalaryLogService tSalaryLogService;
	
	
	/**
	 * 工资
	 */
	@ResponseBody
	@RequestMapping(value = "getWages", method = RequestMethod.POST)
	public String getWages(HttpServletRequest request, Integer pageNumber,String sessionId, Integer pageSize) {
          LoginUser loginUser = validateRedisLogiUser(sessionId);
       //   tSalaryLogService.payroll();
         List<AppTSalaryLog>  appTSalaryLogs=new  ArrayList<AppTSalaryLog>();
         Map<String, Object> pageMap = new HashMap<String, Object>();
         if(pageNumber > 1 && pageSize > 0) {
        	 Integer pageMix=(pageNumber - 1) * pageSize;
        	 pageMap.put("pageNumber", pageMix);
         }
	       
	       pageMap.put("orgId",loginUser.getOrgId() );
	       pageMap.put("uid",loginUser.getId() );
		   pageMap.put("pageSize", 10);
		   pageMap.put("status", 1);
		   List<TSalaryLog> tSalaryLogs=tSalaryLogService.findListByPage(pageMap);
		   for(TSalaryLog tSalaryLog:tSalaryLogs){
			   AppTSalaryLog appTSalaryLog=new AppTSalaryLog();
			   appTSalaryLog.setIssueTime(StringUtil.date2String5(tSalaryLog.getPayTime()));
				BeanUtils.copyProperties(tSalaryLog,appTSalaryLog );
				appTSalaryLogs.add(appTSalaryLog);
		   }
		   return ReturnJson.jsonString("OK", appTSalaryLogs, AppConstants.OK);
		   
	}
	
	/**
	 * 工资详细
	 */
	@ResponseBody
	@RequestMapping(value = "getWagesList", method = RequestMethod.POST)
	public String getWagesList(HttpServletRequest request, String sessionId,Long id) {
        LoginUser loginUser = validateRedisLogiUser(sessionId);
	       Map<String, Object> pageMap = new HashMap<String, Object>();
	       pageMap.put("wid",id);
	       pageMap.put("uid",loginUser.getId() );
		   List<WagesList> tWages=wagesListService.findList(pageMap);
		   return ReturnJson.jsonString("OK", tWages, AppConstants.OK);
	}
}
