package com.framework.loippi.service;

import com.framework.loippi.entity.CourseBookRecord;
import java.util.List;
/**
 * SERVICE - CourseBookRecord(试课跟进)
 * 
 * @author wyr
 * @version 2.0
 */
public interface CourseBookRecordService  extends GenericService<CourseBookRecord, Long> {
	public	List<CourseBookRecord> findListByPage(Object parameter);
}
