package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OrgTeacherviewDao;
import com.framework.loippi.entity.OrgTeacherview;
import com.framework.loippi.service.OrgTeacherviewService;
import java.util.List;
/**
 * SERVICE - OrgTeacherview(机构教师)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class OrgTeacherviewServiceImpl extends GenericServiceImpl<OrgTeacherview, Long> implements OrgTeacherviewService {
	
	@Autowired
	private OrgTeacherviewDao orgTeacherviewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgTeacherviewDao);
	}

	public List<OrgTeacherview> findListByPage(Object parameter){
		return orgTeacherviewDao.findListByPage(parameter);
	}
}
