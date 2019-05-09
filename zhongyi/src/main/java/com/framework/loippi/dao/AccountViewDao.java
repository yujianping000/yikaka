package com.framework.loippi.dao;

import com.framework.loippi.entity.AccountView;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - AccountView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface AccountViewDao  extends GenericDao<AccountView, Long> {
	List<AccountView> findListByPage(Object parameter);
	String seachDayaccount(Object parameter);
}
