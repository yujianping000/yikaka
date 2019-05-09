package com.framework.loippi.dao;

import com.framework.loippi.entity.SystemAppversion;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - SystemAppversion(app版本更新信息)
 * 
 * @author wgb
 * @version 2.0
 */
public interface SystemAppversionDao  extends GenericDao<SystemAppversion, Long> {
	List<SystemAppversion> findListByPage(Object parameter);

}
