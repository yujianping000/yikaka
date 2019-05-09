package com.framework.loippi.service;

import com.framework.loippi.entity.OffWork;
import java.util.List;
/**
 * SERVICE - OffWork(请假)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OffWorkService  extends GenericService<OffWork, Long> {
	public	List<OffWork> findListByPage(Object parameter);
}
