/*
 ============================================================================
 Name        : tad-vetor.c
 Author      : waldir
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "tad-vetor.h"

int main(void) {
	Vetor v;

	InicializaVetor(&v);

	ImprimeVetor(v);
	InsereVetor(2, &v);
	InsereVetor(5, &v);
	InsereVetor(1, &v);
	InsereVetor(7, &v);

	ImprimeVetor(v);
	int pos = 5;
	printf("Posicao de %d: %d\n", pos, PosicaoDeVetor(pos, v));
	pos = 6;
	printf("Posicao de %d: %d\n", pos, PosicaoDeVetor(pos, v));
	printf("Elemento em %d: %d\n", 3, ElementoEmVetor(3, v));
	printf("Elemento em %d: %d\n", 5, ElementoEmVetor(5, v));
	printf("Cheio: %d\n", CheioVetor(v));
	system("PAUSE");
	return EXIT_SUCCESS;
}
