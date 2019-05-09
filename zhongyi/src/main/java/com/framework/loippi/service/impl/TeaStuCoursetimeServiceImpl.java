package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TeaStuCoursetimeDao;
import com.framework.loippi.entity.TeaStuCoursetime;
import com.framework.loippi.service.TeaStuCoursetimeService;
import java.util.List;
/**
 * SERVICE - TeaStuCoursetime(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TeaStuCoursetimeServiceImpl extends GenericServiceImpl<TeaStuCoursetime, Long> implements TeaStuCoursetimeService {
	
	@Autowired
	private TeaStuCoursetimeDao teaStuCoursetimeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(teaStuCoursetimeDao);
	}

	public List<TeaStuCoursetime> findListByPage(Object parameter){
		return teaStuCoursetimeDao.findListByPage(parameter);
	}
}
