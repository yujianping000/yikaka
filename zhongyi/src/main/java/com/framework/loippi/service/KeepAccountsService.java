package com.framework.loippi.service;

import com.framework.loippi.entity.KeepAccounts;
import java.util.List;
/**
 * SERVICE - KeepAccounts(手机记账表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface KeepAccountsService  extends GenericService<KeepAccounts, Long> {
	public	List<KeepAccounts> findListByPage(Object parameter);
}
