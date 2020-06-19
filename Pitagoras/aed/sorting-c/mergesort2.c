#include<stdlib.h>
#include<stdio.h>
 
void merge(int arr[], int l, int m, int r){
    int i, j, k;  int n1 = m - l + 1;
    int n2 =  r - m; 
    int L[n1], R[n2]; // vetores temporários 
    // copiando dados nos vetores temporários
    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1+ j];
 
    // mergeando os vetores temporários no vetor final
    i = 0; // indice inicial do 1o subvetor
    j = 0; // indice inicial do 2o subvetor
    k = l; // indice inicial do vetor final
    while (i < n1 && j < n2){
        if (L[i] <= R[j]){
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    } 
    // copiar os demais elementos da esquerda
    while (i < n1){
        arr[k] = L[i];
        i++;
        k++;
    } 
    // copiar os demais elementos da direita
    while (j < n2){
        arr[k] = R[j];
        j++;
        k++;
    }
}
 
void mergeSort(int arr[], int l, int r){
    if (l < r)    {
        // epm
        int m = l+(r-l)/2; 
        // ordenar os dois 1os profiles
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
        // unir as partes ordenadas
        merge(arr, l, m, r);
    }
}
 
/* UTILITY FUNCTIONS */
/* Function to print an array */
void printArray(int A[], int size){
    int i;
    for (i=0; i < size; i++)
        printf("%d ", A[i]);
    printf("\n");
}
 
int main(){
    int arr[] = {12, 11, 13, 5, 6, 7};
    int arr_size = sizeof(arr)/sizeof(arr[0]); 
    printf("Antes:\n");
    printArray(arr, arr_size); 
    mergeSort(arr, 0, arr_size - 1); 
    printf("\nOrdenado:\n");
    printArray(arr, arr_size);
    return 0;
}