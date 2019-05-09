package com.framework.loippi.controller.app;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.Area;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.entity.UserUnread;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.TSalaryLogService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.utils.DayuSmsUtils;
import com.framework.loippi.utils.RandomGUIDUtil;
import com.framework.loippi.utils.RandomUtils;
import com.framework.loippi.utils.ReturnJson;

import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.redis.JedisStringCache;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longbh on 2016/12/11.
 */
@Controller
@RequestMapping("api/app/authparent/*")
public class AuthParentController extends BaseController {

	@Resource
    private TParentService tParentService;

	@Autowired
	private JedisCache jedisCache;
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
    private TeacherService teacherService;
	@Resource
    private TSalaryLogService tSalaryLogService;
	
	
	@Resource
	private  AreaService areaService;
	
	@Resource
	private  UserUnreadService userUnreadService;
	@Resource
	private  TeacherAuthService teacherAuthService;
	@Resource
	private  OrgnizationService orgnizationService;
	
	
	
	
		/** 发送手机验证码 */
		@ResponseBody
		@RequestMapping(value = "payroll", method = RequestMethod.POST)
		public String payroll(HttpServletRequest request, HttpServletResponse response) {
			tSalaryLogService.payroll();
			return ReturnJson.jsonString("OK",AppConstants.OK);
		}

	 
	/** 发送手机验证码 */
	@ResponseBody
	@RequestMapping(value = "genCode", method = RequestMethod.POST)
	public String genCode(HttpServletRequest request, HttpServletResponse response) {

		String phone = request.getParameter("phone");
		if (StringUtil.isEmpty(phone)) {
			return ReturnJson.jsonString("参数有误", AppConstants.PARAMETER__NOT_COMPLETE);
		}

		String code = RandomUtils.getRandomNumberString(6);

		DayuSmsUtils.send(phone, code);
		this.setRedisLoginCode(Constants.MOBILE_GENCODE + phone, code);
		return ReturnJson.jsonString("OK",AppConstants.OK);
	}

	
	/**
	 * 注册
	 */
	@ResponseBody
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, String phone, String password, String code) {
		if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(password) || StringUtil.isEmpty(code)) {
			return ReturnJson.jsonString("参数有误", AppConstants.PARAMETER__NOT_COMPLETE);
		}

		TParent tuserold = tParentService.find("account", phone);
		if (tuserold != null) {
			return ReturnJson.jsonString("该手机号已经被注册！", AppConstants.IS_USER);
		}

		JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(Constants.MOBILE_GENCODE + phone);
		if (jedisStringCache.get() == null || !jedisStringCache.get().equals(code)) {
			return ReturnJson.jsonString("验证码错误", AppConstants.CAPTCHA__NOT_EXIST);

		}
		RandomGUIDUtil myGUID = new RandomGUIDUtil();
		String uuid = myGUID.valueAfterMD5;
	    TParent tParent=new TParent();
	    tParent.setCreateDate(new Date());
	    tParent.setPassword(DigestUtils.md5Hex(password));
	    tParent.setAccount(phone);
	    tParent.setNickName("用户"+phone);
	    tParent.setType(1);
	    tParent.setFirstName(StringUtil.getFirstSpell(tParent.getNickName()));
	    tParent.setUuid(uuid);
	    tParent.setStatus(1);
	    tParentService.save(tParent);
	    
	    setUnread(tParent);
		return ReturnJson.jsonString("OK", AppConstants.OK);
	}

	/** 第三方登陆 */
	@ResponseBody
	@RequestMapping(value = "thirdlogin", method = RequestMethod.POST)
	public String thirdLogin(HttpServletRequest request, HttpServletResponse response, Integer loginType) {
		String thirdAccount = request.getParameter("thirdAccount");
		String name = request.getParameter("name");
		String avatar = request.getParameter("avatar");
		TParent tParent =null;
		RandomGUIDUtil myGUID = new RandomGUIDUtil();
		String sessionid = myGUID.valueAfterMD5;
		LoginUser loginUser = new LoginUser();
		// 微信
		if (loginType == 2) {
			tParent = tParentService.find("weixinAccount",  thirdAccount);
			if (tParent == null) {
				tParent= new TParent();
				tParent.setWeixinName(name);
				tParent.setWeixinAvatar(avatar);
				if(tParent.getAvatar()==null){
					tParent.setAvatar(avatar);
				}
				if(tParent.getNickName()==null){
					tParent.setNickName(name);
				}
				tParent.setWeixinAccount(thirdAccount);
			    tParent.setType(2);
			    tParent.setStatus(1);
			    tParent.setFirstName(StringUtil.getFirstSpell(name));
			    tParent.setUuid(sessionid);
			    tParentService.save(tParent);
			}
			//qq
		} else if (loginType == 3) {
			tParent = tParentService.find("qqAccount",  thirdAccount);
			if (tParent == null) {
				tParent= new TParent();
				tParent.setQqAccount(thirdAccount);
				tParent.setQqAvatar(avatar);
				tParent.setQqName(name);
				if(tParent.getAvatar()==null){
					tParent.setAvatar(avatar);
				}
				if(tParent.getNickName()==null){
					tParent.setNickName(name);
				}
			    tParent.setType(3);
			    tParent.setStatus(1);
			    tParent.setFirstName(StringUtil.getFirstSpell(name));
			    tParent.setUuid(sessionid);
			    tParentService.save(tParent);
			}
		}
	   
	    tParent.setType(loginType);
		tParentService.update(tParent);
		
		
		  if (tParent.getStatus() == 0) {
	            return ReturnJson.jsonString("您的用户已被禁用,请联系管理员!", AppConstants.LOCK_USER);
	        }
		
	    Map map=new HashMap();
	    map.put("userId",loginUser.getId() );
	    map.put("userType",1 );
	    Long count=userUnreadService.count(map);
	    if(count==0){
	    	   setUnread(tParent);
	    }
	
	   loginUser = LoginUser.ofParent(tParent,sessionid);
	   this.setRedisLogiUser(sessionid, loginUser);
		return ReturnJson.jsonString("OK",loginUser,AppConstants.OK);
	}


	
    /**
     * 密码登陆
     */
    @ResponseBody
    @RequestMapping(value = "loginParent", method = RequestMethod.POST)
    public String loginParent(HttpServletRequest request, String phone, String password) {
        TParent tUser = tParentService.find("account", phone);
        if (tUser == null) {
            return ReturnJson.jsonString("找不到用户!", AppConstants.USER_NOT_INFO);
        }

        if (!tUser.getPassword().equals(DigestUtils.md5Hex(password))) {
            return ReturnJson.jsonString("密码错误!", AppConstants.USER_NOT_INFO);
        }

		  if (tUser.getStatus() == 0) {
	            return ReturnJson.jsonString("您的用户已被禁用,请联系管理员!", AppConstants.LOCK_USER);
	        }
		  
        RandomGUIDUtil myGUID = new RandomGUIDUtil();
        String sessionid = myGUID.valueAfterMD5;
        tUser.setType(1);
        LoginUser loginUser = LoginUser.ofParent(tUser,sessionid);
        tUser.setType(1);
        tParentService.update(tUser);
        this.setRedisLogiUser(sessionid, loginUser);
        return ReturnJson.jsonString("OK", loginUser, AppConstants.OK);
    }
    
    /**
     * 教师密码登陆
     */
    @ResponseBody
    @RequestMapping(value = "loginTeacher", method = RequestMethod.POST)
    public String loginTeacher(HttpServletRequest request, String phone, String password) {
        Teacher tUser = teacherService.find("account", phone);
        if (tUser == null) {
            return ReturnJson.jsonString("找不到用户!", AppConstants.USER_NOT_INFO);
        }

        if (!tUser.getPassword().equals(DigestUtils.md5Hex(password))) {
            return ReturnJson.jsonString("密码错误!", AppConstants.USER_NOT_INFO);
        }

		  if (tUser.getStatus() == 0) {
	            return ReturnJson.jsonString("您的用户已被禁用,请联系管理员!", AppConstants.LOCK_USER);
	        }

        RandomGUIDUtil myGUID = new RandomGUIDUtil();
        String sessionid = myGUID.valueAfterMD5;
    
        tUser.setType(2);
        LoginUser loginUser = LoginUser.ofTeacher(tUser,sessionid);
        Map<String,Object> map=new HashMap<String,Object> ();
        map.put("uid", tUser.getId());
    	List<Orgnization> orglist = orgnizationService.findListByPage1(map);
        if(orglist.size()!=0){
        	loginUser.setOrgId(orglist.get(0).getId());
        }
        this.setRedisLogiUser(sessionid, loginUser);
        return ReturnJson.jsonString("OK", loginUser, AppConstants.OK);
    }


    /**
     * 忘记密码
     *
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "forgetPass", method = RequestMethod.POST)
    public String forgetPass(HttpServletRequest request, String phone, String code, String password) throws UnsupportedEncodingException {
        
        
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(Constants.MOBILE_GENCODE + phone);
        TParent tUser = tParentService.find("account", phone);
        if (tUser == null) {
            return ReturnJson.jsonString("帐号不存在！", AppConstants.ACCOUNT_NOT_EXIST);
        }
         
		if (jedisStringCache.get() == null || !jedisStringCache.get().equals(code)) {
			return ReturnJson.jsonString("验证码错误！", AppConstants.CAPTCHA__NOT_EXIST);
		}


        tUser.setPassword(DigestUtils.md5Hex(password));
        tParentService.update(tUser);
        return jsonSucess();
    }
    
    /**
     * 忘记密码
     *
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "forgetPassTeacher", method = RequestMethod.POST)
    public String forgetPassTeacher(HttpServletRequest request, String phone, String code, String password) throws UnsupportedEncodingException {
        
        
        JedisStringCache jedisStringCache = jedisCache.getJedisStringCache(Constants.MOBILE_GENCODE + phone);
        Teacher tUser = teacherService.find("account", phone);
        if (tUser == null) {
            return ReturnJson.jsonString("帐号不存在！", AppConstants.ACCOUNT_NOT_EXIST);
        }
         
		if (jedisStringCache.get() == null || !jedisStringCache.get().equals(code)) {
			return ReturnJson.jsonString("验证码错误！", AppConstants.CAPTCHA__NOT_EXIST);
		}


        tUser.setPassword(DigestUtils.md5Hex(password));
        teacherService.update(tUser);
        return jsonSucess();
    }
    
    
    
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
    	 char word = "广州".charAt(0);
    	 String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,new HanyuPinyinOutputFormat());
        System.err.println( (pinyinArray[0].charAt(0)+"").toUpperCase());
	}
   
    public void setUnread(TParent tParent){
        UserUnread userUnread=new UserUnread();
  	    userUnread.setUserId(tParent.getId());
  	    userUnread.setUserType(1);
  	    userUnread.setSystemMesStatus(1);
  	    userUnread.setCourserMesStatus(1);
  	    userUnread.setDynamicStatus(1);
  	    userUnread.setSystemMesStatus(1);
  	    userUnread.setTaskTotal(0);
  	    userUnread.setClassRemindStatus(1);
  	    userUnread.setFriendStatus(1);
  	    userUnreadService.save(userUnread);
    }
}
