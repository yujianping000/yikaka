package com.framework.loippi.dao;

import com.framework.loippi.entity.StudentCommentRemark;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - StudentCommentRemark(学生评价)
 * 
 * @author wyr
 * @version 2.0
 */
public interface StudentCommentRemarkDao  extends GenericDao<StudentCommentRemark, Long> {
	List<StudentCommentRemark> findListByPage(Object parameter);

}
