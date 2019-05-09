package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OrgnizationScanlogDao;
import com.framework.loippi.entity.OrgnizationScanlog;
import com.framework.loippi.service.OrgnizationScanlogService;
import java.util.List;
/**
 * SERVICE - OrgnizationScanlog(扫码机构列表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class OrgnizationScanlogServiceImpl extends GenericServiceImpl<OrgnizationScanlog, Long> implements OrgnizationScanlogService {
	
	@Autowired
	private OrgnizationScanlogDao orgnizationScanlogDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgnizationScanlogDao);
	}

	public List<OrgnizationScanlog> findListByPage(Object parameter){
		return orgnizationScanlogDao.findListByPage(parameter);
	}
}
