package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.controller.app.model.MesList;
import com.framework.loippi.dao.OrgDynamicDao;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.service.OrgDynamicService;
import java.util.List;
/**
 * SERVICE - OrgDynamic(机构动态)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class OrgDynamicServiceImpl extends GenericServiceImpl<OrgDynamic, Long> implements OrgDynamicService {
	
	@Autowired
	private OrgDynamicDao orgDynamicDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgDynamicDao);
	}

	public List<OrgDynamic> findListByPage(Object parameter){
		return orgDynamicDao.findListByPage(parameter);
	}

	@Override
	public List<OrgDynamic> findOrgDynamic(Object parameter) {
		// TODO Auto-generated method stub
		return  orgDynamicDao.findOrgDynamic(parameter);
	}

	@Override
	public List<MesList> findMesList(Object parameter) {
		// TODO Auto-generated method stub
		return  orgDynamicDao.findMesList(parameter);
	}

	@Override
	public List<OrgDynamic> findByType3(Object parameter) {
		// TODO Auto-generated method stub
		return orgDynamicDao.findByType3(parameter);
	}

	@Override
	public Long deleteByParams(Object parameter) {
		// TODO Auto-generated method stub
		return orgDynamicDao.deleteByParams(parameter);
	}
	
	
}
