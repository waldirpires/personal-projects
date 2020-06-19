package br.org.eteg.curso.javaoo.capitulo04.estoque;

public class EstoqueInvalidoException extends Exception {

	public EstoqueInvalidoException(int quantidadeEstoque)
	{
		super("Erro ao operar no estoque - quantidade: " + quantidadeEstoque);
	}
	
}
