package com.framework.loippi.service;

import com.framework.loippi.entity.CourseChangeRecord;
import java.util.List;
/**
 * SERVICE - CourseChangeRecord(调班跟进)
 * 
 * @author wyr
 * @version 2.0
 */
public interface CourseChangeRecordService  extends GenericService<CourseChangeRecord, Long> {
	public	List<CourseChangeRecord> findListByPage(Object parameter);
}
