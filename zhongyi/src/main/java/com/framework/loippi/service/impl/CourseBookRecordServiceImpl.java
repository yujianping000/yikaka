package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseBookRecordDao;
import com.framework.loippi.entity.CourseBookRecord;
import com.framework.loippi.service.CourseBookRecordService;
import java.util.List;
/**
 * SERVICE - CourseBookRecord(试课跟进)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class CourseBookRecordServiceImpl extends GenericServiceImpl<CourseBookRecord, Long> implements CourseBookRecordService {
	
	@Autowired
	private CourseBookRecordDao courseBookRecordDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseBookRecordDao);
	}

	public List<CourseBookRecord> findListByPage(Object parameter){
		return courseBookRecordDao.findListByPage(parameter);
	}
}
