package br.org.eteg.curso.javaoo.capitulo08.system;

import java.util.Properties;
import java.util.Set;

public class PropriedadeSistema {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// obt�em as propriedades do sistema
		Properties propriedades = System.getProperties();
		// obt�em a liste de chaves 
		Set<Object> chaves = propriedades.keySet();
		// obt�em cada chave
		for (Object chave:chaves)
		{
			System.out.println(chave + " : " + 
					propriedades.get(chave));
		}		
	}
}