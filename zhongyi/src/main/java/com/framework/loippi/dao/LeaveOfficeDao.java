package com.framework.loippi.dao;

import com.framework.loippi.entity.LeaveOffice;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - LeaveOffice(离职审核表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface LeaveOfficeDao  extends GenericDao<LeaveOffice, Long> {
	List<LeaveOffice> findListByPage(Object parameter);

}
