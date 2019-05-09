package com.framework.loippi.service;

import com.framework.loippi.entity.CourseTimeComment;
import java.util.List;
/**
 * SERVICE - CourseTimeComment(课程教学时间评论)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseTimeCommentService  extends GenericService<CourseTimeComment, Long> {
	public	List<CourseTimeComment> findListByPage(Object parameter);
}
