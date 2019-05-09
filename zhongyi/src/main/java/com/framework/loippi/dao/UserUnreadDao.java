package com.framework.loippi.dao;

import com.framework.loippi.entity.UserUnread;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - UserUnread(用户未读)
 * 
 * @author wgb
 * @version 2.0
 */
public interface UserUnreadDao  extends GenericDao<UserUnread, Long> {
	List<UserUnread> findListByPage(Object parameter);

}
