package com.framework.loippi.service;

import com.framework.loippi.entity.UsrRelation;
import java.util.List;
/**
 * SERVICE - UsrRelation(用户关系：关注、好友、黑名单)
 * 
 * @author wgb
 * @version 2.0
 */
public interface UsrRelationService  extends GenericService<UsrRelation, Long> {
	public	List<UsrRelation> findListByPage(Object parameter);
	public List<String> findListFirstName(Object parameter);
}
