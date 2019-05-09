package com.framework.loippi.dao;

import java.util.List;

import com.framework.loippi.entity.TTrip;
import com.framework.loippi.mybatis.dao.GenericDao;

/**
 * DAO - TTrip(行程)
 * 
 * @author longbh
 * @version 2.0
 */
public interface TTripDao  extends GenericDao<TTrip, Long> {
	  List<TTrip> findListTrip();
}
