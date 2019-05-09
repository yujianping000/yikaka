package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.ActivityUserDao;
import com.framework.loippi.entity.ActivityUser;
import com.framework.loippi.service.ActivityUserService;
import java.util.List;
/**
 * SERVICE - ActivityUser(用户活动列表)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class ActivityUserServiceImpl extends GenericServiceImpl<ActivityUser, Long> implements ActivityUserService {
	
	@Autowired
	private ActivityUserDao activityUserDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(activityUserDao);
	}

	public List<ActivityUser> findListByPage(Object parameter){
		return activityUserDao.findListByPage(parameter);
	}
}
