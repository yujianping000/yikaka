package com.framework.loippi.service;

import com.framework.loippi.entity.OrgTeacherview;
import java.util.List;
/**
 * SERVICE - OrgTeacherview(机构教师)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgTeacherviewService  extends GenericService<OrgTeacherview, Long> {
	public	List<OrgTeacherview> findListByPage(Object parameter);
}
