package com.framework.loippi.service;

import com.framework.loippi.entity.TBanner;
import java.util.List;
/**
 * SERVICE - TBanner(横幅表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TBannerService  extends GenericService<TBanner, Long> {
	public	List<TBanner> findListByPage(Object parameter);
}
