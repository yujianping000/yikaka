package com.framework.loippi.controller.app;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.controller.app.common.ReturnJson;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.redis.JedisHashCache;
import com.framework.loippi.utils.redis.JedisStringCache;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by longbh on 16/7/28.
 */
public class BaseController {
	

    @Autowired
    protected JedisCache jedisCache;

    public void setRedisLogiUser(String sessionId, LoginUser loginUser) {
        // 检查是否已经登录
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(sessionId);
        jedisStringCache.set(JSONObject.fromObject(loginUser).toString());
        
     //==============只能登录一端   
        //检查是否已经登录
        String user_id="";
        if(1==loginUser.getType()){
        	user_id="user_parent_"+loginUser.getId();
        }
        if(2==loginUser.getType()){
        	user_id="user_teacher_"+loginUser.getId();
        }
		
		JedisStringCache user_idStringCache=	jedisCache.getJedisStringCache(user_id);
		 if(user_idStringCache.get()!=null){
			 //如果存在则修改其状态为已经在其他手机登陆了
			 JedisStringCache oldUserjedisStringCache=	jedisCache.getJedisStringCache(user_idStringCache.get());
			 if(oldUserjedisStringCache.get()!=null){
				 oldUserjedisStringCache.remove(user_idStringCache.get());
			 }
		 }
		
		jedisStringCache.set(JSONObject.fromObject(loginUser).toString());
		user_idStringCache.set(sessionId);
    }
    
    public void updateRedisLogiUser(String sessionId, LoginUser loginUser) {
        // 检查是否已经登录
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(sessionId);
        if (jedisStringCache.get() != null) {
            jedisStringCache.set(JSONObject.fromObject(loginUser).toString());
        }
    }

    /* 缓存相关 */
    public void setRedisLoginCode(String mobileNum, String code) {
        // 设置验证码缓存
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(mobileNum);
        jedisStringCache.set(code, 120);
    }

    public void removeRedisLogiUser(String sessionId) {
        // 检查是否已经登录
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(sessionId);
        jedisStringCache.remove(sessionId);
    }

    public LoginUser validateRedisLogiUser(String sessionId) {
    	
        if (StringUtil.isEmpty(sessionId)) {
            return null;
        }
        // 检查是否已经登录
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(sessionId);
        if (jedisStringCache.get() == null) {
            return null;
        }
        JSONObject jsonObject = new JSONObject().fromObject(jedisStringCache.get());// 将json字符串转换为json对象

        LoginUser loginUser = (LoginUser) JSONObject.toBean(jsonObject, LoginUser.class);// 将建json对象转换为Person对象
        return loginUser;
    }


    
	
    /* 缓存相关 */
    public void setRedisAccessGuard(String child) {
        // 设置验证码缓存
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(child);
        jedisStringCache.set(child, 30*60);
    }
  

    
    
    protected String jsonSucess() {
        return ReturnJson.jsonString("成功", AppConstants.OK);
    }

    protected String jsonSucess(Object data) {
        return ReturnJson.jsonString("成功", data, AppConstants.OK);
    }

    protected String jsonFail() {
        return ReturnJson.jsonString("失败", AppConstants.FAIL);
    }

    protected String jsonFail(int status) {
        return ReturnJson.jsonString("失败", status);
    }

    protected String jsonFail(int status, String message) {
        return ReturnJson.jsonString(message, status);
    }
    

}
