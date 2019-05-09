package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.PaymentStatisticsDao;
import com.framework.loippi.entity.PaymentStatistics;
import com.framework.loippi.service.PaymentStatisticsService;

import java.math.BigDecimal;
import java.util.List;
/**
 * SERVICE - PaymentStatistics(支出统计)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class PaymentStatisticsServiceImpl extends GenericServiceImpl<PaymentStatistics, Long> implements PaymentStatisticsService {
	
	@Autowired
	private PaymentStatisticsDao paymentStatisticsDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(paymentStatisticsDao);
	}

	public List<PaymentStatistics> findListByPage(Object parameter){
		return paymentStatisticsDao.findListByPage(parameter);
	}

	@Override
	public String findSumOs(Object parameter) {
		// TODO Auto-generated method stub
		return paymentStatisticsDao.findSumOs(parameter);
	}

	@Override
	public String findSumOa(Object parameter) {
		// TODO Auto-generated method stub
		return paymentStatisticsDao.findSumOa(parameter);
	}

	@Override
	public String findSumOaos(Object parameter) {
		// TODO Auto-generated method stub
		return paymentStatisticsDao.findSumOaos(parameter);
	}

	@Override
	public String findSumOw(Object parameter) {
		// TODO Auto-generated method stub
		return paymentStatisticsDao.findSumOw(parameter);
	}

	@Override
	public String findSumOaow(Object parameter) {
		// TODO Auto-generated method stub
		return paymentStatisticsDao.findSumOaow(parameter);
	}
}
