package com.framework.loippi.controller.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.controller.app.model.AppOrg;
import com.framework.loippi.controller.app.model.AppOrg2;
import com.framework.loippi.controller.app.model.AppTeacher;
import com.framework.loippi.controller.app.model.AppTeacherAuth;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.AppOrgnization;
import com.framework.loippi.entity.LeaveOffice;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.OrgInvite;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.service.LeaveOfficeService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.OrgInviteService;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.utils.StringUtil;

/**
 * 老师认证
 */
@Controller
@RequestMapping("api/app/teacherAuth/*")
public class AppTeacherAuthController extends BaseController {

	@Autowired
	private TeacherAuthService teacherAuthService;
	
	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private OrgInviteService orgInviteService;
	
	@Autowired
	private OrgDynamicService orgDynamicService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private OrgUserService orgUserService;

	@Resource
	private UserUnreadService userUnreadService;
	
	@Resource
	private LeaveOfficeService leaveOfficeService;
	
	
	
	/**
	 *首页机构列表
	 */
	@ResponseBody
	@RequestMapping(value = "getOrgList",method = RequestMethod.POST)
	public String getOrgList(HttpServletRequest request,HttpServletResponse response,Integer pageNumber) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		 Integer pageMix=(pageNumber - 1) * 10;
		
		Map map = new HashMap<>();
		map.put("uid", loginuser.getId());
	    map.put("pageNumber",pageMix);
	    map.put("pageSize",10);
		
		List<AppOrg> appOrgnizationList = new ArrayList<AppOrg>();
		List<Orgnization> orglist = orgnizationService.findListByPage1(map);
		
		if(orglist != null) {
			for (Orgnization orgnization : orglist) {
				AppOrg appOrgnization = new AppOrg();
				if(orgnization.getId().equals(loginuser.getOrgId())){
					appOrgnization.setIsCheck(1);
				}
				appOrgnization.setId(orgnization.getId());
				appOrgnization.setImage(orgnization.getImage());
				appOrgnization.setFavStar(orgnization.getFavStar());
				appOrgnization.setName(orgnization.getName());
				appOrgnization.setLocation(orgnization.getLocation());
				appOrgnizationList.add(appOrgnization);
			}
		}
		return ReturnJson.jsonString("OK", appOrgnizationList, AppConstants.OK);
		
	}
	
	/**
	 *首页机构切换
	 */
	@ResponseBody
	@RequestMapping(value = "orgtab",method = RequestMethod.POST)
	public String orgtab(HttpServletRequest request,HttpServletResponse response,Long orgId) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		loginuser.setOrgId(orgId);
		this.setRedisLogiUser(sessionId, loginuser);
		return ReturnJson.jsonString("OK", loginuser, AppConstants.OK);
	}
	
	
	/**
	 *显示机构列表 
	 */
	@ResponseBody
	@RequestMapping(value = "orgnizationList",method = RequestMethod.POST)
	public String orgnizationList(HttpServletRequest request,HttpServletResponse response) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		
		Map map = new HashMap<>();
		
		map.put("pageNumber",(Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize)); 
		map.put("pageSize", Integer.parseInt(pageSize));
		map.put("order", "create_date desc");
		
		List<AppOrgnization> appOrgnizationList = new ArrayList<>();
		List<Orgnization> orglist = orgnizationService.findListByPage(map);
		
		if(orglist != null) {
			for (Orgnization orgnization : orglist) {
				AppOrgnization appOrgnization = new AppOrgnization();
				
				appOrgnization.setId(orgnization.getId());
				appOrgnization.setImage(orgnization.getImage());
				appOrgnization.setFavStar(orgnization.getFavStar());
				appOrgnization.setName(orgnization.getName());
				appOrgnization.setLocation(orgnization.getLocation());
				
				appOrgnizationList.add(appOrgnization);
			}
		}
		return ReturnJson.jsonString("OK", appOrgnizationList, AppConstants.OK);
		
	}
	
	/**
	 * 认证提交
	 */
	@ResponseBody
	@RequestMapping(value = "teacherAuthSumbit",method = RequestMethod.POST)
	public String teacherAuthSumbit(HttpServletRequest request,HttpServletResponse response,AppTeacherAuth appteacherAuth) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		Map map=new HashMap();
		map.put("teacherId",loginuser.getId());
		List<TeacherAuth> teacherAuthList = teacherAuthService.findList(map);
		
		//当教师属于某一机构全职教师则不能提交
		if(teacherAuthList != null) {
			for (TeacherAuth teacherAuth : teacherAuthList) {
				if(teacherAuth.getOrgId() != null && teacherAuth.getType() == 1) {
					
					return ReturnJson.jsonString("提交认证失败", AppConstants.FAIL);
				}
			}
		}
		
		TeacherAuth teacherAuth2 = new TeacherAuth();
		BeanUtils.copyProperties(appteacherAuth, teacherAuth2);
		
		System.err.println("============================");
		System.err.println(appteacherAuth.getBirthday());
		
		teacherAuth2.setBirthday(StringUtil.StringDateToDate(appteacherAuth.getBirthday()));
		
		
		System.err.println("============================");
		System.err.println(teacherAuth2.getBirthday());
		
		teacherAuth2.setCreateTime(new Date());
		teacherAuth2.setUpdateTime(new Date());
		teacherAuth2.setStatus(2);
		teacherAuth2.setAccount(loginuser.getPhone());
		teacherAuth2.setReason("您的资料正在审核中，请耐心等待~");
		teacherAuth2.setIsRead(2);
		teacherAuth2.setTeacherId(loginuser.getId());
		teacherAuthService.save(teacherAuth2);
		
		Orgnization orgnization = orgnizationService.find(teacherAuth2.getOrgId());
		
		//发送消息
		OrgDynamic orgDynamic = new OrgDynamic();
		orgDynamic.setPrompt("入职申请通知");
		if(orgnization != null) {
			orgDynamic.setTextContent("您的入职申请已经提交至"+orgnization.getName()+"进行审核，请耐心等待");
			orgDynamic.setOrgId(orgnization.getId());
			orgDynamic.setCreateDate(new Date());
		}
		orgDynamic.setTeacherId(teacherAuth2.getTeacherId());
		orgDynamic.setTeacherName(teacherAuth2.getName());
		orgDynamic.setUserId(teacherAuth2.getTeacherId());
		orgDynamic.setType(5);
		orgDynamic.setUserType(2);
		
		orgDynamicService.save(orgDynamic);
		userUnreadService.setUserUnread(2, teacherAuth2.getTeacherId(), 5);
		
		return ReturnJson.jsonString("OK", AppConstants.OK);
		
	}
	
	/**
	 * 认证记录
	 */
	@ResponseBody
	@RequestMapping(value="teacherAuthRecord",method=RequestMethod.POST)
	public String teacherAuthRecord(HttpServletRequest request,HttpServletResponse response) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		
		Map map = new HashMap<>();
		
		map.put("teacherId", loginuser.getId());
		map.put("pageNumber",(Integer.parseInt(pageNumber)-1)*Integer.parseInt(pageSize)); 
		map.put("pageSize", Integer.parseInt(pageSize));
		map.put("order", "create_time desc");
		
		List<AppTeacherAuth> appTeacherAuthList = new ArrayList<>();
		List<TeacherAuth> teacherAuthList = teacherAuthService.findListByPage(map);
		
		if(teacherAuthList != null) {
			for (TeacherAuth teacherAuth : teacherAuthList) {
				AppTeacherAuth appTeacherAuth = new AppTeacherAuth();
				
				appTeacherAuth.setId(teacherAuth.getId());
				if(!StringUtil.isEmpty(teacherAuth.getIdCard())){
				String[] IdCard = teacherAuth.getIdCard().split(",");
				appTeacherAuth.setIdCard(IdCard[0]);
				}
				appTeacherAuth.setName(teacherAuth.getName());
				appTeacherAuth.setStatus(teacherAuth.getStatus());
				appTeacherAuth.setCreateTime(StringUtil.date2String4(teacherAuth.getCreateTime()));
				appTeacherAuth.setUpdateTime(StringUtil.date2String4(teacherAuth.getUpdateTime()));
				
				//若该条认证记录状态为离职的，前端显示为认证已通过状态
				if(teacherAuth.getStatus() == 4) {
					appTeacherAuth.setStatus(1);
				}
				
				Orgnization orgnization = orgnizationService.find("id", teacherAuth.getOrgId());
				appTeacherAuth.setOrgName(orgnization.getName());
				
				appTeacherAuthList.add(appTeacherAuth);
			}
		}
		return ReturnJson.jsonString("OK", appTeacherAuthList, AppConstants.OK);
	}
	
	
	/**
	 * 认证详情
	 */
	@ResponseBody
	@RequestMapping(value="teacherAuthDetails",method=RequestMethod.POST)
	public String teacherAuthDetails(HttpServletRequest request,HttpServletResponse response) {
		
		String sessionId = request.getParameter("sessionId");
		
		String id = request.getParameter("id");
		
		TeacherAuth teacherAuth = teacherAuthService.find("id",Long.parseLong(id));
		
		AppTeacherAuth appTeacherAuth = new AppTeacherAuth();
		if(teacherAuth != null) {
			
			BeanUtils.copyProperties(teacherAuth, appTeacherAuth);
			if(teacherAuth.getBirthday() != null) {
				appTeacherAuth.setBirthday(StringUtil.date2String(teacherAuth.getBirthday()));
			}
			
			if(teacherAuth.getStatus() == 1) {
				appTeacherAuth.setReason("您的资料已经通过审核啦~");
			}
			
			if(teacherAuth.getStatus() == 3) {
				appTeacherAuth.setReason("你的资料申请审核不通过，请重新提交资料");
			}
			
			//若该条认证记录状态为离职的，前端显示为认证已通过状态
			if(teacherAuth.getStatus() == 4) {
				appTeacherAuth.setStatus(1);
			}
			
			Orgnization orgnization = orgnizationService.find("id", teacherAuth.getOrgId());
			appTeacherAuth.setOrgName(orgnization.getName());
		}
		return ReturnJson.jsonString("OK", appTeacherAuth, AppConstants.OK);
	}
	
	//更新redis缓存
	@ResponseBody
	@RequestMapping(value = "updateCache",method = RequestMethod.POST)
	public String updateCache(HttpServletRequest request,HttpServletResponse response) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		  
		
		   Map<String,Object> map=new HashMap<String,Object> ();
	        map.put("uid", loginuser.getId());
	    	List<Orgnization> orglist = orgnizationService.findListByPage1(map);   
		 
	        Orgnization orgnization=orgnizationService.find(loginuser.getOrgId());
	        if(orgnization==null){
	        	   if(orglist.size()!=0){
	   	        	loginuser.setOrgId(orglist.get(0).getId());
	   	        }else{
	   	        	loginuser.setOrgId(Long.valueOf(0));
	   	        }
	        }
	      this.setRedisLogiUser(sessionId, loginuser);
		return ReturnJson.jsonString("OK", loginuser, AppConstants.OK);
	}
	
	
	/**
	 *教师邀请同意
	 */
	@ResponseBody
	@RequestMapping(value = "agreeInvite",method = RequestMethod.POST)
	public String agreeInvite(HttpServletRequest request,HttpServletResponse response,Long id) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		OrgDynamic orgDynamic=orgDynamicService.find(id);
		OrgInvite orgInvite=orgInviteService.find(orgDynamic.getInviteId());
		 Map<String,Object> map=new HashMap<String,Object> ();
	     map.put("teacherId", loginuser.getId());
	     map.put("status", 1);
	     map.put("type", 1);
		List<TeacherAuth> teacherAuths=teacherAuthService.findList(map);
		if(teacherAuths .size()!=0&&orgInvite.getType()==1){
			return ReturnJson.jsonString("您已经加入了其他机构，无法成为该机构的全职教师", AppConstants.HAVE_ORG);
		}else{
			TeacherAuth teacherAuth=new TeacherAuth();
			teacherAuth.setOrgId(orgInvite.getOrgId());
			teacherAuth.setName(loginuser.getUserName());
			teacherAuth.setType(orgInvite.getType());
			teacherAuth.setCreateTime(new Date());
			teacherAuth.setTeacherId(loginuser.getId());
			teacherAuth.setStatus((1));
			teacherAuth.setIsRead(1);
			teacherAuthService.save(teacherAuth);
			
			//若教师是第二次加入该机构，将该教师之前的离职信息删除
			Map map1 = new HashMap<>();
			map1.put("orgId", teacherAuth.getOrgId());
			map1.put("teacherId", teacherAuth.getTeacherId());
			List<LeaveOffice> leaveOfficeList = leaveOfficeService.findList(map1);
			
			if(leaveOfficeList != null) {
				for (LeaveOffice leaveOffice : leaveOfficeList) {
					leaveOfficeService.deleteEntity(leaveOffice);
				}
			}
			
			//同意入职，则机构教师总数加一
			Orgnization orgnization = orgnizationService.find(orgInvite.getOrgId());
			orgnization.setTeacherCount(orgnization.getTeacherCount()+1);
			orgnizationService.update(orgnization);
			
			//机构用户表添加一条记录
			OrgUser orgUser = new OrgUser();
			orgUser.setCreateTime(new Date());
			orgUser.setOrgId(orgInvite.getOrgId());
			orgUser.setUid(loginuser.getId());
			orgUser.setType(2);
			orgUserService.save(orgUser);
			
			orgDynamic.setInviteStatus(1);
			orgDynamicService.update(orgDynamic);
			
			//若该教师为公海教师，则入职成功修改教师状态
			//认证成功，若为公海教师将教师状态进行修改
			Teacher teacher = teacherService.find(teacherAuth.getTeacherId());
			if(teacher != null && teacher.getTeacherType() == 3) {
				teacher.setTeacherType(teacherAuth.getType());
				teacherService.update(teacher);
			}
			
			AppOrg2 appOrg = new AppOrg2();
			appOrg.setId(orgInvite.getOrgId());
			appOrg.setReason(orgInvite.getReason());
			
			return ReturnJson.jsonString("OK", AppConstants.OK);
		}

		
	}
	
	/**
	 *教师邀请拒绝
	 */
	@ResponseBody
	@RequestMapping(value = "refuseInvite",method = RequestMethod.POST)
	public String refuseInvite(HttpServletRequest request,HttpServletResponse response,Long id) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		OrgDynamic orgDynamic=orgDynamicService.find(id);
		OrgInvite orgInvite=orgInviteService.find(orgDynamic.getInviteId());
		 
		if(orgInvite != null) {
			orgInvite.setInvitetype(3);
			orgInviteService.update(orgInvite);
		}
			
		orgDynamic.setInviteStatus(3);
		orgDynamicService.update(orgDynamic);
			
		return ReturnJson.jsonString("OK", AppConstants.OK);
	}
	
	/**
	 * 认证前判断教师类型 —全职、兼职、两者都不是
	 */
	@ResponseBody
	@RequestMapping(value = "judgeTeacherType",method = RequestMethod.POST)
	public String judgeTeacherType(HttpServletRequest request,HttpServletResponse response) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		AppTeacher appteacher = new AppTeacher();
		
		if(loginuser != null) {
			Teacher teacher = teacherService.find("id", loginuser.getId());
			if(teacher != null) {
				BeanUtils.copyProperties(teacher, appteacher);
			}
		}
		
		return ReturnJson.jsonString("OK", appteacher, AppConstants.OK);
		
	}
	
	/**
	 * 认证提交判断是否该教师已经认证过机构
	 */
	@ResponseBody
	@RequestMapping(value = "sumbitJudge",method = RequestMethod.POST)
	public String sumbitJudge(HttpServletRequest request,HttpServletResponse response,AppTeacherAuth appteacherAuth) {
		
		String orgId = request.getParameter("orgId");
		String sessionId = request.getParameter("sessionId");
		LoginUser loginuser = validateRedisLogiUser(sessionId);
		
		//提交全职认证记录时
		if(appteacherAuth != null && appteacherAuth.getType() == 1) {
			
			List<TeacherAuth> teacherAuthList = teacherAuthService.findList("teacherId",loginuser.getId());
			
			//未认证过任何机构，可以提交
			if(teacherAuthList.size() == 0) {
				return ReturnJson.jsonString("OK", AppConstants.OK);
				
			} else {//认证记录状态为拒绝、离职时，可以提交，为通过、审核的不可以提交
				for (TeacherAuth teacherAuth : teacherAuthList) {
					if(teacherAuth.getStatus() == 1 || teacherAuth.getStatus() == 2 ) {
						return ReturnJson.jsonString("您已经有其它机构的认证记录，不能再认证全职教师", AppConstants.HAVE_ORG);
					}
				}
				
				return ReturnJson.jsonString("OK", AppConstants.OK);
			}
		}
		
		
		//提交兼职认证记录时
		if(appteacherAuth != null && appteacherAuth.getType() == 2) {
			
			Map map1 = new HashMap();
			map1.put("teacherId", loginuser.getId());
			map1.put("orgId", Long.parseLong(orgId));
			List<TeacherAuth> teacherAuthList = teacherAuthService.findList(map1);
			
			//在该机构没有认证记录的
			if(teacherAuthList.size() == 0) {
				Map map2 = new HashMap();
				map2.put("teacherId", loginuser.getId());
				map2.put("type", 1);
				List<TeacherAuth> teacherAuthList2 = teacherAuthService.findList(map2);
				
				//在其他机构有全职认证记录且申请状态为拒绝或离职的可以提交
				if(teacherAuthList2.size() != 0) {
					for (TeacherAuth teacherAuth2 : teacherAuthList2) {
						
						if(teacherAuth2.getStatus() == 1 || teacherAuth2.getStatus() == 2 ) {
							return ReturnJson.jsonString("您有其他机构全职教师的认证记录，不能提交此次认证申请", AppConstants.HAVE_ORG);
						} 
					}
					return ReturnJson.jsonString("OK", AppConstants.OK);
					
				}
				
			} else {//在该机构有认证记录的
				return ReturnJson.jsonString("您已经提交过该机构的认证申请或已经是该机构的认证用户，无需再次提交", AppConstants.HAVE_ORG);
			}
		}
		
		return ReturnJson.jsonString("OK", AppConstants.OK);
		
	}
}
