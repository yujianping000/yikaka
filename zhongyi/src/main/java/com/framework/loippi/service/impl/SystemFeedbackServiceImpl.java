package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.SystemFeedbackDao;
import com.framework.loippi.entity.SystemFeedback;
import com.framework.loippi.service.SystemFeedbackService;
import java.util.List;
/**
 * SERVICE - SystemFeedback(意见反馈)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class SystemFeedbackServiceImpl extends GenericServiceImpl<SystemFeedback, Long> implements SystemFeedbackService {
	
	@Autowired
	private SystemFeedbackDao systemFeedbackDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(systemFeedbackDao);
	}

	public List<SystemFeedback> findListByPage(Object parameter){
		return systemFeedbackDao.findListByPage(parameter);
	}
}
