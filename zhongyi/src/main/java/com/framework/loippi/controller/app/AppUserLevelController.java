package com.framework.loippi.controller.app;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppLevelInfo;
import com.framework.loippi.controller.app.model.AppOrgDynamic;
import com.framework.loippi.controller.app.model.AppTask;
import com.framework.loippi.controller.app.model.AppTaskCenter;
import com.framework.loippi.controller.app.model.AppTripDetail;
import com.framework.loippi.controller.app.model.AppUserCenter;
import com.framework.loippi.controller.app.model.AppUserLevel;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.controller.app.model.MesList;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.TTask;
import com.framework.loippi.entity.TTrip;
import com.framework.loippi.entity.TaskLevel;
import com.framework.loippi.entity.TaskUser;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.UserUnread;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.TTaskService;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.service.TaskLevelService;
import com.framework.loippi.service.TaskUserService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;

import cn.jpush.api.report.UsersResult;

@Controller
@RequestMapping("api/app/userlevel/*")
public class AppUserLevelController extends BaseController {
	@Resource
	private TParentService tParentService;
	@Resource
	private TaskUserService taskUserService;

	@Resource
	private UserUnreadService userUnreadService;
	@Resource
	private TTaskService tTaskService;
	@Resource
	private OrgDynamicService orgDynamicService;
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private TaskLevelService taskLevelService;
	@Resource
    private TTripService tTripService;
	/**
	 * 用户首页详情
	 */
	@ResponseBody
	@RequestMapping(value = "getLevelDetail", method = RequestMethod.POST)
	public String getLevelDetail(HttpServletRequest request, String sessionId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		TParent tParent = tParentService.find(loginUser.getId());
		Map map = new HashMap();
		map.put("userId", loginUser.getId());
		map.put("status", 0);
		Long count = taskUserService.count(map);

		Map param = new HashMap();
		param.put("userId", loginUser.getId());
		param.put("userType", 1);
		List<UserUnread> userUnreads = userUnreadService.findList(param);
		if(tParent.getLevel()==null||tParent.getLevel()==0){
			tParent.setLevel(1);
		}
		AppUserLevel appUserLevel = new AppUserLevel();
		appUserLevel.setLevel(tParent.getLevel());
		appUserLevel.setStarts(tParent.getStarts());
		appUserLevel.setGrowupValue(tParent.getGrowupValue());
		appUserLevel.setTaskTotal(count);
		
		//获取等级，设置等级图片，今天任务图像
		if(tParent.getLevel()==null||tParent.getLevel()==0){
			tParent.setLevel(1);
		}
		
		TaskLevel taskLevel	=taskLevelService.find("level", tParent.getLevel());
		if(taskLevel!=null){
			if(count==0){
				appUserLevel.setLevelimage(taskLevel.getLevelImage3());//完成今天任务
			}else if(count<5){
				appUserLevel.setLevelimage(taskLevel.getLevelImage2());//没有完成今天任务
			}else{
				appUserLevel.setLevelimage(taskLevel.getLevelImage1());//没有做今天任务
			}
		}else{
			
		}

		
		
		
		if (userUnreads.size() != 0) {
			UserUnread userUnread = userUnreads.get(0);
			appUserLevel.setDynamicIsRead(userUnread.getDynamicStatus());
		}

		return jsonSucess(appUserLevel);
	}

	/**
	 * 任务中心
	 */
	@ResponseBody
	@RequestMapping(value = "getTaskList", method = RequestMethod.POST)
	public String getTaskList(HttpServletRequest request, Integer pageNumber, String sessionId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		AppTaskCenter appTaskCenter = new AppTaskCenter();
		AppLevelInfo appLevelInfo = new AppLevelInfo();
		TParent tParent = tParentService.find(loginUser.getId());
		appLevelInfo.setGrowupValue(tParent.getGrowupValue());
		
		Map map = new HashMap();
		map.put("userId", loginUser.getId());
		map.put("status", 0);
		Long count = taskUserService.count(map);
		
		//获取等级，设置等级图片，今天任务图像
				if(tParent.getLevel()==null||tParent.getLevel()==0){
					tParent.setLevel(1);
				}
				
				TaskLevel taskLevel	=taskLevelService.find("level", tParent.getLevel());
				if(taskLevel!=null){
					if(count==0){
						appLevelInfo.setLevelimage(taskLevel.getLevelImage3());//完成今天任务
					}else if(count<5){
						appLevelInfo.setLevelimage(taskLevel.getLevelImage2());//没有完成今天任务
					}else{
						appLevelInfo.setLevelimage(taskLevel.getLevelImage1());//没有做今天任务
					}
				}else{
					
				}
		
		
		
		appLevelInfo.setLevel(tParent.getLevel());

		List<TaskUser> taskUsers = taskUserService.findList("userId", loginUser.getId());
		
		List<TTask>  tTasks=	tTaskService.findAll();
		
		if(taskUsers==null||taskUsers.size()==0){
			if(tTasks!=null){
				for (TTask tTask :tTasks) {
					TaskUser taskUser=new TaskUser();
					taskUser.setCreateDate(new Date());
					taskUser.setStatus(0);
					taskUser.setTaskId(tTask.getId());
					taskUser.setUserId(loginUser.getId());
					taskUser.setValue(tTask.getValue());
					taskUserService.save(taskUser);
				}
				 taskUsers = taskUserService.findList("userId", loginUser.getId());
			}
		}
		
		List<AppTask> appTasks = new ArrayList<AppTask>();
		if(tTasks!=null&&taskUsers!=null){
		for (TaskUser taskUser : taskUsers) {
			AppTask appTask = new AppTask();
			for (TTask tTask :tTasks) {
			if(tTask.getId().equals(taskUser.getTaskId())){
			appTask.setContent(tTask.getContent());
			appTask.setGrowupvalue(tTask.getValue());
			appTask.setStatus(taskUser.getStatus());
			appTask.setTaskId(taskUser.getId());
			appTasks.add(appTask);
			}
			
			}
		}
		}
		
		
	
		appTaskCenter.setAppTasks(appTasks);
		appTaskCenter.setAppLevelInfo(appLevelInfo);
		return jsonSucess(appTaskCenter);
	}
	
	public static void main(String[] args) {
		Long l=4l;
		System.err.println(l==4);
	}

	/**
	 * 领取成长值
	 */
	@ResponseBody
	@RequestMapping(value = "getValue", method = RequestMethod.POST)
	public String getValue(HttpServletRequest request, String sessionId, Long taskid) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		System.err.println("taskid==="+taskid);

		TaskUser taskUser = taskUserService.find(taskid);
		//4，需要当天发布一条状态
		if(taskUser.getTaskId().equals(4l)){
			
			
			Map params=new HashMap();
			params.put("uid", loginUser.getId());
			params.put("filter_create_date",StringUtil.date2String(new Date()));
			 List<TTrip>	tTrips=tTripService.findList(params);
			 if(tTrips==null||tTrips.size()==0){
					return ReturnJson.jsonString("请先发布一条个人动态", AppConstants.TRIP__ERROR);
			 }
		}
		
		
		
		
		taskUser.setStatus(1);
		taskUserService.update(taskUser);
		TTask ttask = tTaskService.find(taskUser.getTaskId());
		TParent tParent = tParentService.find(loginUser.getId());
		Integer value = tParent.getGrowupValue();
		if (value == null || value == 0) {
			tParent.setGrowupValue(ttask.getValue());
		} else {
			tParent.setGrowupValue(value + ttask.getValue());
		}
		if(tParent.getLevel()==null||tParent.getLevel()==0){
			tParent.setLevel(1);
		}
		
		Map map=new HashMap();
		map.put("levelValues", tParent.getGrowupValue());
		map.put("maxlevel", "maxlevel");
		List<TaskLevel> taskLevels	=taskLevelService.findListByPage(map);
        if(taskLevels!=null&&taskLevels.size()>0){
        	TaskLevel taskLevel=taskLevels.get(0);
        	tParent.setLevel(taskLevel.getLevel());
        	tParent.setLevelimage(taskLevel.getLevelImage1());
        }
		
		
		
		tParentService.update(tParent);
		return jsonSucess();
	}
	

	/**
	 * 个人中心
	 */
	@ResponseBody
	@RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
	public String getUserInfo(HttpServletRequest request, String sessionId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		TParent tParent = tParentService.find(loginUser.getId());
	    AppUserCenter appUserCenter=new AppUserCenter();
	    appUserCenter.setAvatar(tParent.getAvatar());
	    appUserCenter.setNickname(tParent.getNickName());
	    appUserCenter.setSex(tParent.getSex());
	    appUserCenter.setRealName(tParent.getRelName());
	    if(tParent.getType()==1){
	    	 appUserCenter.setIsEditPwd(1);
	    }else{
	    	appUserCenter.setIsEditPwd(0);
	    }
	   
	    Map map=new HashMap();
	    map.put("userId",loginUser.getId() );
	    map.put("userType",1 );
	   List<UserUnread> userUnreads= userUnreadService.findList(map);
	   if(userUnreads.size()!=0){
		   UserUnread userUnread=userUnreads.get(0);
		   Integer mesIsRead=userUnread.getClassRemindStatus()+userUnread.getCourserMesStatus()+userUnread.getSystemMesStatus();
		   if(mesIsRead==3){
			   appUserCenter.setMesIsRead(1);
		   }else{
			   appUserCenter.setMesIsRead(0);
		   }
	   }
		return jsonSucess(appUserCenter);
	}
	
	
	/**
	 * 修改个人头像
	 */
	@ResponseBody
	@RequestMapping(value = "editAvatar", method = RequestMethod.POST)
	public String editAvatar(HttpServletRequest request, String sessionId,String avatar) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser.getUserType()==1){
			TParent tParent=tParentService.find(loginUser.getId());
			tParent.setAvatar(avatar);
			tParentService.update(tParent);
		}else{
			Teacher teacher=teacherService.find(loginUser.getId());
			teacher.setAvatar(avatar);
			teacherService.update(teacher);
		}
		return jsonSucess();
	}
	
	/**
	 * 修改昵称
	 */
	@ResponseBody
	@RequestMapping(value = "editNickName", method = RequestMethod.POST)
	public String editNickName(HttpServletRequest request, String sessionId,String nickName) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser.getUserType()==1){
			TParent tParent=tParentService.find(loginUser.getId());
			tParent.setNickName(nickName);
			 tParent.setFirstName(StringUtil.getFirstSpell(nickName));
			tParentService.update(tParent);
		}
		return jsonSucess();
	}
	
	
	/**
	 * 修改性别
	 */
	@ResponseBody
	@RequestMapping(value = "editSex", method = RequestMethod.POST)
	public String editSex(HttpServletRequest request, String sessionId,String sex) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser.getUserType()==1){
			TParent tParent=tParentService.find(loginUser.getId());
			tParent.setSex(sex);
			tParentService.update(tParent);
		}
		return jsonSucess();
	}
	
	/**
	 * 修改真实姓名
	 */
	@ResponseBody
	@RequestMapping(value = "editRealName", method = RequestMethod.POST)
	public String editRealName(HttpServletRequest request, String sessionId,String realName) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser.getUserType()==1){
			TParent tParent=tParentService.find(loginUser.getId());
			tParent.setRelName(realName);
			tParentService.update(tParent);
		}
		return jsonSucess();
	}
	
	/**
	 * 修改密码
	 */
	@ResponseBody
	@RequestMapping(value = "editPwd", method = RequestMethod.POST)
	public String editPwd(HttpServletRequest request, String sessionId,String realName,String pwdold,String pwdnew) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		if(loginUser.getUserType()==1){
			TParent tParent=tParentService.find(loginUser.getId());
			if(DigestUtils.md5Hex(pwdold).equals(tParent.getPassword())){
				   tParent.setPassword(DigestUtils.md5Hex(pwdnew));
				   tParentService.update(tParent);
					return jsonSucess();
			}else{
				return ReturnJson.jsonString("密码错误", AppConstants.PASSWORD__ERROR);
			}
		}else{
			 Teacher  teacher=teacherService.find(loginUser.getId());
			 if(DigestUtils.md5Hex(pwdold).equals(teacher.getPassword())){
			    	 teacher.setPassword(DigestUtils.md5Hex(pwdnew));
			    	 teacherService.update(teacher);
					return jsonSucess();
			}else{
				return ReturnJson.jsonString("密码错误", AppConstants.PASSWORD__ERROR);
			}
		}
	
	}
	
	/**
	 * 消息列表
	 */
	@ResponseBody
	@RequestMapping(value = "getMesList", method = RequestMethod.POST)
	public String getMesList(HttpServletRequest request, String sessionId) {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
	    Map map=new HashMap();
	    map.put("userId",loginUser.getId() );
	    map.put("userType",loginUser.getUserType() );
	     List<UserUnread> userUnreads= userUnreadService.findList(map);
	     if(userUnreads.size()!=0){
	    	   UserUnread userUnread=userUnreads.get(0);
	   	    Map param=new HashMap();
	   	    param.put("userId",loginUser.getId() );
	   	    param.put("userType",loginUser.getUserType());
	   	    List<MesList>  meslist=orgDynamicService.findMesList(param);
	           for(MesList mes:meslist){
	       	     if(mes.getType()==3){
	       	    	 mes.setIsRead(userUnread.getCourserMesStatus());
	       	     }else if(mes.getType()==4){
	       	    	 mes.setIsRead(userUnread.getClassRemindStatus());
	       	     }else{
	       	    	 mes.setIsRead(userUnread.getSystemMesStatus());
	       	     }
	          }
	   		return jsonSucess(meslist);
	   	}else{
	   		return jsonFail();
	     }
	     }
	
	/**
	 * 消息详情
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping(value = "getMesDetail", method = RequestMethod.POST)
	public String getMesDetail(HttpServletRequest request, String sessionId,Integer type,Integer pageNumber ) throws IllegalAccessException, InvocationTargetException {
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		 List<OrgDynamic> orgDynamics=new ArrayList<OrgDynamic>();
	    Integer pageMix=(pageNumber - 1) * 10;
	    Map map=new HashMap();
	    map.put("userId",loginUser.getId() );
	    map.put("userType",loginUser.getUserType() );
	    map.put("type",type );
	    map.put("pageNumber", pageMix);
	    map.put("pageSize", 10);
	    if(type==3){
	    	   orgDynamics=orgDynamicService.findByType3(map);
	    }else{
	         orgDynamics=orgDynamicService.findListByPage(map);
	    }
	    List<AppOrgDynamic> appOrgDynamics=new ArrayList<AppOrgDynamic>();
        for(OrgDynamic orgDynamic:orgDynamics){
      	  AppOrgDynamic dto = new AppOrgDynamic();
      	  BeanUtils.copyProperties(dto, orgDynamic);
      	  if(orgDynamic.getCreateDate()!=null){
      	  dto.setCreateDate1(StringUtil.parseDate(orgDynamic.getCreateDate()));
      	  
        }
      	  appOrgDynamics.add(dto);
        }
        Map mapmes=new HashMap();
        mapmes.put("userType", loginUser.getUserType());
        mapmes.put("userId", loginUser.getId());
        List<UserUnread>   userUnreads=userUnreadService.findList(mapmes);
        for(UserUnread userUnread:userUnreads){
        	 if(type==5){
        		 userUnread.setSystemMesStatus(1);
        	 }else if(type==3){
        		 userUnread.setCourserMesStatus(1);
        	 }else if(type==4){
        		 userUnread.setClassRemindStatus(1);
        	 }
        	 userUnreadService.update(userUnread);
        }
  	  return jsonSucess(appOrgDynamics);
	}
	
	/** 退出登陆 */
	@ResponseBody
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = request.getParameter("sessionId");
		this.removeRedisLogiUser(sessionId);
		return ReturnJson.jsonString("OK", AppConstants.OK);

	}
	
}
