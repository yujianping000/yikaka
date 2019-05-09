package com.framework.loippi.service;

import com.framework.loippi.entity.CourseMakeupRecord;
import java.util.List;
/**
 * SERVICE - CourseMakeupRecord(补课跟进)
 * 
 * @author wyr
 * @version 2.0
 */
public interface CourseMakeupRecordService  extends GenericService<CourseMakeupRecord, Long> {
	public	List<CourseMakeupRecord> findListByPage(Object parameter);
}
