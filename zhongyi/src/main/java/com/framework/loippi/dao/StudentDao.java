package com.framework.loippi.dao;

import com.framework.loippi.entity.Student;
import com.framework.loippi.mybatis.dao.GenericDao;


import java.util.List;

import org.apache.ibatis.annotations.Param;





/**
 * DAO - Student(学生表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface StudentDao  extends GenericDao<Student, Long> {
	List<Student> findListByPage(Object parameter);
	
	//根据机构id查询学生及家长信息
	List<Student> findStudentListByOrgId(Object parameter);
	//根据家长账号查询学生id
	Long getById(@Param("account") String account);

}
