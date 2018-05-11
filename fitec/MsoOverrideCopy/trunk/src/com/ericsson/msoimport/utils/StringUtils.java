package com.ericsson.msoimport.utils;

public class StringUtils {

	public static boolean isEmpty(String s)
	{
		return s == null || s.trim().equals("");
	}
}
