package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.OrderRemarkDao;
import com.framework.loippi.entity.OrderRemark;
import com.framework.loippi.service.OrderRemarkService;
import java.util.List;
/**
 * SERVICE - OrderRemark(评论标签)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class OrderRemarkServiceImpl extends GenericServiceImpl<OrderRemark, Long> implements OrderRemarkService {
	
	@Autowired
	private OrderRemarkDao orderRemarkDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orderRemarkDao);
	}

	public List<OrderRemark> findListByPage(Object parameter){
		return orderRemarkDao.findListByPage(parameter);
	}
}
