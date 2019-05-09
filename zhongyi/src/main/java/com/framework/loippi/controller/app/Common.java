package com.framework.loippi.controller.app;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
public static void main(String[] args) throws ParseException {
    Date date =new Date();
	SimpleDateFormat sdf =new SimpleDateFormat("mm");//只有时分秒
	SimpleDateFormat sdf2 =new SimpleDateFormat("yyyyMMddHH");//只有时分秒
	String time=sdf.format(date);
	String time2=sdf2.format(date);
	if(Integer.parseInt(time)<30){
		time2=time2+"00";
	}else{
		time2=time2+"30";
	}
	System.err.println(time2);

}
}
