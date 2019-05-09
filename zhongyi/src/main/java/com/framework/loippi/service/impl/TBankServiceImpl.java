package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TBankDao;
import com.framework.loippi.entity.TBank;
import com.framework.loippi.service.TBankService;
import java.util.List;
/**
 * SERVICE - TBank(银行)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TBankServiceImpl extends GenericServiceImpl<TBank, Long> implements TBankService {
	
	@Autowired
	private TBankDao tBankDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tBankDao);
	}

	public List<TBank> findListByPage(Object parameter){
		return tBankDao.findListByPage(parameter);
	}
}
