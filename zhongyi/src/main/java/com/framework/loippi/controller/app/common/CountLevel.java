package com.framework.loippi.controller.app.common;
//计算等级和等级小时
public class CountLevel {
	public long levelPoint=0;  
	public long level=1;  
	public void sum(long point)  
	{  
		levelPoint+=level*35;  
	
	   if(levelPoint<=point)  
	   {
		level++;  
	    sum(point);//调用自身实现递归  
	   }  
	}  
	public static void main(String[] args) {  
		CountLevel test=new CountLevel();  
	   test.sum(73);  
	   System.out.println("计算结果：a="+test.level+"   sum="+test.levelPoint);  
	}  
}
