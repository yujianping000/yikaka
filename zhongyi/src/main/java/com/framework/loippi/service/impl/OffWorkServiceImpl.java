package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OffWorkDao;
import com.framework.loippi.entity.OffWork;
import com.framework.loippi.service.OffWorkService;
import java.util.List;
/**
 * SERVICE - OffWork(请假)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class OffWorkServiceImpl extends GenericServiceImpl<OffWork, Long> implements OffWorkService {
	
	@Autowired
	private OffWorkDao offWorkDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(offWorkDao);
	}

	public List<OffWork> findListByPage(Object parameter){
		return offWorkDao.findListByPage(parameter);
	}
}
