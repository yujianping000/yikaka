package com.framework.loippi.dao;

import com.framework.loippi.entity.ValidTotal;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - ValidTotal(提现金额)
 * 
 * @author wgb
 * @version 2.0
 */
public interface ValidTotalDao  extends GenericDao<ValidTotal, Long> {
	List<ValidTotal> findListByPage(Object parameter);
	ValidTotal findByOrgId(Object parameter);
}
