package com.framework.loippi.dao;

import com.framework.loippi.controller.app.model.OrderUid;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrderInfo;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * DAO - Order(订单表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrderDao  extends GenericDao<Order, Long> {
	List<Order> findListByPage(Object parameter);
	List<Order> findParnetIdByOrgId(Object parameter);
	String findTotalFee(Object parameter);
	String findTotaNum(Object parameter);
	OrderInfo SeachTotalFeeSeach(Object parameter);
	List<Order> findStuIdByCourseId(Object parameter);
	List<OrderUid> findOrderUid(Long orderId);
	Long getById(@Param("uuid") String id);
}
