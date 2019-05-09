package com.framework.loippi.service;

import com.framework.loippi.entity.TcontinueClass;
import java.util.List;
/**
 * SERVICE - TcontinueClass(续班表)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TcontinueClassService  extends GenericService<TcontinueClass, Long> {
	public	List<TcontinueClass> findListByPage(Object parameter);
}
