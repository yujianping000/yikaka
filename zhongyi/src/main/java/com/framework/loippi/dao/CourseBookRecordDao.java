package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseBookRecord;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseBookRecord(试课跟进)
 * 
 * @author wyr
 * @version 2.0
 */
public interface CourseBookRecordDao  extends GenericDao<CourseBookRecord, Long> {
	List<CourseBookRecord> findListByPage(Object parameter);

}
