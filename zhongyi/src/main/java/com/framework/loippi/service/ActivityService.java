package com.framework.loippi.service;

import com.framework.loippi.entity.Activity;
import java.util.List;
/**
 * SERVICE - Activity(活动)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ActivityService  extends GenericService<Activity, Long> {
	public	List<Activity> findListByPage(Object parameter);
	public	List<Activity> findMyListByPage(Object parameter);
	public	 Long  update2buyActivity(Long id);
	public	 Long  update2CancelActivity(Long id);
}
