package com.framework.loippi.dao;

import com.framework.loippi.entity.WithdrawView;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - WithdrawView(VIEW)
 * 
 * @author wgb
 * @version 2.0
 */
public interface WithdrawViewDao  extends GenericDao<WithdrawView, Long> {
	List<WithdrawView> findListByPage(Object parameter);

}
