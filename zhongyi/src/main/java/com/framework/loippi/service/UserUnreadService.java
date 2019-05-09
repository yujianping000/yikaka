package com.framework.loippi.service;

import com.framework.loippi.entity.UserUnread;
import java.util.List;
/**
 * SERVICE - UserUnread(用户未读)
 * 
 * @author wgb
 * @version 2.0
 */
public interface UserUnreadService  extends GenericService<UserUnread, Long> {
	public	List<UserUnread> findListByPage(Object parameter);
	
	public  void setUserUnread(Integer userType,Long UserId,Integer typ);
		
	
}
