package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TleaveManageDao;
import com.framework.loippi.entity.TleaveManage;
import com.framework.loippi.service.TleaveManageService;
import java.util.List;
/**
 * SERVICE - TleaveManage(请假管理)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TleaveManageServiceImpl extends GenericServiceImpl<TleaveManage, Long> implements TleaveManageService {
	
	@Autowired
	private TleaveManageDao tleaveManageDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tleaveManageDao);
	}

	public List<TleaveManage> findListByPage(Object parameter){
		return tleaveManageDao.findListByPage(parameter);
	}
}
