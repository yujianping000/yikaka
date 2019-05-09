package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseRoom;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseRoom(教室)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseRoomDao  extends GenericDao<CourseRoom, Long> {
	List<CourseRoom> findListByPage(Object parameter);

}
