package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.KeepAccountsDao;
import com.framework.loippi.entity.KeepAccounts;
import com.framework.loippi.service.KeepAccountsService;
import java.util.List;
/**
 * SERVICE - KeepAccounts(手机记账表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class KeepAccountsServiceImpl extends GenericServiceImpl<KeepAccounts, Long> implements KeepAccountsService {
	
	@Autowired
	private KeepAccountsDao keepAccountsDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(keepAccountsDao);
	}

	public List<KeepAccounts> findListByPage(Object parameter){
		return keepAccountsDao.findListByPage(parameter);
	}
}
