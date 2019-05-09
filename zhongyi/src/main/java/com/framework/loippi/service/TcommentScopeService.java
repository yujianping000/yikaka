package com.framework.loippi.service;

import com.framework.loippi.entity.TcommentScope;
import java.util.List;
/**
 * SERVICE - TcommentScope(评论范围)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TcommentScopeService  extends GenericService<TcommentScope, Long> {
	public	List<TcommentScope> findListByPage(Object parameter);
}
