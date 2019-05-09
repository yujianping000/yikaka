package com.framework.loippi.service;

import com.framework.loippi.entity.Telphone;
import java.util.List;
/**
 * SERVICE - Telphone(电话)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TelphoneService  extends GenericService<Telphone, Long> {
	public	List<Telphone> findListByPage(Object parameter);
}
