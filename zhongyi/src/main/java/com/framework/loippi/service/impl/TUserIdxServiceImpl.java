package com.framework.loippi.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TUserIdxDao;
import com.framework.loippi.entity.TUserIdx;
import com.framework.loippi.service.TUserIdxService;

import java.util.List;

/**
 * SERVICE - TUserIdx(用户索引表)
 * 
 * @author 用户索引表
 * @version 2.0
 */
@Service
public class TUserIdxServiceImpl extends GenericServiceImpl<TUserIdx, Long> implements TUserIdxService {
	
	@Autowired
	private TUserIdxDao tUserIdxDao;

	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tUserIdxDao);
	}

	@Override
	public List<TUserIdx> findInId(@Param("ids") List<Long> ids) {
		return tUserIdxDao.findInId(ids);
	}
}
