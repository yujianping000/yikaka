package com.framework.loippi.dao;

import com.framework.loippi.entity.TaskLevel;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TaskLevel(任务等级)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TaskLevelDao  extends GenericDao<TaskLevel, Long> {
	List<TaskLevel> findListByPage(Object parameter);

}
