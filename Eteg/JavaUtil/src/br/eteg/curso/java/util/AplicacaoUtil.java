package br.eteg.curso.java.util;

import java.io.IOException;

public class AplicacaoUtil {

	public static String FIREFOX = 
		"C:/Arquivos de programas/Mozilla Firefox/firefox.exe";
	public static String IE = 
		"C:/Arquivos de programas/Internet Explorer/iexplore.exe";
	public static String NOTEPAD = 
		"C:/windows/notepad.exe";		
	
	public static void mostrarArquivoBrowser(String nome) 
	throws IOException
	{
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(FIREFOX + " file:///"+nome);
	}
	
	public static void mostrarArquivoBrowser(String nome, String browser) 
	throws IOException
	{
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(browser + " file:///"+nome);
	}

	public static void mostrarArquivoTexto(String nome) 
	throws IOException
	{
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(NOTEPAD +nome);
	}

	public static void mostrarArquivoTexto(String nome, String aplicacao) 
	throws IOException
	{
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(aplicacao+nome);
	}
	
	public static void main(String[] args) {
		try {
			mostrarArquivoBrowser(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
