package com.framework.loippi.service;

import com.framework.loippi.entity.CourceStuView;
import java.util.List;
/**
 * SERVICE - CourceStuView(课程查询学员)
 * 
 * @author wgb
 * @version 2.0
 */
public interface CourceStuViewService  extends GenericService<CourceStuView, Long> {
	public	List<CourceStuView> findListByPage(Object parameter);
}
