package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TcontinueClassDao;
import com.framework.loippi.entity.TcontinueClass;
import com.framework.loippi.service.TcontinueClassService;
import java.util.List;
/**
 * SERVICE - TcontinueClass(续班表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TcontinueClassServiceImpl extends GenericServiceImpl<TcontinueClass, Long> implements TcontinueClassService {
	
	@Autowired
	private TcontinueClassDao tcontinueClassDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tcontinueClassDao);
	}

	public List<TcontinueClass> findListByPage(Object parameter){
		return tcontinueClassDao.findListByPage(parameter);
	}
}
