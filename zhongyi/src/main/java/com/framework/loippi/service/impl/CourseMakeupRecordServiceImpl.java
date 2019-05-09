package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseMakeupRecordDao;
import com.framework.loippi.entity.CourseMakeupRecord;
import com.framework.loippi.service.CourseMakeupRecordService;
import java.util.List;
/**
 * SERVICE - CourseMakeupRecord(补课跟进)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class CourseMakeupRecordServiceImpl extends GenericServiceImpl<CourseMakeupRecord, Long> implements CourseMakeupRecordService {
	
	@Autowired
	private CourseMakeupRecordDao courseMakeupRecordDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseMakeupRecordDao);
	}

	public List<CourseMakeupRecord> findListByPage(Object parameter){
		return courseMakeupRecordDao.findListByPage(parameter);
	}
}
