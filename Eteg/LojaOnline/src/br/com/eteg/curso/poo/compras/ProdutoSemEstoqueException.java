package br.com.eteg.curso.poo.compras;

public class ProdutoSemEstoqueException extends Exception {

	private Produto p;
	
	public ProdutoSemEstoqueException(Produto p)
	{
		this.p = p;
	}
	
	public Produto getProduto() {
		return p;
	}
}
