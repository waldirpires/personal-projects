package br.com.eteg.curso.poo.compras;

public class FormaDePagamentoNaoEncontradaException extends Exception {

	private String tipo;

	public FormaDePagamentoNaoEncontradaException(String tipo) {
		super();
		this.tipo = tipo;
	}
	
	
}
