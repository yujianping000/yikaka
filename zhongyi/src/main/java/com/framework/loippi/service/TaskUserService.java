package com.framework.loippi.service;

import com.framework.loippi.entity.TaskUser;
import java.util.List;
/**
 * SERVICE - TaskUser(任务用户关联)
 * 
 * @author wmj
 * @version 2.0
 */
public interface TaskUserService  extends GenericService<TaskUser, Long> {
	public	List<TaskUser> findListByPage(Object parameter);
}
