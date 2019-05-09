package com.framework.loippi.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.ValidTotalDao;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.ValidTotal;
import com.framework.loippi.service.ValidTotalService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SERVICE - ValidTotal(提现金额)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class ValidTotalServiceImpl extends GenericServiceImpl<ValidTotal, Long> implements ValidTotalService {
	
	@Autowired
	private ValidTotalDao validTotalDao;

	@Autowired
	public void setGenericDao() {
		super.setGenericDao(validTotalDao);
	}

	public List<ValidTotal> findListByPage(Object parameter){
		return validTotalDao.findListByPage(parameter);
	}

	
	@Override
	public void addValidTotal(Order order) {
		Map map=new HashMap();
		map.put("orgId", order.getOrgId());
		ValidTotal  validTotal=validTotalDao.findByOrgId(map);
		if(validTotal==null){
			 validTotal=new ValidTotal();
			 validTotal.setOrgId(order.getOrgId());
			 validTotal.setTotal(order.getPayment());
			 validTotalDao.insert(validTotal);
			 System.out.println("未有统计");
		}else{
			 BigDecimal total=validTotal.getTotal();
			 validTotal.setTotal(total.add(order.getPayment()));
			 validTotalDao.update(validTotal);
			 System.out.println("已有统计");
		}
		
		
	}
}
