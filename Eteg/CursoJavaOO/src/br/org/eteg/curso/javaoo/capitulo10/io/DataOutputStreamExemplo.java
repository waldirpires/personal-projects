package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DataOutputStreamExemplo {

	private static final String arquivo = "c:\\waldir\\random.dat";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			FileOutputStream fos = 
				new FileOutputStream(arquivo);
			
			DataOutputStream dos = 
				new DataOutputStream(fos);
			
			byte[] bytes = new byte[100];
			Random rand = new Random();
			// obtem 100 bytes
			rand.nextBytes(bytes);
			// salva os bytes obtidos
			mostrarVetor(bytes);
			dos.write(bytes);
			// salva um valor double
			double d = 3.1415;
			dos.writeDouble(d);
			dos.close();
			
			// ler o arquivo
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	
	public static void mostrarVetor(byte [] bytes)
	{
		for (byte b:bytes)
		{
			System.out.print(b);
		}
		System.out.println();
	}
	
	public static void lerArquivo() throws IOException
	{
		FileInputStream fis = new FileInputStream(arquivo);
		DataInputStream dis = new DataInputStream(fis);
		byte [] bytes = new byte[100];
		dis.read(bytes);
		mostrarVetor(bytes);
		double d2 = dis.readDouble();
		System.out.println(d2);
		dis.close();
		fis.close();
		
	}
}
