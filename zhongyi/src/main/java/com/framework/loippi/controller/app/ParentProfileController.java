package com.framework.loippi.controller.app;


import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.service.TParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by longbh on 2016/12/18.
 */
@Controller
@RequestMapping("api/app/parentprofile/*")
public class ParentProfileController extends BaseController {

    @Autowired
    private TParentService tParentService;

    @ResponseBody
    @RequestMapping(value = "getInfo", method = RequestMethod.POST)
    public String getInfo(Long uid) {
        TParent tParent = tParentService.find(uid);
        return jsonSucess(tParent);
    }

    /**
     * x修改昵称
     *
     * @param request
     * @param nickName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateNick", method = RequestMethod.POST)
    public String updateNick(HttpServletRequest request, String nickName) {
        LoginUser loginUser = (LoginUser) request.getAttribute("session");
        TParent tParent = tParentService.find(loginUser.getId());
        tParent.setNickName(nickName);
        tParentService.update(tParent);
        return jsonSucess(tParent);
    }

    /**
     * x修改真实名称
     *
     * @param request
     * @param relName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateRel", method = RequestMethod.POST)
    public String updateRel(HttpServletRequest request, String relName) {
        LoginUser loginUser = (LoginUser) request.getAttribute("session");
        TParent tParent = tParentService.find(loginUser.getId());
        tParent.setRelName(relName);
        tParentService.update(tParent);
        return jsonSucess(tParent);
    }

    /**
     * x修改性别
     *
     * @param request
     * @param sex
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateSex", method = RequestMethod.POST)
    public String updateSex(HttpServletRequest request, String sex) {
        LoginUser loginUser = (LoginUser) request.getAttribute("session");
        TParent tParent = tParentService.find(loginUser.getId());
        tParent.setSex(sex);
        tParentService.update(tParent);
        return jsonSucess(tParent);
    }

    /**
     * x修改密码
     *
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updatePass", method = RequestMethod.POST)
    public String updatePass(HttpServletRequest request, String oldPassword, String newPassword) {
        LoginUser loginUser = (LoginUser) request.getAttribute("session");
        TParent tParent = tParentService.find(loginUser.getId());
        if(!tParent.getPassword().equals(DigestUtils.sha1DigestAsHex(oldPassword))){
            return jsonFail(AppConstants.USER_NOT_INFO);
        }
        tParent.setPassword(DigestUtils.sha1DigestAsHex(newPassword));
        tParentService.update(tParent);
        return jsonSucess(tParent);
    }

}
