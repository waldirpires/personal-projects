package br.eteg.curso.java.util;

public class ExcecaoSistema extends Exception{

	private String codigo;
	private String mensagem;
	
	public ExcecaoSistema(String codigo, String mensagem) {
		super(codigo);
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
	
	
}
