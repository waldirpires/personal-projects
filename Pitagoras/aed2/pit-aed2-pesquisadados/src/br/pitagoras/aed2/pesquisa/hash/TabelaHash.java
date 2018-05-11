package br.pitagoras.aed2.pesquisa.hash;

public class TabelaHash {

	private static final int TAMANHO_MAX = 1000;
	
	private int tamanho; // tamanho da tabela hash
	private EntradaHash[] tabela; // dados da tabela hash
	private int ocupacao; // numero de entradas na tabela
	
	// construtor da tabela hash
	public TabelaHash(int tamanho) {
		this.tamanho = tamanho; // tamanho da tabela
		// alocando o espaço para a tabela hash
		tabela = new EntradaHash[this.tamanho];		
	}
	
	// obter um elemento a partir da chave
	public int get(int chave){
		// calcular a posicao a partir da chave
		// funcao hash
		int hash = chave % tamanho;
		// acessar a posicao da tabela hash
		if (tabela[hash] == null){
			return -1;
		} else {
		// retornar o valor, caso ele exista
			return tabela[hash].getValor();
		}
	}
	
	// colocar um elemento na tabela hash
	public void put(int chave, int valor){
		// calcular a funcao hash a partir da chave
		// funcao hash
		int hash = chave % tamanho;
		// acessar a posicao calculada		
		// se a posicao estiver ocupada
		if (tabela[hash] != null){
		//   colisao!
			System.out.println("ERRO: colisao na posicao " + hash);
		} else {// senao
		//   adicionar uma nova entrada na tabela com a chave
			tabela[hash] = new EntradaHash(chave, valor);
			this.ocupacao++;
		}
	}
	
	public void retirar(int chave){
		// TODO implementar
	}
	
	public int getTamanho() {
		return tamanho;
	}
	
	public int getOcupacao() {
		return ocupacao;
	}
}
