package br.pitagoras.oda.orgarq;

import java.util.List;

public class ProcessadorDeArquivoDireto extends OrgArquivoAbstrato implements OrgArquivosInterface{

	public ProcessadorDeArquivoDireto(String nomeArquivoDeIndices, String nomeArquivoDeDados) {
		this.setNomeArquivoDados(nomeArquivoDeDados);
		this.setNomeArquivoIndices(nomeArquivoDeIndices);
		System.out.println("Criando um processador de arquivo indexado . . .");
		System.out.println("Arquivo de índices: " + nomeArquivoDeIndices);
		System.out.println("Arquivo de dados: " + nomeArquivoDeDados);
	}
	
	@Override
	public RegistroDeArquivo buscar(String chave) {
		RegistroDeArquivo reg = null;
		
		return reg;
	}

	@Override
	public void inserir(RegistroDeArquivo registro) {
	}

	@Override
	public void atualizar(String chave, String valorAtual, String novoValor) {
	}

	@Override
	public List<RegistroDeArquivo> pesquisar(String valor) {
		List<RegistroDeArquivo> registros = null;

		return registros;
	}

	@Override
	public void excluir(String chave) {
	}

}
