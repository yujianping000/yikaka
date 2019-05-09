package com.framework.loippi.dao;

import com.framework.loippi.entity.CourceStuView;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourceStuView(课程查询学员)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourceStuViewDao  extends GenericDao<CourceStuView, Long> {
	List<CourceStuView> findListByPage(Object parameter);

}
