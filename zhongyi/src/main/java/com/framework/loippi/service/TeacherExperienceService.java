package com.framework.loippi.service;

import com.framework.loippi.entity.TeacherExperience;
import java.util.List;
/**
 * SERVICE - TeacherExperience(任教经历)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeacherExperienceService  extends GenericService<TeacherExperience, Long> {
	public	List<TeacherExperience> findListByPage(Object parameter);
}
