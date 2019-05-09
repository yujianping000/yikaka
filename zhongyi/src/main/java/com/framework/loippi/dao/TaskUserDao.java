package com.framework.loippi.dao;

import com.framework.loippi.entity.TaskUser;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TaskUser(任务用户关联)
 * 
 * @author wmj
 * @version 2.0
 */
public interface TaskUserDao  extends GenericDao<TaskUser, Long> {
	List<TaskUser> findListByPage(Object parameter);

}
