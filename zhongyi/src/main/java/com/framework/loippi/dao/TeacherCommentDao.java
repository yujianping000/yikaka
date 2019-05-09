package com.framework.loippi.dao;

import com.framework.loippi.entity.TeacherComment;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - TeacherComment(机构评价)
 * 
 * @author wyr
 * @version 2.0
 */
public interface TeacherCommentDao  extends GenericDao<TeacherComment, Long> {
	List<TeacherComment> findListByPage(Object parameter);

}
