package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TremarkOnDao;
import com.framework.loippi.entity.TremarkOn;
import com.framework.loippi.service.TremarkOnService;
import java.util.List;
/**
 * SERVICE - TremarkOn(点评率)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TremarkOnServiceImpl extends GenericServiceImpl<TremarkOn, Long> implements TremarkOnService {
	
	@Autowired
	private TremarkOnDao tremarkOnDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tremarkOnDao);
	}

	public List<TremarkOn> findListByPage(Object parameter){
		return tremarkOnDao.findListByPage(parameter);
	}
}
