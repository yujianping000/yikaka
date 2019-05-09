package com.framework.loippi.controller.app.model;

import java.util.Calendar;
import java.util.Date;

import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.utils.StringUtil;

/**
 * 认证
 */
public class AppTeacherAuth {

	private Long id;
	
	/** 机构id */
	private Long orgId;
	
	/** 认证用户名 */
	private String name;
	
	/** 生日 */
	private String birthday;
	
	/** 1-全职   2-兼职 */
	private Integer type;
	
	/**性别  */
	private String sex;
	
	/** 身份证 */
	private String idCard;
	
	/** 教师证 */
	private String teacherCard;
	
	/** 学历证 */
	private String educationCard;
	
	/** 资历证 */
	private String qlCard;
	
	/** 创建日期 */
	private String createTime;
	
	/** 更新日期 */
	private String updateTime;
	
	/** 对应老师id */
	private Long teacherId;
	
	/** 1-通过  2-申请中  3-被拒绝 */
	private Integer status;
	
	/** 相关理由 */
	private String reason;
	
	/** 1-已读   2-未读 */
	private Integer isRead;
	
	/** 机构名*/
	private String orgName;
	
	/** 年龄*/
	private Integer age;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTeacherCard() {
		return teacherCard;
	}

	public void setTeacherCard(String teacherCard) {
		this.teacherCard = teacherCard;
	}

	public String getEducationCard() {
		return educationCard;
	}

	public void setEducationCard(String educationCard) {
		this.educationCard = educationCard;
	}

	public String getQlCard() {
		return qlCard;
	}

	public void setQlCard(String qlCard) {
		this.qlCard = qlCard;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAge() throws Exception {
		//按周岁来算
		if(birthday != null && birthday != "") { 
			Date birtchday = StringUtil.StringDateToDate(birthday);
			
			//获取当前系统时间
			Calendar cal = Calendar.getInstance(); 
			
			//如果出生日期大于当前时间，则抛出异常
	        if (cal.before(birtchday)) { 
	            throw new IllegalArgumentException( 
	                "The birthDay is before Now.It's unbelievable!"); 
	        } 
			
			//取出系统当前时间的年、月、日
			int yearNow = cal.get(Calendar.YEAR); 
			int monthNow = cal.get(Calendar.MONTH); 
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
			
			//将日期设置为出生日期
	        cal.setTime(birtchday); 
	        //取出出生日期的年、月、日
	        int yearBirth = cal.get(Calendar.YEAR); 
	        int monthBirth = cal.get(Calendar.MONTH); 
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 
	        
	        //当前年份与出生年份相减，初步计算年龄
	        int age = yearNow - yearBirth; 
	        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
	        if (monthNow <= monthBirth) { 
	            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
	            if (monthNow == monthBirth) { 
	                if (dayOfMonthNow < dayOfMonthBirth) 
	                	age--; 
	            } else { 
	                age--; 
	            } 
	        }
	        
	        return age + "";
		}
		
		return "0";
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
