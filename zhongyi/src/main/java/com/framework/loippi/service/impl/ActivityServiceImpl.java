package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.ActivityDao;
import com.framework.loippi.entity.Activity;
import com.framework.loippi.service.ActivityService;
import java.util.List;
/**
 * SERVICE - Activity(活动)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class ActivityServiceImpl extends GenericServiceImpl<Activity, Long> implements ActivityService {
	
	@Autowired
	private ActivityDao activityDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(activityDao);
	}

	public List<Activity> findListByPage(Object parameter){
		return activityDao.findListByPage(parameter);
	}

	@Override
	public List<Activity> findMyListByPage(Object parameter) {
		// TODO Auto-generated method stub
		return activityDao.findMyListByPage(parameter);
	}

	@Override
	public Long update2buyActivity(Long id) {
		// TODO Auto-generated method stub
		return activityDao.update2buyActivity(id);
	}

	@Override
	public Long update2CancelActivity(Long id) {
		// TODO Auto-generated method stub
		return activityDao.update2CancelActivity(id);
	}
}
