#include <stdio.h>
// TAD Fila
// TAD Fila
#define MAX 900000

// defini��o do tipochave
typedef int TipoChave;

// defini��o do registro tipo item
typedef struct {
  TipoChave Chave;
} TipoItem;

// defini��o do apontador para c�lula
typedef struct TipoCelula *TipoApontador;

// defini��o para o tipo c�lula
typedef struct TipoCelula {
  TipoItem Item;
  TipoApontador Prox;
} TipoCelula;

// defini��o do tipo fila
typedef struct{
  TipoApontador Frente, Tras;
  int Tamanho;
} TipoFila;

// fun��o que inicializa a fila
void FFVazia(TipoFila *Fila);
// fun��o que verifica se a fila est� vazia
int VaziaFila(TipoFila Fila);
// fun��o que enfileira um item na fila (TR�S)
void Enfileira(TipoItem x, TipoFila *Fila);
// fun��o que desenfileira um item da fila (FRENTE)
void Desenfileira(TipoFila *Fila,  TipoItem *Item);
// fun��o que retorna o tamanho da fila
int TamanhoFila(TipoFila Fila);
// fun��o que informa o elemento na frente da fila
TipoItem FrenteFila(TipoFila *Fila);
// fun��o que informa o elemento atr�s na fila
TipoItem TrasFila(TipoFila *Fila);
// fun��o que imprime o estado atual da fila
void ImprimirFila(TipoFila Fila);

// fun��o que cria uma nova fila
void FFVazia(TipoFila *Fila)
{ // cria a c�lula cabe�a
  Fila->Frente = (TipoApontador)
    malloc(sizeof(TipoCelula));
  // inicializa a frente e tr�s
  Fila->Tras = Fila->Frente;
  Fila->Frente->Prox = NULL;
  Fila->Tamanho = 0;
}

// fun��o que verifica se a
//fila est� vazia
int VaziaFila(TipoFila Fila)
{ // se a c�lula na frente for igual
  // a c�lula atr�s, ela est�
  // vazia
  return (Fila.Frente == Fila.Tras);
}

// fun��o que Enfileira um item na fila
void Enfileira(TipoItem x, TipoFila *Fila)
{ Fila->Tras->Prox = (TipoApontador)
    malloc(sizeof(TipoCelula));
  Fila->Tras = Fila->Tras->Prox;
  Fila->Tras->Item = x;
  Fila->Tras->Prox = NULL;
  Fila->Tamanho++;
}

// fun��o que desenfileira o item na
// frente da fila
void Desenfileira(TipoFila *Fila,
  TipoItem *Item)
{ TipoApontador q;
  if (VaziaFila(*Fila)) {
    printf("Erro fila esta vazia\n"); return; }
  q = Fila->Frente;
  Fila->Frente = Fila->Frente->Prox;
  *Item = Fila->Frente->Item;
  Fila->Tamanho--;
  free(q);
}


// fun��o para obter Tamanho da fila
int TamanhoFila(TipoFila Fila)
{ return (Fila.Tamanho); }

// fun��o para obter o elemento na frente da fila
TipoItem FrenteFila(TipoFila *Fila)
{  TipoItem item; item.Chave = -1;
  if (VaziaFila(*Fila) == 0){
    item = Fila->Frente->Prox->Item;
  } return (item);
}
// fun��o para obter o elemento atr�s na fila
TipoItem TrasFila(TipoFila *Fila)
{  TipoItem item; item.Chave = -1;
  if (VaziaFila(*Fila) == 0){
    item = Fila->Tras->Item;
  } return (item);
}

void ImprimeFila(TipoFila Fila)
{ TipoApontador Aux;
  Aux = Fila.Frente->Prox;
  printf("FRENTE: ");
  while (Aux != NULL)
  { printf("%d | ", Aux->Item.Chave);
    Aux = Aux->Prox;
  }  printf("TRAS \n");
}


