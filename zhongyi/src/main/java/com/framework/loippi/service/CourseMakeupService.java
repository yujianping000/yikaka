package com.framework.loippi.service;

import com.framework.loippi.entity.CourseMakeup;
import java.util.List;
/**
 * SERVICE - CourseMakeup(补课申请)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseMakeupService  extends GenericService<CourseMakeup, Long> {
	public	List<CourseMakeup> findListByPage(Object parameter);
}
