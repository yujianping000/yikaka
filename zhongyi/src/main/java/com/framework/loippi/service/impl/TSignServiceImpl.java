package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TSignDao;
import com.framework.loippi.entity.TSign;
import com.framework.loippi.service.TSignService;
import java.util.List;
/**
 * SERVICE - TSign(签到)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TSignServiceImpl extends GenericServiceImpl<TSign, Long> implements TSignService {
	
	@Autowired
	private TSignDao tSignDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tSignDao);
	}

	public List<TSign> findListByPage(Object parameter){
		return tSignDao.findListByPage(parameter);
	}

	@Override
	public Long findIsSign(Object parameter) {
		// TODO Auto-generated method stub
		return tSignDao.findIsSign(parameter);
	}

}
