// cabeçalho da TAD vetor

#define MAX 100
#define CAPACIDADE_INICIAL 8
#define VAZIO -1

typedef int tipo_dado;

struct vetor{
	int ocupacao;
	int capacidade;
	tipo_dado v[CAPACIDADE_INICIAL];
};

typedef struct vetor tipo_vetor;

// retorna a ocupacao do vetor
int obterOcupacao(tipo_vetor vetor);

// retorna a ocupacao do vetor
int obterCapacidade(tipo_vetor vetor);

// insere uma dada chave no 1a posicao disponivel do vetor
int inserir(tipo_vetor * vetor, int k);

// inicializa o vetor
void inicializar(tipo_vetor * vetor);

// informa a posicao de uma chave no vetor
int posicaoDe(tipo_vetor vetor, int k);

// informa o elemento localizado na posicao informada
int elementoEm(tipo_vetor vetor, int pos);

// informa o estado do vetor
void imprime(tipo_vetor vetor);

// informa se o vetor está cheio ou não
int estahCheio(tipo_vetor vetor);


