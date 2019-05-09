package com.framework.loippi.dao;

import com.framework.loippi.entity.ActivityUser;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - ActivityUser(用户活动列表)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ActivityUserDao  extends GenericDao<ActivityUser, Long> {
	List<ActivityUser> findListByPage(Object parameter);

}
