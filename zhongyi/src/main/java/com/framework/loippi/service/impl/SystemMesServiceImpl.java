package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.SystemMesDao;
import com.framework.loippi.entity.SystemMes;
import com.framework.loippi.service.SystemMesService;
import java.util.List;
/**
 * SERVICE - SystemMes(系统消息)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class SystemMesServiceImpl extends GenericServiceImpl<SystemMes, Long> implements SystemMesService {
	
	@Autowired
	private SystemMesDao systemMesDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(systemMesDao);
	}

	public List<SystemMes> findListByPage(Object parameter){
		return systemMesDao.findListByPage(parameter);
	}

	@Override
	public void deleteByMesId(Object parameter) {
		// TODO Auto-generated method stub
		systemMesDao.deleteByMesId(parameter);
	}
}
