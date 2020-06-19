package br.eteg.curso.java.util.teste;

import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

public class TestePropriedadesSistema {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Properties propriedades = 
			System.getProperties();
		
		Enumeration<Object> enumeracao = 
			propriedades.keys();
		String key = null;
		
		while (enumeracao.hasMoreElements())
		{
			key = (String) enumeracao.nextElement();
			System.out.println(key + ": " + 
					System.getProperty(key));
		}
		
		
		
	}

}
