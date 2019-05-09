package com.framework.loippi.service;

import com.framework.loippi.entity.TSalary;
import java.util.List;
/**
 * SERVICE - TSalary(工资表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TSalaryService  extends GenericService<TSalary, Long> {
	public	List<TSalary> findListByPage(Object parameter);
}
