/*
 ============================================================================
 Name        : Projeto-aed-tad-matriz.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "tad-matriz.h"

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
		Matriz m;
		time_t seconds;
		TipoChave c;
		int r, i;
		Posicao pos;

		time(&seconds);
		srand((unsigned int) seconds);

	    // matriz 4x4
		InicializaMatriz2(&m, 10);
		for (i = 0; i <101; i++)
		{
		 int num = rand()%100;
		  printf("%d  ", num);
		  InsereMatriz(num, &m);
		  //ImprimeMatriz(m);
		}
		ImprimeMatriz(m);
		c = 4;
		pos = PosicaoDeMatriz(c, m);
		printf("Posição de %d: %dx%d\n", c, pos.linha, pos.coluna);
		c = 5;
		pos = PosicaoDeMatriz(c, m);
		printf("Posição de %d: %dx%d\n", c, pos.linha, pos.coluna);
		pos.linha = 0;
		pos.coluna = 1;
		r = ElementoEmMatriz(pos, m);
		printf("Elemento em Matriz %dx%d: %d\n", pos.linha, pos.coluna, r);
		pos.linha = 1;
		pos.coluna = 1;
		r = ElementoEmMatriz(pos, m);
		printf("Elemento em Matriz %dx%d: %d\n", pos.linha, pos.coluna, r);

		FreeMatriz(&m);

	return EXIT_SUCCESS;
}
// exercícios
// calcular a soma total de todos os valores da matriz
// informar o menor, maior e a média aritmética dos elementos na matriz
// Informar a quantidade de pares e ímpares na matriz
// Informar quantos números múltiplos de N existem na matriz.
// Gerar uma matriz transposta
// Criar uma submatriz da matriz.
