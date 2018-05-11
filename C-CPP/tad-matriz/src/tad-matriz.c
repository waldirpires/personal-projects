/*
 ============================================================================
 Name        : tad-matriz.c
 Author      : waldir
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "tad-matriz.h"

int main(void) {

	puts("Testando a TAD Matriz");

	Matriz m;

	InicializaMatriz(&m);

    ImprimeMatriz(m);

    InsereMatriz(2, &m);
    ImprimeMatriz(m);

	return EXIT_SUCCESS;
}
