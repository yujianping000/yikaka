package com.framework.loippi.dao;

import com.framework.loippi.entity.OrderRemark;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrderRemark(评论标签)
 * 
 * @author wgb
 * @version 2.0
 */
public interface OrderRemarkDao  extends GenericDao<OrderRemark, Long> {
	List<OrderRemark> findListByPage(Object parameter);

}
