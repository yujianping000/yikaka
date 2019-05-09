package com.framework.loippi.dao;

import com.framework.loippi.entity.TWages;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TWages(工资表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TWagesDao  extends GenericDao<TWages, Long> {
	List<TWages> findListByPage(Object parameter);

}
