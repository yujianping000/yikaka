package com.framework.loippi.dao;

import com.framework.loippi.entity.UsrRelation;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - UsrRelation(用户关系：关注、好友、黑名单)
 * 
 * @author wgb
 * @version 2.0
 */
public interface UsrRelationDao  extends GenericDao<UsrRelation, Long> {
	List<UsrRelation> findListByPage(Object parameter);
	List<String> findListFirstName(Object parameter);

}
