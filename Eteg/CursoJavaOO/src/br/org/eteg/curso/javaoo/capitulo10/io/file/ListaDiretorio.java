package br.org.eteg.curso.javaoo.capitulo10.io.file;

import java.io.File;

public class ListaDiretorio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length > 0)
		{
			File directory = new File(args[0]);
			listElements(directory);
		}

	}

	private static void listElements(File directory) {
		File [] lista = directory.listFiles();
		for (File f:lista)
		{
			System.out.println(f.getAbsoluteFile());
			if (f.isDirectory())
			{
				listElements(f);
			}
		}
	}

}
