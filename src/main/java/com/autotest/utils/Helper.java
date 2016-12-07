package com.autotest.utils;

public class Helper {
	public static String trim(String str) {
		return str == null ? "" : str.trim();
	}
	public static boolean isEmpty(String str){
		return str==null?true:(trim(str).length()==0);
	}
	public static boolean isInteger(String str){
		boolean rs=false;
		if(Helper.trim(str).length()>0){
			try{
				Integer.parseInt(str);
				rs=true;
			}catch(Exception e){
				System.out.println("error:"+e.getMessage());
			}
		}
		return rs;
	}
	public static int parseStringToInt(String str){
		int i=0;
		if(isInteger(str)){
			i=Integer.parseInt(str);
		}
		return i;
	}
}
