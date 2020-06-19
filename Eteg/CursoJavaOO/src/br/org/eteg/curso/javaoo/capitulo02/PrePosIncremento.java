package br.org.eteg.curso.javaoo.capitulo02;

import java.util.Iterator;


public class PrePosIncremento {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String [] vetor = {"Waldir", "Ricardo", "Jorge", "Joao"};
		
		for (int i = 0; i < vetor.length; i++)
		{
			System.out.println(vetor[i]);
		}
		
		for (String s: vetor)
		{
			System.out.println(s);
		}
		
		
	}

}
