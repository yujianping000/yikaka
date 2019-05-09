package com.framework.loippi.dao;

import com.framework.loippi.entity.OrgInvite;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - OrgInvite(机构邀请)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgInviteDao  extends GenericDao<OrgInvite, Long> {
	List<OrgInvite> findListByPage(Object parameter);

}
