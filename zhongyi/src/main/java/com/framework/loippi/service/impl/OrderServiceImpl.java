package com.framework.loippi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.controller.app.model.AppArea;
import com.framework.loippi.controller.app.model.LoginUser;
import com.framework.loippi.controller.app.model.OrderUid;
import com.framework.loippi.dao.ActivityDao;
import com.framework.loippi.dao.CourseDao;
import com.framework.loippi.dao.CourseStuTimeDao;
import com.framework.loippi.dao.CourseTimeDao;
import com.framework.loippi.dao.OrderDao;
import com.framework.loippi.dao.OrgUserDao;
import com.framework.loippi.dao.OrgnizationDao;
import com.framework.loippi.entity.Activity;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseStuTime;
import com.framework.loippi.entity.CourseTime;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrderInfo;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.entity.User;
import com.framework.loippi.entity.ValidTotal;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.ValidTotalService;
import com.framework.loippi.utils.RandomUtils;
import com.framework.loippi.utils.ReturnJson;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.wechat.utils.AppOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SERVICE - Order(订单表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class OrderServiceImpl extends GenericServiceImpl<Order, Long> implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private CourseStuTimeDao courseStuTimeDao;
	@Autowired
	private CourseTimeDao courseTimeDao;
	@Autowired
	private ValidTotalService validTotalService;
	
	@Autowired
	private OrgnizationDao orgnizationDao;
	
	@Autowired
	private OrgUserDao orgUserDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orderDao);
	}

	public List<Order> findListByPage(Object parameter){
		return orderDao.findListByPage(parameter);
	}
	
	
	public String toBuyNow(Order order,LoginUser user ,Course course){
		
		
	
	    Long l1=courseDao.update2buyCourse(course.getId());
	    if(l1==0){
	    	return "0";
	    }
	    Long    id= orderDao.insert(order);
	    System.err.println(id);
	    Map parameter=new HashMap();
	    parameter.put("uuid", order.getUuid());
	    List<Order> orders= orderDao.findListByPage(parameter);
	    order= orders.get(0);
		String bn=StringUtil.orderSn()+ order.getId()+ RandomUtils.getRandomNumberString(1);
		order.setTerm(course.getTerm());
		order.setBn(bn);
		orderDao.update(order);
		parameter=new HashMap();
		parameter.put("bn", bn);
		parameter.put("orderId", order.getId());
		parameter.put("payment", order.getPayment());
		
		//集体课课时
		Map map=new HashMap();
		map.put("courseId", course.getId());
		map.put("notstudent", course.getId());
		List<CourseStuTime> CourseStuTimeList = new ArrayList<>();
		List<CourseTime> courseTimes=courseTimeDao.findListByPage(map);
		if(courseTimes!=null){
			for (CourseTime courseTime : courseTimes) {
				//已上过的课不生成上课时间表
				Long dateLong = Long.parseLong(StringUtil.date2String3(new Date()));
				if(courseTime.getCourseTime3() > dateLong) {
					
					//生成学生上课时间
					CourseStuTime	dto=new  CourseStuTime();
					BeanUtils.copyProperties(courseTime, dto);
					dto.setOrderId(order.getId());
					dto.setStatus(1);
					dto.setStudentId(order.getStuId());
					dto.setCreateTime(new Date());
					dto.setTerm(course.getTerm());
					dto.setCourseTimeId(courseTime.getId());
					dto.setId(null);
					courseStuTimeDao.insert(dto);
					
					CourseStuTimeList.add(dto);
				}
				
			}
			if(CourseStuTimeList != null && CourseStuTimeList.size() > 0) {
				order.setClasstimes(CourseStuTimeList.size());
				//判断是否中间报名
				if(courseTimes.size()-order.getClasstimes() > 0) {
					//设置类型为中间报名且记录少上的课时数
					order.setRestType(1);
					order.setRestclass(courseTimes.size()-order.getClasstimes());
				}
			}
			orderDao.update(order);
		}
	
		return ReturnJson.jsonString("OK",parameter,AppConstants.OK);
		
	}
	
	
	

	@Override
	public boolean toPayByMoney(Integer payType,Order order) {
		// TODO Auto-generated method stub
		
		
		
		//支付成功
		order.setPayType(payType);
		order.setStatus(2);
		order.setUpdateTime(new Date());
		order.setPayTime(new Date());
		order.setTallyType(1);
		order.setOrderType(1);
	 
		
		//查询该学生之前是否报过该课程,未报过机构用户数加一，报过则不加
		try {
			Map map = new HashMap<>();
			map.put("orgId", order.getOrgId());
			map.put("type", 1);
			map.put("stuId", order.getStuId());
			map.put("uid", order.getUid());
			List list1 = new ArrayList();
			list1.add(2);
			list1.add(3);
			list1.add(8);
			map.put("orderStatus", list1);
			
			List<Order> orderList = orderDao.findListByPage(map);
			
			if(orderList == null || orderList.size() == 0) {
				//同一孩子还没加入该机构的记录，则机构用户表添加一条记录且机构学生用户数加一
				Orgnization orgnization = orgnizationDao.find(order.getOrgId());
				orgnization.setStudentCount(orgnization.getStudentCount()+1);
				orgnizationDao.update(orgnization);
				
				//机构用户表添加一条记录
				OrgUser orgUser = new OrgUser();
				orgUser.setCreateTime(new Date());
				orgUser.setOrgId(order.getOrgId());
				orgUser.setUid(order.getUid());
				orgUser.setType(1);
				orgUserDao.insert(orgUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    
	    //计算已报该课程的小孩，如果报过两次就设置改订单为续班。
	    Map map2=new HashMap();
        map2.put("targetId",order.getTargetId());
        map2.put("stuId",order.getStuId());
        map2.put("type", 1);
        	List list=new ArrayList();
        	list.add(2);
        	list.add(3);
        	list.add(8);
        map2.put("orderStatus", list);
        List<Order>   orders=orderDao.findByParams(map2);
        if(orders!=null&&orders.size()>1){
        	order.setContinueclass(1);
        	 orderDao.update(order);
        }else{
        	 orderDao.update(order);
        }
        
	    
	    validTotalService.addValidTotal(order);
	    
	    	
	    
		return true;
	}

	@Override
	public String toBuyNow(Order order, LoginUser user, Activity activity) {
		   Long l1=activityDao.update2buyActivity(activity.getId());
		    if(l1==0){
		    	return "0";
		    }
		    Long    id= orderDao.insert(order);
		    System.err.println(id);
		    Map parameter=new HashMap();
		    parameter.put("uuid", order.getUuid());
		    List<Order> orders= orderDao.findListByPage(parameter);
		    order= orders.get(0);
			String bn=StringUtil.orderSn()+ order.getId()+ RandomUtils.getRandomNumberString(1);
			order.setBn(bn);
			orderDao.update(order);
			
			parameter=new HashMap();
			parameter.put("bn", bn);
			parameter.put("orderId", order.getId());
			parameter.put("payment", order.getPayment());
			return ReturnJson.jsonString("OK",parameter,AppConstants.OK);
	}
	
	
	public List<Order> findParnetIdByOrgId(Object parameter){
		return  orderDao.findParnetIdByOrgId(parameter);
	}
	
	public List<Order> findStuIdByCourseId(Object parameter){
		return  orderDao.findStuIdByCourseId(parameter);
	}

	@Override
	public String findTotalFee(Object parameter) {
		// TODO Auto-generated method stub
		return orderDao.findTotalFee(parameter);
	}

	@Override
	public String findTotaNum(Object parameter) {
		// TODO Auto-generated method stub
		return orderDao.findTotaNum(parameter);
	}

	@Override
	public OrderInfo SeachTotalFeeSeach(Object parameter) {
		// TODO Auto-generated method stub
		return orderDao.SeachTotalFeeSeach(parameter);
	}
	

	/**
	 * 后台手动记账
	 */
	public String toBuyNow2(Order order,User user ,Course course){
			
		    Long l1=courseDao.update2buyCourse(course.getId());
		    if(l1==0){
		    	return "0";
		    }
		    Long    id= orderDao.insert(order);
		    System.err.println(id);
		    Map parameter=new HashMap();
		    parameter.put("uuid", order.getUuid());
		    List<Order> orders= orderDao.findListByPage(parameter);
		    order= orders.get(0);
			String bn=StringUtil.orderSn()+ order.getId()+ RandomUtils.getRandomNumberString(1);
			order.setTerm(course.getTerm());
			order.setBn(bn);
			orderDao.update(order);
			parameter=new HashMap();
			parameter.put("bn", bn);
			parameter.put("orderId", order.getId());
			parameter.put("payment", order.getPayment());
			
			//集体课课时
			Map map=new HashMap();
			map.put("courseId", course.getId());
			map.put("notstudent", course.getId());
			List<CourseStuTime> CourseStuTimeList = new ArrayList<>();
			List<CourseTime> courseTimes=courseTimeDao.findListByPage(map);
			if(courseTimes!=null){
				for (CourseTime courseTime : courseTimes) {
					//已上过的课不生成上课时间表
					Long dateLong = Long.parseLong(StringUtil.date2String3(new Date()));
					if(courseTime.getCourseTime3() > dateLong) {
						
						//生成学生上课时间
						CourseStuTime	dto=new  CourseStuTime();
						BeanUtils.copyProperties(courseTime, dto);
						dto.setOrderId(order.getId());
						dto.setStatus(1);
						dto.setStudentId(order.getStuId());
						dto.setCreateTime(new Date());
						dto.setTerm(course.getTerm());
						dto.setCourseTimeId(courseTime.getId());
						dto.setId(null);
						courseStuTimeDao.insert(dto);
						
						CourseStuTimeList.add(dto);
					}
					
				}
				if(CourseStuTimeList != null && CourseStuTimeList.size() > 0) {
					order.setClasstimes(CourseStuTimeList.size());
					//判断是否中间报名
					if(courseTimes.size()-order.getClasstimes() > 0) {
						//设置类型为中间报名且记录少上的课时数
						order.setRestType(1);
						order.setRestclass(courseTimes.size()-order.getClasstimes());
					}
				}
				orderDao.update(order);
			}
		
			return ReturnJson.jsonString("OK",parameter,AppConstants.OK);
			
		}

	@Override
	public List<OrderUid> findOrderUid(Long orderId) {
		return orderDao.findOrderUid(orderId);
	}

	@Override
	public Long getById(String id) {
		// TODO Auto-generated method stub
		
		return orderDao.getById(id);
	}

	
	
}
