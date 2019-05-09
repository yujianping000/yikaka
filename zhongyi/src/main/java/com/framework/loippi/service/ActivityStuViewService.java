package com.framework.loippi.service;

import com.framework.loippi.entity.ActivityStuView;
import java.util.List;
/**
 * SERVICE - ActivityStuView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface ActivityStuViewService  extends GenericService<ActivityStuView, Long> {
	public	List<ActivityStuView> findListByPage(Object parameter);
}
