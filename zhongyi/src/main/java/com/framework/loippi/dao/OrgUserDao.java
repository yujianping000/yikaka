package com.framework.loippi.dao;

import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrgUser(机构用户)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgUserDao  extends GenericDao<OrgUser, Long> {
	List<OrgUser> findListByPage(Object parameter);

}
