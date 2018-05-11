package br.edu.pitagoras.pa.arq;

import java.io.BufferedReader;
import java.io.FileReader;

public class LerArquivoTexto {

	public static void main(String[] args) {
		String fileName = "teste.txt";
		
		try {
			// criando um leitor de arquivo texto
			BufferedReader br = new BufferedReader(
					new FileReader(fileName));
			String line = "";
			// enquanto houver dados para ler
			while ((line = br.readLine()) != null)
			{ // escrever a frase na tela
				System.out.println(line);
			}
			// fecha o arquivo
			br.close();
			System.out.println("Arquivo lido com sucesso.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
