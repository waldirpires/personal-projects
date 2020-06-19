package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteCounter {

	public static void main(String[] args) throws IOException{
		
		InputStream in;
		
		if (args.length == 0)
		{
			in = System.in;
		} else 
		{
			in = new FileInputStream(args[0]);
		}
		int total = 0;
		while (in.read() != -1)
		{
			total++;
		}
		System.out.println(total+ " bytes.");
	}
}
