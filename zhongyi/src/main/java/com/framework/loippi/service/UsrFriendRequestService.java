package com.framework.loippi.service;

import com.framework.loippi.entity.UsrFriendRequest;
import java.util.List;
/**
 * SERVICE - UsrFriendRequest(好友请求)
 * 
 * @author wgb
 * @version 2.0
 */
public interface UsrFriendRequestService  extends GenericService<UsrFriendRequest, Long> {
	public	List<UsrFriendRequest> findListByPage(Object parameter);
}
