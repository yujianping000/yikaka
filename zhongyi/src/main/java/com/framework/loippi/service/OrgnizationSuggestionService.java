package com.framework.loippi.service;

import com.framework.loippi.entity.OrgnizationSuggestion;
import java.util.List;
/**
 * SERVICE - OrgnizationSuggestion(机构意见)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrgnizationSuggestionService  extends GenericService<OrgnizationSuggestion, Long> {
	public	List<OrgnizationSuggestion> findListByPage(Object parameter);
}
