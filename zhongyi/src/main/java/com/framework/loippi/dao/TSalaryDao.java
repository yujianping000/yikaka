package com.framework.loippi.dao;

import com.framework.loippi.entity.TSalary;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TSalary(工资表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TSalaryDao  extends GenericDao<TSalary, Long> {
	List<TSalary> findListByPage(Object parameter);
}
