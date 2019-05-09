package com.framework.loippi.dao;

import com.framework.loippi.entity.OffWork;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OffWork(请假)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OffWorkDao  extends GenericDao<OffWork, Long> {
	List<OffWork> findListByPage(Object parameter);

}
