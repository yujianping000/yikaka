package com.framework.loippi.dao;

import com.framework.loippi.entity.OrgStudentview;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrgStudentview(机构学员)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgStudentviewDao  extends GenericDao<OrgStudentview, Long> {
	List<OrgStudentview> findListByPage(Object parameter);

}
