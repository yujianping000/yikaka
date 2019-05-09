package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseChangeRecord;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseChangeRecord(调班跟进)
 * 
 * @author wyr
 * @version 2.0
 */
public interface CourseChangeRecordDao  extends GenericDao<CourseChangeRecord, Long> {
	List<CourseChangeRecord> findListByPage(Object parameter);

}
