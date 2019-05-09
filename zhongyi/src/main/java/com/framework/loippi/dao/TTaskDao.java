package com.framework.loippi.dao;

import com.framework.loippi.entity.TTask;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TTask(任务表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface TTaskDao  extends GenericDao<TTask, Long> {
	List<TTask> findListByPage(Object parameter);

}
