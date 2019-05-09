package com.framework.loippi.dao;

import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TeacherAuth(老师认证)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeacherAuthDao  extends GenericDao<TeacherAuth, Long> {
	List<TeacherAuth> findListByPage(Object parameter);

}
