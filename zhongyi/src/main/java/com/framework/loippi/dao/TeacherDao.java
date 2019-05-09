package com.framework.loippi.dao;


import com.framework.loippi.entity.Teacher;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Teacher(教师表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeacherDao  extends GenericDao<Teacher, Long> {
	List<Teacher> findListByPage(Object parameter);
	
	//根据机构id查询教师信息
	List<Teacher> findTeacherListByOrgId(Object parameter);

}
