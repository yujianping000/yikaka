package com.framework.loippi.service;

import com.framework.loippi.entity.Holiday;
import java.util.List;
/**
 * SERVICE - Holiday(节假日)
 * 
 * @author wgb
 * @version 2.0
 */
public interface HolidayService  extends GenericService<Holiday, Long> {
	public	List<Holiday> findListByPage(Object parameter);
}
