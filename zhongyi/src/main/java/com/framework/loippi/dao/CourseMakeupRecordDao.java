package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseMakeupRecord;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseMakeupRecord(补课跟进)
 * 
 * @author wyr
 * @version 2.0
 */
public interface CourseMakeupRecordDao  extends GenericDao<CourseMakeupRecord, Long> {
	List<CourseMakeupRecord> findListByPage(Object parameter);

}
