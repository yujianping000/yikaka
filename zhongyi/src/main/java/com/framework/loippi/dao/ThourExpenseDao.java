package com.framework.loippi.dao;

import com.framework.loippi.entity.ThourExpense;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - ThourExpense(课时费用)
 * 
 * @author wgb
 * @version 2.0
 */
public interface ThourExpenseDao  extends GenericDao<ThourExpense, Long> {
	List<ThourExpense> findListByPage(Object parameter);

}
