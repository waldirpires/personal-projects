/*
 ============================================================================
 Name        : Projeto-pa-ordenacao.c
 Author      : waldir
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void imprimeVetor (int *a, int tam)
{
	int i;
	for (i = 0; i < tam; i++)
	{
		printf("%5d   ", a[i]);
	}
	printf("\n\n");

}

int gerarNumeroAleatorio(int max)
{
	return rand()%max;
}

void trocarValores(int *a, int i, int j)
{
	int aux;
	aux = a[i]; a[i] = a[j]; a[j] = aux;
}

void selectionSort(int * a, int tam)
{
	int i, j, menor;

	for (i = 0; i < tam; i++)
	{
		menor = i;
		for (j = i+1; j < tam; j++)
		{
			if (a[j] < a[menor])
			{
				menor = j;
			}
		}
		if (menor != i)
		{
			trocarValores(a, menor, i);
		}
	}

}

int main(void) {

	int *a, tam, i;
	time_t seconds, start, end;
	double duration;

	tam = 1000;
	time(&seconds);
	srand((unsigned int) seconds);

	//printf("Digite o tamanho do vetor:");
	//scanf("\n%d", &tam);

	a = (int*) malloc(tam*sizeof(int));

	for (i = 0; i < tam; i++)
	{
		a[i] = gerarNumeroAleatorio(1000);
	}

	time(&start);
	imprimeVetor(a, tam);
	time(&end);
	duration = difftime(end, start);
	system("PAUSE");
	return EXIT_SUCCESS;
}
