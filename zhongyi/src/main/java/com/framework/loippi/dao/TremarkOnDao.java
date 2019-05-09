package com.framework.loippi.dao;

import com.framework.loippi.entity.TremarkOn;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TremarkOn(点评率)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TremarkOnDao  extends GenericDao<TremarkOn, Long> {
	List<TremarkOn> findListByPage(Object parameter);

}
