package com.framework.loippi.service;

import com.framework.loippi.entity.TeacherComment;
import java.util.List;
/**
 * SERVICE - TeacherComment(机构评价)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TeacherCommentService  extends GenericService<TeacherComment, Long> {
	public	List<TeacherComment> findListByPage(Object parameter);
}
