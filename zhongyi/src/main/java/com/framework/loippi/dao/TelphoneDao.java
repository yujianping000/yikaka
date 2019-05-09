package com.framework.loippi.dao;

import com.framework.loippi.entity.Telphone;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Telphone(电话)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TelphoneDao  extends GenericDao<Telphone, Long> {
	List<Telphone> findListByPage(Object parameter);

}
