package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.ActivityStuViewDao;
import com.framework.loippi.entity.ActivityStuView;
import com.framework.loippi.service.ActivityStuViewService;
import java.util.List;
/**
 * SERVICE - ActivityStuView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class ActivityStuViewServiceImpl extends GenericServiceImpl<ActivityStuView, Long> implements ActivityStuViewService {
	
	@Autowired
	private ActivityStuViewDao activityStuViewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(activityStuViewDao);
	}

	public List<ActivityStuView> findListByPage(Object parameter){
		return activityStuViewDao.findListByPage(parameter);
	}
}
