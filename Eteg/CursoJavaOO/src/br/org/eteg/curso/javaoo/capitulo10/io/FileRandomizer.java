package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileRandomizer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		FileOutputStream fos = new FileOutputStream("random.dat");
		
		byte [] bytes = new byte[100];
		
		Random rand = new Random();
		// obtem 100 bytes
		rand.nextBytes(bytes);
		
		// salva os bytes
		fos.write(bytes);
		fos.close();		
		
	}

}
