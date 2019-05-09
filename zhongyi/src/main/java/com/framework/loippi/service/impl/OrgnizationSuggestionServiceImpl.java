package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OrgnizationSuggestionDao;
import com.framework.loippi.entity.OrgnizationSuggestion;
import com.framework.loippi.service.OrgnizationSuggestionService;
import java.util.List;
/**
 * SERVICE - OrgnizationSuggestion(机构意见)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class OrgnizationSuggestionServiceImpl extends GenericServiceImpl<OrgnizationSuggestion, Long> implements OrgnizationSuggestionService {
	
	@Autowired
	private OrgnizationSuggestionDao orgnizationSuggestionDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgnizationSuggestionDao);
	}

	public List<OrgnizationSuggestion> findListByPage(Object parameter){
		return orgnizationSuggestionDao.findListByPage(parameter);
	}
}
