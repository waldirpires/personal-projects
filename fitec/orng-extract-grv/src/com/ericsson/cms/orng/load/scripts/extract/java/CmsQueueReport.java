package com.ericsson.cms.orng.load.scripts.extract.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CmsQueueReport {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fileName = "C:/share/cms_queue_report_mt_2013-11-22.txt";
				//mt8/cms-queue.log";
		
//		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
//		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
		String line = "";
		String time = "";
		String data = null;
		int i = 1;
		int numLines = 0;
		String used, free, buff;
		Scanner sc = new Scanner(new File(fileName));
		String size;
		while ((line = sc.nextLine()) != null)
		{
			if (line.indexOf("CET") != -1)
			{
				time = line;
			}
			if (line.indexOf("items.") != -1)
			{
				size = sc.next();
				sc.nextLine();
				if (i%10 == 0)
				System.out.println(i + "\t" + time+ "\t" +size);
				i++;
			}
			numLines++;
			if (numLines > 296618)
			{
				break;
			}
		}

	}
	
}
