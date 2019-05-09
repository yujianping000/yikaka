package com.framework.loippi.dao;

import com.framework.loippi.entity.OrgnizationSuggestion;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrgnizationSuggestion(机构意见)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrgnizationSuggestionDao  extends GenericDao<OrgnizationSuggestion, Long> {
	List<OrgnizationSuggestion> findListByPage(Object parameter);

}
