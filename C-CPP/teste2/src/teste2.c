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

void exibirPorcentagem(int i, int tam)
{
	if (i % (tam/100) == 0)
		printf("%d %\n", i);
}

long bubbleSort(int *a, int tam)
{
	int i, j;
	unsigned long trocas = 0;

	  for(i=0; i<tam; i++){
	     for(j=0; j<tam-1; j++){
	         if(a[j]>a[j+1]) {
	        	 trocarValores(a, j, j+1);
	        	 trocas++;
	         }
	      }
	     exibirPorcentagem(i, tam);
	   }
	  return trocas;
}

long insertionSort(int *a, int tam)
{
	int i, iHole, item;
	unsigned long trocas = 0;

	for (i = 1 ; i < tam; i++)
	   {
	     // A[ i ] is added in the sorted sequence A[0, .. i-1]
	     // save A[i] to make a hole at index iHole
	     item = a[i];
	     iHole = i;
	     // keep moving the hole to next smaller index until A[iHole - 1] is <= item
	     while (iHole > 0 && a[iHole - 1] > item)
	       {
	         // move hole to next smaller index
	         a[iHole] = a[iHole - 1];
	         iHole = iHole - 1;
	         trocas ++;
	       }
	     // put item in the hole
	     a[iHole] = item;
	     exibirPorcentagem(i, tam);
	   }
	return trocas;
}

long selectionSort(int * a, int tam)
{
	int i, j, menor; // declaração de variáveis
	unsigned long trocas = 0;

	// percorrer todas as posições do vetor, um a um
	for (i = 0; i < tam; i++)
	{
		menor = i; // assumir que o primeiro no intervalo é menor
		// percorrer o vetor para buscar alguem menor, caso exista
		for (j = i+1; j < tam; j++)
		{   // se for menor que o menor encontrado
			if (a[j] < a[menor])
			{   // anote-o
				menor = j;
			}
		}// se alguem menor que o menor for encontrado
		if (menor != i)
		{   // trocar as posições dos dois
			trocarValores(a, menor, i);
			trocas++;
		}
		exibirPorcentagem(i, tam);
	}
	return trocas;
}

void copy(int *b, int *a, int tam)
{
	int i = 0;
	for (i = 0; i < tam; i++)
	{
		a[i] = b[i];
	}

}

int main(void) {

	int *a, *b, tam, i, num;
	time_t seconds, start, end;
	double duration;
	unsigned long trocas = 0;


	tam = 100000;
	time(&seconds);
	srand((unsigned int) seconds);

	//printf("Digite o tamanho do vetor:");
	//scanf("\n%d", &tam);

	a = (int*) malloc(tam*sizeof(int));
	b = (int*) malloc(tam*sizeof(int));

	for (i = 0; i < tam; i++)
	{
		num = gerarNumeroAleatorio(1000);
		a[i] = num;
		b[i] = num;
	}

	time(&start);
	trocas = selectionSort(a, tam);
	time(&end);
	duration = difftime(end, start);
	printf("\nDuration: %f seconds \tTrocas: %u\n", duration, trocas);
	system("PAUSE");
	copy(b, a, tam);
	time(&start);
	trocas = insertionSort(a, tam);
	time(&end);
	duration = difftime(end, start);
	printf("\nDuration: %f seconds \tTrocas: %u\n", duration, trocas);
	system("PAUSE");
	copy(b, a, tam);
	time(&start);
	trocas = bubbleSort(a, tam);
	time(&end);
	duration = difftime(end, start);
	printf("\nDuration: %f seconds \tTrocas: %u\n", duration, trocas);
	system("PAUSE");
	return EXIT_SUCCESS;
}
