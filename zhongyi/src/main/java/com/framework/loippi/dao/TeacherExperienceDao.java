package com.framework.loippi.dao;

import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TeacherExperience(任教经历)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeacherExperienceDao  extends GenericDao<TeacherExperience, Long> {
	List<TeacherExperience> findListByPage(Object parameter);

}
