package br.pitagoras.oda.orgarq.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class OrgArquivosBaseUtil {
	
	/**
	 * Retorna a listagem do conteúdo do arquivo
	 * @param nomeDoArquivo
	 * @return
	 * @throws IOException
	 */
	public static List<String> listar(String nomeDoArquivo) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo));
        List<String> registros = new ArrayList<String>();
        String s = br.readLine();
        while (s != null) {
            registros.add(s);
            s = br.readLine();
        }
        br.close();
        return registros;
	}
	
	public static String obter(String chave, String nomeDoArquivo) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo));
        String s = br.readLine();
        while (s != null) {
        	if (s.split(";")[0].equals(chave)){
        		br.close();
        		return s;
        	}
            s = br.readLine();
        }
        br.close();
        return null;
	}
	
	public static void inserir(String chave, String valor, String nomeDoArquivo){
		
	}
	
	public static void atualizar(String chave, String valor, String nomeDoArquivo){
		
	}
	
	public static void excluir(String chave, String nomeDoArquivo) throws IOException{
        File tempFile = new File("temp.dat");		// creating temp file for data transfer
		BufferedReader br = new BufferedReader(		// creating file reader
				new FileReader(nomeDoArquivo));		
        BufferedWriter bw = new BufferedWriter(		// creating file writer
        		new FileWriter(tempFile));
        String s = br.readLine();					// reading first line
        while (s != null) {							// while there is data to read
        	if (!s.split(";")[0].equals(chave)){	// if registry key DOES NOT match search key
        		bw.write(s);						// write to temp file
        		bw.newLine();						
        	}
            s = br.readLine();						// read next line
        }
        br.close();									// close file streams
        bw.close();
        FileUtils.copyFile(tempFile, 				// copy temp file to original file
        		new File(nomeDoArquivo));			
        tempFile.delete();        					// remove temp file
	}
	
	public static List<String> pesquisar(String valor, String nomeDoArquivo) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo));
        List<String> registros = new ArrayList<String>();
        String s = br.readLine();
        while (s != null) {
        	if (s.contains(valor)){
        		registros.add(s);
        	}
            s = br.readLine();
        }
        br.close();
        return registros;
	}

	
}
