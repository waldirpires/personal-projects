#include <stdio.h>
#include <stdlib.h>

#include <stdio.h>
#include <sys/time.h>
#include <stdlib.h>
#include <time.h>
 
// troca de valores
void swap(int *xp, int *yp){
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}
 
void initTempo( struct timeval * t){
  gettimeofday(t,NULL);
  srand((unsigned int)t->tv_usec);
}

int obterNumeroAleatorio(double min, double max){
  return (int) (max * rand()/(RAND_MAX + min));
}

void imprimirVetor(int *v, int tam){
  int i;         
  for(i = 0; i < tam; i++){
    printf("%d\t", v[i]);
  }
  printf("\n");
}

void preencherVetor(int *v, int tam, int min, int max)
{
  int i, k;
  for(i = 0; i < tam; i++)
    { k =  (int) (max*rand()/(RAND_MAX + min));  
      printf("%d\n", k);
    v[i]=k;
  }
}   

#include <stdio.h>
#include <stdlib.h>
#include "utils.h"

// pesquisa sequencial
int pesquisaSequencial(int arr[], int n, int x){
    int i;
    for (i=0; i<n; i++)
        if (arr[i] == x)
         return i;
    return -1;
}

// pesquisa binaria recursiva
 int buscaBinariaRecursiva(int arr[], int l, int r, int x){
   if (r >= l) {
        int mid = l + (r - l)/2; // calculo do EPM
        if (arr[mid] == x)  return mid; // se for no meio
        // se for menor, ir para a esquerda
        if (arr[mid] > x) return buscaBinariaRecursiva(arr, l, mid-1, x);
        // senão é maior, ir para a direita
        return buscaBinariaRecursiva(arr, mid+1, r, x);
   }
   return -1;
}
// pesquisa binaria iterativa 
int buscaBinariaIterativa(int arr[], int l, int r, int x){
  while (l <= r)  {
    int m = l + (r-l)/2;
    if (arr[m] == x) // se for no meio
        return m;  
    if (arr[m] < x) // se for maior, ir para a direita
        l = m + 1; 
    else // senão, ir para a esquerda
         r = m - 1; 
  }
  return -1; // elemento não encontrado
}



int main()
{
	struct timeval t;
	gettimeofday(&t,NULL);
    srand((unsigned int)t.tv_usec);
    
    int *v, k, i;
    int tam = 100;
    v = (int*) malloc(tam*sizeof(int));    

  double min = 1.0;
  double max = 1000.0;
  clock_t tic = clock();
  for(i = 0; i < tam; i++)
    { k =  (int) (max*rand()/(RAND_MAX + min));    
    v[i]=k;
    //printf("%d\t", k);
  }
    imprimirVetor(v, tam);
    
    return 0;
}