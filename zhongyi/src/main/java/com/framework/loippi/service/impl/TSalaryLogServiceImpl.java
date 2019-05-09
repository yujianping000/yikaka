package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.TSalaryLogDao;
import com.framework.loippi.entity.OrgUser;
import com.framework.loippi.entity.TSalary;
import com.framework.loippi.entity.TSalaryLog;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.ThourExpense;
import com.framework.loippi.service.OrgUserService;
import com.framework.loippi.service.TSalaryLogService;
import com.framework.loippi.service.TSalaryService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.ThourExpenseService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SERVICE - TSalaryLog(工资记录表)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class TSalaryLogServiceImpl extends GenericServiceImpl<TSalaryLog, Long> implements TSalaryLogService {
	
	@Autowired
	private TSalaryLogDao tSalaryLogDao;
	@Autowired
	private ThourExpenseService thourExpenseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private OrgUserService orgUserService;
	@Autowired
	private TSalaryService tSalaryService;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(tSalaryLogDao);
	}

	public List<TSalaryLog> findListByPage(Object parameter){
		return tSalaryLogDao.findListByPage(parameter);
	}

	@Override
	public void payroll() {
		//遍历所有的机构用户
		List<OrgUser> orgUsers=orgUserService.findList("type",2);
		for(OrgUser orgUser:orgUsers){
			Double gscore=0.00;
			Double  rateAccount=0.00;
			Double  conRate=0.00;
		 Long teacherid=orgUser.getUid();
		 Long orgId=orgUser.getOrgId();
		 Teacher teacher=teacherService.find(teacherid);
		//用户评价绩效
		Map map=new HashMap();
		map.put("orgId", orgId);
		map.put("teacherId",teacherid );
		String starts=  tSalaryLogDao.getStarts(map);
		if(starts!=null){
			map.put("starts", starts);
			 gscore= tSalaryLogDao.getCommentSum(map);
		}
		//点评率绩效
		Integer shoundCom=tSalaryLogDao.getCountOrder(map); //应该点评
		Integer practicalCom=tSalaryLogDao.getCountComment(map);  //实际点评
		Double commentRate= (double)practicalCom/(double)shoundCom;
		if(commentRate!=null&&!commentRate.isNaN()){
			map.put("commentRate", commentRate*100);
			rateAccount= tSalaryLogDao.getRemarkOnSum(map);
		}
	
		//续班率绩效
		Integer conClass=tSalaryLogDao.getcontinueclasTotal(map);
		Integer orderTotal=tSalaryLogDao.getOrderTotal(map);
		Double conScore =(double)conClass/(double)orderTotal;
		if(conScore!=null&&!conScore.isNaN()){
			map.put("conScore", conScore*100);
			 conRate= tSalaryLogDao.getconOnSum(map);
		}
	
		//实际上课课时全职老师
		Integer teaTime=tSalaryLogDao.getCountTime(map);
		List<ThourExpense> thourExpenses=thourExpenseService.findList(map);
		BigDecimal hourBonus = new BigDecimal(0);
		Double performance=0.00;
		if(thourExpenses.size()!=0){
			ThourExpense thourExpense=thourExpenses.get(0);
			//全职老师
			if(teacher!=null&&teacher.getTeacherType()!=null){
			if(teacher.getTeacherType()==1){
				Integer hour=thourExpense.getHour();
				if(teaTime!=null&&hour!=null){
					Integer difference=teaTime-hour;
					if(difference<=0){
						if(thourExpense.getDeduct()!=null){
							hourBonus=new BigDecimal(difference).multiply(thourExpense.getDeduct()) ;
						}
					}else if(difference>=1){
						if(thourExpense.getAward()!=null){
						hourBonus=new BigDecimal(difference).multiply(thourExpense.getAward()) ;
						}
					}
				}
			}else if(teacher.getTeacherType()==2){
				//兼职教师1
				if(thourExpense.getOneClass()!=null&&thourExpense.getOneClass()!=new BigDecimal(0)&&new BigDecimal(teaTime)!=null){
						hourBonus=new BigDecimal(teaTime).multiply(thourExpense.getOneClass()) ;
				}else if(thourExpense.getMoneyRate()!=null&&thourExpense.getMoneyRate()!=new BigDecimal(0)&&tSalaryLogDao.getTotal(map)!=null){
					//兼职教师2
				
						hourBonus=tSalaryLogDao.getTotal(map).multiply(thourExpense.getMoneyRate().divide(new BigDecimal(100))) ;
				}
			}
		}
		//绩效总计
		if(gscore==null){
			gscore=new Double(0);
		}
		if(rateAccount==null){
			rateAccount=new Double(0);
		}
		if(conRate==null){
			conRate=new Double(0);
		}
		performance=gscore+rateAccount+conRate;
		Map<String,Object> mapsalary=new HashMap<String,Object>();
		mapsalary.put("uid",teacherid );
		mapsalary.put("orgId",orgId );
		List<TSalary> tSalarys=tSalaryService.findList(mapsalary);
		if(tSalarys.size()!=0){
			TSalary tSalary=tSalarys.get(0);
				TSalaryLog tSalaryLog=new TSalaryLog();
				//基本工资
				BigDecimal basicSalary=tSalary.getBasicSalary();
				if(basicSalary==null){
					basicSalary=new BigDecimal(0);
				}
				tSalaryLog.setBasicSalary(basicSalary);
				//
				tSalaryLog.setCourseSalary(hourBonus);
				tSalaryLog.setPerformanceSalary(new BigDecimal(performance));
				//全勤奖
				BigDecimal allTsutomu=tSalary.getAllTsutomu();
				if(allTsutomu==null){
					allTsutomu=new BigDecimal(0);
				}
				tSalaryLog.setAllTsutomu(allTsutomu);
				//奖金
				BigDecimal bonus=tSalary.getBonus();
				if(bonus==null){
					bonus=new BigDecimal(0);
				}
				tSalaryLog.setBonus(bonus);
				//提成
				BigDecimal pushMoney=tSalary.getPushMoney();
				if(pushMoney==null){
					pushMoney=new BigDecimal(0);
				}
				tSalaryLog.setPushMoney(pushMoney);
				//报销
				BigDecimal reimburse=tSalary.getReimburse();
				if(reimburse==null){
					reimburse=new BigDecimal(0);
				}
				tSalaryLog.setReimburse(reimburse);
				
				BigDecimal edit1=tSalary.getEdit1();
				if(edit1==null){
					edit1=new BigDecimal(0);
				}
				
				BigDecimal Edit2=tSalary.getEdit2();
				if(Edit2==null){
					Edit2=new BigDecimal(0);
				}
				
				BigDecimal Edit3=tSalary.getEdit3();
				if(Edit3==null){
					Edit3=new BigDecimal(0);
				}
				
				tSalaryLog.setOther(Edit2.add(edit1).add(Edit3));
				tSalaryLog.setCreateTime(new Date());
				tSalaryLog.setStatus(2);
				tSalaryLog.setUid(teacher.getId());
				tSalaryLog.setOrgId(orgId);
				tSalaryLog.setAccount(teacher.getAccount());
				tSalaryLog.setUserName(teacher.getNickname());
				tSalaryLogDao.insert(tSalaryLog);
				mapsalary.put("id",tSalaryLog.getId() );
				BigDecimal totalSalary=tSalaryLogDao.getSalaryTotal(mapsalary);
				tSalaryLog.setTotalSalary(totalSalary);
				tSalaryLogDao.update(tSalaryLog);
			}
		}
		
		}
	}
	
}
