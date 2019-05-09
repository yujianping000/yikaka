package com.framework.loippi.service;

import com.framework.loippi.entity.ActivityUser;
import java.util.List;
/**
 * SERVICE - ActivityUser(用户活动列表)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ActivityUserService  extends GenericService<ActivityUser, Long> {
	public	List<ActivityUser> findListByPage(Object parameter);
}
