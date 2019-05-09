package com.framework.loippi.dao;

import com.framework.loippi.entity.SystemMes;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - SystemMes(系统消息)
 * 
 * @author wgb
 * @version 2.0
 */
public interface SystemMesDao  extends GenericDao<SystemMes, Long> {
	List<SystemMes> findListByPage(Object parameter);
	void deleteByMesId(Object parameter);
}
