package com.ericsson.cms.orng.load.scripts.extract.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class NetExtract {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fileName = "C:/share/net.log";
		
//		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
//		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
		String line = "";
		String time = "";
		String data = null;
		int i = 1;
		String used, free, buff;
		Scanner sc = new Scanner(new File(fileName));
		while ((line = sc.nextLine()) != null)
		{
			if (line.indexOf("CET") != -1)
			{
				time = line;
			}
			String [] strs = {"eth0"};//, "eth1", "eth3"};
			if (line.indexOf("Average:") != -1 && lineContainsStrings(line, strs))
			{
				// rcpx trpx rcbt tx bt
				String rcpk, tmpk, rcbt, tmbt, eth;
				sc.next(); // average
				eth = sc.next(); // ethernet
				rcpk = sc.next();
				tmpk = sc.next();
				rcbt = sc.next();
				tmbt = sc.next();
				//System.out.println(i + "\t" + time + "\t" + line);
				if (i % 10 == 0)
				System.out.println(i + "\t" + time + "\t" + eth + "\t" + rcpk + "\t" + tmpk + "\t" + rcbt + "\t" + tmbt);
				sc.nextLine();
				i++;
			}
			
		}
		
	}
	
	private static boolean lineContainsStrings(String line, String ... strings)
	{
		for (String s : strings)
		{
			if (line.contains(s)){
				return true;
			}
		}
		return false;
	}
}
