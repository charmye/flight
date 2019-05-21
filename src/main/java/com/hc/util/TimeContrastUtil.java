package com.hc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeContrastUtil {

	public static boolean TimeContrast(String time){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				try {
					 
					Date date1 = df.parse(time);
					Date date2 = new Date();
					if(date1.getTime()<date2.getTime()) {
						return false;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
	            return true;
	}
	
	public static String timeFormat(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null ;
		try {
			date = sdf.parse(time);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(date);
	}
	
}
