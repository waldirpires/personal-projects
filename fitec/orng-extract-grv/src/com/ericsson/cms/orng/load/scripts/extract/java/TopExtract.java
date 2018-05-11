package com.ericsson.cms.orng.load.scripts.extract.java;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class TopExtract {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		String fileName = "C:/share/MT7-2013.11.22-1700/top.log"; 
				//"C:/share/top.log";
		
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
			if (line.indexOf("Mem:") != -1)
			{
				data = line;
				sc.next(); // mem: 
				sc.next(); // num
				sc.next(); // total
				used = sc.next();
				sc.next(); //used
				free = sc.next();
				sc.next();//free
				buff = sc.next();
				
				System.out.println(i + "\t" + time + "\t" + used + "\t" + free + "\t" + buff);
				i++;
			}
			//52509
			//64287
			// && line.indexOf("top") == -1
//			if (line.indexOf("oracle") != -1)
//			{
//				String cpu, mem;
//				//System.out.println(i + "\t"+ time + "\t" +line);
//				sc.next(); // pid
//				sc.next(); //nobody
//				sc.next(); // 20
//				sc.next(); // 0
//				sc.next(); // 63.4g
//				sc.next(); // 29g
//				sc.next(); // shr
//				sc.next(); // s
//				cpu = sc.next();
//				mem = sc.next();
//				//&& !cpu.equals("top")
//				if (!cpu .equals("0.0") )
//				System.out.println(i + "\t" + time + "\t" + cpu  + "\t" + mem);
//				sc.nextLine();
//				i++;
//			}
//			
		}

	}
	
}
