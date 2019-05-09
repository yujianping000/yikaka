package com.framework.loippi.service;

import com.framework.loippi.entity.ThourExpense;
import java.util.List;
/**
 * SERVICE - ThourExpense(课时费用)
 * 
 * @author wgb
 * @version 2.0
 */
public interface ThourExpenseService  extends GenericService<ThourExpense, Long> {
	public	List<ThourExpense> findListByPage(Object parameter);
}
