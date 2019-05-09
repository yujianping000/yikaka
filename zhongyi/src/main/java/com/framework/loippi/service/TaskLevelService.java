package com.framework.loippi.service;

import com.framework.loippi.entity.TaskLevel;
import java.util.List;
/**
 * SERVICE - TaskLevel(任务等级)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TaskLevelService  extends GenericService<TaskLevel, Long> {
	public	List<TaskLevel> findListByPage(Object parameter);
}
