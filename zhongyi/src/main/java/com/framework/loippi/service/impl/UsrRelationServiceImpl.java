package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.UsrRelationDao;
import com.framework.loippi.entity.UsrRelation;
import com.framework.loippi.service.UsrRelationService;
import java.util.List;
/**
 * SERVICE - UsrRelation(用户关系：关注、好友、黑名单)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class UsrRelationServiceImpl extends GenericServiceImpl<UsrRelation, Long> implements UsrRelationService {
	
	@Autowired
	private UsrRelationDao usrRelationDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(usrRelationDao);
	}

	public List<UsrRelation> findListByPage(Object parameter){
		return usrRelationDao.findListByPage(parameter);
	}

	@Override
	public List<String> findListFirstName(Object parameter) {
		// TODO Auto-generated method stub
		return usrRelationDao.findListFirstName(parameter);
	}
}
