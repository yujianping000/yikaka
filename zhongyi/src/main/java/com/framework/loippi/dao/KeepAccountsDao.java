package com.framework.loippi.dao;

import com.framework.loippi.entity.KeepAccounts;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - KeepAccounts(手机记账表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface KeepAccountsDao  extends GenericDao<KeepAccounts, Long> {
	List<KeepAccounts> findListByPage(Object parameter);

}
