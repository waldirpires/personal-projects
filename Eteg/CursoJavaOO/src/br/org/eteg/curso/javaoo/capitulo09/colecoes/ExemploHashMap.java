package br.org.eteg.curso.javaoo.capitulo09.colecoes;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ExemploHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		
		Integer ONE = new Integer(1);
		
		for (int i = 0, n = args.length; i < n; i++)
		{
			String key = args[i];
			Integer frequency = (Integer) map.get(key);
			
			if (frequency == null)
			{
				frequency = ONE;
			} else 
			{
				int value = frequency.intValue();
				frequency = new Integer(value + 1);
			}
			map.put(key, frequency);
		}
		System.out.println(map);
		Map<String, Integer> sortedMap = new TreeMap<String, Integer>(map);
		System.out.println(sortedMap);
		
	}

}
