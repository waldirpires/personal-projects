package br.org.eteg.curso.javaoo.capitulo10.io;

import java.io.RandomAccessFile;

public class RandomAccessFileExemplo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			RandomAccessFile inout = new RandomAccessFile ("c:\\waldir\\story.dat", "rw"); 
			System.out.println ("Pointer is at : " + inout.getFilePointer ()); 
			inout.writeUTF ("Spike is a "); 
			long pointer = inout.getFilePointer (); 
			System.out.println ("Pointer is now at : " + pointer); 
			inout.writeUTF ("good dog."); 
			inout.seek (pointer); 
			inout.writeUTF ("very good dog."); // Overwrites old data 
			inout.seek (0); 
			System.out.println (inout.readUTF () + inout.readUTF ()); 
			inout.close ();			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
