package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.ParentDao;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.service.ParentService;
import java.util.List;
/**
 * SERVICE - Parent(家长)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class ParentServiceImpl extends GenericServiceImpl<Parent, Long> implements ParentService {
	
	@Autowired
	private ParentDao parentDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(parentDao);
	}

	public List<Parent> findListByPage(Object parameter){
		return parentDao.findListByPage(parameter);
	}

	
	public Long getById(String account) {
		
		return parentDao.getById(account);
	}
}
