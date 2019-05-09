package com.framework.loippi.service;

import com.framework.loippi.entity.Student;

import com.framework.loippi.support.Pageable;

import java.util.List;

/**
 * SERVICE - Student(学生表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface StudentService  extends GenericService<Student, Long> {
	public	List<Student> findListByPage(Object parameter);
	
	List<Student> findStudentListByOrgId(Object parameter);

	public Long getById(String account);
}
