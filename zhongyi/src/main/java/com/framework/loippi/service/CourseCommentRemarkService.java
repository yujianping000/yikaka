package com.framework.loippi.service;

import com.framework.loippi.entity.CourseCommentRemark;
import java.util.List;
/**
 * SERVICE - CourseCommentRemark(评论标签)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourseCommentRemarkService  extends GenericService<CourseCommentRemark, Long> {
	public	List<CourseCommentRemark> findListByPage(Object parameter);
}
