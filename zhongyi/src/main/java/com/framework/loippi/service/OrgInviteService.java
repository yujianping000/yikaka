package com.framework.loippi.service;

import com.framework.loippi.entity.OrgInvite;
import java.util.List;
/**
 * SERVICE - OrgInvite(机构邀请)
 * 
 * @author wyr
 * @version 2.0
 */
public interface OrgInviteService  extends GenericService<OrgInvite, Long> {
	public	List<OrgInvite> findListByPage(Object parameter);
}
