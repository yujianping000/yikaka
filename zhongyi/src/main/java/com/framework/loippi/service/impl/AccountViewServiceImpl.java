package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.AccountViewDao;
import com.framework.loippi.entity.AccountView;
import com.framework.loippi.service.AccountViewService;
import java.util.List;
/**
 * SERVICE - AccountView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class AccountViewServiceImpl extends GenericServiceImpl<AccountView, Long> implements AccountViewService {
	
	@Autowired
	private AccountViewDao accountViewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(accountViewDao);
	}

	public List<AccountView> findListByPage(Object parameter){
		return accountViewDao.findListByPage(parameter);
	}

	@Override
	public String seachDayaccount(Object parameter) {
		// TODO Auto-generated method stub
		return accountViewDao.seachDayaccount(parameter);
	}
}
