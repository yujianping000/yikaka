package com.framework.loippi.controller.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.framework.loippi.controller.app.model.AppActivity;
import com.framework.loippi.controller.app.model.AppActivity2;
import com.framework.loippi.controller.app.model.AppFriends;
import com.framework.loippi.controller.app.model.AppFrinend;
import com.framework.loippi.controller.app.model.AppUsrFriendRequest;
import com.framework.loippi.controller.app.model.AppUsrRelation;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.controller.app.model.Mycode;
import com.framework.loippi.entity.Activity;
import com.framework.loippi.entity.ActivityJoin;
import com.framework.loippi.entity.ActivityUser;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.UserUnread;
import com.framework.loippi.entity.UsrFriendRequest;
import com.framework.loippi.entity.UsrRelation;
import com.framework.loippi.service.ActivityJoinService;
import com.framework.loippi.service.ActivityService;
import com.framework.loippi.service.ActivityUserService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.service.UsrFriendRequestService;
import com.framework.loippi.service.UsrRelationService;
import com.framework.loippi.utils.StringUtil;

@Controller
@RequestMapping("api/app/friends/*")
public class AppFridendsController extends BaseController {
	@Autowired 
	private UsrRelationService usrRelationService;
	@Autowired 
	private UsrFriendRequestService usrFriendRequestService;
	@Autowired 
	private TParentService tParentService;
	@Autowired 
	private UserUnreadService userUnreadService;
	
	
	
	/**
	 * 好友列表
	 */
	@ResponseBody
	@RequestMapping(value="getFriends",method=RequestMethod.POST)
	public String getFriends(HttpServletRequest request,HttpServletResponse response) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
	
		
		
		Map map=new HashMap();
		map.put("uid",loginUser.getId());
		List<String> firstNmaes=usrRelationService.findListFirstName(map);
		AppFrinend appFrinend=new AppFrinend();
		List<AppFriends>  appFriends=new ArrayList<AppFriends>();
		for(String firstNmae:firstNmaes){
			AppFriends  appFriend=new AppFriends();
			List<AppUsrRelation> appUsrRelations=new ArrayList<AppUsrRelation>();
			Map param=new HashMap();
			param.put("uid", loginUser.getId());
			param.put("destFirstName", firstNmae);
			List<UsrRelation> usrRelations=usrRelationService.findList(param);
			for(UsrRelation usrRelation:usrRelations){
				AppUsrRelation appUsrRelation=new AppUsrRelation();
				appUsrRelation.setDestAvatar(usrRelation.getDestAvatar());
				appUsrRelation.setDestName(usrRelation.getDestName());
				appUsrRelation.setDestFirstName(usrRelation.getDestFirstName());
				appUsrRelation.setDestUid(usrRelation.getDestUid());
				appUsrRelations.add(appUsrRelation);
			}
			appFriend.setAppUsrRelation(appUsrRelations);
			appFriend.setIndexWord(firstNmae);
			appFriends.add(appFriend);
		}
		appFrinend.setAppFriends(appFriends);
		
		
		Map map1=new HashMap();
		map1.put("userType",1 );
		map1.put("userId",loginUser.getId());
		List<UserUnread> userUnreads=userUnreadService.findList(map1);
		if(userUnreads.size()!=0){
		   	UserUnread userUnread=userUnreads.get(0);
				appFrinend.setIsRead(userUnread.getFriendStatus());
		}
		
		
		return ReturnJson.jsonString("OK",appFrinend, AppConstants.OK);
	}
	
	/**
	 * 新好友列表
	 */
	@ResponseBody
	@RequestMapping(value="getnewFriends",method=RequestMethod.POST)
	public String getnewFriends(HttpServletRequest request,HttpServletResponse response) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		
		
		Map map1=new HashMap();
		map1.put("userType",1 );
		map1.put("userId",loginUser.getId());
		List<UserUnread> userUnreads=userUnreadService.findList(map1);
		if(userUnreads.size()!=0){
			UserUnread userUnread=userUnreads.get(0);
			userUnread.setFriendStatus(1);
			userUnreadService.update(userUnread);
		}
		
		Map<String,Object> map=new HashMap<String,Object> ();
		map.put("uid",loginUser.getId());
		List<UsrFriendRequest> usrFriendRequests=usrFriendRequestService.findList(map);
		List<AppUsrFriendRequest> appUsrFriendRequests=new ArrayList<AppUsrFriendRequest>();
		for (UsrFriendRequest ad : usrFriendRequests) {
			AppUsrFriendRequest appUsrFriendRequest=new AppUsrFriendRequest();
			BeanUtils.copyProperties(ad,appUsrFriendRequest );
			appUsrFriendRequests.add(appUsrFriendRequest);
		}
		return ReturnJson.jsonString("OK",appUsrFriendRequests, AppConstants.OK);
	}
	
	
	
	/**
	 *同意好友申请
	 */
	@ResponseBody
	@RequestMapping(value="agreeFriends",method=RequestMethod.POST)
	public String agreeFriends(HttpServletRequest request,HttpServletResponse response,Long id) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		UsrFriendRequest usrFriendRequest=usrFriendRequestService.find(id);
		TParent tParent=tParentService.find(usrFriendRequest.getDestUid());
		TParent tParent1=tParentService.find(loginUser.getId());
		usrFriendRequest.setStatus(1);
		usrFriendRequestService.update(usrFriendRequest);
		//添加别人的
		UsrRelation usrRelation=new UsrRelation();
		usrRelation.setCreateTime(new Date());
		usrRelation.setDestAvatar(usrFriendRequest.getDestAvatar());
		usrRelation.setDestName(usrFriendRequest.getDestName());
		usrRelation.setDestUid(usrFriendRequest.getDestUid());
		usrRelation.setUid(loginUser.getId());
		usrRelation.setDestFirstName(tParent.getFirstName());
		usrRelationService.save(usrRelation);
		
		//同时别人添加我的
		UsrRelation usrRelation1=new UsrRelation();
		usrRelation1.setCreateTime(new Date());
		usrRelation1.setDestAvatar(loginUser.getAvatar());
		usrRelation1.setDestName(loginUser.getUserName());
		usrRelation1.setDestUid(loginUser.getId());
		usrRelation1.setUid(tParent.getId());
		usrRelation1.setDestFirstName(tParent1.getFirstName());
		usrRelationService.save(usrRelation1);
		return ReturnJson.jsonString("OK", AppConstants.OK);
	}
	
	/**
	 *添加好友
	 */
	@ResponseBody
	@RequestMapping(value="addFriend",method=RequestMethod.POST)
	public String addFriend(HttpServletRequest request,HttpServletResponse response,String  uuid,String content) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		TParent tParent=tParentService.find("uuid",uuid);
		Map map=new HashMap();
		map.put("uid",tParent.getId());
		map.put("destUid", loginUser.getId());
		List<UsrFriendRequest> usrFriendRequestold=usrFriendRequestService.findList(map);
		if(usrFriendRequestold.size()!=0){
			return ReturnJson.jsonString("你已经发送添加好友申请!", AppConstants.IS_FRIEND);
		}else{
			UsrFriendRequest usrFriendRequest=new UsrFriendRequest();
			usrFriendRequest.setContent(content);
			usrFriendRequest.setCreateTime(new Date());
			usrFriendRequest.setDestAvatar(loginUser.getAvatar());
			usrFriendRequest.setDestName(loginUser.getUserName());
			usrFriendRequest.setDestUid(loginUser.getId());
			usrFriendRequest.setStatus(0);
			usrFriendRequest.setUid(tParent.getId());
			usrFriendRequest.setUpdateTime(new Date());
			usrFriendRequestService.save(usrFriendRequest);
			
			Map map1=new HashMap();
			map1.put("userType",1 );
			map1.put("userId",tParent.getId() );
			List<UserUnread> userUnreads=userUnreadService.findList(map1);
			if(userUnreads.size()!=0){
				UserUnread userUnread=userUnreads.get(0);
				userUnread.setFriendStatus(0);
				userUnreadService.update(userUnread);
			}
		
			return ReturnJson.jsonString("OK", AppConstants.OK);
		}

	}
	
	
	/**
	 *删除好友
	 */
	@ResponseBody
	@RequestMapping(value="delFriend",method=RequestMethod.POST)
	public String delFriend(HttpServletRequest request,HttpServletResponse response,Long uid) {
		
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		
		if(uid.intValue()==loginUser.getId()){
			return ReturnJson.jsonString("OK", AppConstants.OK);
		}
		
		Map map=new HashMap();
		map.put("uid",uid);
		map.put("destUid", loginUser.getId());
		List<UsrRelation> usrRelations=usrRelationService.findList(map);
		if(usrRelations!=null&&usrRelations.size()!=0){
			usrRelationService.delete(usrRelations.get(0).getId());
		}
		
		Map map1=new HashMap();
		map1.put("uid",loginUser.getId());
		map1.put("destUid", uid);
		List<UsrRelation> usrRelations1=usrRelationService.findList(map1);
		if(usrRelations1!=null&&usrRelations1.size()!=0){
			usrRelationService.delete(usrRelations1.get(0).getId());
		}
		
		/**同时删除好友请求记录*/
		Map map2=new HashMap();
		map2.put("uid",uid);
		map2.put("destUid", loginUser.getId());
		List<UsrFriendRequest> usrFriendRequesList = usrFriendRequestService.findList(map2);
		if(usrFriendRequesList != null && usrFriendRequesList.size() > 0) {
			for (UsrFriendRequest usrFriendRequest : usrFriendRequesList) {
				usrFriendRequestService.delete(usrFriendRequest.getId());
			}
		}
		
		
		return ReturnJson.jsonString("OK", AppConstants.OK);
	
	}
	
	/**
	 *扫描二维码后
	 */
	@ResponseBody
	@RequestMapping(value="getInfoByuuid",method=RequestMethod.POST)
	public String getInfoByuuid(HttpServletRequest request,HttpServletResponse response,String  uuid) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		TParent tParent=tParentService.find("uuid",uuid);
	     Mycode mycode=new Mycode();
	     mycode.setAvatar(tParent.getAvatar());
	     mycode.setName(tParent.getNickName());
	     mycode.setSex(tParent.getSex());
	     mycode.setUuid(tParent.getUuid());
	     return ReturnJson.jsonString("OK", mycode,AppConstants.OK);
	}
	
	/**
	 *我的二维码
	 */
	@ResponseBody
	@RequestMapping(value="mycode",method=RequestMethod.POST)
	public String mycode(HttpServletRequest request,HttpServletResponse response) {
		String sessionId = request.getParameter("sessionId");
		LoginUser loginUser = validateRedisLogiUser(sessionId);
		TParent tParent=tParentService.find(loginUser.getId());
	     Mycode mycode=new Mycode();
	     mycode.setAvatar(tParent.getAvatar());
	     mycode.setName(tParent.getNickName());
	     mycode.setSex(tParent.getSex());
	     mycode.setUuid(tParent.getUuid());
	     return ReturnJson.jsonString("OK", mycode,AppConstants.OK);
	}
}
