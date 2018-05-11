package br.pitagoras.oda.orgarq;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ProcessadorDeArquivoIndexado extends OrgArquivoAbstrato implements OrgArquivosInterface {

	
	public ProcessadorDeArquivoIndexado(String nomeArquivoDeIndices, String nomeArquivoDeDados) {
		this.setNomeArquivoDados(nomeArquivoDeDados);
		this.setNomeArquivoIndices(nomeArquivoDeIndices);
		System.out.println("Criando um processador de arquivo indexado . . .");
		System.out.println("Arquivo de índices: " + nomeArquivoDeIndices);
		System.out.println("Arquivo de dados: " + nomeArquivoDeDados);
	}

	String buscarNoIndice(String chave) throws FileNotFoundException, IOException{
		return null;
	}
	
	@Override
	public RegistroDeArquivo buscar(String chave){
		RegistroDeArquivo reg = null;
		
		return reg;
	}
	
	@Override
	public void inserir(RegistroDeArquivo registro){
		
	}
	
	@Override
	public void atualizar(String chave, String valorAtual, String valorNovo){
	
	}
	
	@Override
	public List<RegistroDeArquivo> pesquisar(String valor){
		
		return null;
	}

	@Override
	public void excluir(String chave){
		
	}
}
