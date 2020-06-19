package br.org.eteg.curso.javaoo.capitulo10.io.file;

import java.io.File;
import java.io.IOException;

public class CriarArquivo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String filePath = "c:\\" + File.separator + "savedsts.dat";
		
		File savedStatus = new File(filePath);
		savedStatus.createNewFile();
		System.out.println(savedStatus.exists());
		System.out.println(savedStatus.getAbsolutePath());
		
	}

}
