package com.framework.loippi.service;

import com.framework.loippi.entity.TleaveExplain;
import java.util.List;
/**
 * SERVICE - TleaveExplain(请假说明)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TleaveExplainService  extends GenericService<TleaveExplain, Long> {
	public	List<TleaveExplain> findListByPage(Object parameter);
}
