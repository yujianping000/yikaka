package com.framework.loippi.service;

import com.framework.loippi.entity.Teacher;
import java.util.List;
/**
 * SERVICE - Teacher(教师表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeacherService  extends GenericService<Teacher, Long> {
	public	List<Teacher> findListByPage(Object parameter);
	
	List<Teacher> findTeacherListByOrgId(Object parameter);
}
