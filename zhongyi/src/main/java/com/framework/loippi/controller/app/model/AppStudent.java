package com.framework.loippi.controller.app.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import com.framework.loippi.utils.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 学生表
 * 
 * @author wgb
 * @version 2.0
 */

public class AppStudent  {


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirtchdayString() {
		return birtchdayString;
	}

	public void setBirtchdayString(String birtchdayString) {
		this.birtchdayString = birtchdayString;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**  */
	private Long id;
	
	/** 家长ID */
	private Long uid;
	
	/** 性别 */
	private String sex;
	
	/** 生日 */
	private String birtchdayString;
	
	/** 爱好 */
	private String hobby;
	
	/** 姓名 */
	private String nickname;
	
	/** 头像 */
	private String avatar;

	/** 年龄*/
	private String age;

	/*public String getAge() {
		if(birtchdayString!=null&&birtchdayString!=""){
		
		 int i=Integer.parseInt(birtchdayString.split("-")[0])	;
		 Date date=new Date();
		  DateFormat format1 = new java.text.SimpleDateFormat("yyyy");
		  String yearString=  format1.format(date);
		 i=Integer.parseInt(yearString)-i+1;
		 if(i<0){
			 return "0";
		 }else{
			 return i+"";
		 }
		 
		}
		
		
		return  "0";
	}*/
	
	public String getAge() throws Exception {
		//按周岁来算
		if(birtchdayString != null && birtchdayString != "") { 
			Date birtchday = StringUtil.StringDateToDate(birtchdayString);
			
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

	public void setAge(String age) {
		this.age = age;
	}
	
	public static void main(String[] args) {
		 Date date=new Date();
		System.err.println(date.getYear());
	}
}
