package com.framework.loippi.service;

import com.framework.loippi.controller.app.model.MesList;
import com.framework.loippi.entity.OrgDynamic;
import java.util.List;
/**
 * SERVICE - OrgDynamic(机构动态)
 * 
 * @author wmj
 * @version 2.0
 */
public interface OrgDynamicService  extends GenericService<OrgDynamic, Long> {
	public	List<OrgDynamic> findListByPage(Object parameter);
	public List<OrgDynamic> findOrgDynamic(Object parameter);
	public List<MesList> findMesList(Object parameter);
	List<OrgDynamic> findByType3(Object parameter);
	public Long deleteByParams(Object parameter);
}
