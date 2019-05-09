package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TeacherTalentViewDao;
import com.framework.loippi.entity.TeacherTalentView;
import com.framework.loippi.service.TeacherTalentViewService;
import java.util.List;
/**
 * SERVICE - TeacherTalentView(机构邀请)
 * 
 * @author wyr
 * @version 2.0
 */
@Service
public class TeacherTalentViewServiceImpl extends GenericServiceImpl<TeacherTalentView, Long> implements TeacherTalentViewService {
	
	@Autowired
	private TeacherTalentViewDao teacherTalentViewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(teacherTalentViewDao);
	}

	public List<TeacherTalentView> findListByPage(Object parameter){
		return teacherTalentViewDao.findListByPage(parameter);
	}
}
