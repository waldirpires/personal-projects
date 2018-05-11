package br.com.eteg.curso.poo.compras;

public class Produto {

	private double valor;
	private String descricao;
	
	private boolean possuiEstoque;
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isPossuiEstoque() {
		return possuiEstoque;
	}
	public void setPossuiEstoque(boolean possuiEstoque) {
		this.possuiEstoque = possuiEstoque;
	}
	
	
	
}
