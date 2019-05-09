package com.framework.loippi.service;

import com.framework.loippi.entity.TremarkOn;
import java.util.List;
/**
 * SERVICE - TremarkOn(点评率)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TremarkOnService  extends GenericService<TremarkOn, Long> {
	public	List<TremarkOn> findListByPage(Object parameter);
}
