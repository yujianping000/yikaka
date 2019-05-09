package com.framework.loippi.dao;

import com.framework.loippi.entity.Activity;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Activity(活动)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ActivityDao  extends GenericDao<Activity, Long> {
	List<Activity> findListByPage(Object parameter);
	List<Activity> findMyListByPage(Object parameter);
    Long  update2buyActivity(Long id);
    Long  update2CancelActivity(Long id);
}
