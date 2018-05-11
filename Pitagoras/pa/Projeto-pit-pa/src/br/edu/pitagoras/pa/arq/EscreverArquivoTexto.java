package br.edu.pitagoras.pa.arq;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscreverArquivoTexto {
	
	public static void main(String[] args) {
		String fileName = "teste.txt";
		// acessando o arquivo para escrita
		File f = new File(fileName);
		FileWriter fw; // declarando um escritor de arquivo
		try {
			// criando um escritor de arquivo
			fw = new FileWriter(f);
			// escrevendo no arquivo
			fw.write("The book is on the table\n");
			fw.write("The sky is blue\n");
			// fechando o arquivo
			fw.close();
			// exibindo o statos
			System.out.println("Arquivo escrito com sucesso: " + 
					f.getAbsolutePath() + " - " + f.length() + " bytes");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
