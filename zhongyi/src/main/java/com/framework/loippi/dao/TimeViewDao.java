package com.framework.loippi.dao;

import com.framework.loippi.entity.TimeView;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TimeView(机构课时)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TimeViewDao  extends GenericDao<TimeView, Long> {
	List<TimeView> findListByPage(Object parameter);

}
