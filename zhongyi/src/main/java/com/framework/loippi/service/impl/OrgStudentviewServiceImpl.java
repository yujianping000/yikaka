package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OrgStudentviewDao;
import com.framework.loippi.entity.OrgStudentview;
import com.framework.loippi.service.OrgStudentviewService;
import java.util.List;
/**
 * SERVICE - OrgStudentview(机构学员)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class OrgStudentviewServiceImpl extends GenericServiceImpl<OrgStudentview, Long> implements OrgStudentviewService {
	
	@Autowired
	private OrgStudentviewDao orgStudentviewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgStudentviewDao);
	}

	public List<OrgStudentview> findListByPage(Object parameter){
		return orgStudentviewDao.findListByPage(parameter);
	}
}
