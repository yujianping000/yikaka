package com.framework.loippi.dao;

import com.framework.loippi.entity.ActivityStuView;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - ActivityStuView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface ActivityStuViewDao  extends GenericDao<ActivityStuView, Long> {
	List<ActivityStuView> findListByPage(Object parameter);

}
