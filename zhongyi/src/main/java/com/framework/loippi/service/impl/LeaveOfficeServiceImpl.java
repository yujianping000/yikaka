package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.LeaveOfficeDao;
import com.framework.loippi.entity.LeaveOffice;
import com.framework.loippi.service.LeaveOfficeService;
import java.util.List;
/**
 * SERVICE - LeaveOffice(离职审核表)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class LeaveOfficeServiceImpl extends GenericServiceImpl<LeaveOffice, Long> implements LeaveOfficeService {
	
	@Autowired
	private LeaveOfficeDao leaveOfficeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(leaveOfficeDao);
	}

	public List<LeaveOffice> findListByPage(Object parameter){
		return leaveOfficeDao.findListByPage(parameter);
	}
}
