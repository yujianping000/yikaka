package com.framework.loippi.service;

import com.framework.loippi.entity.HourView;
import java.util.List;
/**
 * SERVICE - HourView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface HourViewService  extends GenericService<HourView, Long> {
	public	List<HourView> findListByPage(Object parameter);
}
