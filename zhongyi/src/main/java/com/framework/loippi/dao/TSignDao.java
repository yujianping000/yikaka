package com.framework.loippi.dao;

import com.framework.loippi.entity.TSign;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TSign(签到)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TSignDao  extends GenericDao<TSign, Long> {
	List<TSign> findListByPage(Object parameter);
	Long findIsSign(Object parameter);
	

}
