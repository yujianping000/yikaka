package com.framework.loippi.dao;

import com.framework.loippi.entity.HourView;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - HourView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface HourViewDao  extends GenericDao<HourView, Long> {
	List<HourView> findListByPage(Object parameter);

}
