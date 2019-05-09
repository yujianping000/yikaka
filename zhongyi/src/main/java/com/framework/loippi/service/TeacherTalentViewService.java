package com.framework.loippi.service;

import com.framework.loippi.entity.TeacherTalentView;
import java.util.List;
/**
 * SERVICE - TeacherTalentView(机构邀请)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TeacherTalentViewService  extends GenericService<TeacherTalentView, Long> {
	public	List<TeacherTalentView> findListByPage(Object parameter);
}
