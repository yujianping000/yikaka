package com.framework.loippi.dao;

import com.framework.loippi.entity.PaymentStatistics;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * DAO - PaymentStatistics(支出统计)
 * 
 * @author wgb
 * @version 2.0
 */
public interface PaymentStatisticsDao  extends GenericDao<PaymentStatistics, Long> {
	List<PaymentStatistics> findListByPage(Object parameter);
	String findSumOs(Object parameter);
	String findSumOa(Object parameter);
	String findSumOaos(Object parameter);
	String findSumOw(Object parameter);
	String findSumOaow(Object parameter);
}
