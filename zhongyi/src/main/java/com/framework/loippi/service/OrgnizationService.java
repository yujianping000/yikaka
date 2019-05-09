package com.framework.loippi.service;

import com.framework.loippi.entity.Area;
import com.framework.loippi.entity.Orgnization;
import java.util.List;
/**
 * SERVICE - Orgnization(机构)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrgnizationService  extends GenericService<Orgnization, Long> {
	public	List<Orgnization> findListByPage(Object parameter);
	public	List<Orgnization> findListByPage1(Object parameter);
	public	 List getGroup(List<Area> cityss);
}
