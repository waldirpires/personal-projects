#include <stdio.h>
// TAD Fila
// TAD Fila
#define MAX 900000

// definição do tipochave
typedef int TipoChave;

// definição do registro tipo item
typedef struct {
  TipoChave Chave;
} TipoItem;

// definição do apontador para célula
typedef struct TipoCelula *TipoApontador;

// definição para o tipo célula
typedef struct TipoCelula {
  TipoItem Item;
  TipoApontador Prox;
} TipoCelula;

// definição do tipo fila
typedef struct{
  TipoApontador Frente, Tras;
  int Tamanho;
} TipoFila;

// função que inicializa a fila
void FFVazia(TipoFila *Fila);
// função que verifica se a fila está vazia
int VaziaFila(TipoFila Fila);
// função que enfileira um item na fila (TRÁS)
void Enfileira(TipoItem x, TipoFila *Fila);
// função que desenfileira um item da fila (FRENTE)
void Desenfileira(TipoFila *Fila,  TipoItem *Item);
// função que retorna o tamanho da fila
int TamanhoFila(TipoFila Fila);
// função que informa o elemento na frente da fila
TipoItem FrenteFila(TipoFila *Fila);
// função que informa o elemento atrás na fila
TipoItem TrasFila(TipoFila *Fila);
// função que imprime o estado atual da fila
void ImprimirFila(TipoFila Fila);

// função que cria uma nova fila
void FFVazia(TipoFila *Fila)
{ // cria a célula cabeça
  Fila->Frente = (TipoApontador)
    malloc(sizeof(TipoCelula));
  // inicializa a frente e trás
  Fila->Tras = Fila->Frente;
  Fila->Frente->Prox = NULL;
  Fila->Tamanho = 0;
}

// função que verifica se a
//fila está vazia
int VaziaFila(TipoFila Fila)
{ // se a célula na frente for igual
  // a célula atrás, ela está
  // vazia
  return (Fila.Frente == Fila.Tras);
}

// função que Enfileira um item na fila
void Enfileira(TipoItem x, TipoFila *Fila)
{ Fila->Tras->Prox = (TipoApontador)
    malloc(sizeof(TipoCelula));
  Fila->Tras = Fila->Tras->Prox;
  Fila->Tras->Item = x;
  Fila->Tras->Prox = NULL;
  Fila->Tamanho++;
}

// função que desenfileira o item na
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


// função para obter Tamanho da fila
int TamanhoFila(TipoFila Fila)
{ return (Fila.Tamanho); }

// função para obter o elemento na frente da fila
TipoItem FrenteFila(TipoFila *Fila)
{  TipoItem item; item.Chave = -1;
  if (VaziaFila(*Fila) == 0){
    item = Fila->Frente->Prox->Item;
  } return (item);
}
// função para obter o elemento atrás na fila
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


