package com.framework.loippi.controller.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppCourse;
import com.framework.loippi.controller.app.model.AppOrderComment;
import com.framework.loippi.controller.app.model.AppOrders;
import com.framework.loippi.controller.app.model.AppOrders2;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.entity.Activity;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.Student;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.service.ActivityService;
import com.framework.loippi.service.CourseCommentRemarkService;
import com.framework.loippi.service.CourseCommentService;
import com.framework.loippi.service.CourseService;
import com.framework.loippi.service.CourseTypeService;
import com.framework.loippi.service.OrderRemarkService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgnizationService;
import com.framework.loippi.service.OrgnizationSuggestionService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.utils.RandomGUIDUtil;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.alipay.AliPay;
import com.framework.loippi.utils.alipay.AlipayNotify;
import com.framework.loippi.utils.alipay.OrderInfo;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.wechat.utils.AppOrder;
import com.framework.loippi.utils.wechat.utils.WeixinMobilePaymentPlugin;


import net.sf.json.JSONObject;
import java.util.Map.Entry;

//订单控制类
@Controller
@RequestMapping("api/app/order/*")
public class AppOrderController  extends BaseController{
	private Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private JedisCache jedisCache;
	
	@Resource
	private  OrgnizationService orgnizationService;
	
	@Resource
	private  CourseTypeService  courseTypeService;
	
	@Resource
    private TParentService tParentService;
	
	@Resource
	private  OrgnizationSuggestionService orgnizationSuggestionService;
	@Resource
	private CourseService courseService;
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderRemarkService orderRemarkService;
	
	@Resource
	private CourseCommentService courseCommentService;
	@Resource
	private CourseCommentRemarkService courseCommentRemarkService;
	@Resource
	private ActivityService activityService;
	
	@Resource
	private StudentService studentService;
	
	/** 报名课程-生成预支付单信息 */
	@ResponseBody
	@RequestMapping(value = "toBuyCourseNow",method = RequestMethod.POST)
	public String toBuyCourseNow(HttpServletRequest request, HttpServletResponse response) {
		String sessionId=request.getParameter("sessionId");
		String courseId=request.getParameter("courseId");
		String courseName=request.getParameter("courseName");
		String orgName=request.getParameter("orgName");
		String orgId=request.getParameter("orgId");
		
		String childName=request.getParameter("childName");
		String sex=request.getParameter("sex");
		String childId=request.getParameter("childId");
		String childAvatar=request.getParameter("childAvatar");
		String childAge=request.getParameter("childAge");
		String phone=request.getParameter("phone");
		
		String image=request.getParameter("image");
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		Course	course=courseService.find(Long.parseLong(courseId));
		if(course==null||course.getStatus()==2){
			return ReturnJson.jsonString("课程已关闭", AppConstants.Order_DOWN);
		}

		if(course.getFreez()!=null&&course.getFreez()==0){
			return ReturnJson.jsonString("报名人数已满，请选择其他课程", AppConstants.Order_OUT);
		}
		Order order=new Order();
		
		
		RandomGUIDUtil myGUID = new RandomGUIDUtil();
		String uuid=myGUID.valueAfterMD5;
		order.setType(1);
		order.setCourseId(Long.parseLong(courseId));
		order.setCourseName(course.getName());
		order.setTeacherId(course.getTeacherId());
		order.setImage(image);
		order.setUpdateTime(new Date());
		order.setCreatedTime(new Date());
		order.setPayment(course.getPrice());
		order.setTotalFee(course.getPrice());
		if(!StringUtil.isEmpty(childAge)){
		order.setStuAge(Integer.parseInt(childAge));
		}
		order.setTargetId(Long.parseLong(courseId));
		order.setTargetName(courseName);
		order.setOrgId(Long.parseLong(orgId));
		order.setOrgName(orgName);
		order.setUid(user.getId());
		order.setUnickname(user.getUserName());
		order.setStuId(Long.parseLong(childId));
		order.setStuName(childName);
		order.setStuSex(sex);
		order.setStuAvatar(childAvatar);
		
		Student student = studentService.find(Long.parseLong(childId));
		if(student != null) {
			order.setStuBirthday(student.getBirtchday());
			order.setStuSex(student.getSex());
		}
		
		order.setPhone(phone);
		order.setUuid(uuid);
		order.setStatus(1);
		order.setClasstimes(course.getPeriods());
		order.setTerm(course.getTerm());
		String result=orderService.toBuyNow(order, user, course);
		
		if(!"0".equals(result)){
			 return  result;
		}else{
			return ReturnJson.jsonString("报名人数已满，请选择其他课程", AppConstants.Order_OUT);
		}
	}
	

	/** 报名-生成预支付单信息 */
	@ResponseBody
	@RequestMapping(value = "toBuyActivityNow",method = RequestMethod.POST)
	public String toBuyActivityNow(HttpServletRequest request, HttpServletResponse response) {
		String sessionId=request.getParameter("sessionId");
		String activityId=request.getParameter("activityId");
		String courseName=request.getParameter("activityName");
		String orgName=request.getParameter("orgName");
		String orgId=request.getParameter("orgId");
		
		String childName=request.getParameter("childName");
		String sex=request.getParameter("sex");
		String childId=request.getParameter("childId");
		String childAvatar=request.getParameter("childAvatar");
		String childAge=request.getParameter("childAge");
		String phone=request.getParameter("phone");
		String image=request.getParameter("image");
		String remark=request.getParameter("remark");
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		Activity	activity=activityService.find(Long.parseLong(activityId));
		if(activity==null||activity.getStatus()==4){
			return ReturnJson.jsonString("活动已结束", AppConstants.Order_DOWN);
		}
		else if(activity==null||activity.getStatus()==3){
			return ReturnJson.jsonString("活动未开始", AppConstants.Order_DOWN);
		}
		if(activity.getTotalFeez()!=null&&activity.getTotalFeez()==0){
			return ReturnJson.jsonString("报名人数已满，请选择其他活动", AppConstants.Order_OUT);
		}
		Order order=new Order();
		
		
		RandomGUIDUtil myGUID = new RandomGUIDUtil();
		String uuid=myGUID.valueAfterMD5;
		order.setType(2);
		order.setUpdateTime(new Date());
		order.setCreatedTime(new Date());
		order.setPayment(activity.getFee());
		order.setTotalFee(activity.getFee());
		if(!StringUtil.isEmpty(childAge)){
		order.setStuAge(Integer.parseInt(childAge));
		}
		order.setRemark(remark);
		order.setImage(image);
		order.setTargetId(Long.parseLong(activityId));
		order.setTargetName(courseName);
		order.setOrgId(Long.parseLong(orgId));
		order.setOrgName(orgName);
		order.setUid(user.getId());
		order.setUnickname(user.getUserName());
		order.setStuId(Long.parseLong(childId));
		order.setStuName(childName);
		order.setStuSex(sex);
		order.setStuAvatar(childAvatar);
		
		Student student = studentService.find(Long.parseLong(childId));
		if(student != null) {
			order.setStuBirthday(student.getBirtchday());
			order.setStuSex(student.getSex());
		}
		
		order.setPhone(phone);
		order.setUuid(uuid);
		order.setStatus(1);

		String result=orderService.toBuyNow(order, user, activity);
		
		if(!"0".equals(result)){
			 return  result;
		}else{
			return ReturnJson.jsonString("报名人数已满，请选择其他课程", AppConstants.Order_OUT);
		}
	}
	
	/**0元活动报名*/
	@ResponseBody
	@RequestMapping(value = "notMoneyActivityNow",method = RequestMethod.POST)
	public String notMoneyActivityNow(HttpServletRequest request, HttpServletResponse response) {
		String sessionId=request.getParameter("sessionId");
		String activityId=request.getParameter("activityId");
		String courseName=request.getParameter("activityName");
		String orgName=request.getParameter("orgName");
		String orgId=request.getParameter("orgId");
		
		String childName=request.getParameter("childName");
		String sex=request.getParameter("sex");
		String childId=request.getParameter("childId");
		String childAvatar=request.getParameter("childAvatar");
		String childAge=request.getParameter("childAge");
		String phone=request.getParameter("phone");
		String image=request.getParameter("image");
		String remark=request.getParameter("remark");
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		Activity	activity=activityService.find(Long.parseLong(activityId));
		if(activity==null||activity.getStatus()==4){
			return ReturnJson.jsonString("活动已结束", AppConstants.Order_DOWN);
		}
		else if(activity==null||activity.getStatus()==3){
			return ReturnJson.jsonString("活动未开始", AppConstants.Order_DOWN);
		}
		if(activity.getTotalFeez()!=null&&activity.getTotalFeez()==0){
			return ReturnJson.jsonString("报名人数已满，请选择其他活动", AppConstants.Order_OUT);
		}
		Order order=new Order();
		
		
		RandomGUIDUtil myGUID = new RandomGUIDUtil();
		String uuid=myGUID.valueAfterMD5;
		order.setType(2);
		order.setUpdateTime(new Date());
		order.setCreatedTime(new Date());
		order.setPayment(activity.getFee());
		order.setTotalFee(activity.getFee());
		if(!StringUtil.isEmpty(childAge)){
		order.setStuAge(Integer.parseInt(childAge));
		}
		order.setRemark(remark);
		order.setImage(image);
		order.setTargetId(Long.parseLong(activityId));
		order.setTargetName(courseName);
		order.setOrgId(Long.parseLong(orgId));
		order.setOrgName(orgName);
		order.setUid(user.getId());
		order.setUnickname(user.getUserName());
		order.setStuId(Long.parseLong(childId));
		order.setStuName(childName);
		order.setStuSex(sex);
		order.setStuAvatar(childAvatar);
		
		Student student = studentService.find(Long.parseLong(childId));
		if(student != null) {
			order.setStuBirthday(student.getBirtchday());
			order.setStuSex(student.getSex());
		}
		
		order.setPhone(phone);
		order.setUuid(uuid);
		order.setStatus(8);

		String result=orderService.toBuyNow(order, user, activity);
		
		if(!"0".equals(result)){
			 return  result;
		}else{
			return ReturnJson.jsonString("报名人数已满，请选择其他活动", AppConstants.Order_OUT);
		}
	}

	/**生成支付签名信息*/
	@ResponseBody
	@RequestMapping(value = "toPay",method = RequestMethod.POST)
	public String toPay(HttpServletRequest request, HttpServletResponse response) {
				String orderId=request.getParameter("orderId");
				String payMethod=request.getParameter("payMethod");
				String sessionId=request.getParameter("sessionId");	 
					LoginUser user = validateRedisLogiUser(sessionId);
					if(user==null){
					return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
					}
					

		Order order = orderService.find(Long.parseLong(orderId));
		
		if(order==null||order.getStatus()==2){
			 return 		ReturnJson.jsonString("支付失败", AppConstants.Order_FAILE);
		}
		
	//	order.setPayment(new BigDecimal(0.01));
		int payType = 0;
		if(payMethod.equals("wechat")){
			payType = 2;

			try {
				JSONObject obj = new JSONObject();  
				int  total_fee=0;
				AppOrder appOrder=new AppOrder();
				appOrder.setOrderCode(order.getBn());
				//appOrder.setPay(order.getTotalFee());
				 BigDecimal b2 = new BigDecimal("100");
				appOrder.setPay(order.getPayment());
				appOrder.setOrderSessionId(order.getUuid());
				Map result=	WeixinMobilePaymentPlugin.getPrepaySn(appOrder, "ykk" + order.getBn(), request);
	            return    ReturnJson.jsonString("OK",result, AppConstants.OK);
			} catch (Exception e) {
				 return 		ReturnJson.jsonString("支付失败", AppConstants.Order_FAILE);
			} 
			
			
			
		}else if(payMethod.equals("alipay")){

			
			String pay=	AliPay.pay(order.getBn(), order.getBn(), order.getBn(),order.getUuid(), order.getPayment().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
			System.err.println(  ReturnJson.jsonString("OK",pay, AppConstants.OK));
			
			return    ReturnJson.jsonString("OK",pay, AppConstants.OK);
		} 
		
		return 		ReturnJson.jsonString("支付失败", AppConstants.Order_FAILE);
		
		
	}
	
	
	
	/**
	 * 微信回调支付成功
	 * 
	 * payMethod : wechat alipay
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "notify/{orderId}/{payMethod}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String notify_wechat(@PathVariable String orderId,@PathVariable String payMethod,HttpServletRequest request) {
	

		log.warn("orderId==" + orderId);
		log.warn("payMethod==" + payMethod);
		System.err.println("orderId==" + orderId+"    payMethod==" + payMethod);

		// 1 支付宝 2微信支付 3银联 4积分
		int payType = 0;
		if (payMethod.equals("deposit")) {
			payType = 3;
		} else if (payMethod.equals("wechat")) {
			payType = 2;// 微信端微信支付
		} else if (payMethod.equals("alipay")) {
			payType = 1;
		}
		Order order = orderService.find("uuid", orderId);

		log.warn("app/order/notify orderId=" + request.getRemoteAddr() + "   "
				+ orderId + " payMethod=" + payMethod + "  "
				+ order.getStatus() + "  ");

		// 验证订单是否已被提交了
		if (!(1 == order.getStatus())) {
			return "SUCCESS";
		}

		boolean b = orderService.toPayByMoney( payType,order);
		if (!b) {
			return "FAIL";
		}


		return "SUCCESS";
	}

	/**
	 * 支付宝回调支付成功
	 * 
	 * payMethod : wechat alipay
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "alipay/{orderId}/{payMethod}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String alipay(@PathVariable String orderId,@PathVariable String payMethod,HttpServletRequest request) {

		log.info("orderId==" + orderId);
		log.info("payMethod==" + payMethod);

		// 1 支付宝 2微信支付 3银联 4积分
		int payType = 1;

		StringBuffer parameter = new StringBuffer();
		Map<String, String> mapParameter = new HashMap();
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap != null) {
			for (Entry<String, String[]> entry : parameterMap.entrySet()) {
				String parameterName = entry.getKey();

				String[] parameterValues = entry.getValue();
				if (parameterValues != null) {
					for (String parameterValue : parameterValues) {
						parameter.append(parameterName + " = " + parameterValue
								+ "\n");
						mapParameter.put(parameterName, parameterValue);
					}
				}

			}
		}

		log.info("parameter=====" + parameter.toString());

		boolean verify = AlipayNotify.verify(mapParameter);
		if (!verify) {
			log.info("支付宝支付失败 ：alipay/{orderId}/{payMethod} orderId="
					+ request.getRemoteAddr() + "   " + orderId + " payMethod="
					+ payMethod + "");
			return "false";
		}
		Order order = orderService.find("uuid", orderId);
		//验证订单是否已被提交了
		if(!(1==order.getStatus())){
			return "true";
		}

		boolean b = orderService.toPayByMoney(payType,order);
		if (!b) {
			return "false";
		}
		return "true";
	}
	
	

	/** 所有订单列表 */
	@ResponseBody
	@RequestMapping(value = "getOrders",method = RequestMethod.POST)
	public String getOrders(@RequestParam(value = "pageNumber") Integer pageNumber,
@RequestParam(value = "pageSize")Integer pageSize,@RequestParam(value = "status")String status,
@RequestParam(value = "sessionId") String sessionId,HttpServletRequest request, HttpServletResponse response) {
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		    Map map=new HashMap();
	        map.put("order", "update_time desc");
	        map.put("pageNumber", (pageNumber-1)*pageSize);
	        map.put("pageSize", pageSize);
	        
	        map.put("uid",user.getId());
	        
	        if("all".equals(status)){
	        	List list=new ArrayList();
	        	list.add(1);
	        	list.add(2);
	        	list.add(3);
	        	list.add(4);
	        	list.add(8);
	        map.put("orderStatus", list);
	        }else{
	        	map.put("status", status);
		     }
	        
			List<Order> orders = orderService.findListByPage(map);
			List<AppOrders> appOrderss =new ArrayList();

			if(orders!=null) {
				for (Order order : orders) {
					AppOrders dto=new AppOrders();
	 				BeanUtils.copyProperties(order, dto);
	 				appOrderss.add(dto);
				}
			} 
			  return  ReturnJson.jsonString("OK",appOrderss,AppConstants.OK);

	}
	
	
	/**订单详情 */
	@ResponseBody
	@RequestMapping(value = "getOrderById",method = RequestMethod.POST)
	public String getOrderById(HttpServletRequest request, HttpServletResponse response) {
		String sessionId=request.getParameter("sessionId");
		String orderId=request.getParameter("orderId");
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		   
	        
			Order order = orderService.find(Long.parseLong(orderId));
			AppOrders2 appOrders2 =new AppOrders2();

			if(order!=null) {
	 				BeanUtils.copyProperties(order, appOrders2);
	 				appOrders2.setCreatedTimeString(StringUtil.date2StringTime(order.getCreatedTime()));
	 				
	 				System.err.println("==========================================");
	 				System.err.println(appOrders2.getStuSex());
	 				
	 				if(order.getStuSex().equals("男") || order.getStuSex().equals("男孩")) {
	 					appOrders2.setStuSex("男");
	 				}
	 				
	 				if(order.getStuSex().equals("女") || order.getStuSex().equals("女孩")) {
	 					appOrders2.setStuSex("女");
	 				}
	 			
			} 
			
			/**类型为活动则添加主办单位*/
			if(order.getType() == 2) {
				Activity activity = activityService.find(order.getTargetId());
				if(activity != null) {
					appOrders2.setCompany(activity.getCompany());
				}
			}
			
			  return  ReturnJson.jsonString("OK",appOrders2,AppConstants.OK);

	}
	
	
	
	/** 评价标签 */
	@ResponseBody
	@RequestMapping(value = "getRemarks",method = RequestMethod.POST)
	public String getRemarks() {
		 return  ReturnJson.jsonString("OK",courseCommentRemarkService.findAll(),AppConstants.OK);
	}
	
	/** 评价 */
	@ResponseBody
	@RequestMapping(value = "setComment",method = RequestMethod.POST)
	public String setComment(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionId=request.getParameter("sessionId");
		String orderId=request.getParameter("orderId");
		String favStar=request.getParameter("favStar");
		String content=request.getParameter("content");
		String mark=request.getParameter("mark");
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		
		Order order=orderService.find(Long.parseLong(orderId));
		order.setUpdateTime(new Date());
		order.setStatus(8);
		orderService.update(order);
		CourseComment courseComment=new CourseComment();
		courseComment.setAvatar(user.getAvatar());
		courseComment.setContent(content);
		courseComment.setCourseId(order.getTargetId());
		courseComment.setCreateDate(new Date());
		courseComment.setFavStar(Integer.parseInt(favStar));
		courseComment.setMark(mark);
		courseComment.setName(user.getUserName());
		courseComment.setOrderId(Long.parseLong(orderId));
		courseComment.setOrgId(order.getOrgId());
		courseComment.setUid(user.getId());
		courseComment.setTeacherId(order.getTeacherId());
		courseComment.setAccount(order.getPhone());
		courseComment.setStatus(1);
		courseCommentService.save(courseComment);
		
		//更新机构，课程评价数据，星星
		courseCommentService.updateCourseCommentCountAddCount(courseComment.getCourseId());
		
		/*Map map = new HashMap<>();
		map.put("", value);
		.getOrgId()*/
		
		
		
		 return  ReturnJson.jsonString("OK",AppConstants.OK);
	}
	
	
	/** 查看评价 */
	@ResponseBody
	@RequestMapping(value = "getComment",method = RequestMethod.POST)
	public String getComment(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionId=request.getParameter("sessionId");
		String orderId=request.getParameter("orderId");
	
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		CourseComment  courseComment =courseCommentService.find("orderId", orderId);
		AppOrderComment appOrderComment=new AppOrderComment();
		if(courseComment!=null) {
 				BeanUtils.copyProperties(courseComment, appOrderComment);
 				appOrderComment.setCreateDateString(StringUtil.date2String4(courseComment.getCreateDate()));
		} 
		
		 return  ReturnJson.jsonString("OK",appOrderComment,AppConstants.OK);
	}
	
	
	/** 取消订单 */
	@ResponseBody
	@RequestMapping(value = "cancelOrderById",method = RequestMethod.POST)
	public String cancelOrderById(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionId=request.getParameter("sessionId");
		String orderId=request.getParameter("orderId");
	
		LoginUser user = validateRedisLogiUser(sessionId);
		if(user==null){
		return ReturnJson.jsonString("用户会话失效", AppConstants.USER_SESS_EXPIRED);
		}
		
		
		Order order=orderService.find(Long.parseLong(orderId));
		
		order.setStatus(4);
		order.setUpdateTime(new Date());
		order.setCancelStatus(1);
		order.setCancelTime(new Date());
		orderService.update(order);
		
		//活动取消的订单加回去一个名额
		if(2==order.getType()){
			activityService.update2CancelActivity(order.getTargetId());
		}
		
	
		
		
		
		 return  ReturnJson.jsonString("OK",AppConstants.OK);
	}
	
	
//	
//	/** 所有订单列表 */
//	@ResponseBody
//	@RequestMapping(value = "searchOrders",method = RequestMethod.POST)
//	public String searchOrders(@RequestParam(value = "pageNumber") Integer pageNumber,
//@RequestParam(value = "pageSize")Integer pageSize,@RequestParam(value = "status")String status,@RequestParam(value = "key")String key,
//@RequestParam(value = "sessionId") String sessionId,HttpServletRequest request, HttpServletResponse response) {
//
//
//		 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//		 if(loginUser==null){
//			 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//		 }else if(loginUser.getStatus()==2){
//			 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//		 }
//		 
//		    Map map=new HashMap();
//	        map.put("orderTime", "modified_time desc");
//	        map.put("pageNumber", (pageNumber-1)*pageSize);
//	        map.put("pageSize", pageSize);
//	        if(!StringUtil.isEmpty(key)){
//	        	  map.put("searchkey",StringUtil.filterSQL(key));
//	        }else{
//	        	 map.put("searchkey","-");
//	        }
//	      
//	        map.put("userId",loginUser.getId());
//	        
//	        if("all".equals(status)){
//	        map.put("orderStatus", "1,2,3,4,5,6,8,10,11,12");
//	        }else if("6".equals(status)){
//		        map.put("orderStatus", "6,10,11,12");
//		     }else{
//	        	map.put("status", status);
//		     }
//	        
//			List<TradeOrder> orders = tradeOrderService.findListByPage(map);
//			List<AppGoodsOrders> appGoodsOrderss =new ArrayList();
//			
//			
//           if(orders!=null){
//        	   for (TradeOrder tradeOrder : orders) {
//        		   AppGoodsOrders appGoodsOrders=new AppGoodsOrders();
//        		   
//        		   appGoodsOrders.setId(tradeOrder.getId());
//        		   appGoodsOrders.setBn(tradeOrder.getBn());
//        		   appGoodsOrders.setPayment(tradeOrder.getPayment());
//        		   appGoodsOrders.setStatus(tradeOrder.getStatus());
//       		    map=new HashMap();
//    	        map.put("orderTime", "modified_time");
//    	        map.put("orderId",tradeOrder.getId());
//    			List<TradeOrderItem> tradeOrderItems = tradeOrderItemService.findListByPage(map);
//    			List<AppOrderItem>  appOrderItems=new ArrayList();
//      			
//    			//检查子订单评价状态，数据不对应的，再设置一次
//    			boolean b=true;
//        		   if(tradeOrderItems!=null){
//        			   for (TradeOrderItem tradeOrderItem : tradeOrderItems) {
//        				   AppOrderItem appOrderItem=new AppOrderItem();
//        				   appOrderItem.setId(tradeOrderItem.getId());
//        				   appOrderItem.setItemId(tradeOrderItem.getItemId());
//        				   appOrderItem.setNum(tradeOrderItem.getNum());
//        				   appOrderItem.setPicPath(tradeOrderItem.getPicPath());
//        				   appOrderItem.setPrice(tradeOrderItem.getPrice());
//        				   appOrderItem.setPropertiesName(tradeOrderItem.getSkuPropertiesName());
//        				   appOrderItem.setTitle(tradeOrderItem.getTitle());
//        				   appOrderItem.setComment(tradeOrderItem.getStatus());
//
//        				   appOrderItems.add(appOrderItem);
//        				   if(1==tradeOrderItem.getStatus()){
//        					   b=false;
//        				   }
//					}
//        		   }
//        		   
//        		   //子订单的状态都是已评价，如果订单显示未评价则设置已评价
//        		   if(b&&4==tradeOrder.getStatus()){
//        			   tradeOrder.setStatus(5);
//        			   tradeOrderService.update(tradeOrder);
//        			   appGoodsOrders.setStatus(5);
//        		   }
//        		   
//        		   appGoodsOrders.setAppOrderItems(appOrderItems);
//        		   appGoodsOrders.setMalltype(tradeOrder.getMalltype());
//        		   appGoodsOrderss.add(appGoodsOrders);
//			}
//           }
//		
//			  return  ReturnJson.jsonString("OK",appGoodsOrderss,AppConstants.AppSUCCESS2000000);
//
//	}
//	
//	/**订单列表-申请退款*/
//	@ResponseBody
//	@RequestMapping(value = "toRefund",method = RequestMethod.POST)
//	public String cancelRefund(HttpServletRequest request, HttpServletResponse response) {
//		String orderId=request.getParameter("orderId");
//		String content=request.getParameter("content");
//		String refundphone=request.getParameter("refundphone");
//		String sessionId=request.getParameter("sessionId");
//		 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//		 if(loginUser==null){
//			 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//		 }else if(loginUser.getStatus()==2){
//			 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//		 }
//	     
//		 TradeOrder tradeOrder=tradeOrderService.find(Long.parseLong(orderId));
//		 
//		 if(tradeOrder==null){
//			 
//			 return  ReturnJson.jsonString("订单已删除",AppConstants.AppError5000408);
//		 }
//		 tradeOrder.setRefundcontent(content);
//		 tradeOrder.setRefundphone(refundphone);
//		 tradeOrder.setOldstatus(tradeOrder.getStatus());
//		 tradeOrder.setStatus(10);
//		 tradeOrder.setRefundtime(new Date());
//		 tradeOrder.setModifiedTime(new Date());
//
//		 tradeOrderService.update(tradeOrder);
//		 
//		 
//		 
//		 UserInfo userInfo= userInfoService.find(tradeOrder.getUserId());
//			
//	       Integer 	returntimes =	userInfo.getReturntimes();
//	       if(returntimes!=null){
//	    	   userInfo.setReturntimes(returntimes+1);
//	       }else{
//	    	   userInfo.setReturntimes(1);
//	       }
//	       userInfoService.update(userInfo);
//		 
//		 return  ReturnJson.jsonString("OK",AppConstants.AppSUCCESS2000000);
//	}
//
//	/**订单列表-确认收货*/
//	@ResponseBody
//	@RequestMapping(value = "toConfirmReceipt",method = RequestMethod.POST)
//	public String toConfirmReceipt(HttpServletRequest request, HttpServletResponse response) {
//		String orderId=request.getParameter("orderId");
//		String sessionId=request.getParameter("sessionId");
//		 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//		 if(loginUser==null){
//			 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//		 }else if(loginUser.getStatus()==2){
//			 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//		 }
//	     
//		 TradeOrder tradeOrder=tradeOrderService.find(Long.parseLong(orderId)); 
//		 if(tradeOrder==null){
//			 return  ReturnJson.jsonString("订单已删除",AppConstants.AppError5000408);
//		 }
//		 if(tradeOrder.getStatus()!=null&&tradeOrder.getStatus()!=3){
//			 return  ReturnJson.jsonString("订单已确认收货",AppConstants.AppError5000408);
//		 }
//		 //积分商城商品不用评价
//		 if(1==tradeOrder.getMalltype()){
//		 tradeOrder.setStatus(5);
//		 tradeOrder.setModifiedTime(new Date());
//		 tradeOrderService.update(tradeOrder);
//		 }else{
//		 tradeOrder.setStatus(4);
//		 tradeOrder.setModifiedTime(new Date());
//		boolean b= tradeOrderService.toConfirmReceipt(tradeOrder.getId());
//		if(b){
//		 tradeOrderService.update(tradeOrder);
//		 
//		}
//		 }
//		 
//		 return  ReturnJson.jsonString("OK",AppConstants.AppSUCCESS2000000);
//	}
//	
//	/**订单列表-删除订单*/
//	@ResponseBody
//	@RequestMapping(value = "delOrder",method = RequestMethod.POST)
//	public String delOrder(HttpServletRequest request, HttpServletResponse response) {
//		String orderId=request.getParameter("orderId");
//		String sessionId=request.getParameter("sessionId");
//		 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//		 if(loginUser==null){
//			 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//		 }else if(loginUser.getStatus()==2){
//			 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//		 }
//	     
//		 TradeOrder tradeOrder=tradeOrderService.find(Long.parseLong(orderId)); 
//		 if(tradeOrder==null){
//			 return  ReturnJson.jsonString("订单已删除",AppConstants.AppError5000408);
//		 }
//		 tradeOrder.setStatus(9);
//		 tradeOrder.setModifiedTime(new Date());
//		 tradeOrderService.update(tradeOrder);
//		 return  ReturnJson.jsonString("OK",AppConstants.AppSUCCESS2000000);
//	}
//	
//	/**订单列表-取消订单*/
//	@ResponseBody
//	@RequestMapping(value = "cancelOrder",method = RequestMethod.POST)
//	public String cancelOrder(HttpServletRequest request, HttpServletResponse response) {
//		String orderId=request.getParameter("orderId");
//		String sessionId=request.getParameter("sessionId");
//		 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//		 if(loginUser==null){
//			 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//		 }else if(loginUser.getStatus()==2){
//			 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//		 }
//	     
//		 TradeOrder tradeOrder=tradeOrderService.find(Long.parseLong(orderId)); 
//		 if(tradeOrder==null){
//			 return  ReturnJson.jsonString("订单已删除",AppConstants.AppError5000408);
//		 }
//		 tradeOrder.setStatus(8);
//		 tradeOrder.setModifiedTime(new Date());
//		 tradeOrderService.update(tradeOrder);
//		 return  ReturnJson.jsonString("OK",AppConstants.AppSUCCESS2000000);
//	}
//	
//	
//	/**订单列表-取消退款*/
//	@ResponseBody
//	@RequestMapping(value = "cancelRefund",method = RequestMethod.POST)
//	public String toRefund(HttpServletRequest request, HttpServletResponse response) {
//		String orderId=request.getParameter("orderId");
//	
//		String sessionId=request.getParameter("sessionId");
//		 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//		 if(loginUser==null){
//			 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//		 }else if(loginUser.getStatus()==2){
//			 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//		 }
//	     
//		 TradeOrder tradeOrder=tradeOrderService.find(Long.parseLong(orderId));
//		 if(tradeOrder==null){ 
//			 return  ReturnJson.jsonString("订单已删除",AppConstants.AppError5000408);
//		 }
//		 tradeOrder.setStatus(tradeOrder.getOldstatus());
//		 tradeOrderService.update(tradeOrder);
//		 
//		 return  ReturnJson.jsonString("OK",AppConstants.AppSUCCESS2000000);
//	}
//
//		
//	/** 订单列表-订单详情 */
//	@ResponseBody
//	@RequestMapping(value = "getOrderDetail",method =RequestMethod.POST)
//	public String getOrderDetail(HttpServletRequest request, HttpServletResponse response) {
//
//		String orderId=request.getParameter("orderId");
//		String sessionId=request.getParameter("sessionId");
//		 if(StringUtil.isEmpty(orderId)){
//			 return 		ReturnJson.jsonString("参数有误", AppConstants.AppError5000020);
//		 }
//	 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//	 if(loginUser==null){
//		 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//	 }else if(loginUser.getStatus()==2){
//		 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//	 }
//	 TradeOrder tradeOrder=tradeOrderService.find(Long.parseLong(orderId));
//	 List<TradeOrderItem>   tradeOrderItems= tradeOrderItemService.findList("orderId", Long.parseLong(orderId));
//	 //订单的商品
//	 List <AppOrderItem> appOrderItems=new ArrayList();
//		boolean b=true;
//	 if(tradeOrderItems!=null){
//			//检查子订单评价状态，数据不对应的，再设置一次
//		
//		for (TradeOrderItem tradeOrderItem : tradeOrderItems) {
//			   //返回给app实体
//				AppOrderItem appOrderItem=new AppOrderItem();
//				appOrderItem.setId(tradeOrderItem.getId());
//				appOrderItem.setPicPath(tradeOrderItem.getPicPath());
//				appOrderItem.setTitle(tradeOrderItem.getTitle());
//				appOrderItem.setPrice(tradeOrderItem.getPrice());
//				appOrderItem.setNum(tradeOrderItem.getNum());
//				appOrderItem.setPropertiesName(tradeOrderItem.getSkuPropertiesName());
//				appOrderItem.setItemId(tradeOrderItem.getItemId());
//				appOrderItem.setComment(tradeOrderItem.getStatus());
//
//				GoodsSku	goodsSku=goodsSkuService.find(tradeOrderItem.getSkuId());
//				if(goodsSku==null){
//					 return 		ReturnJson.jsonString("参数有误", AppConstants.AppError5000020);
//				}
//				appOrderItem.setStatus(goodsSku.getStatus());
//				  if(1==tradeOrderItem.getStatus()){
//					   b=false;
//				   }
//				
//		        appOrderItems.add(appOrderItem);
//		} 
//	 }
//	 
//     AppGoodsOrder appOrder=new AppGoodsOrder();
//	   //子订单的状态都是已评价，如果订单显示未评价则设置已评价
//	   if(b&&4==tradeOrder.getStatus()){
//		   tradeOrder.setStatus(5);
//		   tradeOrderService.update(tradeOrder);
//	   }
//
//		 appOrder.setBn(tradeOrder.getBn());
//		 appOrder.setId(tradeOrder.getId());
//		 appOrder.setAppOrderItems(appOrderItems);
//		 appOrder.setPostFee(tradeOrder.getPostFee());
//		 appOrder.setTotalFee(tradeOrder.getTotalFee());
//         appOrder.setPayment(tradeOrder.getPayment());
//         if(tradeOrder.getReceiverState()!=null){
//		 if(tradeOrder.getReceiverState()!=null&&tradeOrder.getReceiverState().equals(tradeOrder.getReceiverCity())){
//				 appOrder.setReceiverAddress(tradeOrder.getReceiverState()+tradeOrder.getReceiverDistrict()
//				 +tradeOrder.getReceiverAddress());
//			}else{
//				 appOrder.setReceiverAddress(tradeOrder.getReceiverState()+tradeOrder.getReceiverCity()
//				 +tradeOrder.getReceiverDistrict() +tradeOrder.getReceiverAddress());
//			}
//		 appOrder.setReceiverName(tradeOrder.getReceiverName());
//		 appOrder.setReceiverPhone(tradeOrder.getReceiverPhone());
//		 appOrder.setAddrsId(tradeOrder.getAddrsid());
//         }
//		
//		if(tradeOrder.getSendTime()!=null){
//			appOrder.setPayTimeString(StringUtil.date2String4(tradeOrder.getSendTime()));
//		}
//
//        appOrder.setInvoiceName(tradeOrder.getInvoiceName());
//        appOrder.setTradeMemo(tradeOrder.getTradeMemo());
//        appOrder.setMalltype(tradeOrder.getMalltype());
//        appOrder.setStatus(tradeOrder.getStatus());
//	 return  ReturnJson.jsonString("OK",appOrder,AppConstants.AppSUCCESS2000000);
//	}
//	
//	
//	/**订单未处理 待付款数，待收货数，待评价数*/
//	@ResponseBody
//	@RequestMapping(value = "orderCount",method = RequestMethod.POST)
//	public String orderCount(HttpServletRequest request, HttpServletResponse response) {
//		String sessionId=request.getParameter("sessionId");
//		 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//		 if(loginUser==null){
//			 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//		 }else if(loginUser.getStatus()==2){
//			 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//		 }
//	     
//		 
//		 Map map=new HashMap();
//	     map.put("userId",loginUser.getId());
//		 map.put("filter_status", "1");
//	     Long payCount=	 tradeOrderService.count(map);
//	     
//		 map.put("filter_status", "3");
//	     Long receiveCount=	 tradeOrderService.count(map);
//	     
//		 map.put("filter_status", "4");
//	     Long commentCount=	 tradeOrderService.count(map);
//	    
//	     map=new HashMap();
//		 map.put("payCount", payCount+"");
//		 map.put("receiveCount", receiveCount+"");
//		 map.put("commentCount", commentCount+"");
//		 
//		 return  ReturnJson.jsonString("OK",map,AppConstants.AppSUCCESS2000000);
//	}
//	
//	
//	
//	
//	/**快递回调地址*/
//	@ResponseBody
//	@RequestMapping(value = "callback",method ={ RequestMethod.POST, RequestMethod.GET})
//	public String callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		NoticeResponse resp = new NoticeResponse();
//		resp.setResult(false);
//		resp.setReturnCode("500");
//		resp.setMessage("保存失败");
//		try {
//			String param = request.getParameter("param");
//			log.info("==kuaidi====param============="+param);
//			NoticeRequest nReq = JacksonHelper.fromJSON(param,
//					NoticeRequest.class);
//
//			Result result = nReq.getLastResult();
//			log.info("==kuaidi====result.getState()============="+result.getState());
//			log.info("==kuaidi====result.getState()============="+result.getStatus());
//			ArrayList<ResultItem> resultItems=result.getData();
//			// 处理快递结果
//			log.info(JacksonHelper.toJSON(result).toString());//这里必须返回，否则认为失败，过30分钟又会重复推送。
//			log.info("==kuaidi=================");
//			
//			JSONArray  json =JSONArray .fromObject(resultItems);
//
//			log.info(json.toString());
//			log.info("===================");
//			TradeOrder	tradeOrder=tradeOrderService.find("expressNumber", result.getNu());
//			if(tradeOrder!=null){
//				tradeOrder.setExpressContent(json.toString());
//				tradeOrderService.update(tradeOrder);
//				
//				
//				//已经签收了，推送消息到App
//				if("3".equals(result.getState())){
//
//					UserMessage	userMessage=new UserMessage();
//					userMessage.setContent("您的订单-"+tradeOrder.getBn()+"已签收。");
//					userMessage.setCreateDate(new Date());
//					userMessage.setTitle("物流信息");
//					userMessage.setDestAvatarUrl(tradeOrder.getAvatar());
//					userMessage.setDestUid(tradeOrder.getUserId());
//					userMessage.setDestName(tradeOrder.getNickname());
//					userMessage.setStatus(1);
//					userMessage.setType(3);
//					String uuid= UUID.randomUUID().toString();
//					userMessage.setImage(uuid);
//					userMessage.setDestId(tradeOrder.getId());
//					userMessageService.save(userMessage);
//					userMessage=userMessageService.find("image",uuid);
//				    //极光推送信息
//					JpushRunnable 	 t1=new JpushRunnable(userMessage.getContent(),tradeOrder.getUserId(),"wuliu",userMessage.getDestId()+"",this.mapService());
//					t1.start();
//				}
//				
//				
//			}
//			resp.setResult(true);
//			resp.setReturnCode("200");
//			resp.setMessage("保存成功");
//			return  JacksonHelper.toJSON(resp); //这里必须返回，否则认为失败，过30分钟又会重复推送。
//		} catch (Exception e) {
//			resp.setMessage("保存失败" + e.getMessage());
//			return  JacksonHelper.toJSON(resp); //保存失败，服务端等30分钟会重复推送。
//		}
//	}
//	
//	/**获取快递信息*/
//	@ResponseBody
//	@RequestMapping(value = "getExpress",method = RequestMethod.POST)
//	public String getExpress(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String orderId=request.getParameter("orderId");
//		String sessionId=request.getParameter("sessionId");
//		 if(StringUtil.isEmpty(orderId)){
//			 return 		ReturnJson.jsonString("参数有误", AppConstants.AppError5000020);
//		 }
//	 LoginUser loginUser=this.validateRedisLogiUser(sessionId);
//	 if(loginUser==null){
//		 return 		ReturnJson.jsonString("会话信息失效，请重新登录", AppConstants.AppError5000103);
//	 }else if(loginUser.getStatus()==2){
//		 return 		ReturnJson.jsonString("该账号在其他手机登录了", AppConstants.AppError5000413);
//	 }
//	 TradeOrder tradeOrder=tradeOrderService.find(Long.parseLong(orderId));
//	 if(tradeOrder!=null){
//		 AppExpress appExpress=new AppExpress();
//		 appExpress.setCode(tradeOrder.getExpressNumber());
//
//		 if(!StringUtil.isEmpty(tradeOrder.getExpressContent())){
//	     JSONArray jsonarray = JSONArray.fromObject(tradeOrder.getExpressContent());  
//	        System.out.println(jsonarray);  
//	        List<ResultItem> list = (List)JSONArray.toCollection(jsonarray, ResultItem.class);
//			 appExpress.setContent(list);
//		 }else{
//			 appExpress.setContent(new ArrayList());
//		 }
//		 appExpress.setWays(tradeOrder.getExpressWays());
//		 return  ReturnJson.jsonString("OK",appExpress,AppConstants.AppSUCCESS2000000);
//	 }
//	 
//	 
//	UserAddrs userAddrs= userAddrsService.find(1l);
//	
//	 AppExpress appExpress=new AppExpress();
//	 appExpress.setCode("710240168360");
//	 
//
//	 
//	 
//	 
//	 appExpress.setWays("圆通快递");
//	 return  ReturnJson.jsonString("OK",appExpress,AppConstants.AppSUCCESS2000000);
//	}
//	
//	private void  setRedisLogiUser(String sessionId,LoginUser loginUser,String type){
//		//检查是否已经登录
//		JedisStringCache jedisStringCache=	jedisCache.getJedisStringCache(sessionId);
//		
//		String user_id="user_"+loginUser.getId();
//		JedisStringCache user_idStringCache=	jedisCache.getJedisStringCache(user_id);
//		 if(user_idStringCache.get()!=null){
//			 //如果存在则修改其状态为已经在其他手机登陆了
//			 JedisStringCache oldjedisStringCache=	jedisCache.getJedisStringCache(user_idStringCache.get());
//			 if(oldjedisStringCache.get()!=null){
//				
//			 JSONObject jsonObject = new JSONObject().fromObject(oldjedisStringCache.get());//将json字符串转换为json对象
//
//			 LoginUser oldloginUser = (LoginUser)JSONObject.toBean(jsonObject, LoginUser.class);//将建json对象转换为Person对象
//			 oldloginUser.setStatus(2);
//			 oldjedisStringCache.set(JSONObject.fromObject(loginUser).toString());
//			 }
//		 }
//		
//		jedisStringCache.set(JSONObject.fromObject(loginUser).toString());
//		user_idStringCache.set(sessionId);
//	}
//	
//	private void  removeRedisLogiUser(String sessionId){
//		//检查是否已经登录
//		JedisStringCache jedisStringCache=	jedisCache.getJedisStringCache(sessionId);
//		jedisStringCache.remove(sessionId);
//	}
//	private LoginUser validateRedisLogiUser(String sessionId){
//		 if(StringUtil.isEmpty(sessionId)){
//			 return null;
//		 }
//		//检查是否已经登录
//		JedisStringCache jedisStringCache=	jedisCache.getJedisStringCache(sessionId);
//		 if(jedisStringCache.get()==null){
//			 return null;
//		 }
//		 JSONObject jsonObject = new JSONObject().fromObject(jedisStringCache.get());//将json字符串转换为json对象
//
//		 LoginUser loginUser = (LoginUser)JSONObject.toBean(jsonObject, LoginUser.class);//将建json对象转换为Person对象
//		 return loginUser;
//		
//	}
//	
//	//网银支付
//	private String tn(TradeOrder order) {
//		
//		//PurchaseOrder order = purchaseOrderService.find(PurchaseOrder.PROPERTY_PURCHASEORDER_CODE, sn);
//		
//		Map<String, String> contentData = new HashMap<String, String>();
//		
//		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
//		contentData.put("version", "5.0.0");            					//版本号 全渠道默认值
//		contentData.put("encoding", "UTF-8");     					//字符集编码 可以使用UTF-8,GBK两种方式
//		contentData.put("signMethod", "01");           		 				//签名方法 目前只支持01：RSA方式证书加密
//		contentData.put("txnType", "01");              		 				//交易类型 01:消费
//		contentData.put("txnSubType", "01");           		 				//交易子类 01：消费
//		contentData.put("bizType", "000201");          		 				//填写000201
//		contentData.put("channelType", "08");          		 				//渠道类型 08手机
//System.err.println("MERID==="+MERID);
//		/***商户接入参数***/
//		contentData.put("merId", MERID);   		 							//商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
//		contentData.put("accessType", "0");            		 				//接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）
//		contentData.put("orderId", System.currentTimeMillis()+"");        	 	    					//商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则	
//		contentData.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));		 		    //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
//		contentData.put("accType", "01");
//		 BigDecimal b2 = new BigDecimal("100");
//		//账号类型 01：银行卡02：存折03：IC卡帐号类型(卡介质)
//		contentData.put("txnAmt", order.getPayment().multiply(b2).intValue()+"");	//交易金额 单位为分，不能带小数点
//		contentData.put("currencyCode", "156");                 			//境内商户固定 156 人民币
//		
//		//后台通知地址（需设置为外网能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，【支付失败的交易银联不会发送后台通知】
//		//后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
//		//注意:1.需设置为外网能访问，否则收不到通知   
//		//    2.http https均可  
//		//	  3.收单后台通知后需要10秒内返回http200或302状态码 
//		//    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200或302，那么银联会间隔一段时间再次发送。总共发送5次，银联后续间隔1、2、4、5 分钟后会再次通知。
//		//    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
//		contentData.put("backUrl", AzuraWechatUtil.BASE_URL+ "/api/app/order/unionpay/callback/"+order.getSessionid()+"/unionpay.json");
//		
//		/**对请求参数进行签名并发送http post请求，接收同步应答报文**/
//		Map<String, String> reqData = AcpService.sign(contentData,"UTF-8");			 				//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
//		String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();								 	//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
//		Map<String, String> rspData = AcpService.post(reqData,requestAppUrl,"UTF-8");  				//发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
//		
//		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
//		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
//		if(!rspData.isEmpty()){
//			if(AcpService.validate(rspData, "UTF-8")){
//				log.info("验证签名成功");
//				String respCode = rspData.get("respCode") ;
//				if(("00").equals(respCode)){
//					//成功,获取tn号
//					return  ReturnJson.jsonString("OK",rspData.get("tn"),AppConstants.AppSUCCESS2000000);
//					
//				}else{
//					log.info("验证其他应答码为失败请排查原因或做失败处理");
//					//其他应答码为失败请排查原因或做失败处理
//					return  ReturnJson.jsonString("ERROR",respCode,AppConstants.AppError5000020);
//
//				}
//			}else{
//				log.error("验证签名失败");
//				//检查验证签名失败的原因
//				return  ReturnJson.jsonString("ERROR","验证签名失败",AppConstants.AppError5000020);
//			}
//		}else{
//			//未返回正确的http状态
//			log.error("未获取到返回报文或返回http状态码非200");
//		}
//		return  ReturnJson.jsonString("OK","验证签名失败",AppConstants.AppError5000020);
//
//	}
//	/**
//	 * 网银回调
//	 * 
//	 * @param sn
//	 * @return
//	 * @throws UnsupportedEncodingException 
//	 */
//	@RequestMapping(value = "unionpay/callback/{orderId}/{payMethod}", method = { RequestMethod.POST ,RequestMethod.GET })
//	public @ResponseBody String callback(@PathVariable String orderId,@PathVariable String payMethod,HttpServletRequest request) throws UnsupportedEncodingException {
//
//		request.setCharacterEncoding("ISO-8859-1");
//		String encoding = request.getParameter(SDKConstants.param_encoding);
//		// 获取银联通知服务器发送的后台通知参数
//		Map<String, String> reqParam = getAllRequestParam(request);
//		LogUtil.printRequestLog(reqParam);
//		Map<String, String> valideData = null;
//		if (null != reqParam && !reqParam.isEmpty()) {
//			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
//			valideData = new HashMap<String, String>(reqParam.size());
//			while (it.hasNext()) {
//				Entry<String, String> e = it.next();
//				String key = (String) e.getKey();
//				String value = (String) e.getValue();
//				value = new String(value.getBytes("ISO-8859-1"), encoding);
//				valideData.put(key, value);
//			}
//		}
//
//		//重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
//		if (!AcpService.validate(valideData, encoding)) {
//			log.info("验证签名结果[失败].");
//			//验签失败，需解决验签问题
//			 return "false";
//		} else {
//			log.info("验证签名结果[成功].");
//			//【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
////			String orderId =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
//
//			//String respCode =valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。
//			TradeOrder order = tradeOrderService.find("sessionid",orderId);
//			boolean b=tradeOrderService.toPayByMoney(3,order);
//			log.info("网银支付[成功]修改订单状态："+b);
//	         if(!b){
//	        	 return "false";
//	         }
//	         
//	     	return "ok";
//			
//		}
//		
//		//返回给银联服务器http 200  状态码
//	
//	}
//
//	/**
//	 * 获取请求参数中所有的信息
//	 * 
//	 * @param request
//	 * @return
//	 */
//	public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
//		Map<String, String> res = new HashMap<String, String>();
//		Enumeration<?> temp = request.getParameterNames();
//		if (null != temp) {
//			while (temp.hasMoreElements()) {
//				String en = (String) temp.nextElement();
//				String value = request.getParameter(en);
//				res.put(en, value);
//				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
//				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
//				if (null == res.get(en) || "".equals(res.get(en))) {
//					res.remove(en);
//				}
//			}
//		}
//		return res;
//	}
//	
//	private Map mapService(){
//		Map map=new  HashMap();
//
//		return map;
//		
//	}
//	
//	
//	public static void main(String[] args) {
//		//PurchaseOrder order = purchaseOrderService.find(PurchaseOrder.PROPERTY_PURCHASEORDER_CODE, sn);
//		
//				Map<String, String> contentData = new HashMap<String, String>();
//				
//				/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
//				contentData.put("version", "5.0.0");            					//版本号 全渠道默认值
//				contentData.put("encoding", "UTF-8");     					//字符集编码 可以使用UTF-8,GBK两种方式
//				contentData.put("signMethod", "01");           		 				//签名方法 目前只支持01：RSA方式证书加密
//				contentData.put("txnType", "01");              		 				//交易类型 01:消费
//				contentData.put("txnSubType", "01");           		 				//交易子类 01：消费
//				contentData.put("bizType", "000201");          		 				//填写000201
//				contentData.put("channelType", "08");          		 				//渠道类型 08手机
//		System.err.println("MERID==="+MERID);
//				/***商户接入参数***/
//				contentData.put("merId", MERID);   		 							//商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
//				contentData.put("accessType", "0");            		 				//接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）
//				contentData.put("orderId", System.currentTimeMillis()+"");        	 	    					//商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则	
//				contentData.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));		 		    //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
//				contentData.put("accType", "01");					 				//账号类型 01：银行卡02：存折03：IC卡帐号类型(卡介质)
//				contentData.put("txnAmt", "1");	//交易金额 单位为分，不能带小数点
//				contentData.put("currencyCode", "156");                 			//境内商户固定 156 人民币
//				
//				//后台通知地址（需设置为外网能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，【支付失败的交易银联不会发送后台通知】
//				//后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
//				//注意:1.需设置为外网能访问，否则收不到通知   
//				//    2.http https均可  
//				//	  3.收单后台通知后需要10秒内返回http200或302状态码 
//				//    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200或302，那么银联会间隔一段时间再次发送。总共发送5次，银联后续间隔1、2、4、5 分钟后会再次通知。
//				//    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
//				contentData.put("backUrl", AzuraWechatUtil.BASE_URL+ "/api/app/order/unionpay/callback/"+System.currentTimeMillis()+"/unionpay.json");
//				
//				/**对请求参数进行签名并发送http post请求，接收同步应答报文**/
//				Map<String, String> reqData = AcpService.sign(contentData,"UTF-8");			 				//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
//				String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();								 	//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
//				Map<String, String> rspData = AcpService.post(reqData,requestAppUrl,"UTF-8");  				//发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
//				
//				/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
//				//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
//				if(!rspData.isEmpty()){
//					if(AcpService.validate(rspData, "UTF-8")){
//						System.err.println("验证签名成功");
//						String respCode = rspData.get("respCode") ;
//						if(("00").equals(respCode)){
//							//成功,获取tn号
//						
//							
//						}else{
//							System.err.println("验证其他应答码为失败请排查原因或做失败处理:"+respCode);
//							//其他应答码为失败请排查原因或做失败处理
//
//						}
//					}
//				}else{
//					//未返回正确的http状态
//					
//				}
//	}
}
