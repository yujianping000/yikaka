package com.framework.loippi.dao;

import com.framework.loippi.entity.TattendaanceCount;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TattendaanceCount(考勤管理)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TattendaanceCountDao  extends GenericDao<TattendaanceCount, Long> {
	List<TattendaanceCount> findListByPage(Object parameter);

}
