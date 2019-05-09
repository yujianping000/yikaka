package com.framework.loippi.service;

import com.framework.loippi.entity.PaymentStatistics;

import java.math.BigDecimal;
import java.util.List;
/**
 * SERVICE - PaymentStatistics(支出统计)
 * 
 * @author wgb
 * @version 2.0
 */
public interface PaymentStatisticsService  extends GenericService<PaymentStatistics, Long> {
	public	List<PaymentStatistics> findListByPage(Object parameter);
	String findSumOs(Object parameter);
	String findSumOa(Object parameter);
	String findSumOaos(Object parameter);
	String findSumOw(Object parameter);
	String findSumOaow(Object parameter);
}
