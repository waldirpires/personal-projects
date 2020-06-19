package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class LeituraBuferizadaExemplo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BufferedInputStream bis = null;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(args[0]));

			File f = new File(args[0]);
			byte [] temp = new byte[(int)f.length()];
			bis.read(temp, 0, temp.length);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
