package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TleaveExplainDao;
import com.framework.loippi.entity.TleaveExplain;
import com.framework.loippi.service.TleaveExplainService;
import java.util.List;
/**
 * SERVICE - TleaveExplain(请假说明)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TleaveExplainServiceImpl extends GenericServiceImpl<TleaveExplain, Long> implements TleaveExplainService {
	
	@Autowired
	private TleaveExplainDao tleaveExplainDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tleaveExplainDao);
	}

	public List<TleaveExplain> findListByPage(Object parameter){
		return tleaveExplainDao.findListByPage(parameter);
	}
}
