package com.framework.loippi.controller.app.common;
//计算通过的等级需要小时数
public class CountPassHours {
	public long sum=0;  
	public long a=1;  
	public void sum(long level)  
	{  
	   sum+=a*35;  
	   a++;  
	   if(a<=level)  
	   {  
	    sum(level);//调用自身实现递归  
	   }  
	}  
	public static void main(String[] args) {  
	
	   
	   
	   CountLevel countLevel=new CountLevel();  
	   countLevel.sum(75);  
	   System.out.println("计算结果：a="+countLevel.level+"   sum="+countLevel.levelPoint);

	   CountPassHours test=new CountPassHours();  
	   test.sum(2);  
	   System.out.println("计算结果："+test.sum);  
	   System.out.println("计算结果："+(75-test.sum)); 
	}  
}
