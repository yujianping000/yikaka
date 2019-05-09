package com.framework.loippi.service;

import com.framework.loippi.entity.TeaStuCoursetime;
import java.util.List;
/**
 * SERVICE - TeaStuCoursetime(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeaStuCoursetimeService  extends GenericService<TeaStuCoursetime, Long> {
	public	List<TeaStuCoursetime> findListByPage(Object parameter);
}
