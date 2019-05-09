package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TaskUserDao;
import com.framework.loippi.entity.TaskUser;
import com.framework.loippi.service.TaskUserService;
import java.util.List;
/**
 * SERVICE - TaskUser(任务用户关联)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class TaskUserServiceImpl extends GenericServiceImpl<TaskUser, Long> implements TaskUserService {
	
	@Autowired
	private TaskUserDao taskUserDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(taskUserDao);
	}

	public List<TaskUser> findListByPage(Object parameter){
		return taskUserDao.findListByPage(parameter);
	}
}
