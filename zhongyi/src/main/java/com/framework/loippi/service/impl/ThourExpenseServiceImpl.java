package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.ThourExpenseDao;
import com.framework.loippi.entity.ThourExpense;
import com.framework.loippi.service.ThourExpenseService;
import java.util.List;
/**
 * SERVICE - ThourExpense(课时费用)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class ThourExpenseServiceImpl extends GenericServiceImpl<ThourExpense, Long> implements ThourExpenseService {
	
	@Autowired
	private ThourExpenseDao thourExpenseDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(thourExpenseDao);
	}

	public List<ThourExpense> findListByPage(Object parameter){
		return thourExpenseDao.findListByPage(parameter);
	}
}
