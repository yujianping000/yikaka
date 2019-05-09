package com.framework.loippi.dao;

import com.framework.loippi.entity.Holiday;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Holiday(节假日)
 * 
 * @author wgb
 * @version 2.0
 */
public interface HolidayDao  extends GenericDao<Holiday, Long> {
	List<Holiday> findListByPage(Object parameter);

}
