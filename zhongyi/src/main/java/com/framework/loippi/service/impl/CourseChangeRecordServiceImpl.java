package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseChangeRecordDao;
import com.framework.loippi.entity.CourseChangeRecord;
import com.framework.loippi.service.CourseChangeRecordService;
import java.util.List;
/**
 * SERVICE - CourseChangeRecord(调班跟进)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class CourseChangeRecordServiceImpl extends GenericServiceImpl<CourseChangeRecord, Long> implements CourseChangeRecordService {
	
	@Autowired
	private CourseChangeRecordDao courseChangeRecordDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseChangeRecordDao);
	}

	public List<CourseChangeRecord> findListByPage(Object parameter){
		return courseChangeRecordDao.findListByPage(parameter);
	}
}
