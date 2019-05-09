package com.framework.loippi.service;

import com.framework.loippi.entity.CourseRoom;
import java.util.List;
/**
 * SERVICE - CourseRoom(教室)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseRoomService  extends GenericService<CourseRoom, Long> {
	public	List<CourseRoom> findListByPage(Object parameter);
}
