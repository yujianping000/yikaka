package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TaskLevelDao;
import com.framework.loippi.entity.TaskLevel;
import com.framework.loippi.service.TaskLevelService;
import java.util.List;
/**
 * SERVICE - TaskLevel(任务等级)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TaskLevelServiceImpl extends GenericServiceImpl<TaskLevel, Long> implements TaskLevelService {
	
	@Autowired
	private TaskLevelDao taskLevelDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(taskLevelDao);
	}

	public List<TaskLevel> findListByPage(Object parameter){
		return taskLevelDao.findListByPage(parameter);
	}
}
