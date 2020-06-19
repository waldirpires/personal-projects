package br.eteg.curso.java.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class RedeUtil {


	/**
	 * retorna um String com o conteúdo do arquivo de rede
	 * @param url a URL a ser acessada
	 * @return o String contendo o conteúdo do arquivo
	 */
	public static String obterArquivoDeRede(String url)
	{
		URL yahoo = null;
		StringBuffer buffer = new StringBuffer();
		
		try {
			yahoo = new URL(url);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yahoo.openStream()));
			
			String inputLine;
			
			while ((inputLine = in.readLine()) != null)
				buffer.append(inputLine);
			
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	/**
	 * obtem um arquivo na rede e o salva em um arquivo local
	 * @param urlFile a URL do arquivo na rede
	 * @param toFileName o arquivo a ser salvo.
	 */
	public static void obterArquivoDeRede(String urlFile, String toFileName) {
		try {
			// Set-up the Connection
			HttpURLConnection con = (HttpURLConnection) ((new URL(urlFile)
					.openConnection()));
			con.connect();

			// Create the outfile
			FileOutputStream fw = new FileOutputStream(toFileName);
			BufferedOutputStream bw = new BufferedOutputStream(fw);

			BufferedInputStream in = new BufferedInputStream(con
					.getInputStream());
			System.out.println(con.getContentEncoding());
			boolean eof = false;
			while (!eof) {
				int nextChunk = in.read();
				if (nextChunk == -1)
					eof = true;
				else {
					bw.write(nextChunk);
				}
			}

			in.close();
			bw.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void escreverDadosParaSoquete(String host, int port, 
			String data)
	{
		ServerSocket serverSocket = null;
		try {
		    serverSocket = new ServerSocket(port);
		} catch (IOException e) {
		    System.out.println("Could not listen on port: " + port);
		    System.exit(-1);
		}
		Socket clientSocket = null;
		try {
		    clientSocket = serverSocket.accept();
		    PrintWriter out = new PrintWriter(
		    		clientSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		    		new InputStreamReader(
		    				clientSocket.getInputStream()));
		    
		    // initiate conversation with client
		    out.println(data);
		    out.close();
		    in.close();
		    clientSocket.close();
		    serverSocket.close();	
		} catch (IOException e) {
		    System.out.println("Accept failed: " + port);
		    System.exit(-1);
		}	
	}
	
	public static String lerDadosDeSoquete(String host, int port)
	{
		Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String userInput = null;
        try {
            echoSocket = new Socket(host, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(
            		new InputStreamReader(System.in));
            
            while ((userInput = stdIn.readLine()) != null) {
            	out.println(userInput);
            	System.out.println("echo: " + in.readLine());
            }
            
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();		
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: "+ host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + host);
            System.exit(1);
        }
        return userInput;
	}
	
	/**
	 * retorna um objeto URL da url sendo requisitada
	 * @param url a string contento a URL
	 * @return o objeto URL
	 */
	public static URL getObjetoURL(String url)
	{
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void getURLInfo(String url)
	{
		URL aURL = null;
		try {
			aURL = new URL(url);
			System.out.println("protocol = " + aURL.getProtocol());
			System.out.println("authority = " + aURL.getAuthority());
			System.out.println("host = " + aURL.getHost());
			System.out.println("port = " + aURL.getPort());
			System.out.println("path = " + aURL.getPath());
			System.out.println("query = " + aURL.getQuery());
			System.out.println("filename = " + aURL.getFile());
			System.out.println("ref = " + aURL.getRef());		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
}
