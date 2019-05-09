package com.framework.loippi.dao;

import com.framework.loippi.entity.TWithdraw;
import com.framework.loippi.entity.WithdrawInfo;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TWithdraw(tWithdraw)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TWithdrawDao  extends GenericDao<TWithdraw, Long> {
	List<TWithdraw> findListByPage(Object parameter);
	WithdrawInfo seachListByPage(Object parameter);
	
}
