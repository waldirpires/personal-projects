package br.eteg.curso.java.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SistemaUtil {


	
	/**
	 * método que retorna as propriedades do sistema de execução.
	 * @return um mapa contendo as propriedades.
	 */
	public static Map<String, String> mostrarPropriedadesSistema() {
		Properties propriedades = System.getProperties();

		Enumeration<Object> enumeracao = propriedades.keys();
		String key = null;
		Map<String, String> propSistema = new HashMap<String, String>();
		while (enumeracao.hasMoreElements()) {
			key = (String) enumeracao.nextElement();
			System.out.println(key + ": " + System.getProperty(key));
			propSistema.put(key, System.getProperty(key));
		}
		return propSistema;
	}

	/**
	 * método que retorna as propriedades do ambiente de execução.
	 * @return um mapa contendo as propriedades.
	 */
	public static Map<String, String> mostrarPropriedadesAmbiente() {
		Map<String, String> propsEnv = System.getenv();

		Collection<String> chaves = propsEnv.keySet();
		
		for (String chave:chaves)
		{
			System.out.println(chave + " : " + 
					System.getenv(chave));
		}		
		return propsEnv;
	}

	/**
	 * método que executa um comando no sistema.
	 * @param args o comando a ser executado e seus argumentos.
	 */
	public static void executarComando(String[] args) {
		if (args.length > 0) {
			Runtime runtime = Runtime.getRuntime();
			try {
				Process proc = runtime.exec(args);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(proc.getInputStream()));
				String lineRead = null;
				while ((lineRead = reader.readLine()) != null) {
					System.out.println(lineRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		mostrarPropriedadesAmbiente();
	}
	
}
