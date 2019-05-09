package com.framework.loippi.dao;

import com.framework.loippi.entity.CallRoll;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CallRoll(点名)
 * 
 * @author wmj
 * @version 2.0
 */
public interface CallRollDao  extends GenericDao<CallRoll, Long> {
	List<CallRoll> findListByPage(Object parameter);
	 List<CallRoll>   selectStatus();

}
