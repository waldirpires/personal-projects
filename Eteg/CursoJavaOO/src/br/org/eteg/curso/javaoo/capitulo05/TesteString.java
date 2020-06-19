package br.org.eteg.curso.javaoo.capitulo05;

public class TesteString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = "the book is on the table";
		
		// pegando um substring
		String j = s.substring(4, 8);
		System.out.println(j);
		
		int pos = s.indexOf("table");
		System.out.println(pos);
		
		pos = s.indexOf("books");
		System.out.println(pos);
		
		String k = "dook";
		String l = "data";

		System.out.println(k.compareTo(l));
		
		
	}

}
