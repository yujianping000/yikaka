package com.framework.loippi.service;

import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.ValidTotal;
import java.util.List;
/**
 * SERVICE - ValidTotal(提现金额)
 * 
 * @author wgb
 * @version 2.0
 */
public interface ValidTotalService  extends GenericService<ValidTotal, Long> {
	public	List<ValidTotal> findListByPage(Object parameter);
	
	public void addValidTotal(Order order);
}
