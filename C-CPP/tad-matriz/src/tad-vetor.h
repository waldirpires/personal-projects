#define MAX 100;

typedef int TipoChave;
struct TipoVetor{
  int tamanho;
  TipoChave valores[100];
  int capacidade;
};
typedef struct TipoVetor Vetor;

void InicializaVetor(Vetor * v);
void InicializaVetor2(Vetor * v, int capacidade);
int TamanhoVetor(Vetor v);
void InsereVetor(TipoChave chave, Vetor * v);
int PosicaoDeVetor(TipoChave chave, Vetor v);
int ElementoEmVetor(int pos, Vetor v);
void ImprimeVetor(Vetor v);
int CheioVetor(Vetor v);
//void RetiraVetor(TipoChave chave, Vetor * v);

void InicializaVetor2(Vetor * v, int capacidade){
  v->tamanho = 0;
  v->capacidade = capacidade;
  printf("Vetor: %d/%d\n", v->tamanho, v->capacidade);
}

void InicializaVetor(Vetor * v){
  int max = MAX;
  InicializaVetor2(v, max);
}

int TamanhoVetor(Vetor v){
  return v.tamanho;
}

int PosicaoDeVetor(TipoChave chave, Vetor v)
{
  int i = 0;
  int tam = v.tamanho;
  for (i = 0; i < tam; i++){
      if (v.valores[i] == chave){
        return i;
      }
  }
  return -1;
}

int ElementoEmVetor(int pos, Vetor v){
  if (pos >= v.tamanho){
    printf("ERRO: posição inválida\n");
    return -1;
  }
  return v.valores[pos];
}

void InsereVetor(TipoChave chave, Vetor * v){
  if (v->capacidade <= v->tamanho){
    printf("ERRO: Vetor já cheio\n");
    return;
  }
  int index = v->tamanho;
  v->valores[index] = chave;
  v->tamanho++;
}

void ImprimeVetor(Vetor v){
  printf("Vetor: %d/%d\n", v.tamanho, v.capacidade);
  int i = 0;
  for (i = 0; i < v.tamanho; i++){
    printf("%d   ", v.valores[i]);
  }
  printf("\n");
}

int CheioVetor(Vetor v){
  return v.tamanho - v.capacidade;
}
