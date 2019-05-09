package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OrgInviteDao;
import com.framework.loippi.entity.OrgInvite;
import com.framework.loippi.service.OrgInviteService;
import java.util.List;
/**
 * SERVICE - OrgInvite(机构邀请)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class OrgInviteServiceImpl extends GenericServiceImpl<OrgInvite, Long> implements OrgInviteService {
	
	@Autowired
	private OrgInviteDao orgInviteDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgInviteDao);
	}

	public List<OrgInvite> findListByPage(Object parameter){
		return orgInviteDao.findListByPage(parameter);
	}
}
