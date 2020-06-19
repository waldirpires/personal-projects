#include <math.h>
#include "utils.h"
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   classificacao.h
 * Author: wrpires
 *
 * Created on 18 de Maio de 2017, 13:05
 */

#ifndef CLASSIFICACAO_H
#define CLASSIFICACAO_H

#ifdef __cplusplus
extern "C" {
#endif

/* ordenação por inserção*/
void insertionSort(int arr[], int n){
   int i, key, j;
   for (i = 1; i < n; i++){
       key = arr[i];
       j = i-1; 
       // Mover os elementos do vetor que são maiores que a chave após a mesma
       while (j >= 0 && arr[j] > key){
           arr[j+1] = arr[j];
           j = j-1;
       }
       arr[j+1] = key;
   }
}
    
// A function to implement bubble sort
void bubbleSort(int arr[], int n)
{
   int i, j;
   for (i = 0; i < n-1; i++)      
 
       // Last i elements are already in place   
       for (j = 0; j < n-i-1; j++) 
           if (arr[j] > arr[j+1])
              swap(&arr[j], &arr[j+1]);
}

#ifdef __cplusplus
}
#endif

#endif /* CLASSIFICACAO_H */

