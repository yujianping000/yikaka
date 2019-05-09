package com.framework.loippi.dao;

import com.framework.loippi.entity.OrgnizationScanlog;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrgnizationScanlog(扫码机构列表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrgnizationScanlogDao  extends GenericDao<OrgnizationScanlog, Long> {
	List<OrgnizationScanlog> findListByPage(Object parameter);

}
