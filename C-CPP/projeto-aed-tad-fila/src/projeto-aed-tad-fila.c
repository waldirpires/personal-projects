/*
 ============================================================================
 Name        : projeto-aed-tad-fila.c
 Author      : waldir
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "tad-fila.h"

int main(void) {
  TipoFila fila; TipoItem item; int i; int chaves[] = {10, 11, 12, 45, 1};

  FFVazia(&fila); // Inicializa a fila
  printf("Fila vazia?: %d\n", VaziaFila(fila)); // fila vazia?
  item = FrenteFila(&fila); // elemento na frente da fila
  printf("Elemento na frente da fila: %d\n", item.Chave);

  for (i = 0; i < 5; i++){ /*Enfileira cada chave */
    item.Chave = chaves[i]; Enfileira(item, &fila);
    printf("Enfileirou: %3d \n", item.Chave);
    ImprimeFila(fila);
  }
  int tam = TamanhoFila(fila); // tamanho da fila
  printf("Tamanho da fila: %d \n", tam);
  printf("Fila vazia?: %d\n", VaziaFila(fila)); // fila vazia?
  item = FrenteFila(&fila); // elemento na frente da fila
  printf("Elemento na frente da fila: %d\n", item.Chave);
  for (i = 0; i < tam; i++) { /*Desenfileira cada chave */
    Desenfileira(&fila, &item);
    printf("Desenfileirou: %3d \n", item.Chave);
    ImprimeFila(fila);
  }
  printf("Tamanho da fila: %d\n", TamanhoFila(fila)); // tamanho da pilha
  printf("Fila vazia?: %d\n", VaziaFila(fila)); // fila vazia?
  system("PAUSE");
  return EXIT_SUCCESS;
}
