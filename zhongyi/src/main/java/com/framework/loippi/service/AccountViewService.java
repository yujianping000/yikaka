package com.framework.loippi.service;

import com.framework.loippi.entity.AccountView;
import java.util.List;
/**
 * SERVICE - AccountView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface AccountViewService  extends GenericService<AccountView, Long> {
	public	List<AccountView> findListByPage(Object parameter);
	String seachDayaccount(Object parameter);
}
