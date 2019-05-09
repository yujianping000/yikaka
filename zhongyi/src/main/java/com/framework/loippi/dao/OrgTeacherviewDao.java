package com.framework.loippi.dao;

import com.framework.loippi.entity.OrgTeacherview;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrgTeacherview(机构教师)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgTeacherviewDao  extends GenericDao<OrgTeacherview, Long> {
	List<OrgTeacherview> findListByPage(Object parameter);

}
