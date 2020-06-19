package br.eteg.curso.java.util.teste;


public enum OperacaoContaBancaria{

	EXTRATOS_SALDOS("Extratos/Saldos"),
	DEPOSITAR("Depositar"),
	SACAR("Sacar"),
	PAGAMENTOS_DEBITOS("Pagamentos/D�bitos"),
	TRANSFERENCIA_DOC("Transfer�ncia/DOC"),
	EMPRESTIMOS("Empr�stimos"),
	INVESTIMENTOS("Investimentos"),
	SAIR("Sair");
	
	private String descricao;
	
	OperacaoContaBancaria(String s)
	{
		descricao = s;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}