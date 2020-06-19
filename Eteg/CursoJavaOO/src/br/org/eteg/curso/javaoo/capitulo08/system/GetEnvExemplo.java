package br.org.eteg.curso.javaoo.capitulo08.system;

import java.util.Map;
import java.util.Set;

public class GetEnvExemplo {

	public static void main(String[] args) {

		// obtendo o mapa de propriedades do ambiente (SO)
		Map<String, String> mapa = System.getenv();
		// obtendo as chaves
		Set<String> chaves = mapa.keySet();
		// iterando sobre as chaves
		for (String s : chaves)
		{
			System.out.println(s + " : " + 
					System.getenv(s));
		}
	}
	
}
