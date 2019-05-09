package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourceStuViewDao;
import com.framework.loippi.entity.CourceStuView;
import com.framework.loippi.service.CourceStuViewService;
import java.util.List;
/**
 * SERVICE - CourceStuView(课程查询学员)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourceStuViewServiceImpl extends GenericServiceImpl<CourceStuView, Long> implements CourceStuViewService {
	
	@Autowired
	private CourceStuViewDao courceStuViewDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courceStuViewDao);
	}

	public List<CourceStuView> findListByPage(Object parameter){
		return courceStuViewDao.findListByPage(parameter);
	}
}
