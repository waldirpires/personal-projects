package com.ericsson.cms.orng.load.scripts.extract.java;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class VmstatExtract {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		String fileName = "C:\\share\\mt8\\MT8-1700-cms\\vmstat.log"; 
				//"C:/share/MT7-2013.11.22-1700/vmstat.log"; 
				//"C:/apps/office/notepadplus/vmstat.log";
		
//		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
//		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
		String line = "";
		String time = "";
		int i = 1;
		Scanner sc = new Scanner(new File(fileName));
		int free, swpd, cache,buff;
		while ((line = sc.nextLine()) != null)
		{
			time = line;
			sc.nextLine();// vmstat
			sc.nextLine(); // headers
			sc.nextLine(); //header 2
			sc.nextInt(); // r
			sc.nextInt(); // b
			swpd = sc.nextInt();//swpd
			free = sc.nextInt();// free
			buff = sc.nextInt();// buff
			cache = sc.nextInt();// cache
			sc.nextLine(); // rest of line
			System.out.println(time + "\t" + swpd + "\t" + free + "\t" + buff+ "\t" + cache);
			//+ "\t" + free+ "\t" + buff+ "\t" + cache);
			sc.nextLine();
		}

	}
}
