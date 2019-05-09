package com.framework.loippi.service;

import com.framework.loippi.entity.TimeView;
import java.util.List;
/**
 * SERVICE - TimeView(机构课时)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TimeViewService  extends GenericService<TimeView, Long> {
	public	List<TimeView> findListByPage(Object parameter);
}
