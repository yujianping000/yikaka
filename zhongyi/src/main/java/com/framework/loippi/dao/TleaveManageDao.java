package com.framework.loippi.dao;

import com.framework.loippi.entity.TleaveManage;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TleaveManage(请假管理)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TleaveManageDao  extends GenericDao<TleaveManage, Long> {
	List<TleaveManage> findListByPage(Object parameter);

}
