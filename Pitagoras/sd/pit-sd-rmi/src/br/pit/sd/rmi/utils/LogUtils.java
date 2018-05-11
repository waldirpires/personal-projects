package br.pit.sd.rmi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

	public static void info(String msg){
		System.out.println(
				String.format("%s: INFO %s", new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(new Date()), 
				msg));
	}

	public static void error(String msg){
		System.out.println(
				String.format("%s: ERROR %s", new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(new Date()), 
				msg));
	}
}
