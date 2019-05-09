package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TelphoneDao;
import com.framework.loippi.entity.Telphone;
import com.framework.loippi.service.TelphoneService;
import java.util.List;
/**
 * SERVICE - Telphone(电话)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TelphoneServiceImpl extends GenericServiceImpl<Telphone, Long> implements TelphoneService {
	
	@Autowired
	private TelphoneDao telphoneDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(telphoneDao);
	}

	public List<Telphone> findListByPage(Object parameter){
		return telphoneDao.findListByPage(parameter);
	}
}
