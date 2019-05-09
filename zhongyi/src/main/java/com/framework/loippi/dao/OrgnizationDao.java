package com.framework.loippi.dao;

import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Orgnization(机构)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrgnizationDao  extends GenericDao<Orgnization, Long> {
	List<Orgnization> findListByPage(Object parameter);
	List<Orgnization> findListByPage1(Object parameter);
	
}
