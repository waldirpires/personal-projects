package br.eteg.curso.java.util.teste;


public enum OperacaoContaBancaria{

	EXTRATOS_SALDOS("Extratos/Saldos"),
	DEPOSITAR("Depositar"),
	SACAR("Sacar"),
	PAGAMENTOS_DEBITOS("Pagamentos/Débitos"),
	TRANSFERENCIA_DOC("Transferência/DOC"),
	EMPRESTIMOS("Empréstimos"),
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