package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.ActivityJoinDao;
import com.framework.loippi.entity.ActivityJoin;
import com.framework.loippi.service.ActivityJoinService;
import java.util.List;
/**
 * SERVICE - ActivityJoin(活动报名)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class ActivityJoinServiceImpl extends GenericServiceImpl<ActivityJoin, Long> implements ActivityJoinService {
	
	@Autowired
	private ActivityJoinDao activityJoinDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(activityJoinDao);
	}

	public List<ActivityJoin> findListByPage(Object parameter){
		return activityJoinDao.findListByPage(parameter);
	}
}
