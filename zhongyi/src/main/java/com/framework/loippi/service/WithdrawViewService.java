package com.framework.loippi.service;

import com.framework.loippi.entity.WithdrawView;
import java.util.List;
/**
 * SERVICE - WithdrawView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface WithdrawViewService  extends GenericService<WithdrawView, Long> {
	public	List<WithdrawView> findListByPage(Object parameter);
}
