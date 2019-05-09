package com.framework.loippi.service;

import com.framework.loippi.entity.SystemFeedback;
import java.util.List;
/**
 * SERVICE - SystemFeedback(意见反馈)
 * 
 * @author wyr
 * @version 2.0
 */
public interface SystemFeedbackService  extends GenericService<SystemFeedback, Long> {
	public	List<SystemFeedback> findListByPage(Object parameter);
}
