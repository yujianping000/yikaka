package com.framework.loippi.service;

import com.framework.loippi.entity.Parent;
import java.util.List;
/**
 * SERVICE - Parent(家长)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ParentService  extends GenericService<Parent, Long> {
	public	List<Parent> findListByPage(Object parameter);
	//根据账号查询id
	public Long getById(String account);
}
