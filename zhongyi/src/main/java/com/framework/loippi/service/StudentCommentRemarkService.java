package com.framework.loippi.service;

import com.framework.loippi.entity.StudentCommentRemark;
import java.util.List;
/**
 * SERVICE - StudentCommentRemark(学生评价)
 * 
 * @author wyr
 * @version 2.0
 */
public interface StudentCommentRemarkService  extends GenericService<StudentCommentRemark, Long> {
	public	List<StudentCommentRemark> findListByPage(Object parameter);
}
