/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   utils.h
 * Author: wrpires
 *
 * Created on 18 de Maio de 2017, 13:04
 */

#ifndef UTILS_H
#define UTILS_H

#ifdef __cplusplus
extern "C" {
#endif

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


#ifdef __cplusplus
}
#endif

#endif /* UTILS_H */

