package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TeacherExperienceDao;
import com.framework.loippi.entity.TeacherExperience;
import com.framework.loippi.service.TeacherExperienceService;
import java.util.List;
/**
 * SERVICE - TeacherExperience(任教经历)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TeacherExperienceServiceImpl extends GenericServiceImpl<TeacherExperience, Long> implements TeacherExperienceService {
	
	@Autowired
	private TeacherExperienceDao teacherExperienceDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(teacherExperienceDao);
	}

	public List<TeacherExperience> findListByPage(Object parameter){
		return teacherExperienceDao.findListByPage(parameter);
	}
}
