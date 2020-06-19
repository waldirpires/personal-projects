#include <stdio.h>
#include <stdlib.h>
#include "tad-vetor.h"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	tipo_vetor vetor;
	int i = 0;
	inicializar(&vetor);
	
	printf("Imprimindo o vetor inicializado: \n");
	imprime(vetor);
	
	printf("inserir: \n");
	int v[10] = {6,5,4,3,2,7,8,9,11,12};
	
	for (i = 0; i < 10; i++){
		inserir(&vetor, v[i]);
		printf("Imprimindo o vetor depois da insercao: \n");
		imprime(vetor);		
	}
	
	printf("Imprimindo o vetor depois da insercao: \n");
	imprime(vetor);
	
	return 0;
}
