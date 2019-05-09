package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseCommentRemark;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseCommentRemark(评论标签)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseCommentRemarkDao  extends GenericDao<CourseCommentRemark, Long> {
	List<CourseCommentRemark> findListByPage(Object parameter);

}
