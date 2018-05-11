package com.ericsson.cms.orng.load.scripts.extract.java;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class SarExtract {

	public static void main(String[] args) throws IOException {
		String fileName = "c:/share/MT7-2013.11.22-1700/sar.log"; 
				//"C:/apps/office/notepadplus/sar.txt";
		
		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
		String line = "";
		String time = "";
		int i = 1;
		while ((line = br.readLine()) != null)
		{
			if (line.indexOf("CET") != -1)
			{
				time = line;
			}
			if (line.indexOf("Average") != -1)
			{
				System.out.println(time + ":\t" + line.substring(line.length()-6, line.length()));
				//System.out.println(time + ":\t" + line);
				//line = line.replace(" ", "\t");
				//System.out.println(time + "\t" + line);
				i++;
			}
		}
		System.out.println(i);
		br.close();
		dis.close();

	}
}
