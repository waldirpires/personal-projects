#include "tad-vetor.h"
// implementação da TAD Vetor

int obterOcupacao(tipo_vetor vetor){
	return vetor.ocupacao;
}

void inicializar(tipo_vetor * vetor){
	int i = 0;
	vetor->ocupacao = 0;
	vetor->capacidade = CAPACIDADE_INICIAL;
	
	for (i = 0; i < CAPACIDADE_INICIAL; i++){
		vetor->v[i] = VAZIO;
	}
}

int estahCheio(tipo_vetor vetor){
	// se for zero, estah cheio
	return vetor.capacidade - vetor.ocupacao;
}

int inserir(tipo_vetor * vetor, int k){
	// se o vetor estiver cheio: 
	if (estahCheio(*vetor) == 0){
	// - exibir uma mensagem de erro
	  printf("ERRO: vetor jah estah cheio!\n");
	  return -1;
	}
	// senão, inserir a chave e atualizar a ocupacao
	vetor->v[vetor->ocupacao] = k;
	vetor->ocupacao++;	
}

void imprime(tipo_vetor vetor){
	int i = 0;
	printf("Imprimindo vetor: \n");
	printf("Ocupacao: %d\n", vetor.ocupacao);
	printf("Capacidade: %d\n\n", vetor.capacidade);
	for (i = 0; i < vetor.ocupacao; i++){
		printf("%d  ", vetor.v[i]);
	}
	printf("\n\n");

}
