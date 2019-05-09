package com.framework.loippi.service.impl;

import com.framework.loippi.entity.TUserIdx;
import com.framework.loippi.service.TUserIdxService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TParentDao;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.service.TParentService;

/**
 * SERVICE - TParent(家长表)
 *
 * @author longbh
 * @version 2.0
 */
@Service
public class TParentServiceImpl extends GenericServiceImpl<TParent, Long> implements TParentService {

    @Autowired
    private TParentDao tParentDao;
    @Autowired
    private TUserIdxService tUserIdxService;


    @Autowired
    public void setGenericDao() {
        super.setGenericDao(tParentDao);
    }

    @Override
    public TParent socialLogin(String code, String avatar, String nickName) {
        TParent tParent = new TParent();
        tParent.setSocialCode(code);
        tParent.setAvatar(avatar);
        tParent.setNickName(nickName);

        TUserIdx tUserIdx = new TUserIdx();
        tUserIdx.setAvatar(tParent.getAvatar());
        tUserIdx.setName(nickName);
        tUserIdx.setAvatar(avatar);
        tUserIdx.setAccount(code);
        tUserIdx.setType(2);
        tUserIdxService.save(tUserIdx);
        tParent.setId(tUserIdx.getId());

        save(tParent);
        tParent.setId(tUserIdx.getId());
        return tParent;
    }

    @Override
    public TParent registor(String phone, String password) {
        TParent tParent = new TParent();
        tParent.setAccount(phone);
        tParent.setPassword(DigestUtils.md5Hex(password));

        TUserIdx tUserIdx = new TUserIdx();
        tUserIdx.setAvatar(tParent.getAvatar());
        tUserIdx.setAccount(phone);
        tUserIdx.setType(2);
        tUserIdxService.save(tUserIdx);
        tParent.setId(tUserIdx.getId());

        save(tParent);
        tParent.setId(tUserIdx.getId());
        return tParent;
    }

}
