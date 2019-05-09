package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TBannerDao;
import com.framework.loippi.entity.TBanner;
import com.framework.loippi.service.TBannerService;
import java.util.List;
/**
 * SERVICE - TBanner(横幅表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TBannerServiceImpl extends GenericServiceImpl<TBanner, Long> implements TBannerService {
	
	@Autowired
	private TBannerDao tBannerDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tBannerDao);
	}

	public List<TBanner> findListByPage(Object parameter){
		return tBannerDao.findListByPage(parameter);
	}
}
