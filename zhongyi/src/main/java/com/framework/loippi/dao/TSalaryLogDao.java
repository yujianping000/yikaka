package com.framework.loippi.dao;

import com.framework.loippi.entity.TSalaryLog;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * DAO - TSalaryLog(工资记录表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TSalaryLogDao  extends GenericDao<TSalaryLog, Long> {
	List<TSalaryLog> findListByPage(Object parameter);
	String  getStarts(Object parameter);
	Double getCommentSum(Object parameter);
    Integer	getCountOrder(Object parameter);
    Integer	getCountComment(Object parameter);
    Double getRemarkOnSum(Object parameter);
    Double getconOnSum(Object parameter);
    Integer getCountTime(Object parameter);
    BigDecimal getTotal(Object parameter);
    Integer getOrderTotal(Object parameter);
    Integer getcontinueclasTotal(Object parameter);
    BigDecimal getSalaryTotal(Object parameter);
}
