package br.pitagoras.aed2.pesquisa;

import br.org.pitagoras.algoritmos.util.AlgoritmosUtil;

public class PesquisaPrincipal {

	public static void main(String[] args) {
		
		int tamanho = 200000000;
		int limite = 10000;
		int [] v = 
			//{2,3,7,1,2,1,6,8,9,9,2,8,1,5,2,7,10,5,8,2}; 
				AlgoritmosUtil.gerarVetor(tamanho, limite);
		PesquisaSequencial.numOperacoes = 0;
		int chave = 21;
		long tempo = System.currentTimeMillis();
		int resultado = PesquisaSequencial.pesquisaSequencialIterativo(chave, v);
		tempo = System.currentTimeMillis() - tempo;
		System.out.println("Resultado - PESQUISA SEQUENCIAL INTERATIVA:");
		System.out.println("Chave de busca: " + chave);
		System.out.println("Pos: " + resultado + " \t " + 
				((double)resultado/tamanho*100));
		System.out.println("Tempo: " + tempo + " ms");
		System.out.println("Número de operações: " + 
				PesquisaSequencial.numOperacoes);
		
		PesquisaSequencial.numOperacoes = 0;
		tempo = System.currentTimeMillis();
		resultado = 
				PesquisaSequencial.pesquisaSequencialRecursivo2(chave, v, 
						0, v.length-1);
		tempo = System.currentTimeMillis() - tempo;
		System.out.println("Resultado - PESQUISA SEQUENCIAL RECURSIVA:");
		System.out.println("Chave de busca: " + chave);
		System.out.println("Pos: " + resultado + " \t " + 
				((double)resultado/tamanho*100));
		System.out.println("Tempo: " + tempo + " ms");
		System.out.println("Número de operações: " + 
				PesquisaSequencial.numOperacoes);
		

	}

}
