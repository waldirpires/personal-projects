package br.org.eteg.curso.javaoo.capitulo08.system;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


public class ExemploSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("teste");
		try {
			while (true)
			{
				char c = (char) System.in.read();
				
				if (c == 'x')
					break;
				
				if (c < '0' || c > '9')
				{
					System.out.println("Digite um n�mero");
				} else
				{
					System.out.println(c);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void mostrarOutrosUsos()
	{
		
		// carrega uma biblioteca nativa
		System.loadLibrary("ODBC.dll");
		
		// pedir a execu��o do coletor de lixo
		System.gc();
		
		// finalizar a aplica��o
		System.exit(0);
	}
	
	
	
	public static void listarPropriedades()
	{
		Properties propriedades = System.getProperties();
		
		Enumeration<Object> enumeracao = propriedades.elements();
		String chave = null;
		
		while (enumeracao.hasMoreElements())
		{
			chave = (String) enumeracao.nextElement();
			System.out.println(chave + " : " + 
					propriedades.getProperty(chave));
		}
		
	}
}
