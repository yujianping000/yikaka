package com.framework.loippi.service;

import com.framework.loippi.entity.TWages;
import java.util.List;
/**
 * SERVICE - TWages(工资表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TWagesService  extends GenericService<TWages, Long> {
	public	List<TWages> findListByPage(Object parameter);
}
