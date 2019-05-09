package com.framework.loippi.service;

import com.framework.loippi.entity.TeacherCommentRemark;
import java.util.List;
/**
 * SERVICE - TeacherCommentRemark(教师评论标签)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TeacherCommentRemarkService  extends GenericService<TeacherCommentRemark, Long> {
	public	List<TeacherCommentRemark> findListByPage(Object parameter);
}
