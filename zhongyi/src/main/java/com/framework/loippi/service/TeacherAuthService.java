package com.framework.loippi.service;

import com.framework.loippi.entity.TeacherAuth;
import java.util.List;
/**
 * SERVICE - TeacherAuth(老师认证)
 * 
 * @author wgb
 * @version 2.0
 */
public interface TeacherAuthService  extends GenericService<TeacherAuth, Long> {
	public	List<TeacherAuth> findListByPage(Object parameter);
}
