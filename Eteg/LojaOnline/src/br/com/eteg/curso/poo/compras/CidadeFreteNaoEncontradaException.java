package br.com.eteg.curso.poo.compras;

public class CidadeFreteNaoEncontradaException extends Exception {

	private Comprador comprador;

	public CidadeFreteNaoEncontradaException(Comprador comprador) {
		super();
		this.comprador = comprador;
	}

	public Comprador getComprador() {
		return comprador;
	}

}
