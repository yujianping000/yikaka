package com.framework.loippi.service;

import com.framework.loippi.entity.TBank;
import java.util.List;
/**
 * SERVICE - TBank(银行)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TBankService  extends GenericService<TBank, Long> {
	public	List<TBank> findListByPage(Object parameter);
}
