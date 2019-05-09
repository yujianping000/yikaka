package com.framework.loippi.dao;

import com.framework.loippi.entity.ActivityJoin;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - ActivityJoin(活动报名)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ActivityJoinDao  extends GenericDao<ActivityJoin, Long> {
	List<ActivityJoin> findListByPage(Object parameter);

}
