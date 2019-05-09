package com.framework.loippi.service;

import com.framework.loippi.entity.OrgnizationScanlog;
import java.util.List;
/**
 * SERVICE - OrgnizationScanlog(扫码机构列表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrgnizationScanlogService  extends GenericService<OrgnizationScanlog, Long> {
	public	List<OrgnizationScanlog> findListByPage(Object parameter);
}
