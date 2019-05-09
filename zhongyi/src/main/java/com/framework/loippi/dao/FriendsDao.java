package com.framework.loippi.dao;

import com.framework.loippi.entity.Friends;
import com.framework.loippi.mybatis.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO - Friends(user)
 *
 * @author huangqi
 * @version 2.0
 */
public interface FriendsDao extends GenericDao<Friends, Long> {

    List<Friends> findIn(@Param("destUids") List<Long> destUids, @Param("uid") Long uid);
    
    public void clearApply(@Param("uid") Long uid);

}
