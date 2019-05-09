package com.framework.loippi.dao;

import com.framework.loippi.entity.TcontinueClass;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TcontinueClass(续班表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TcontinueClassDao  extends GenericDao<TcontinueClass, Long> {
	List<TcontinueClass> findListByPage(Object parameter);

}
