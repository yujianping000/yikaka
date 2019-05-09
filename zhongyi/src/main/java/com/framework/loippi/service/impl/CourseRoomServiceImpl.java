package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseRoomDao;
import com.framework.loippi.entity.CourseRoom;
import com.framework.loippi.service.CourseRoomService;
import java.util.List;
/**
 * SERVICE - CourseRoom(教室)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseRoomServiceImpl extends GenericServiceImpl<CourseRoom, Long> implements CourseRoomService {
	
	@Autowired
	private CourseRoomDao courseRoomDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseRoomDao);
	}

	public List<CourseRoom> findListByPage(Object parameter){
		return courseRoomDao.findListByPage(parameter);
	}
}
