package com.framework.loippi.service;

import com.framework.loippi.entity.TleaveManage;
import java.util.List;
/**
 * SERVICE - TleaveManage(请假管理)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TleaveManageService  extends GenericService<TleaveManage, Long> {
	public	List<TleaveManage> findListByPage(Object parameter);
}
