package br.pitagoras.oda.orgarq;

import java.util.List;

public interface OrgArquivosInterface {

	public abstract RegistroDeArquivo buscar(String chave);

	public abstract void inserir(RegistroDeArquivo registro);

	public abstract void atualizar(String chave, String campo, String valor);

	public abstract List<RegistroDeArquivo> pesquisar(String valor);

	public abstract void excluir(String chave);

}