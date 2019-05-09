package com.framework.loippi.dao;

import com.framework.loippi.entity.UsrFriendRequest;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - UsrFriendRequest(好友请求)
 * 
 * @author wgb
 * @version 2.0
 */
public interface UsrFriendRequestDao  extends GenericDao<UsrFriendRequest, Long> {
	List<UsrFriendRequest> findListByPage(Object parameter);

}
