package com.framework.loippi.service;

import com.framework.loippi.entity.TParent;

/**
 * SERVICE - TParent(家长表)
 *
 * @author longbh
 * @version 2.0
 */
public interface TParentService extends GenericService<TParent, Long> {

    TParent socialLogin(String code, String avatar, String nickName);

    TParent registor(String phone, String password);

}
