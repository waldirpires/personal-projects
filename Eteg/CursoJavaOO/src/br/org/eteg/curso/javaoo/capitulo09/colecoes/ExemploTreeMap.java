package br.org.eteg.curso.javaoo.capitulo09.colecoes;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ExemploTreeMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String [] planetas = {"Mercurio", "Venus", "Terra", "Marte", 
				"Jupiter", "Saturno", 
				"Urano", "Netuno", "Plutão"};
		
		int [] distancias = {10, 20, 30, 40, 80, 160, 320, 600, 1000};
		
		Map<String, Integer> mapaPlanetas = new TreeMap<String, Integer>();
		
		int i = 0;
		for (String s: planetas)
		{
			mapaPlanetas.put(s, distancias[i]);
			i++;
		}
		
		Iterator<String> iterador = mapaPlanetas.keySet().iterator();
		String planeta = null;
		while (iterador.hasNext())
		{
			planeta = iterador.next();
			System.out.println(planeta + " : " + mapaPlanetas.get(planeta));
		}
	}

}
