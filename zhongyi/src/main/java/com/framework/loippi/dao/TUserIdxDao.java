package com.framework.loippi.dao;

import com.framework.loippi.entity.TUserIdx;
import com.framework.loippi.mybatis.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO - TUserIdx(用户索引表)
 * 
 * @author 用户索引表
 * @version 2.0
 */
public interface TUserIdxDao  extends GenericDao<TUserIdx, Long> {

    List<TUserIdx> findInId(@Param("ids")List<Long> ids);

}
