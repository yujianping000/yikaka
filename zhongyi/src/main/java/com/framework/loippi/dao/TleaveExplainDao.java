package com.framework.loippi.dao;

import com.framework.loippi.entity.TleaveExplain;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TleaveExplain(请假说明)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TleaveExplainDao  extends GenericDao<TleaveExplain, Long> {
	List<TleaveExplain> findListByPage(Object parameter);

}
