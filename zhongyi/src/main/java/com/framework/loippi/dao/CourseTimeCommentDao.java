package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseTimeComment;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseTimeComment(课程教学时间评论)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTimeCommentDao  extends GenericDao<CourseTimeComment, Long> {
	List<CourseTimeComment> findListByPage(Object parameter);

}
