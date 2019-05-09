package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TTaskDao;
import com.framework.loippi.entity.TTask;
import com.framework.loippi.service.TTaskService;
import java.util.List;
/**
 * SERVICE - TTask(任务表)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class TTaskServiceImpl extends GenericServiceImpl<TTask, Long> implements TTaskService {
	
	@Autowired
	private TTaskDao tTaskDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tTaskDao);
	}

	public List<TTask> findListByPage(Object parameter){
		return tTaskDao.findListByPage(parameter);
	}
}
