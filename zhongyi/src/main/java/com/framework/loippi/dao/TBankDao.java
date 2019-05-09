package com.framework.loippi.dao;

import com.framework.loippi.entity.TBank;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TBank(银行)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TBankDao  extends GenericDao<TBank, Long> {
	List<TBank> findListByPage(Object parameter);

}
