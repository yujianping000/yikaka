package com.framework.loippi.service;

import com.framework.loippi.entity.TSalaryLog;
import java.util.List;
/**
 * SERVICE - TSalaryLog(工资记录表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TSalaryLogService  extends GenericService<TSalaryLog, Long> {
	public	List<TSalaryLog> findListByPage(Object parameter);
	public	void  payroll();
}
