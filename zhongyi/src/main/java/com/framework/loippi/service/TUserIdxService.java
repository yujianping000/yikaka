package com.framework.loippi.service;

import com.framework.loippi.entity.TUserIdx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SERVICE - TUserIdx(用户索引表)
 * 
 * @author 用户索引表
 * @version 2.0
 */
public interface TUserIdxService  extends GenericService<TUserIdx, Long> {

    List<TUserIdx> findInId(@Param("ids")List<Long> ids);

}
