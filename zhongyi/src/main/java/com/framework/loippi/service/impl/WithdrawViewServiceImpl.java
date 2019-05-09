package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.WithdrawViewDao;
import com.framework.loippi.entity.WithdrawView;
import com.framework.loippi.service.WithdrawViewService;
import java.util.List;
/**
 * SERVICE - WithdrawView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class WithdrawViewServiceImpl extends GenericServiceImpl<WithdrawView, Long> implements WithdrawViewService {
	
	@Autowired
	private WithdrawViewDao withdrawViewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(withdrawViewDao);
	}

	public List<WithdrawView> findListByPage(Object parameter){
		return withdrawViewDao.findListByPage(parameter);
	}
}
