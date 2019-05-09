package com.framework.loippi.dao;

import com.framework.loippi.entity.SystemFeedback;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - SystemFeedback(意见反馈)
 * 
 * @author wyr
 * @version 2.0
 */
public interface SystemFeedbackDao  extends GenericDao<SystemFeedback, Long> {
	List<SystemFeedback> findListByPage(Object parameter);

}
