package com.framework.loippi.dao;

import com.framework.loippi.entity.TeaStuCoursetime;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TeaStuCoursetime(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeaStuCoursetimeDao  extends GenericDao<TeaStuCoursetime, Long> {
	List<TeaStuCoursetime> findListByPage(Object parameter);

}
