#include "tad-vetor.h"

#define MAX 100;

struct TipoMatriz{
  int tamanho;
  int linhas;
  Vetor a[100];
  int capacidade;
};
typedef struct TipoMatriz Matriz;

void InicializaMatriz(Matriz * m);
void InicializaMatriz2(Matriz * m, int capacidade);
int TamanhoMatriz(Matriz m);
void InsereMatriz(TipoChave chave, Matriz * m);
void PosicaoDeMatriz(TipoChave chave, Matriz m, int *linha, int *coluna);
int ElementoEmMatriz(int linha, int col, Matriz m);
void ImprimeMatriz(Matriz m);
int CheioMatriz(Matriz m);
int TamanhoNumLinhas(Matriz m);

void zerarValoresMatriz(Matriz * m){
  int tam = MAX;
  int i, j;
  for (i = 0; i < tam; i++){
      Vetor v = m->a[i];
      int tamVetor = v.tamanho;
      for (j = 0; j < tamVetor; j++){
          v.valores[j] = 0;
      }
  }
}

void InicializaMatriz2(Matriz * m, int capacidade){
  m->tamanho = 0;
  m->linhas = 0;
  m->capacidade = capacidade;
  //zerarValoresMatriz(m);
  printf("Matriz: %d/%d - %d\n", m->tamanho, m->capacidade, m->linhas);
}

void InicializaMatriz(Matriz * m){
  int max = MAX;
  InicializaMatriz2(m, max);
}

int TamanhoMatriz(Matriz m){
  return m.tamanho;
}

void ImprimeMatriz(Matriz m){
  printf("Matriz: %d/%d\n", m.tamanho, m.capacidade);
  int i = 0;
  for (i = 0; i < m.linhas; i++){
    Vetor v = m.a[i];
    ImprimeVetor(v);
  }
  printf("\n");
}

void InsereMatriz(TipoChave chave, Matriz * m){

  Vetor v = m->a[m->linhas];
  if (CheioVetor(v)){
    m->linhas++;
  }
  v = m->a[m->linhas];

  int index = v.tamanho;
  v.valores[index] = chave;
  v.tamanho++;
}
