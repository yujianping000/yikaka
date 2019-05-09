package com.framework.loippi.dao;

import com.framework.loippi.controller.app.model.MesList;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrgDynamic(机构动态)
 * 
 * @author wmj
 * @version 2.0
 */
public interface OrgDynamicDao  extends GenericDao<OrgDynamic, Long> {
	List<OrgDynamic> findListByPage(Object parameter);
	List<OrgDynamic> findOrgDynamic(Object parameter);
	List<MesList> findMesList(Object parameter);
	List<OrgDynamic> findByType3(Object parameter);
	Long deleteByParams(Object parameter);
	

}
