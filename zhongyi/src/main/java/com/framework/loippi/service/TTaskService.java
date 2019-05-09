package com.framework.loippi.service;

import com.framework.loippi.entity.TTask;
import java.util.List;
/**
 * SERVICE - TTask(任务表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface TTaskService  extends GenericService<TTask, Long> {
	public	List<TTask> findListByPage(Object parameter);
}
