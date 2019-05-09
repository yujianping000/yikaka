package com.framework.loippi.service;

import com.framework.loippi.entity.LeaveOffice;
import java.util.List;
/**
 * SERVICE - LeaveOffice(离职审核表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface LeaveOfficeService  extends GenericService<LeaveOffice, Long> {
	public	List<LeaveOffice> findListByPage(Object parameter);
}
