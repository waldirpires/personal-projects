package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileWriterExemplo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String s = null;
		int i = 0;
		
		FileWriter fout = new FileWriter("text.txt");
		s = "abcde";
		fout.write(s + "\n");
		fout.write("xyz\n");
		fout.close();
		fout.write("abx");
		
		FileReader fin = new FileReader("text.txt");
		char[] c = new char[256];
		fin.read(c);
		fin.close();
		
		RandomAccessFile f = new RandomAccessFile("text.txt", "rw");
		f.writeChars("w");
		f.seek(0);
		
		i = 0;
		while(true)
		{
			s = f.readLine();
			if (s == null)
				break;
			i++;
			System.out.println("Linha #"+ i + " : " + s);
		}
		f.close();
	}
}
