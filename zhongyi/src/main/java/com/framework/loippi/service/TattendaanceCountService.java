package com.framework.loippi.service;

import com.framework.loippi.entity.TattendaanceCount;
import java.util.List;
/**
 * SERVICE - TattendaanceCount(考勤管理)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TattendaanceCountService  extends GenericService<TattendaanceCount, Long> {
	public	List<TattendaanceCount> findListByPage(Object parameter);
}
