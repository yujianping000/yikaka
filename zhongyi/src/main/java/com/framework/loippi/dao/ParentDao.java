package com.framework.loippi.dao;

import com.framework.loippi.entity.Parent;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * DAO - Parent(家长)
 * 
 * @author wyr
 * @version 2.0
 */
public interface ParentDao  extends GenericDao<Parent, Long> {
	List<Parent> findListByPage(Object parameter);
	Long getById(@Param("account") String account);

}
