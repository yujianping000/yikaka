package com.framework.loippi.service;

import com.framework.loippi.entity.OrgStudentview;
import java.util.List;
/**
 * SERVICE - OrgStudentview(机构学员)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgStudentviewService  extends GenericService<OrgStudentview, Long> {
	public	List<OrgStudentview> findListByPage(Object parameter);
}
