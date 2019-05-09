package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CallRollDao;
import com.framework.loippi.entity.CallRoll;
import com.framework.loippi.service.CallRollService;
import java.util.List;
/**
 * SERVICE - CallRoll(点名)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class CallRollServiceImpl extends GenericServiceImpl<CallRoll, Long> implements CallRollService {
	
	@Autowired
	private CallRollDao callRollDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(callRollDao);
	}

	public List<CallRoll> findListByPage(Object parameter){
		return callRollDao.findListByPage(parameter);
	}

	@Override
	public List<CallRoll>  selectStatus() {
		// TODO Auto-generated method stub
		return callRollDao.selectStatus();
	}
}
