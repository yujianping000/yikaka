package com.framework.loippi.service;

import com.framework.loippi.entity.WagesList;
import java.util.List;
/**
 * SERVICE - WagesList(工资明细)
 * 
 * @author wgb
 * @version 2.0
 */
public interface WagesListService  extends GenericService<WagesList, Long> {
	public	List<WagesList> findListByPage(Object parameter);
}
