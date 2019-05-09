package com.framework.loippi.service;

import com.framework.loippi.entity.TSign;
import java.util.List;
/**
 * SERVICE - TSign(签到)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TSignService  extends GenericService<TSign, Long> {
	public	List<TSign> findListByPage(Object parameter);
	Long findIsSign(Object parameter);
}
