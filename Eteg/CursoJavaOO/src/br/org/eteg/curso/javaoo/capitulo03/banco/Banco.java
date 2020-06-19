package br.org.eteg.curso.javaoo.capitulo03.banco;


public class Banco {

	String nome;
	
	private static Banco banco;
	
	// PADRAO singleton
	public static Banco getInstance(String nome)
	{
		if (banco == null)
		{
			banco = new Banco(nome);
		}
		return banco;
	}
	
	private Banco(String nome)
	{
		this.nome = nome;
	}
	
	public static void main(String[] args) {
		Banco banco = new Banco("Itau");
		
	}
	
}
