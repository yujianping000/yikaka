package com.framework.loippi.service;

import com.framework.loippi.entity.OrgUser;
import java.util.List;
/**
 * SERVICE - OrgUser(机构用户)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgUserService  extends GenericService<OrgUser, Long> {
	public	List<OrgUser> findListByPage(Object parameter);
}
