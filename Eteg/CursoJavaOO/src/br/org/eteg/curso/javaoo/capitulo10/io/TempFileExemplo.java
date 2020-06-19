package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TempFileExemplo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		File temp = File.createTempFile("serial", "tmp");
		temp.deleteOnExit();
		
		BufferedWriter out = new BufferedWriter(new FileWriter(temp));
		out.write("string");
		out.flush();
		out.close();

	}

}
