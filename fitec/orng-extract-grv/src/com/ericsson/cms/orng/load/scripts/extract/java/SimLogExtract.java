package com.ericsson.cms.orng.load.scripts.extract.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class SimLogExtract {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fileName = "C:\\share\\mt8\\MT8-sim\\catalina2.out.mt8.log"; 
				//"C:\\share\\catalina.out.bk3";
		// "C:/share/top.log";

		// DataInputStream dis = new DataInputStream(new FileInputStream(new
		// File(fileName)));
		// BufferedReader br = new BufferedReader(new InputStreamReader(dis));

		String line = "";
		String date, time, level, clazz, id;
		int i = 1;
		Scanner sc = new Scanner(new File(fileName));
		Map<String, Integer> mapIds = new TreeMap<String, Integer>();
		Map<String, Set<String>> mapFreqH = new TreeMap<String, Set<String>>();
		
		try {
			while ((line = sc.nextLine()) != null) {
				// 2013-11-28 18:10:42
				//  2013-11-28 18:12:42
				if (line.startsWith("2013-11-"))
				{
					String [] split = line.split(" ");
					date = split[0];
					time = split[1];
					level = split[2];
					clazz = split[4];
					id = split[6];
					
//					if (time.compareTo("18:10:42") >= 0 && 
//							time.compareTo("18:12:42") <= 0)
//					{
						//System.out.println(line);
						if (mapIds.get(id) == null)
						{
							mapIds.put(id, 1);
						} else
						{
							mapIds.put(id, mapIds.get(id) + 1);
						}
//					}
				}
				
				if (line.indexOf("2013-1") != -1)
				{
					String [] split = line.split(" ");
					date = split[0];
					time = split[1];
					level = split[2];
					clazz = split[4];
					id = split[6];
//					System.out.println(line);
//					
//					if (id.startsWith("["))
//					{
//						
//					if (mapFreqH.get(id) == null)
//					{
//						mapFreqH.put(id, new TreeSet<String>());
//					}
//					mapFreqH.get(id).add(date.concat(" ").concat(time));
//					System.out.println(mapFreqH);
					
					if (id.startsWith("[") && line.indexOf("AbstractSimServlet") == -1 && date.compareTo("2013-12-02") <= 0 || (date.compareTo("2013-11-29") >= 0 ))
					{
							String k = line.substring(0, 13).concat(id);
							//System.out.println(line);
							if (mapIds.get(k) == null)
							{
								mapIds.put(k, 1);
							} else
							{
								mapIds.put(k, mapIds.get(k) + 1);
							}
							String key = line.substring(0, 13);
							if (mapFreqH.get(key) == null)
							{
								mapFreqH.put(key, new HashSet<String>());
							} 
							mapFreqH.get(key).add(id);
							i++;
							if (i % 1000 == 0)
							{
								System.out.println(i);
							}
					}
				}}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total IDs: " + mapIds.keySet().size());
		
		for (String s: mapIds.keySet())
		{
			System.out.println(s + "\t" + mapIds.get(s));
		}
//		
//		System.out.println("Total Requests per hour: ");
		for (String k: mapFreqH.keySet())
		{
			System.out.println(k + "\t" + mapFreqH.get(k).size());
			//System.out.println(mapFreqH.get(k));
		}

	}

}
