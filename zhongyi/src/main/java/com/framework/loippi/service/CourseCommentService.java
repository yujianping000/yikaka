package com.framework.loippi.service;

import com.framework.loippi.entity.CourseComment;
import java.util.List;
/**
 * SERVICE - CourseComment(课程评论)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseCommentService  extends GenericService<CourseComment, Long> {
	public	List<CourseComment> findListByPage(Object parameter);
	public void updateCourseCommentCount(Long courseId);
	public void updateCourseCommentCountAddCount(Long courseId);
	
}
