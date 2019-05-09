package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TSalaryDao;
import com.framework.loippi.entity.TSalary;
import com.framework.loippi.service.TSalaryService;
import java.util.List;
/**
 * SERVICE - TSalary(工资表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TSalaryServiceImpl extends GenericServiceImpl<TSalary, Long> implements TSalaryService {
	
	@Autowired
	private TSalaryDao tSalaryDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tSalaryDao);
	}

	public List<TSalary> findListByPage(Object parameter){
		return tSalaryDao.findListByPage(parameter);
	}
}
