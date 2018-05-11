package br.pitagoras.aed2.pesquisa;

import br.org.pitagoras.algoritmos.util.AlgoritmosUtil;

public class PesquisaSequencial {

	static int numOperacoes;

	/**
	 * realiza a pesquisa sequencial
	 * @param chave a chave de busca
	 * @param v o vetor
	 * @return o indice do elemento caso ele seja encontrado, 
	 * -1 caso contrário
	 */
	static int pesquisaSequencialIterativo(int chave, int [] v){
		int pos = -1; numOperacoes++;
		// para cada elemento no vetor
		numOperacoes++;	// estado inicial
		for (int i = 0; i < v.length; i++){
			numOperacoes++; // teste de continuidade
			numOperacoes++; // incrementador
		//   Se o elemento for igual a chave de busca
			numOperacoes++;
			if (v[i] == chave){ 
		//     Guardar a posição do elemento encontrado
				pos = i; numOperacoes++;
		//     Encerrar o laço
				break;
			}
		}
		// retornar a posicao
		return pos;
	}
	
	/**
	 * metodo recursivo que efetua a busca pela chave no vetor
	 * @param chave a chave de busca
	 * @param vetor o vetor
	 * @return
	 */
	public static int pesquisaSequencialRecursivo(int chave, 
			int [] v, int pos){

		// caso base
		// Se o vetor possuir tamanho zero
		numOperacoes++;
		if (v.length == 0){
			return -1;//   retornar vazio ou -1
		}
		numOperacoes++;
		// Se a chave de busca for igual ao elemento na 1a posicao
		if (chave == v[0]){
			return pos; //   retornar a posicao
		}
		numOperacoes = numOperacoes + v.length-1; //copia do vetor
		numOperacoes++; // atualizacao da posicao
		// passo recursivo
		// buscar pela chave passando o vetor com 1 elemento a menos
		return pesquisaSequencialRecursivo(chave, 
				AlgoritmosUtil.criarCopiaVetor(v, 1, v.length), 
				pos + 1);
		
	}
	
	public static int pesquisaSequencialRecursivo2(int chave, int []v, 
			int inicio, int fim){
		// caso base
		numOperacoes++;
		if (inicio > fim){
			return -1;
		}
		numOperacoes++;
		if (chave == v[inicio]){
			return inicio;
		}
		// passo recursivo
		numOperacoes++;
		return pesquisaSequencialRecursivo2(chave, v, 
				inicio + 1, fim);
	}

}
