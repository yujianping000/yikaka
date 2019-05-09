package com.framework.loippi.dao;

import com.framework.loippi.entity.TeacherCommentRemark;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TeacherCommentRemark(教师评论标签)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TeacherCommentRemarkDao  extends GenericDao<TeacherCommentRemark, Long> {
	List<TeacherCommentRemark> findListByPage(Object parameter);

}
