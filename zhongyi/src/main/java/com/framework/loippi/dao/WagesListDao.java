package com.framework.loippi.dao;

import com.framework.loippi.entity.WagesList;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - WagesList(工资明细)
 * 
 * @author wgb
 * @version 2.0
 */
public interface WagesListDao  extends GenericDao<WagesList, Long> {
	List<WagesList> findListByPage(Object parameter);

}
