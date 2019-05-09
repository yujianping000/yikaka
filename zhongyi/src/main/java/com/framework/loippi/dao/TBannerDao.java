package com.framework.loippi.dao;

import com.framework.loippi.entity.TBanner;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TBanner(横幅表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TBannerDao  extends GenericDao<TBanner, Long> {
	List<TBanner> findListByPage(Object parameter);

}
