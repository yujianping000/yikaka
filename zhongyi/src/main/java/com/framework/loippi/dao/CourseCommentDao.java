package com.framework.loippi.dao;

import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - CourseComment(课程评论)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseCommentDao  extends GenericDao<CourseComment, Long> {
	List<CourseComment> findListByPage(Object parameter);

}
