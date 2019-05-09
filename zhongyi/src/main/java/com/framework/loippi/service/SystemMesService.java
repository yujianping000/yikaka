package com.framework.loippi.service;

import com.framework.loippi.entity.SystemMes;
import java.util.List;
/**
 * SERVICE - SystemMes(系统消息)
 * 
 * @author wgb
 * @version 2.0
 */
public interface SystemMesService  extends GenericService<SystemMes, Long> {
	public	List<SystemMes> findListByPage(Object parameter);
	
	void deleteByMesId(Object parameter);
}
