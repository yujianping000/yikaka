package com.framework.loippi.service;

import com.framework.loippi.entity.ActivityJoin;
import java.util.List;
/**
 * SERVICE - ActivityJoin(活动报名)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ActivityJoinService  extends GenericService<ActivityJoin, Long> {
	public	List<ActivityJoin> findListByPage(Object parameter);
}
