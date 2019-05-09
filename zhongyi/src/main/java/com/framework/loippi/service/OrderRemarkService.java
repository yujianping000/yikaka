package com.framework.loippi.service;

import com.framework.loippi.entity.OrderRemark;
import java.util.List;
/**
 * SERVICE - OrderRemark(评论标签)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrderRemarkService  extends GenericService<OrderRemark, Long> {
	public	List<OrderRemark> findListByPage(Object parameter);
}
