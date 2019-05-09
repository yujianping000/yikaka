package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.UsrFriendRequestDao;
import com.framework.loippi.entity.UsrFriendRequest;
import com.framework.loippi.service.UsrFriendRequestService;
import java.util.List;
/**
 * SERVICE - UsrFriendRequest(好友请求)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class UsrFriendRequestServiceImpl extends GenericServiceImpl<UsrFriendRequest, Long> implements UsrFriendRequestService {
	
	@Autowired
	private UsrFriendRequestDao usrFriendRequestDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(usrFriendRequestDao);
	}

	public List<UsrFriendRequest> findListByPage(Object parameter){
		return usrFriendRequestDao.findListByPage(parameter);
	}
}
