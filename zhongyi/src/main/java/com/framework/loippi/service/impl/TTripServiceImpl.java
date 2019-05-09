package com.framework.loippi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TTripDao;
import com.framework.loippi.entity.TTrip;
import com.framework.loippi.service.TTripService;

/**
 * SERVICE - TTrip(行程)
 * 
 * @author longbh
 * @version 2.0
 */
@Service
public class TTripServiceImpl extends GenericServiceImpl<TTrip, Long> implements TTripService {
	
	@Autowired
	private TTripDao tTripDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tTripDao);
	}


	@Override
	public List<TTrip> findListTrip() {
		// TODO Auto-generated method stub
		return tTripDao.findListTrip();
	}
}
