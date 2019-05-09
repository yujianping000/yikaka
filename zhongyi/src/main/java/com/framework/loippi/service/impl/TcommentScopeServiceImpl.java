package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TcommentScopeDao;
import com.framework.loippi.entity.TcommentScope;
import com.framework.loippi.service.TcommentScopeService;
import java.util.List;
/**
 * SERVICE - TcommentScope(评论范围)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TcommentScopeServiceImpl extends GenericServiceImpl<TcommentScope, Long> implements TcommentScopeService {
	
	@Autowired
	private TcommentScopeDao tcommentScopeDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tcommentScopeDao);
	}

	public List<TcommentScope> findListByPage(Object parameter){
		return tcommentScopeDao.findListByPage(parameter);
	}
}
