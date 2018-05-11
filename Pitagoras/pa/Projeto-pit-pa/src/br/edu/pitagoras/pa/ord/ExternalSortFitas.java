package br.edu.pitagoras.pa.ord;

import java.util.Arrays;

public class ExternalSortFitas {

	public static void main(String[] args) {
		String s = "INTERLACACAOBALANCEADA";
		
		char [][] fitas = new char[6][30];
		
		char [] mem = new char[3];
		
		int pos = 0;
		int idFita = 0;
		while (pos < s.length())
		{
			mem[0] = s.charAt(pos);
			mem[1] = s.charAt(pos+1);
			mem[2] = s.charAt(pos+2);
			pos = pos + 3;
			Arrays.sort(mem);
			
		}
	}
}
