package com.framework.loippi.service;

import com.framework.loippi.entity.TWithdraw;
import com.framework.loippi.entity.WithdrawInfo;

import java.util.List;
/**
 * SERVICE - TWithdraw(tWithdraw)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TWithdrawService  extends GenericService<TWithdraw, Long> {
	public	List<TWithdraw> findListByPage(Object parameter);
	WithdrawInfo seachListByPage(Object parameter);
}
