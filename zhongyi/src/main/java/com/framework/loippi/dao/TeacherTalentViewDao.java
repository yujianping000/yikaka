package com.framework.loippi.dao;

import com.framework.loippi.entity.TeacherTalentView;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TeacherTalentView(机构邀请)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TeacherTalentViewDao  extends GenericDao<TeacherTalentView, Long> {
	List<TeacherTalentView> findListByPage(Object parameter);

}
