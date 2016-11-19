package com.autotest.utils;

public class Helper {
	public static String trim(String str) {
		return str == null ? "" : str.trim();
	}
	public static boolean isEmpty(String str){
		return str==null?true:(trim(str).length()==0);
	}
}
