package com.framework.loippi.dao;

import com.framework.loippi.entity.TcommentScope;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TcommentScope(评论范围)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TcommentScopeDao  extends GenericDao<TcommentScope, Long> {
	List<TcommentScope> findListByPage(Object parameter);

}
