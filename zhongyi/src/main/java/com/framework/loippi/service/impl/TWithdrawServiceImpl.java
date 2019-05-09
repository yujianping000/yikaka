package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TWithdrawDao;
import com.framework.loippi.entity.TWithdraw;
import com.framework.loippi.entity.WithdrawInfo;
import com.framework.loippi.service.TWithdrawService;
import java.util.List;
/**
 * SERVICE - TWithdraw(tWithdraw)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TWithdrawServiceImpl extends GenericServiceImpl<TWithdraw, Long> implements TWithdrawService {
	
	@Autowired
	private TWithdrawDao tWithdrawDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tWithdrawDao);
	}

	public List<TWithdraw> findListByPage(Object parameter){
		return tWithdrawDao.findListByPage(parameter);
	}

	@Override
	public WithdrawInfo seachListByPage(Object parameter) {
		// TODO Auto-generated method stub
		return tWithdrawDao.seachListByPage(parameter);
	}
}
