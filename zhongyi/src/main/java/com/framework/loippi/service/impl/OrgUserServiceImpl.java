package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OrgUserDao;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.service.OrgUserService;
import java.util.List;
/**
 * SERVICE - OrgUser(机构用户)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class OrgUserServiceImpl extends GenericServiceImpl<OrgUser, Long> implements OrgUserService {
	
	@Autowired
	private OrgUserDao orgUserDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgUserDao);
	}

	public List<OrgUser> findListByPage(Object parameter){
		return orgUserDao.findListByPage(parameter);
	}
}
