package com.framework.loippi.service;

import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.controller.app.model.OrderUid;
import com.framework.loippi.entity.Activity;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrderInfo;
import com.framework.loippi.entity.User;

import java.util.List;
/**
 * SERVICE - Order(订单表)
 * y
 * @author wgb
 * @version 2.0
 */
public interface OrderService  extends GenericService<Order, Long> {
	public	List<Order> findListByPage(Object parameter);
	public String toBuyNow(Order order,LoginUser user ,Course	course);
	public String toBuyNow(Order order,LoginUser user ,Activity	activity);
	boolean toPayByMoney(Integer payType,Order order);
	public List<Order> findParnetIdByOrgId(Object parameter);
	String findTotalFee(Object parameter);
	String findTotaNum(Object parameter);
	OrderInfo SeachTotalFeeSeach(Object parameter);
	public List<Order> findStuIdByCourseId(Object parameter);
	
	public String toBuyNow2(Order order,User user ,Course	course);
	
	public List<OrderUid> findOrderUid(Long orderId);
	public Long getById(String id);
}
