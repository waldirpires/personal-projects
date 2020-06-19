// funções utilitárias na ordenação
#include <stdio.h>
#include <sys/time.h>
#include <stdlib.h>
 
// troca de valores
void swap(int *xp, int *yp){
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}
 
// imprime o vetor
void printArray(int arr[], int size)
{
    for (int i=0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

void obterTempo( struct timeval * t){
  gettimeofday(&t,NULL);
  srand((unsigned int)t.tv_usec);
}

int obterNumeroAleatorio(double min, double max){
  return (int) (max * rand()/(RAND_MAX + min);
}