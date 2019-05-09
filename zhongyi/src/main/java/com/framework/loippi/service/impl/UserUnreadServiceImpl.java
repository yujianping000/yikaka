package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.UserUnreadDao;
import com.framework.loippi.entity.UserUnread;
import com.framework.loippi.service.UserUnreadService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SERVICE - UserUnread(用户未读)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class UserUnreadServiceImpl extends GenericServiceImpl<UserUnread, Long> implements UserUnreadService {
	
	@Autowired
	private UserUnreadDao userUnreadDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(userUnreadDao);
	}

	public List<UserUnread> findListByPage(Object parameter){
		return userUnreadDao.findListByPage(parameter);
	}

	@Override
	public void setUserUnread(Integer userType,Long UserId,Integer type) {
	    Map<String,Object> mapmes=new HashMap<String,Object>();
	    mapmes.put("userType", userType);
	    mapmes.put("userId", UserId);
	    List<UserUnread> userUnreads= userUnreadDao.findByParams(mapmes);
	    for(UserUnread userUnread:userUnreads){
	   	       if(type==5){
    		   userUnread.setSystemMesStatus(0);
    	 }else if(type==3){
    		   userUnread.setCourserMesStatus(0);
    	 }else if(type==4){
    		    userUnread.setClassRemindStatus(0);
    	 }
	        	userUnreadDao.update(userUnread);
	    }
		
	}
}
