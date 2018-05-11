package com.ericsson.cms.orng.load.scripts.extract.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;


public class SqlExtract {

	public static void main(String[] args) throws FileNotFoundException {
		
		String fileName = "C:\\share\\mt7\\db\\mt5-mt7.txt";
		
//		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
//		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
		String line = "";
		String time = "";
		int i = 1;
		Scanner sc = new Scanner(new File(fileName));
		Map<String, String> values = new TreeMap<String, String>();
		try {
			while ((line = sc.nextLine().trim()) != null)
			{
				if (line.trim().length() > 0 && line.indexOf("NAME_") == -1  && 
						line.indexOf("----") == -1 && line.indexOf("TOTAL") == -1
						&& line.indexOf("SELECT") == -1 && line.indexOf("WFS") == -1
						&& line.indexOf("rows") == -1)
				{				
					String key = line;
					String value = sc.next().trim();
					values.put(key, value);
					sc.nextLine();
					//System.out.println(values);					
				}
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printHash(values);
	}
	
	private static void printHash(Map<String, String> values)
	{
		for (String s: values.keySet())
		{
			System.out.println(s + "\t" + values.get(s));
		}
	}
	
	private static boolean isNumber(String s)
	{
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
