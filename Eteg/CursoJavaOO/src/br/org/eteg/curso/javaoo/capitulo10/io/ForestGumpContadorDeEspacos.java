package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ForestGumpContadorDeEspacos {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{

		Reader in = null;
		
		if (args.length == 0)
		{
			in = new InputStreamReader(System.in);
		} else 
		{
			in = new FileReader(args[0]);
		}
		int total = 0;
		int espacos = 0;
		int ch = 0;
		for (total = 0; (ch = in.read()) != -1; total++)
		{
			if (Character.isWhitespace((char)ch))
			{
				espacos++;
			}
		}
		System.out.println(total + " caracteres, " + 
				espacos + " espacos.");
	}

}
