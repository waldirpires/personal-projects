package br.org.eteg.curso.javaoo.capitulo02;


public class Exemplo {

	public static void main(String [] args)
	{
		StringBuffer umNome = new StringBuffer("Knuth");
		int status = 0;
		System.out.println("ANTES: " + umNome + ", " + status);
		setMaster(umNome, status);
		System.out.println("DEPOIS: " + umNome + ", " + status);
	}
	
	public static void setMaster(StringBuffer nome, int status)
	{
		nome.insert(0, "Msc. ");
		status = 1;
		nome = new StringBuffer("Outra String");
	}
}
